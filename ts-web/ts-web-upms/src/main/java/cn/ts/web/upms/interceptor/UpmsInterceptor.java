package cn.ts.web.upms.interceptor;

import cn.ts.rpc.upms.api.UpmsUserService;
import cn.ts.rpc.upms.model.UpmsUser;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录信息拦截器
 *
 * @author Created by YL on 2017/2/11.
 */
public class UpmsInterceptor extends HandlerInterceptorAdapter {
    @Reference
    private UpmsUserService upmsUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        // 过滤ajax
        if (null != request.getHeader("X-Requested-With") && request.getHeader("X-Requested-With").equalsIgnoreCase
                ("XMLHttpRequest")) {
            return true;
        }
        // 登录信息
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        UpmsUser upmsUser = upmsUserService.selectUpmsUserByUsername(username);
        request.setAttribute("upmsUser", upmsUser);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception
            ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object
            handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

}
