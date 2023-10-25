package br.com.car.rental.shared.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = IsBeforeOrEqualThanCurrentYearValidator.class)
@Documented
public @interface IsBeforeOrEqualThanCurrentYear {
	String message() default "The production year should be equal to or less than the current year.";
	
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}