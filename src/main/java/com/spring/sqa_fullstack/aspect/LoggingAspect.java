package com.spring.sqa_fullstack.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Slf4j
@Configuration
public class LoggingAspect {
    @Autowired
    private ObjectMapper mapper;

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {}
    @Pointcut("within(com.spring.sqa_fullstack.controller.user.*)")
    public void fromUser() {}
    @Pointcut("within(com.spring.sqa_fullstack.controller.admin.*)")
    public void fromAdmin() {}

    @Pointcut("(requestMapping() && fromAdmin())")
    public void pointCutAdminSide() {}
    @Pointcut("(requestMapping() && fromUser())")
    public void pointCutUserSide() {}
    @Pointcut("execution(* *(..))")
    public void atExecution() {}

    private void logging(JoinPoint joinPoint, String actor, String advice) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();

        try {
            log.info(">>{} {} invoked:: path(s): {}, method(s): {}, arguments: {}",
                    actor, advice, request.getRequestURI(), signature.getName(), mapper.writeValueAsString(joinPoint.getArgs()));
        } catch (JsonProcessingException je) {
            log.error("Error while converting", je);
        }
    }
    private void loggingException(JoinPoint joinPoint, Throwable e, String actor, String advice) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();

        try {
            log.info(">>{} {} invoked:: path(s): {}, method(s): {}, arguments: {}, exception with cause: {}",
                    actor, advice, request.getRequestURI(), signature.getName(), mapper.writeValueAsString(joinPoint.getArgs()),
                    e.getCause() != null ? e.getCause() : "NULL");
        } catch (JsonProcessingException je) {
            log.error("Error while converting", je);
        }
    }

    @After("(pointCutUserSide() && atExecution())")
    public void logAfterMethodUserSide(JoinPoint joinPoint) {
        logging(joinPoint, "[User]", "After");
    }
    @Before("(pointCutUserSide() && atExecution())")
    public void logBeforeMethodUserSide(JoinPoint joinPoint) {
        logging(joinPoint, "[User]", "Before");
    }
    @AfterThrowing(value = "(pointCutUserSide() && atExecution())", throwing = "e")
    public void logAfterThrowingUserSide(JoinPoint joinPoint, Throwable e) {
        loggingException(joinPoint, e, "[User]", "After Throwing");
    }

    @After("(pointCutAdminSide() && atExecution())")
    public void logAfterMethodAdminSide(JoinPoint joinPoint) {
        logging(joinPoint, "[Admin]", "After");
    }
    @Before("(pointCutAdminSide() && atExecution())")
    public void logBeforeMethodAdminSide(JoinPoint joinPoint) {
        logging(joinPoint, "[Admin]", "Before");
    }
    @AfterThrowing(value = "(pointCutAdminSide() && atExecution())", throwing = "e")
    public void logAfterThrowingAdminSide(JoinPoint joinPoint, Throwable e) {
        loggingException(joinPoint, e, "[Admin]", "After Throwing");
    }
}
