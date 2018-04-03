package cn.ts.core.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 接口调用异常拦截基类
 *
 * @author tidy
 */
public class BaseRestController {

    private final static Logger _log = LoggerFactory.getLogger(BaseRestController.class);

    /**
     * 统一异常处理
     *
     * @param request
     * @param exception
     */
    @ExceptionHandler(value = {Exception.class})
    public Object exceptionHandler(Exception exception, HttpServletRequest request) {
        _log.error("统一异常处理：", exception);
        return new BaseResult(BaseConstant.FAILED, exception.toString());
    }
}
