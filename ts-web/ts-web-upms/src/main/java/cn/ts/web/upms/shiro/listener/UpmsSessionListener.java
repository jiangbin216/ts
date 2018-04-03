package cn.ts.web.upms.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * shiro session监听器
 * <p>发现用户退出后，Session没有从Redis中销毁，虽然重新new了一个，但会对统计带来干扰，通过SessionListener解决这个问题</p>
 *
 * @author Created by YL on 2017/2/12.
 */
public class UpmsSessionListener implements SessionListener {
    private static Logger _log = LoggerFactory.getLogger(UpmsSessionListener.class);

    // @Autowired
    // private UpmsCachingSessionDAO upmsCachingSessionDAO;

    /**
     * 会话创建时触发
     *
     * @param session
     */
    @Override
    public void onStart(Session session) {
        _log.info("create shiro session: {}", session.getId());
    }

    /**
     * 会话被停止时触发
     *
     * @param session
     */
    @Override
    public void onStop(Session session) {
        _log.info("stop shiro session: {}", session.getId());
        // upmsCachingSessionDAO.delete(session);
    }

    /**
     * 会话过期时触发
     *
     * @param session
     */
    @Override
    public void onExpiration(Session session) {
        _log.info("expire shiro session: {}", session.getId());
        // upmsCachingSessionDAO.delete(session);
    }
}
