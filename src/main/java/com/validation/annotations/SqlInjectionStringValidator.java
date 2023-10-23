
package com.validation.annotations;
import java.util.Collection;
import java.util.Optional;

import com.validation.util.ValidationUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SqlInjectionStringValidator implements ConstraintValidator<SqlInjectionStringValidation, Object> {



	public void initialize(SqlInjectionStringValidation validate) {
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		if (value == null) {
			return true; // Assuming null values are considered valid
		}

		if (value instanceof Collection<?>) {
			Collection<?> collection = (Collection<?>) value;
			for (Object element : collection) {
				if (!ValidationUtil.isValidString(element)) {
					return false;
				}
			}
			return true;
		}

		if (value instanceof Optional<?>) {
			Optional<?> optional = (Optional<?>) value;
			if (optional.isPresent()) {
				return ValidationUtil.isValidString(optional.get());
			} else {
				return true; // Assuming empty optionals are considered valid
			}
		}
		return ValidationUtil.isValidString(value);
	}

	
}
