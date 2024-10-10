package com.interland.training.entity.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = CustomNumberSizeValidator.class)
public @interface CustomNumberSize {
	String message() default "Value can't be empty";
	Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
	int maxKey();
	int minKey();
}
