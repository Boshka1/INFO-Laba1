package models;

import utils.ValidationUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client extends ClientShort {

    private String address;
    private String inn;
    private String email;
    private String contactPerson;

    private static final String INN_REGEX = "^\\d{10}$|^\\d{12}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String CONTACT_PERSON_REGEX = "^[А-Яа-яЁёA-Za-z\\s\\-]{3,150}$";
    private static final Pattern JSON_FIELD_PATTERN = Pattern.compile(
            "\"([^\"]+)\"\\s*:\\s*(?:\"((?:\\\\.|[^\"\\\\])*)\"|(-?\\d+))"
    );

    public Client(int id, String name, String activityType, String phone,
                  String address, String inn, String email, String contactPerson) {
        super(id, name, activityType, phone);
        setAddress(address);
        setInn(inn);
        setEmail(email);
        setContactPerson(contactPerson);
    }

    public Client(ClientShort clientShort, String address, String inn, String email, String contactPerson) {
        this(requireClientShort(clientShort).getId(),
                clientShort.getName(),
                clientShort.getActivityType(),
                clientShort.getPhone(),
                address, inn, email, contactPerson);
    }

    public Client(String data) {
        this(parseData(data));
    }

    private Client(String[] fields) {
        super(parseId(fields[0]), fields[1], fields[2], fields[3]);
        setAddress(fields[4]);
        setInn(fields[5]);
        setEmail(fields[6]);
        setContactPerson(fields[7]);
    }

    private static ClientShort requireClientShort(ClientShort clientShort) {
        if (clientShort == null) {
            throw new IllegalArgumentException("Ошибка: краткий объект клиента не может быть null.");
        }
        return clientShort;
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

    @Override
    public String toString() {
        return "=== Клиент [ID: " + getId() + "] ===\n" +
                "  Название        : " + getName() + "\n" +
                "  Вид деятельности: " + getActivityType() + "\n" +
                "  Телефон         : " + getPhone() + "\n" +
                "  Адрес           : " + address + "\n" +
                "  ИНН             : " + inn + "\n" +
                "  Email           : " + email + "\n" +
                "  Контактное лицо : " + contactPerson;
    }

    public String toShortString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(inn, client.inn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), inn);
    }
}
