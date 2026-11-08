package models;

import utils.ValidationUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс, представляющий сущность "Поставщик автозапчастей" (Supplier).
 * Выполнен пункт 6: перегрузка конструкторов для нетривиальных примеров (строка CSV, JSON).
 */
public class Supplier {

    private int id;
    private String name;
    private String inn;
    private String ogrn;
    private String phone;
    private String email;
    private String address;
    private String contactPerson;

    // Регулярные выражения (константы) для валидации
    private static final String INN_REGEX = "^\\d{10}$|^\\d{12}$";
    private static final String OGRN_REGEX = "^\\d{13}$|^\\d{15}$";
    private static final String PHONE_REGEX = "^(\\+7|8)[\\s\\-]?\\(?\\d{3}\\)?[\\s\\-]?\\d{3}[\\s\\-]?\\d{2}[\\s\\-]?\\d{2}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String CONTACT_PERSON_REGEX = "^[А-Яа-яЁёA-Za-z\\s\\-]{3,150}$";

    /**
     * Конструктор 1 (полный): инициализация объекта через явную передачу всех аргументов.
     */
    public Supplier(int id, String name, String inn, String ogrn,
                    String phone, String email, String address, String contactPerson) {
        setId(id);
        setName(name);
        setInn(inn);
        setOgrn(ogrn);
        setPhone(phone);
        setEmail(email);
        setAddress(address);
        setContactPerson(contactPerson);
    }

    /**
     * Конструктор 2 (перегрузка для нетривиальных форматов):
     * Создает объект из строки данных (автоматически определяет CSV или JSON формат).
     *
     * @param data строка данных в формате CSV (разделитель ';') или JSON
     */
    public Supplier(String data) {
        if (data == null || data.trim().isEmpty()) {
            throw new IllegalArgumentException("Ошибка: переданная строка данных не может быть пустой.");
        }
        String trimmed = data.trim();
        if (trimmed.startsWith("{") && trimmed.endsWith("}")) {
            initFromJson(trimmed);
        } else {
            initFromCsv(trimmed);
        }
    }

    // --- Вспомогательные методы парсинга для перегруженных конструкторов ---

    private void initFromCsv(String csvLine) {
        // Разделяем по символу точки с запятой
        String[] parts = csvLine.split(";");
        if (parts.length != 8) {
            throw new IllegalArgumentException("Ошибка CSV: ожидалось 8 полей через ';', получено: " + parts.length);
        }
        try {
            setId(Integer.parseInt(parts[0].trim()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ошибка CSV: не удалось преобразовать ID в число: " + parts[0]);
        }
        setName(parts[1].trim());
        setInn(parts[2].trim());
        setOgrn(parts[3].trim());
        setPhone(parts[4].trim());
        setEmail(parts[5].trim());
        setAddress(parts[6].trim());
        setContactPerson(parts[7].trim());
    }

    private void initFromJson(String json) {
        // Простой универсальный парсер JSON без внешних библиотек
        Map<String, String> map = parseSimpleJson(json);

        if (!map.containsKey("id") || !map.containsKey("name") || !map.containsKey("inn")
                || !map.containsKey("ogrn") || !map.containsKey("phone") || !map.containsKey("email")
                || !map.containsKey("address") || !map.containsKey("contactPerson")) {
            throw new IllegalArgumentException("Ошибка JSON: отсутствуют обязательные ключи объекта Supplier.");
        }

        try {
            setId(Integer.parseInt(map.get("id")));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ошибка JSON: некорректный ID: " + map.get("id"));
        }
        setName(map.get("name"));
        setInn(map.get("inn"));
        setOgrn(map.get("ogrn"));
        setPhone(map.get("phone"));
        setEmail(map.get("email"));
        setAddress(map.get("address"));
        setContactPerson(map.get("contactPerson"));
    }

    private static Map<String, String> parseSimpleJson(String json) {
        Map<String, String> map = new HashMap<>();
        Pattern pattern = Pattern.compile("\"([^\"]+)\"\\s*:\\s*(?:\"([^\"]*)\"|([0-9]+))");
        Matcher matcher = pattern.matcher(json);
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2) != null ? matcher.group(2) : matcher.group(3);
            map.put(key, value);
        }
        return map;
    }

    // =============================================================
    // СТАТИЧЕСКИЕ МЕТОДЫ ВАЛИДАЦИИ
    // =============================================================

    public static boolean validateId(int id) {
        return id > 0;
    }

    public static boolean validateName(String name) {
        return ValidationUtils.checkLength(name, 2, 255);
    }

    public static boolean validateInn(String inn) {
        return ValidationUtils.matchesRegex(inn, INN_REGEX);
    }

    public static boolean validateOgrn(String ogrn) {
        return ValidationUtils.matchesRegex(ogrn, OGRN_REGEX);
    }

    public static boolean validatePhone(String phone) {
        return ValidationUtils.matchesRegex(phone, PHONE_REGEX);
    }

    public static boolean validateEmail(String email) {
        return ValidationUtils.matchesRegex(email, EMAIL_REGEX);
    }

    public static boolean validateAddress(String address) {
        return ValidationUtils.checkLength(address, 5, 300);
    }

    public static boolean validateContactPerson(String contactPerson) {
        return ValidationUtils.matchesRegex(contactPerson, CONTACT_PERSON_REGEX);
    }

    // =============================================================
    // ГЕТТЕРЫ И СЕТТЕРЫ
    // =============================================================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (!validateId(id)) {
            throw new IllegalArgumentException("Ошибка: ID поставщика должен быть положительным числом (> 0). Получено: " + id);
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!validateName(name)) {
            throw new IllegalArgumentException("Ошибка: некорректное наименование поставщика (длина от 2 до 255 символов).");
        }
        this.name = name.trim();
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

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        if (!validateOgrn(ogrn)) {
            throw new IllegalArgumentException("Ошибка: некорректный ОГРН (должен содержать 13 или 15 цифр). Получено: " + ogrn);
        }
        this.ogrn = ogrn.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (!validatePhone(phone)) {
            throw new IllegalArgumentException("Ошибка: некорректный формат номера телефона. Получено: " + phone);
        }
        this.phone = phone.trim();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (!validateAddress(address)) {
            throw new IllegalArgumentException("Ошибка: некорректный адрес (длина не менее 5 символов).");
        }
        this.address = address.trim();
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
