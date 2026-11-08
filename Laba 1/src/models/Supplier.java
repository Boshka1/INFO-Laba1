package models;

import utils.ValidationUtils;

/**
 * Класс, представляющий сущность "Поставщик автозапчастей" (Supplier).
 * Выполнен пункт 5: устранение дублирования кода валидации (делегирование в ValidationUtils).
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
     * Полный конструктор для инициализации всех полей сущности.
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

    // =============================================================
    // СТАТИЧЕСКИЕ МЕТОДЫ ВАЛИДАЦИИ (БЕЗ ДУБЛИРОВАНИЯ КОДА)
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
    // ГЕТТЕРЫ И СЕТТЕРЫ С ВАЛИДАЦИЕЙ
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
