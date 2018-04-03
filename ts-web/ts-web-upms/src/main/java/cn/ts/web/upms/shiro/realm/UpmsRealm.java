package cn.ts.web.upms.shiro.realm;

import cn.ts.core.md5.MD5;
import cn.ts.rpc.upms.api.UpmsPermissionService;
import cn.ts.rpc.upms.api.UpmsRoleService;
import cn.ts.rpc.upms.api.UpmsUserRoleService;
import cn.ts.rpc.upms.api.UpmsUserService;
import cn.ts.rpc.upms.model.UpmsPermission;
import cn.ts.rpc.upms.model.UpmsRole;
import cn.ts.rpc.upms.model.UpmsUser;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户认证和授权
 *
 * @author Created by YL on 2017/1/20.
 */
public class UpmsRealm extends AuthorizingRealm {
    @Reference
    private UpmsUserService upmsUserService;
    @Reference
    private UpmsRoleService upmsRoleService;
    @Reference
    private UpmsUserRoleService upmsUserRoleService;
    @Reference
    private UpmsPermissionService upmsPermissionService;

    /**
     * 授权：权限验证
     *
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        String username = (String) principal.getPrimaryPrincipal();
        UpmsUser user = upmsUserService.selectUpmsUserByUsername(username);

        // 把账号信息放到Session中，并更新缓存,用于会话管理
        // Subject subject = SecurityUtils.getSubject();
        // Serializable sid = subject.getSession().getId();
        // UpmsSession session = (UpmsSession) upmsSessionDao.doReadSession(sid);
        // session.setAttribute("user_id", user.getUserId());
        // session.setAttribute("user_name", user.getUsername());
        // upmsSessionDao.update(session);

        // 通过用户名去获得用户的所有资源，并把资源存入info中，这里通常会操作数据库去获取
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 当前用户所有权限
        List<UpmsPermission> upmsPermissions = upmsPermissionService.selectUpmsPermissionByUserId(user.getId());
        Set<String> permissions = new HashSet<>();
        for (UpmsPermission upmsPermission : upmsPermissions) {
            if (StringUtils.isNotBlank(upmsPermission.getPermissionValue())) {
                permissions.add(upmsPermission.getPermissionValue());
            }
        }
        info.setStringPermissions(permissions);

        // 当前用户所有角色
        List<UpmsRole> upmsRoles = upmsRoleService.selectUpmsRoleByUserId(user.getId());
        Set<String> roles = new HashSet<>();
        for (UpmsRole upmsRole : upmsRoles) {
            if (StringUtils.isNotBlank(upmsRole.getName())) {
                roles.add(upmsRole.getName());
            }
        }
        info.setRoles(roles);
        return info;
    }

    /**
     * 认证：登录验证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws
            AuthenticationException {
        // //token中储存着输入的用户名和密码
        // UsernamePasswordToken upToken = (UsernamePasswordToken)token;
        // String username = upToken.getUsername();
        // //通常是根据用户名去数据库中查询相应信息，这里就省略了
        // //当然这里也可以对用户名和密码进行校验

        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        // client无密认证
        // String upmsType = PropertiesFileUtil.getInstance("ts-upms-client").get("upms.type");
        // if ("client".equals(upmsType)) {
        //     return new SimpleAuthenticationInfo(username, password, getName());
        // }

        // TODO 从缓存中读取，如果读取不到，则去数据库读取
        // 查询用户信息
        UpmsUser user = upmsUserService.selectUpmsUserByUsername(username);
        if (user == null) {
            throw new UnknownAccountException();
        }
        if (!user.getPassword().equals(MD5.UTF8.md5UpperCase(password + user.getSalt()))) {
            throw new IncorrectCredentialsException();
        }
        BigDecimal locked = user.getLocked();
        if (locked == null || locked.intValue() == 1) {
            throw new LockedAccountException();
        }
        return new SimpleAuthenticationInfo(username, password, getName());
    }

    // @Override
    // public void setCredentialsMatcher(CredentialsMatcher matcher) {
    //     HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
    //     hashedCredentialsMatcher.setHashAlgorithmName("");
    //     hashedCredentialsMatcher.setHashIterations(0);
    //     super.setCredentialsMatcher(hashedCredentialsMatcher);
    // }
}
