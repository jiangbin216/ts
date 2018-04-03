package cn.ts.web.upms.interceptor;

import cn.ts.core.util.RequestUtil;
import cn.ts.rpc.upms.api.UpmsLogService;
import cn.ts.rpc.upms.model.UpmsLog;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ObjectUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 日志记录AOP实现
 *
 * @author Created by YL on 2017/06/03.
 */
@Component
@Aspect
public class LogAspect {
    @Reference
    private UpmsLogService upmsLogService;

    @Around("execution(* *..controller..*.* (..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        // 获取request
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        UpmsLog upmsLog = new UpmsLog();
        // 从注解中获取操作名称、获取响应结果
        Object result = pjp.proceed();
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation log = method.getAnnotation(ApiOperation.class);
            upmsLog.setDescription(log.value());
        }
        if (method.isAnnotationPresent(RequiresPermissions.class)) {
            RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
            String[] permissions = requiresPermissions.value();
            if (permissions.length > 0) {
                upmsLog.setPermissions(permissions[0]);
            }
        }
        long endTime = System.currentTimeMillis();

        upmsLog.setBasePath(RequestUtil.getBasePath(request));
        upmsLog.setIp(RequestUtil.getIpAddr(request));
        upmsLog.setMethod(request.getMethod());
        // if (request.getMethod().equalsIgnoreCase("GET")) {
        //     upmsLog.setParameter(request.getQueryString());
        // } else {
        //     upmsLog.setParameter(ObjectUtils.toString(request.getParameterMap()));
        // }
        // upmsLog.setResult(ObjectUtils.toString(result));
        upmsLog.setSpendTime(new BigDecimal(endTime - startTime));
        upmsLog.setStartTime(new Date(startTime));
        upmsLog.setUri(request.getRequestURI());
        upmsLog.setUrl(ObjectUtils.toString(request.getRequestURL()));
        upmsLog.setUserAgent(request.getHeader("User-Agent"));
        upmsLog.setUsername(ObjectUtils.toString(request.getUserPrincipal()));
        upmsLogService.insertSelective(upmsLog);
        return result;
    }

}
