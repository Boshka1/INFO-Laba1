package utils;

import java.util.regex.Pattern;

public final class ValidationUtils {

    private ValidationUtils() {
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean matchesRegex(String value, String regex) {
        if (isBlank(value)) {
            return false;
        }
        return Pattern.matches(regex, value.trim());
    }

    public static boolean checkLength(String value, int min, int max) {
        if (isBlank(value)) {
            return false;
        }
        int length = value.trim().length();
        return length >= min && length <= max;
    }
}
