package ru.geekbrains.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;
import ru.geekbrains.aop.annotation.RecoverException;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class ExceptionAspect {
    @Around("@annotation(ru.geekbrains.aop.annotation.RecoverException)")
    public Object proceessingExeption(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object returnValue = joinPoint.proceed();
            log.atInfo().log("Success");
            return returnValue;
        } catch (Throwable e) {
            //log.atError().log("exception = [{}, {}]", e.getClass(), e.getMessage());
            Class exeptionClass = e.getClass();
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            RecoverException recoverException = method.getAnnotation(RecoverException.class);
            Class<? extends RuntimeException>[] annotationValue = recoverException.noRecoverFor();
            boolean contains = Arrays.stream(annotationValue).anyMatch(exeptionClass::equals);
            if (!contains) {
                Class returnClass = ((MethodSignature) joinPoint.getSignature()).getReturnType();
                if (returnClass == null) return null;
                String classStr = returnClass.getSimpleName();
                if (classStr.equals("short") || classStr.equals("Short") ||
                        classStr.equals("int") || classStr.equals("Integer") ||
                        classStr.equals("long") || classStr.equals("Long") ||
                        classStr.equals("double") || classStr.equals("Double") ||
                        classStr.equals("float") || classStr.equals("Float")) {
                    return 0;
                }
                if (classStr.equals("boolean") || classStr.equals("Boolean")) return false;
                if (classStr.equals("char") || classStr.equals("Character")) return '\u0000';
                return null;
            }
            else {
                throw e;
            }
        }
    }
}
