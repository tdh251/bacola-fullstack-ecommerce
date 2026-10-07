package com.tranduchai.server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SlugValidator.class)
public @interface Slug {
   String message() default "Slug không có khoảng trắng và dấu";

   Class<?>[] groups() default {};

   Class<? extends Payload>[] payload() default {};
}
