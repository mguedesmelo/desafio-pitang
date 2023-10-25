package br.com.car.rental.shared.validation;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsBeforeOrEqualThanCurrentYearValidator
		implements ConstraintValidator<IsBeforeOrEqualThanCurrentYear, Integer> {
	@Override
	public void initialize(IsBeforeOrEqualThanCurrentYear constraintAnnotation) {
		// Empty
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
		return value.intValue() <= LocalDate.now().getYear();
	}
}
