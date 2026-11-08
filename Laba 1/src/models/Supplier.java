package models;

import utils.ValidationUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс, представляющий полную сущность "Поставщик автозапчастей" (Supplier).
 * Выполнен пункт 9: объединение Supplier и SupplierShort в единую иерархию наследования.
 * Supplier наследуется от SupplierShort (extends), устраняя повтор кода:
 * общие поля (id, name, inn, phone), их валидация и методы доступа вынесены в базовый класс.
 */
public class Supplier extends SupplierShort {

    // Специфичные поля полного класса (наследуемые id, name, inn, phone объявлены в предке)
    private String ogrn;
    private String email;
    private String address;
    private String contactPerson;

    // Регулярные выражения для валидации специфичных полей
    private static final String OGRN_REGEX = "^\\d{13}$|^\\d{15}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String CONTACT_PERSON_REGEX = "^[А-Яа-яЁёA-Za-z\\s\\-]{3,150}$";

    /**
     * Конструктор 1 (полный):
     * Вызывает конструктор суперкласса super(id, name, inn, phone) для инициализации общих полей,
     * исключая дублирование кода инициализации.
     */
    public Supplier(int id, String name, String inn, String ogrn,
                    String phone, String email, String address, String contactPerson) {
        super(id, name, inn, phone);
        setOgrn(ogrn);
        setEmail(email);
        setAddress(address);
        setContactPerson(contactPerson);
    }

    /**
     * Конструктор 2: создание на основе краткой версии SupplierShort и дополнительных данных.
     */
    public Supplier(SupplierShort supplierShort, String ogrn, String email, String address, String contactPerson) {
        this(supplierShort.getId(), supplierShort.getName(), supplierShort.getInn(), ogrn,
                supplierShort.getPhone(), email, address, contactPerson);
    }

    /**
     * Конструктор 3 (перегрузка для нетривиальных форматов):
     * Создает объект из строки данных (автоматически определяет CSV или JSON формат).
     */
    public Supplier(String data) {
        super(1, "Временное имя", "0000000000", "+79990000000"); // вызов super для компилятора
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
        setInn(parts[2].trim());
        setOgrn(parts[3].trim());
        setPhone(parts[4].trim());
        setEmail(parts[5].trim());
        setAddress(parts[6].trim());
        setContactPerson(parts[7].trim());
    }

    private void initFromJson(String json) {
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
    // СТАТИЧЕСКИЕ МЕТОДЫ ВАЛИДАЦИИ СПЕЦИФИЧНЫХ ПОЛЕЙ
    // (валидаторы id, name, inn, phone унаследованы из SupplierShort)
    // =============================================================

    public static boolean validateOgrn(String ogrn) {
        return ValidationUtils.matchesRegex(ogrn, OGRN_REGEX);
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
    // ГЕТТЕРЫ И СЕТТЕРЫ СПЕЦИФИЧНЫХ ПОЛЕЙ
    // =============================================================

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        if (!validateOgrn(ogrn)) {
            throw new IllegalArgumentException("Ошибка: некорректный ОГРН (13 или 15 цифр). Получено: " + ogrn);
        }
        this.ogrn = ogrn.trim();
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

    // =============================================================
    // ПРЕДСТАВЛЕНИЕ ОБЪЕКТА И СРАВНЕНИЕ
    // =============================================================

    /**
     * Полная версия представления объекта.
     */
    @Override
    public String toString() {
        return "=== Поставщик [ID: " + getId() + "] ===\n" +
                "  Название        : " + getName() + "\n" +
                "  ИНН             : " + getInn() + "\n" +
                "  ОГРН            : " + ogrn + "\n" +
                "  Телефон         : " + getPhone() + "\n" +
                "  Email           : " + email + "\n" +
                "  Адрес           : " + address + "\n" +
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
        Supplier supplier = (Supplier) o;
        return Objects.equals(ogrn, supplier.ogrn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ogrn);
    }
}
