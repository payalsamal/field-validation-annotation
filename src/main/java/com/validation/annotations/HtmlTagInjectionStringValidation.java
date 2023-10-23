package com.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HtmlTagInjectionStringValidator.class)
@Documented
public @interface HtmlTagInjectionStringValidation {
	String message() default "String validation failed";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
