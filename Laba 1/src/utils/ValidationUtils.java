package utils;

import java.util.regex.Pattern;

/**
 * Вспомогательный утилитный класс для валидации данных.
 * Выполнен пункт 5: устранение дублирования кода из методов валидации.
 */
public final class ValidationUtils {

    // Приватный конструктор: запрещаем создание экземпляров утилитного класса
    private ValidationUtils() {
    }

    /**
     * Проверяет, является ли строка null или пустой (включая только пробелы).
     */
    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * Проверяет, соответствует ли строка заданному регулярному выражению.
     */
    public static boolean matchesRegex(String value, String regex) {
        if (isBlank(value)) {
            return false;
        }
        return Pattern.matches(regex, value.trim());
    }

    /**
     * Проверяет длину строки в заданном диапазоне [min, max] (с предварительным trim).
     */
    public static boolean checkLength(String value, int min, int max) {
        if (isBlank(value)) {
            return false;
        }
        int length = value.trim().length();
        return length >= min && length <= max;
    }
}
