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
     *
     * @param value проверяемая строка
     * @return true, если строка пуста или null; false в противном случае
     */
    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * Проверяет, соответствует ли строка заданному регулярному выражению.
     * Если строка null или пуста, возвращает false.
     *
     * @param value проверяемая строка
     * @param regex регулярное выражение
     * @return true, если значение соответствует шаблону
     */
    public static boolean matchesRegex(String value, String regex) {
        if (isBlank(value)) {
            return false;
        }
        return Pattern.matches(regex, value.trim());
    }

    /**
     * Проверяет длину строки в заданном диапазоне [min, max] (с предварительным trim).
     *
     * @param value проверяемая строка
     * @param min минимально допустимая длина
     * @param max максимально допустимая длина
     * @return true, если длина строки в пределах диапазона
     */
    public static boolean checkLength(String value, int min, int max) {
        if (isBlank(value)) {
            return false;
        }
        int length = value.trim().length();
        return length >= min && length <= max;
    }
}
