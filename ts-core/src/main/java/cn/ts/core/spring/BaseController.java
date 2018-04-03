package cn.ts.core.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器基类
 *
 * @author Created by YL on 2017/2/4.
 */
public abstract class BaseController {
    private final static Logger _log = LoggerFactory.getLogger(BaseController.class);

    /**
     * 统一异常处理
     *
     * @param request
     * @param response
     * @param exception
     */
    @ExceptionHandler
    public String exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        _log.error("统一异常处理：", exception);
        request.setAttribute("ex", exception);
        if (null != request.getHeader("X-Requested-With") && request.getHeader("X-Requested-With").equalsIgnoreCase
                ("XMLHttpRequest")) {
            request.setAttribute("requestHeader", "ajax");
        }
        return "/error.jsp";
    }

    /**
     * 返回jsp视图
     *
     * @param path
     * @return
     */
    public static String jsp(String path) {
        return path.concat(".jsp");
    }

    /**
     * 返回thymeleaf视图
     *
     * @param path
     * @return
     */
    public static String thymeleaf(String path) {
        String folder = "";//PropertiesFileUtil.getInstance().get("app.name");
        return "/".concat(folder).concat(path).concat(".html");
    }
}
