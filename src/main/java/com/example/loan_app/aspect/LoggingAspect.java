package com.example.loan_app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.example.loan_app.controller..*(..))")
    public void logBeforeControllerMethods(JoinPoint joinPoint) {
        System.out.println("LoggingAspect - Before Controller: " + joinPoint.getSignature().getName() +
                " in " + joinPoint.getSignature().getDeclaringTypeName());
    }

    @AfterReturning(pointcut = "execution(* com.example.loan_app.controller..*(..))", returning = "result")
    public void logAfterControllerMethods(JoinPoint joinPoint, Object result) {
        System.out.println("LoggingAspect - After Controller: " + joinPoint.getSignature().getName() +
                " in " + joinPoint.getSignature().getDeclaringTypeName());
    }

    @Before("execution(* com.example.loan_app.service..*(..))")
    public void logBeforeServiceMethods(JoinPoint joinPoint) {
        System.out.println("LoggingAspect - Before Service: " + joinPoint.getSignature().getName() +
                " in " + joinPoint.getSignature().getDeclaringTypeName());
    }

    @AfterReturning(pointcut = "execution(* com.example.loan_app.service..*(..))", returning = "result")
    public void logAfterServiceMethods(JoinPoint joinPoint, Object result) {
        System.out.println("LoggingAspect - After Service: " + joinPoint.getSignature().getName() +
                " in " + joinPoint.getSignature().getDeclaringTypeName());
    }

    @Before("execution(* com.example.loan_app.repository..*(..))")
    public void logBeforeRepositoryMethods(JoinPoint joinPoint) {
        System.out.println("LoggingAspect - Before Repository: " + joinPoint.getSignature().getName() +
                " in " + joinPoint.getSignature().getDeclaringTypeName());
    }

    @AfterReturning(pointcut = "execution(* com.example.loan_app.repository..*(..))", returning = "result")
    public void logAfterRepositoryMethods(JoinPoint joinPoint, Object result) {
        System.out.println("LoggingAspect - After Repository: " + joinPoint.getSignature().getName() +
                " in " + joinPoint.getSignature().getDeclaringTypeName());
//        System.out.println("Result: " + result);
    }
}
