package com.interland.training.entity.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = CustomSizeValidator.class)
public @interface CustomSize {
	String message() default "Value can't be empty";
	Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
	String maxKey();
	String minKey();
}
