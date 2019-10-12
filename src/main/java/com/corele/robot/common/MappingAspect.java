package com.corele.robot.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.StringJoiner;

/**
 * @author jun
 */
@Aspect
@Component
@Order(1)
public class MappingAspect {

    private static Logger logger = LoggerFactory.getLogger(MappingAspect.class);

    @Pointcut("(@annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.PutMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.RequestMapping))" +
            "&& within(com.olasharing.record.controller..*)")
    public void aspectAnnotation(){

    }

    @Around("aspectAnnotation()")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {

        Class<?> clazz = joinPoint.getTarget().getClass();
        String clazzName = clazz.getName();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();

        Object[] args = joinPoint.getArgs();
        StringJoiner stringJoiner = new StringJoiner(",");
        for (Object arg : args) {
            stringJoiner.add(String.valueOf(arg));
        }
        String params = stringJoiner.toString();

        String requestMethod = "GET";

        StringJoiner sj = new StringJoiner("/");

        RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
        if (requestMapping != null){
            String[] value = requestMapping.value();
            String string = getString(value);
            sj.add(string);
        }

        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof GetMapping){
                requestMethod = "GET";
                GetMapping getMapping = (GetMapping) annotation;
                String[] value = getMapping.value();
                String string = getString(value);
                sj.add(string);
            }else if (annotation instanceof PostMapping){
                requestMethod = "POST";
                PostMapping mapping = (PostMapping) annotation;
                String[] value = mapping.value();
                String string = getString(value);
                sj.add(string);
            }else if (annotation instanceof PutMapping){
                requestMethod = "PUT";
                PutMapping mapping = (PutMapping) annotation;
                String[] value = mapping.value();
                String string = getString(value);
                sj.add(string);
            }else if (annotation instanceof DeleteMapping){
                requestMethod = "DELETE";
                DeleteMapping mapping = (DeleteMapping) annotation;
                String[] value = mapping.value();
                String string = getString(value);
                sj.add(string);
            }else if (annotation instanceof RequestMapping){
                RequestMapping mapping = (RequestMapping) annotation;
                RequestMethod[] requestMethods = mapping.method();
                if (requestMethods != null && requestMethods.length > 0){
                    requestMethod = requestMethods[0].name();
                }
                String[] value = mapping.value();
                String string = getString(value);
                sj.add(string);
            }
        }

        String path = sj.toString();

        logger.info("[REQUEST {}][path = {}] {}#{}({})",requestMethod,path,clazzName,methodName,params);
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        logger.info("[RESULT {}][path = {}] {} times: {}ms",requestMethod,path,clazzName, System.currentTimeMillis()-startTime);
        return result;
    }

    private String getString(String[] value) {
        StringBuilder sb = new StringBuilder();
        for (String s : value) {
            sb.append(s);
        }
        return sb.toString();
    }
}
