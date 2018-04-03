package cn.ts.web.upms.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Session 工厂
 *
 * @author Created by YL on 2017/2/27.
 */
public class UpmsSessionFactory implements SessionFactory {

    @Override
    public Session createSession(SessionContext context) {
        UpmsSimpleSession session = new UpmsSimpleSession();
        if (null != context && context instanceof WebSessionContext) {
            WebSessionContext webSessionContext = (WebSessionContext) context;
            HttpServletRequest request = (HttpServletRequest) webSessionContext.getServletRequest();
            if (null != request) {
                session.setHost(request.getRemoteAddr());
                session.setUserAgent(request.getHeader("User-Agent"));
            }
        }
        return session;
    }
}
