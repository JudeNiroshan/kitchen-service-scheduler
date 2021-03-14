package com.verimi.kitchenservice.validators;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DateValidator {

    @Before("@annotation(com.verimi.kitchenservice.annotations.ValidDate)")
    public Object before(JoinPoint parameter) {

        // Can check date parameter is with the range
        return null;
    }
}
