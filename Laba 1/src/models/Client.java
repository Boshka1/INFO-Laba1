package models;

import utils.ValidationUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {

    private int id;
    private String name;
    private String activityType;
    private String phone;
    private String address;
    private String inn;
    private String email;
    private String contactPerson;

    private static final String PHONE_REGEX = "^(\\+7|8)[\\s\\-]?\\(?\\d{3}\\)?[\\s\\-]?\\d{3}[\\s\\-]?\\d{2}[\\s\\-]?\\d{2}$";
    private static final String INN_REGEX = "^\\d{10}$|^\\d{12}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String CONTACT_PERSON_REGEX = "^[А-Яа-яЁёA-Za-z\\s\\-]{3,150}$";
    private static final Pattern JSON_FIELD_PATTERN = Pattern.compile(
            "\"([^\"]+)\"\\s*:\\s*(?:\"((?:\\\\.|[^\"\\\\])*)\"|(-?\\d+))"
    );

    public Client(int id, String name, String activityType, String phone,
                  String address, String inn, String email, String contactPerson) {
        setId(id);
        setName(name);
        setActivityType(activityType);
        setPhone(phone);
        setAddress(address);
        setInn(inn);
        setEmail(email);
        setContactPerson(contactPerson);
    }

    public Client(String data) {
        this(parseData(data));
    }

    private Client(String[] fields) {
        this(parseId(fields[0]), fields[1], fields[2], fields[3],
                fields[4], fields[5], fields[6], fields[7]);
    }

    private static String[] parseData(String data) {
        if (ValidationUtils.isBlank(data)) {
            throw new IllegalArgumentException("Ошибка: строка данных не может быть пустой.");
        }
        String trimmed = data.trim();
        if (trimmed.startsWith("{") && trimmed.endsWith("}")) {
            return parseJson(trimmed);
        }
        return parseCsv(trimmed);
    }

    private static String[] parseCsv(String csvLine) {
        String[] parts = csvLine.split(";", -1);
        if (parts.length != 8) {
            throw new IllegalArgumentException("Ошибка CSV: ожидалось 8 полей через ';', получено: " + parts.length);
        }
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        return parts;
    }

    private static String[] parseJson(String json) {
        Map<String, String> map = parseSimpleJson(json);
        String[] requiredKeys = {"id", "name", "activityType", "phone", "address", "inn", "email", "contactPerson"};
        String[] values = new String[requiredKeys.length];
        for (int i = 0; i < requiredKeys.length; i++) {
            String value = map.get(requiredKeys[i]);
            if (value == null) {
                throw new IllegalArgumentException("Ошибка JSON: отсутствует обязательный ключ '" + requiredKeys[i] + "'.");
            }
            values[i] = value.trim();
        }
        return values;
    }

    private static Map<String, String> parseSimpleJson(String json) {
        Map<String, String> map = new HashMap<>();
        Matcher matcher = JSON_FIELD_PATTERN.matcher(json);
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2) != null ? unescapeJson(matcher.group(2)) : matcher.group(3);
            map.put(key, value);
        }
        return map;
    }

    private static String unescapeJson(String value) {
        return value.replace("\\\"", "\"").replace("\\\\", "\\");
    }

    private static int parseId(String rawId) {
        try {
            return Integer.parseInt(rawId.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ошибка: некорректный ID: " + rawId);
        }
    }

    public static boolean validateId(int id) {
        return id > 0;
    }

    public static boolean validateName(String name) {
        return ValidationUtils.checkLength(name, 2, 255);
    }

    public static boolean validateActivityType(String activityType) {
        return ValidationUtils.checkLength(activityType, 2, 150);
    }

    public static boolean validatePhone(String phone) {
        return ValidationUtils.matchesRegex(phone, PHONE_REGEX);
    }

    public static boolean validateAddress(String address) {
        return ValidationUtils.checkLength(address, 5, 300);
    }

    public static boolean validateInn(String inn) {
        return ValidationUtils.matchesRegex(inn, INN_REGEX);
    }

    public static boolean validateEmail(String email) {
        return ValidationUtils.matchesRegex(email, EMAIL_REGEX);
    }

    public static boolean validateContactPerson(String contactPerson) {
        return ValidationUtils.matchesRegex(contactPerson, CONTACT_PERSON_REGEX);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (!validateId(id)) {
            throw new IllegalArgumentException("Ошибка: ID клиента должен быть положительным числом (> 0). Получено: " + id);
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!validateName(name)) {
            throw new IllegalArgumentException("Ошибка: некорректное наименование клиента (длина от 2 до 255 символов).");
        }
        this.name = name.trim();
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        if (!validateActivityType(activityType)) {
            throw new IllegalArgumentException("Ошибка: некорректный вид деятельности (от 2 до 150 символов).");
        }
        this.activityType = activityType.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (!validatePhone(phone)) {
            throw new IllegalArgumentException("Ошибка: некорректный номер телефона. Получено: " + phone);
        }
        this.phone = phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (!validateAddress(address)) {
            throw new IllegalArgumentException("Ошибка: некорректный адрес (длина от 5 до 300 символов).");
        }
        this.address = address.trim();
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        if (!validateInn(inn)) {
            throw new IllegalArgumentException("Ошибка: некорректный ИНН (должен содержать 10 или 12 цифр). Получено: " + inn);
        }
        this.inn = inn.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!validateEmail(email)) {
            throw new IllegalArgumentException("Ошибка: некорректный адрес электронной почты. Получено: " + email);
        }
        this.email = email.trim();
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        if (!validateContactPerson(contactPerson)) {
            throw new IllegalArgumentException("Ошибка: некорректное ФИО контактного лица. Получено: " + contactPerson);
        }
        this.contactPerson = contactPerson.trim();
    }
}
