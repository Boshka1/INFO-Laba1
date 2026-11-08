package models;

import java.util.regex.Pattern;

/**
 * Класс, представляющий сущность "Поставщик автозапчастей" (Supplier).
 * Выполнен пункт 4: статические методы валидации всех необходимых полей.
 * Существование объекта с невалидными данными невозможно (бросаются исключения).
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

    /**
     * Полный конструктор для инициализации всех полей сущности.
     * При передаче любого невалидного поля выбрасывается IllegalArgumentException.
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
    // СТАТИЧЕСКИЕ МЕТОДЫ ВАЛИДАЦИИ (МЕТОДЫ КЛАССА)
    // =============================================================

    public static boolean validateId(int id) {
        return id > 0;
    }

    public static boolean validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return name.trim().length() >= 2 && name.trim().length() <= 255;
    }

    public static boolean validateInn(String inn) {
        if (inn == null || inn.trim().isEmpty()) {
            return false;
        }
        // ИНН юрлица состоит из 10 цифр, ИНН ИП из 12 цифр
        return Pattern.matches("^\\d{10}$|^\\d{12}$", inn.trim());
    }

    public static boolean validateOgrn(String ogrn) {
        if (ogrn == null || ogrn.trim().isEmpty()) {
            return false;
        }
        // ОГРН юрлица 13 цифр, ОГРНИП 15 цифр
        return Pattern.matches("^\\d{13}$|^\\d{15}$", ogrn.trim());
    }

    public static boolean validatePhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }
        // Телефон формата РФ: +7/8 с кодом и разделителями или 11 цифр
        String regex = "^(\\+7|8)[\\s\\-]?\\(?\\d{3}\\)?[\\s\\-]?\\d{3}[\\s\\-]?\\d{2}[\\s\\-]?\\d{2}$";
        return Pattern.matches(regex, phone.trim());
    }

    public static boolean validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return Pattern.matches(regex, email.trim());
    }

    public static boolean validateAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            return false;
        }
        return address.trim().length() >= 5 && address.trim().length() <= 300;
    }

    public static boolean validateContactPerson(String contactPerson) {
        if (contactPerson == null || contactPerson.trim().isEmpty()) {
            return false;
        }
        // ФИО на кириллице или латинице, дефис для двойных фамилий
        String regex = "^[А-Яа-яЁёA-Za-z\\s\\-]+$";
        return Pattern.matches(regex, contactPerson.trim()) && contactPerson.trim().length() >= 3;
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
