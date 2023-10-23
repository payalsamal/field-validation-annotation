
package com.validation.annotations;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SqlInjectionValidator implements ConstraintValidator<SqlInjectionInObjectValidation, Object> {

    private static final String SQL_INJECTION_PATTERN = ".*([';\\\\]+|(--)|(\\b(ALTER|CREATE|DELETE|DROP|EXEC(UTE){0,1}|INSERT( +INTO){0,1}|MERGE|SELECT|UPDATE)\\b)).*";
    private static final Pattern pattern = Pattern.compile(SQL_INJECTION_PATTERN);

    public void initialize(SqlInjectionInObjectValidation constraintAnnotation) {
        // default method
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        // Example: Validate that all string variables are not null or empty
        if (value == null) {
            return true; // Return true if the object is null
        }

        // Check each string variable for null or empty value
        Class<?> clazz = value.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == String.class) {
                try {
                    // Get the name of the getter method
                    String getterMethodName = "get" + capitalize(field.getName());

                    // Get the Method object representing the getter method
                    Method getterMethod = clazz.getMethod(getterMethodName);

                    // Invoke the getter method on the target object
                    Object result = getterMethod.invoke(value);

                    String fieldValue = (String) result;

                    if (fieldValue != null && !fieldValue.isEmpty()
                            && pattern.matcher(fieldValue.toUpperCase()).matches()) {

                        return false;
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                        | NoSuchMethodException | SecurityException e) {
                    return false; // Return false if any exception occurs during access
                }
            }
        }

        return true; // All string variables passed the validation
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

}
