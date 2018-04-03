package cn.ts.web.upms.shiro.session;

import cn.ts.core.redis.JedisUtil;
import cn.ts.web.upms.common.UpmsConstant;
import cn.ts.web.upms.util.ShiroSerializationUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 针对自定义的 ShiroSession 的 Redis CRUD 操作
 * <pre>
 *     最开始通过继承 AbstractSessionDAO 实现，发现 doReadSession 方法调用过于频繁，所以改为通过集成 CachingSessionDAO 来实现
 * </pre>
 *
 * @author Created by YL on 2017/2/23.
 */
public class UpmsCachingSessionDAO extends CachingSessionDAO {
    private static Logger _log = LoggerFactory.getLogger(UpmsCachingSessionDAO.class);
    // 会话key
    private final static String UPMS_SESSION_ID = "ts-upms-shiro-session-id";
    // 全局会话key
    private final static String UPMS_GLOBAL_SESSION_ID = "ts-upms-web-session-id";
    // 全局会话列表key
    private final static String UPMS_GLOBAL_SESSION_IDS = "ts-upms-web-session-ids";
    // code key
    private final static String TS_UPMS_SERVER_CODE = "ts-upms-web-code";
    // 局部会话key
    private final static String TS_UPMS_CLIENT_SESSION_ID = "ts-upms-client-session-id";
    // 单点同一个code所有局部会话key
    private final static String TS_UPMS_CLIENT_SESSION_IDS = "ts-upms-client-session-ids";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 如 DefaultSessionManager 在创建完 session 后会调用该方法；
     * 如保存到关系数据库/文件系统/NoSQL数据库；即可以实现会话的持久化；
     * 返回会话ID
     */
    @Override
    protected Serializable doCreate(Session session) {
        // 创建一个Id并设置给Session
        Serializable sid = this.generateSessionId(session);
        this.assignSessionId(session, sid);
        if (_log.isDebugEnabled()) {
            _log.debug("doCreate >>>>> sid={}", sid);
        }
        JedisUtil.set(redisTemplate, UPMS_SESSION_ID + "_" + sid, ShiroSerializationUtils.serialize
                ((UpmsSimpleSession) session), session.getTimeout() / 1000, TimeUnit.SECONDS);
        return sid;
    }

    /**
     * 根据会话ID获取会话
     *
     * @param sid
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sid) {
        if (_log.isDebugEnabled())
            _log.debug("doReadSession >>>>> sid={}", sid);
        String base64 = JedisUtil.get(redisTemplate, UPMS_SESSION_ID + "_" + sid);
        if (StringUtils.isBlank(base64)) {
            return null;
        }
        Session session = ShiroSerializationUtils.deserialize(base64);
        // 重置 Redis 中缓存过期时间
        JedisUtil.expire(redisTemplate, UPMS_SESSION_ID + "_" + sid, session.getTimeout() / 1000, TimeUnit.SECONDS);
        return session;
    }

    /**
     * 更新会话：如更新会话最后访问时间/停止会话/设置超时时间/设置移除属性等会调用
     *
     * @param session
     */
    @Override
    protected void doUpdate(Session session) {
        // 如果会话过期/停止 没必要再更新了
        if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
            return;
        }
        Serializable sid = session.getId();
        // // 重置 Redis 中缓存过期时间
        // this.doReadSession(sid);

        // if (session instanceof UpmsSession) {
        // } else if (session instanceof Serializable) {
        // }
        // 更新 session 的最后一次访问时间
        UpmsSimpleSession upmsSession = (UpmsSimpleSession) session;
        UpmsSimpleSession cacheUpmsSession = (UpmsSimpleSession) doReadSession(sid);
        if (null != cacheUpmsSession) {
            upmsSession.setStatus(cacheUpmsSession.getStatus());
            upmsSession.setAttribute("FORCE_LOGOUT", cacheUpmsSession.getAttribute("FORCE_LOGOUT"));
        }
        JedisUtil.set(redisTemplate, UPMS_SESSION_ID + "_" + sid, ShiroSerializationUtils
                        .serialize(upmsSession),
                session.getTimeout() / 1000, TimeUnit.SECONDS);
        // TODO 更新TS_UPMS_SERVER_SESSION_ID、TS_UPMS_SERVER_CODE过期时间
        if (_log.isDebugEnabled())
            _log.debug("doUpdate >>>>> sid={}", sid);
    }

    /**
     * 删除会话：当会话过期/会话停止（如用户退出时）会调用
     *
     * @param session
     */
    @Override
    protected void doDelete(Session session) {
        String sid = session.getId().toString();
        String upmsType = ObjectUtils.toString(session.getAttribute(UpmsConstant.UPMS_TYPE));
        if ("client".equals(upmsType)) {
            // 删除局部会话和同一code注册的局部会话
            String code = JedisUtil.get(redisTemplate, TS_UPMS_CLIENT_SESSION_ID + "_" + sid);
            JedisUtil.delete(redisTemplate, TS_UPMS_CLIENT_SESSION_ID + "_" + sid);
            JedisUtil.srem(redisTemplate, TS_UPMS_CLIENT_SESSION_IDS + "_" + code, sid);
        }
        if ("server".equals(upmsType)) {
            // 当前全局会话code
            String code = JedisUtil.get(redisTemplate, UPMS_GLOBAL_SESSION_ID + "_" + sid);
            // 清除全局会话
            JedisUtil.delete(redisTemplate, UPMS_GLOBAL_SESSION_ID + "_" + sid);
            // 清除code校验值
            JedisUtil.delete(redisTemplate, TS_UPMS_SERVER_CODE + "_" + code);
            // 清除所有局部会话
            Set<String> clientSessionIds = JedisUtil.smembers(redisTemplate, TS_UPMS_CLIENT_SESSION_IDS + "_" +
                    code);
            for (String clientSessionId : clientSessionIds) {
                JedisUtil.delete(redisTemplate, TS_UPMS_CLIENT_SESSION_ID + "_" + clientSessionId);
                JedisUtil.srem(redisTemplate, TS_UPMS_CLIENT_SESSION_IDS + "_" + code, clientSessionId);
            }
            // 维护会话id列表，提供会话分页管理
            JedisUtil.lrem(redisTemplate, UPMS_GLOBAL_SESSION_IDS, 1, sid);
        }
        // 删除session
        JedisUtil.delete(redisTemplate, UPMS_SESSION_ID + "_" + sid);
        if (_log.isDebugEnabled())
            _log.debug("doDelete >>>>> sid={}", sid);
    }

    // /**
    //  * 删除cache中缓存的Session
    //  *
    //  * @param sid
    //  */
    // public void uncache(Serializable sid) {
    //     Session session = this.readSession(sid);
    //     super.uncache(session);
    //     _log.info("uncache >>>>> sid={}", sid);
    // }

    /**
     * 获取当前所有活跃用户会话列表，如果用户量多此方法影响性能
     *
     * @param offset
     * @param limit
     * @return
     */
    public Map<String, Object> getActiveSessions(int offset, int limit) {
        Map<String, Object> sessions = new HashMap<>();
        // 获取在线会话总数
        long total = JedisUtil.llen(redisTemplate, UPMS_GLOBAL_SESSION_IDS);
        // 获取当前页会话详情
        List<String> ids = JedisUtil.lrange(redisTemplate, UPMS_GLOBAL_SESSION_IDS, offset, (offset + limit -
                1));
        List<Session> rows = new ArrayList<>();
        for (String id : ids) {
            String session = JedisUtil.get(redisTemplate, UPMS_SESSION_ID + "_" + id);
            // 过滤redis过期session
            if (null == session) {
                JedisUtil.lrem(redisTemplate, UPMS_GLOBAL_SESSION_IDS, 1, id);
                total = total - 1;
                continue;
            }
            rows.add(ShiroSerializationUtils.deserialize(session));
        }
        sessions.put("total", total);
        sessions.put("rows", rows);
        return sessions;
    }

    /**
     * 强制退出
     *
     * @param ids
     * @return
     */
    public int forceout(String ids) {
        String[] sids = ids.split(",");
        for (String sid : sids) {
            // 会话增加强制退出属性标识，当此会话访问系统时，判断有该标识，则退出登录
            String base64 = JedisUtil.get(redisTemplate, UPMS_SESSION_ID + "_" + sid);
            UpmsSimpleSession session = (UpmsSimpleSession) ShiroSerializationUtils.deserialize(base64);
            session.setStatus(UpmsSimpleSession.OnlineStatus.force_logout);
            session.setAttribute("FORCE_LOGOUT", "FORCE_LOGOUT");
            JedisUtil.set(redisTemplate, UPMS_SESSION_ID + "_" + sid, ShiroSerializationUtils.serialize
                            (session),
                    session.getTimeout() / 1000, TimeUnit.SECONDS);
        }
        return sids.length;
    }

    /**
     * 更改在线状态
     *
     * @param sid
     * @param onlineStatus
     */
    public void updateStatus(Serializable sid, UpmsSimpleSession.OnlineStatus onlineStatus) {
        UpmsSimpleSession session = (UpmsSimpleSession) doReadSession(sid);
        if (null == session) {
            return;
        }
        session.setStatus(onlineStatus);
        JedisUtil.set(redisTemplate, UPMS_SESSION_ID + "_" + session.getId(), ShiroSerializationUtils
                        .serialize
                                (session),
                session.getTimeout() / 1000, TimeUnit.SECONDS);
    }

}
