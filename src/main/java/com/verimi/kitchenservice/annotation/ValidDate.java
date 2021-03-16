package com.verimi.kitchenservice.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.verimi.kitchenservice.validator.DateValidator;

@Documented
@Constraint(validatedBy = DateValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDate {
    String message() default "Week-plan is only available for last 6 weeks and future 6 weeks. Please consider recent date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
