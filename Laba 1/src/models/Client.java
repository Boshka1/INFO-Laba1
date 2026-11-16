package models;

import utils.ValidationUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс, представляющий полную сущность "Клиент нотариальной конторы" (Client).
 * Выполнен пункт 9: объединение Client и ClientShort в единую иерархию наследования.
 * Client наследуется от ClientShort (extends), устраняя повтор кода:
 * общие поля (id, name, activityType, phone), их валидация и методы доступа вынесены в базовый класс.
 */
public class Client extends ClientShort {

    // Специфичные поля полного класса (наследуемые id, name, activityType, phone объявлены в предке)
    private String address;
    private String inn;
    private String email;
    private String contactPerson;

    // Регулярные выражения для валидации специфичных полей
    private static final String INN_REGEX = "^\\d{10}$|^\\d{12}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String CONTACT_PERSON_REGEX = "^[А-Яа-яЁёA-Za-z\\s\\-]{3,150}$";

    /**
     * Конструктор 1 (полный):
     * Вызывает конструктор суперкласса super(id, name, activityType, phone) для инициализации общих полей,
     * исключая дублирование кода инициализации.
     */
    public Client(int id, String name, String activityType, String phone,
                  String address, String inn, String email, String contactPerson) {
        super(id, name, activityType, phone);
        setAddress(address);
        setInn(inn);
        setEmail(email);
        setContactPerson(contactPerson);
    }

    /**
     * Конструктор 2: создание на основе краткой версии ClientShort и дополнительных данных.
     */
    public Client(ClientShort clientShort, String address, String inn, String email, String contactPerson) {
        this(clientShort.getId(), clientShort.getName(), clientShort.getActivityType(), clientShort.getPhone(),
                address, inn, email, contactPerson);
    }

    /**
     * Конструктор 3 (перегрузка для нетривиальных форматов):
     * Создает объект из строки данных (автоматически определяет CSV или JSON формат).
     */
    public Client(String data) {
        super(1, "Временное имя", "Деятельность", "+79990000000"); // вызов super для компилятора
        if (data == null || data.trim().isEmpty()) {
            throw new IllegalArgumentException("Ошибка: строка данных не может быть пустой.");
        }
        String trimmed = data.trim();
        if (trimmed.startsWith("{") && trimmed.endsWith("}")) {
            initFromJson(trimmed);
        } else {
            initFromCsv(trimmed);
        }
    }

    // --- Парсинг данных ---

    private void initFromCsv(String csvLine) {
        String[] parts = csvLine.split(";");
        if (parts.length != 8) {
            throw new IllegalArgumentException("Ошибка CSV: ожидалось 8 полей через ';', получено: " + parts.length);
        }
        try {
            setId(Integer.parseInt(parts[0].trim()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ошибка CSV: некорректный ID: " + parts[0]);
        }
        setName(parts[1].trim());
        setActivityType(parts[2].trim());
        setPhone(parts[3].trim());
        setAddress(parts[4].trim());
        setInn(parts[5].trim());
        setEmail(parts[6].trim());
        setContactPerson(parts[7].trim());
    }

    private void initFromJson(String json) {
        Map<String, String> map = parseSimpleJson(json);
        if (!map.containsKey("id") || !map.containsKey("name") || !map.containsKey("activityType")
                || !map.containsKey("phone") || !map.containsKey("address") || !map.containsKey("inn")
                || !map.containsKey("email") || !map.containsKey("contactPerson")) {
            throw new IllegalArgumentException("Ошибка JSON: отсутствуют обязательные ключи объекта Client.");
        }
        try {
            setId(Integer.parseInt(map.get("id")));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ошибка JSON: некорректный ID: " + map.get("id"));
        }
        setName(map.get("name"));
        setActivityType(map.get("activityType"));
        setPhone(map.get("phone"));
        setAddress(map.get("address"));
        setInn(map.get("inn"));
        setEmail(map.get("email"));
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
    // СТАТИЧЕСКИЕ МЕТОДЫ ВАЛИДАЦИИ СПЕЦИФИЧНЫХ ПОЛЕЙ
    // (методы validateId, validateName, validateActivityType, validatePhone унаследованы из ClientShort)
    // =============================================================

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

    // =============================================================
    // ГЕТТЕРЫ И СЕТТЕРЫ СПЕЦИФИЧНЫХ ПОЛЕЙ
    // =============================================================

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (!validateAddress(address)) {
            throw new IllegalArgumentException("Ошибка: некорректный адрес (длина не менее 5 символов).");
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

    // =============================================================
    // ПРЕДСТАВЛЕНИЕ ОБЪЕКТА И СРАВНЕНИЕ
    // =============================================================

    /**
     * Полная версия представления объекта.
     */
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

    /**
     * Краткая версия представления: использует метод суперкласса.
     */
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
