package com.example.tanyue.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

public class ControllerLogsAspect {

    private final static Logger logger = LoggerFactory.getLogger(ControllerLogsAspect.class);

    @Pointcut("execution(* com.example.tanyue.controller..*(..))")
    public void controllerLog(){}

    @Before("controllerLog()")
    public void doBefore(JoinPoint joinPoint){
        try{
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if(attributes==null||joinPoint==null){
                return;
            }
            HttpServletRequest request = attributes.getRequest();


            StringBuffer methodBuf = new StringBuffer();
            methodBuf.append(joinPoint.getSignature().getDeclaringType()).append(".").append(joinPoint.getSignature().getName());
            logger.info("REQUEST: url=>{}, httpMethod=>{}, ip=>{}, classMethod=>{}, httpParams=>{}",
                    request.getRequestURI(),request.getMethod(),request.getRemoteAddr(),new String(methodBuf),
                    buildHttpParams(request));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String buildHttpParams(HttpServletRequest request){
        StringBuffer paramBuf = new StringBuffer();
        paramBuf.append("{");
        Enumeration em = request.getParameterNames();
        while(em.hasMoreElements()){
            String name = (String) em.nextElement();
            String[] value = request.getParameterValues(name);
            paramBuf.append(name).append(":").append(value.length==1?value[0]: Arrays.toString(value)).append(" ");
        }
        paramBuf.append("}");
        return new String(paramBuf);
    }
}
