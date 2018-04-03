package cn.ts.web.upms.shiro.filter;

import cn.ts.web.upms.common.UpmsConstant;
import cn.ts.web.upms.common.UpmsResult;
import cn.ts.web.upms.common.UpmsResultConstant;
import cn.ts.web.upms.shiro.realm.UpmsStatelessToken;
import cn.ts.web.upms.util.JWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * shiro-jwt无状态认证过滤器
 *
 * @author tidy
 */
public class UpmsStatelessAuthcFilter extends AccessControlFilter {

    private static final JWT jwt = new JWT.Builder(UpmsConstant.SHIRO_JWT_ENCRYPT_KEY).build();

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws
            Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //1、获取jwt加密参数
        String encryptCode = request.getParameter(UpmsConstant.SHIRO_JWT_ENCRYPT_CODE);

        if (StringUtils.isBlank(encryptCode)) {
            onLoginFail(response, "code is null"); //encryptcode为空
            return false;
        }

        try {
            Jws<Claims> parser = jwt.parser(encryptCode);
            //2、生成无状态Token
            UpmsStatelessToken token = new UpmsStatelessToken(parser);
            //3、委托给Realm进行登录
            getSubject(request, response).login(token);
        } catch (JwtException jwtException) {
            onLoginFail(response, "code error"); //jwt验证失败
            return false;
        } catch (Exception e) {
            onLoginFail(response, "login error"); //shiro验证失败
            return false;
        }
        return true;
    }

    //登录失败时默认返回401状态码
    private void onLoginFail(ServletResponse response, String errMsg) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write(JSONObject.fromObject(new UpmsResult(UpmsResultConstant.FAILED, errMsg)).toString());
    }
}
