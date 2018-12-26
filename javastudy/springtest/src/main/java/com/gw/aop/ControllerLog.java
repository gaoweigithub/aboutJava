package com.gw.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Component
public class ControllerLog {

    public Object Around(ProceedingJoinPoint point) throws Throwable {
        StringBuffer sb = new StringBuffer();
        sb.append("开始拦截接口入参\r\n");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object[] objs = point.getArgs();
        sb.append("参数列表:");
        //121
        if (objs != null) {
            for (Object obj : objs) {
                sb.append(obj + "\r\n");
            }
        }
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Object result = point.proceed(objs);
        sb.append("响应：" + JSON.toJSONString(result));
        return result;
    }
}
