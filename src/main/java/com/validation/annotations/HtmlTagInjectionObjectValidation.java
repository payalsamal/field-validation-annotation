package com.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HtmlTagInjectionObjectValidator.class)
@Documented
public @interface HtmlTagInjectionObjectValidation {
	String message() default "String validation failed";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
