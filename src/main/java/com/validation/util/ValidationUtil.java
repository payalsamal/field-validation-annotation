package com.validation.util;


import java.util.Collection;
import java.util.regex.Pattern;

public class ValidationUtil {
	
	private static final String SQL_INJECTION_PATTERN = ".*([';\\\\]+|(--)|(\\b(ALTER|CREATE|DELETE|DROP|EXEC(UTE){0,1}|INSERT( +INTO){0,1}|MERGE|SELECT|UPDATE)\\b)).*";

	private static final Pattern pattern = Pattern.compile(SQL_INJECTION_PATTERN);
	
	private static final String HTML_INJECTION_PATTERN = "(<[^>]*>)";
	private static final Pattern htmlPattern = Pattern.compile(HTML_INJECTION_PATTERN);
	
	public static boolean isValidString(Object value) {
		if (value instanceof String) {
			String stringValue = (String) value;
			return !pattern.matcher(stringValue.toUpperCase()).matches() && !htmlPattern.matcher(stringValue.toUpperCase()).matches();
		} else {
			if (value instanceof Collection<?>) {
				Collection<?> collection = (Collection<?>) value;
				for (Object element : collection) {
					if (!isValidString(element)) {
						return false;
					}
				}
				return true;
			}
		}
		return false; // Non-string values are considered invalid}
	}
	

}
