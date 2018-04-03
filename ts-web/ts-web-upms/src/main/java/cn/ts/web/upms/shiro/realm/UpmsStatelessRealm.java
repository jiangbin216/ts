package cn.ts.web.upms.shiro.realm;// package cn.ts.upms.web.shiro.realm;
//
// import UpmsPermission;
// import UpmsRole;
// import UpmsUser;
// import UpmsApiService;
// import org.apache.commons.lang.StringUtils;
// import org.apache.shiro.authc.AuthenticationException;
// import org.apache.shiro.authc.AuthenticationInfo;
// import org.apache.shiro.authc.AuthenticationToken;
// import org.apache.shiro.authc.IncorrectCredentialsException;
// import org.apache.shiro.authc.LockedAccountException;
// import org.apache.shiro.authc.SimpleAuthenticationInfo;
// import org.apache.shiro.authc.UnknownAccountException;
// import org.apache.shiro.authz.AuthorizationInfo;
// import org.apache.shiro.authz.SimpleAuthorizationInfo;
// import org.apache.shiro.realm.AuthorizingRealm;
// import org.apache.shiro.subject.PrincipalCollection;
// import org.springframework.beans.factory.annotation.Autowired;
//
// import java.util.HashSet;
// import java.util.List;
// import java.util.Set;
//
// /**
//  * shiro-jwt无状态登录realm
//  *
//  * @author tidy
//  */
// public class UpmsStatelessRealm extends AuthorizingRealm {
//
//     @Autowired
//     private UpmsApiService upmsApiService;
//
//     @Override
//     public boolean supports(AuthenticationToken token) {
//         //仅支持StatelessToken类型的Token
//         return token instanceof UpmsStatelessToken;
//     }
//
//     /**
//      * 授权
//      *
//      * @param principal
//      * @return
//      */
//     @Override
//     protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
//         String username = (String) principal.getPrimaryPrincipal();
//         UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);
//
//         // 当前用户所有角色
//         List<UpmsRole> upmsRoles = upmsApiService.selectUpmsRoleByUpmsUserId(upmsUser.getUserId());
//         Set<String> roles = new HashSet<>();
//         for (UpmsRole upmsRole : upmsRoles) {
//             if (StringUtils.isNotBlank(upmsRole.getName())) {
//                 roles.add(upmsRole.getName());
//             }
//         }
//
//         // 当前用户所有权限
//         List<UpmsPermission> upmsPermissions = upmsApiService.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());
//         Set<String> permissions = new HashSet<>();
//         for (UpmsPermission upmsPermission : upmsPermissions) {
//             if (StringUtils.isNotBlank(upmsPermission.getPermissionValue())) {
//                 permissions.add(upmsPermission.getPermissionValue());
//             }
//         }
//
//         SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//         simpleAuthorizationInfo.setStringPermissions(permissions);
//         simpleAuthorizationInfo.setRoles(roles);
//         return simpleAuthorizationInfo;
//     }
//
//     /**
//      * 认证
//      *
//      * @param token
//      * @return
//      * @throws AuthenticationException
//      */
//     @Override
//     protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//         UpmsStatelessToken statelessToken = (UpmsStatelessToken) token;
//         String username = (String) statelessToken.getPrincipal();
//         //根据用户名获取密钥（和客户端的一样）
//         UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);
//
//         if (null == upmsUser) {
//             throw new UnknownAccountException();
//         }
//
//         //然后进行客户端消息摘要和服务器端消息摘要的匹配
//         if (!upmsUser.getPassword().equals(statelessToken.getCredentials())) {
//             throw new IncorrectCredentialsException();
//         }
//         if (upmsUser.getLocked() == 1) {
//             throw new LockedAccountException();
//         }
//
//         return new SimpleAuthenticationInfo(
//                 username,
//                 statelessToken.getCredentials(),
//                 getName());
//     }
//
// }
