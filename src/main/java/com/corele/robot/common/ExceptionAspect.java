package com.corele.robot.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author jun
 */
@Slf4j
@Aspect
@Component
@Order(99)
public class ExceptionAspect {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void aspect(){
    }

    @Around("aspect()")
    public Object invoke(ProceedingJoinPoint joinPoint){
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Class<?> returnType = method.getReturnType();
        try {
            return joinPoint.proceed();
        } catch (BaseException ex) {
            log.error("{}",ex);
            if (returnType == BaseResponse.class){
                BaseResponse response = BaseResponse.baseResponse(ex.getErrorCode(),null,ex.getMessage());
                return response;
            }
            throw new RuntimeException(ex.getMessage());
        } catch (Throwable throwable){
            log.error("{}",throwable);
            if (returnType == BaseResponse.class){
                BaseResponse response = BaseResponse.baseResponse(0,null,throwable.getMessage());
                return response;
            }
            throw new RuntimeException(throwable.getMessage());
        }
    }

}
