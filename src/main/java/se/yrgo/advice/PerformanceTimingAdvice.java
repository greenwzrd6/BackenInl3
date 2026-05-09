package se.yrgo.advice;

import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.*;

@Aspect
@Component
public class PerformanceTimingAdvice {

    @Around("execution(* se.yrgo.services..*(..)) || execution(* se.yrgo.dataaccess..*(..))")
    public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.nanoTime();

        try {
            return joinPoint.proceed();
        } finally {
            long endTime = System.nanoTime();
            double timeTakenMs = (endTime - startTime) / 1_000_000.0;
            String methodName = joinPoint.getSignature().getName();
            String className = joinPoint.getTarget().getClass().getName();
            System.out.printf("Time taken for the method %s from the class %s took %.4fms%n",
                    methodName, className, timeTakenMs);
        }
    }
}