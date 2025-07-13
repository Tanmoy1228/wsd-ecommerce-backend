package com.wsd.ecommerce_app.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class MethodLoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(MethodLoggerAspect.class);

    @Around("execution(* com.wsd.ecommerce_app..*(..))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();

        Object result = null;
        Throwable throwable = null;
        try {
            result = joinPoint.proceed();
            return result;

        } catch (Throwable t) {
            throwable = t;
            throw t;

        } finally {

            long durationMs = (System.nanoTime() - start) / 1_000_000;

            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
            Object[] args = joinPoint.getArgs();

            if (throwable == null) {
                logger.info("Executed method [{}] in {} ms with args {} — Status: SUCCESS",
                        methodName, durationMs, Arrays.toString(args));
            } else {
                logger.error("Method [{}] failed after {} ms with args {} — Exception: {}: {}",
                        methodName, durationMs, Arrays.toString(args),
                        throwable.getClass().getSimpleName(), throwable.getMessage());
            }

            Map<String, Object> logData = new HashMap<>();
            logData.put("method", methodName);
            logData.put("durationMs", durationMs);
            logData.put("args", args);
            if (throwable != null) {
                logData.put("status", "ERROR");
                logData.put("exception", throwable.getClass().getSimpleName());
                logData.put("message", throwable.getMessage());
            } else {
                logData.put("status", "SUCCESS");
            }

            logger.info("{}", logData);
        }
    }
}
