package com.exatern.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);

    @Before("controller() || service()")
    public void loggingBefore(JoinPoint joinPoint) {

        String methodName = getMethodName(joinPoint);
        String className = getClassName(joinPoint);

        LOGGER.info( "className: {} methodName: {} ", className, methodName);
    }

    @AfterReturning(pointcut = "controller() || service()", returning = "returnValue")
    public void logAfter(JoinPoint joinPoint, Object returnValue) {

        LOGGER.info("Method Return value : " + returnValue);
    }

    @AfterThrowing(pointcut = "controller()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        LOGGER.error("An exception has been thrown in " + getClassName(joinPoint) + " ()");
        LOGGER.error("Cause : " + exception.getCause());
    }


    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void controller() {
    }

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void service() {
    }

    private String getMethodName(JoinPoint joinPoint) {
        return MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName();
    }

    private String getClassName(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getName();
    }


}
