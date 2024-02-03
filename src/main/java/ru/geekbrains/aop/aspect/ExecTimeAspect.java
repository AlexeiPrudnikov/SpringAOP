package ru.geekbrains.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Slf4j
@Aspect
@Component
public class ExecTimeAspect {
    @Pointcut("within(@ru.geekbrains.aop.annotation.Timer *)")
    public void beansAnnotatedWith() {
    }
    @Pointcut("@annotation(ru.geekbrains.aop.annotation.Timer)")
    public void methodsAnnotatedWith() {
    }
    @Around("beansAnnotatedWith() || methodsAnnotatedWith()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info(joinPoint.getSignature().getDeclaringType().getSimpleName() +
                " - " + joinPoint.getSignature().getName() + " # " + executionTime + " миллисекунд");
        return proceed;
    }
}
