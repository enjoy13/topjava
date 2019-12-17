package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.web.ExceptionInfoHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    String message() default ExceptionInfoHandler.USER_WITH_THIS_EMAIL_ALREADY_EXISTS;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

