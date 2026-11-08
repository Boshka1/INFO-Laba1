package models;

import utils.ValidationUtils;

import java.util.Objects;

/**
 * Класс, содержащий краткую версию данных сущности "Поставщик" (SupplierShort).
 * Выполнен пункт 8: отдельный легковесный класс (ID, Название, ИНН, один контактный телефон)
 * без адреса, email и контактных лиц.
 */
public class SupplierShort {

    protected int id;
    protected String name;
    protected String inn;
    protected String phone;

    private static final String INN_REGEX = "^\\d{10}$|^\\d{12}$";
    private static final String PHONE_REGEX = "^(\\+7|8)[\\s\\-]?\\(?\\d{3}\\)?[\\s\\-]?\\d{3}[\\s\\-]?\\d{2}[\\s\\-]?\\d{2}$";

    /**
     * Конструктор 1: инициализация краткой версии по ключевым полям.
     */
    public SupplierShort(int id, String name, String inn, String phone) {
        setId(id);
        setName(name);
        setInn(inn);
        setPhone(phone);
    }

    /**
     * Конструктор 2: создание краткой версии на основе полного объекта Supplier.
     * Позволяет удобно проецировать полный объект в легковесную модель.
     */
    public SupplierShort(Supplier supplier) {
        if (supplier == null) {
            throw new IllegalArgumentException("Ошибка: объект поставщика не может быть null.");
        }
        setId(supplier.getId());
        setName(supplier.getName());
        setInn(supplier.getInn());
        setPhone(supplier.getPhone());
    }

    /**
     * Конструктор 3: создание из краткой строки CSV ("id;name;inn;phone").
     */
    public SupplierShort(String shortCsv) {
        if (shortCsv == null || shortCsv.trim().isEmpty()) {
            throw new IllegalArgumentException("Ошибка: строка не может быть пустой.");
        }
        String[] parts = shortCsv.split(";");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Ошибка: ожидалось 4 поля через ';', получено: " + parts.length);
        }
        try {
            setId(Integer.parseInt(parts[0].trim()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ошибка: некорректный ID: " + parts[0]);
        }
        setName(parts[1].trim());
        setInn(parts[2].trim());
        setPhone(parts[3].trim());
    }

    // --- Валидация общих полей ---

    public static boolean validateId(int id) {
        return id > 0;
    }

    public static boolean validateName(String name) {
        return ValidationUtils.checkLength(name, 2, 255);
    }

    public static boolean validateInn(String inn) {
        return ValidationUtils.matchesRegex(inn, INN_REGEX);
    }

    public static boolean validatePhone(String phone) {
        return ValidationUtils.matchesRegex(phone, PHONE_REGEX);
    }

    // --- Геттеры и сеттеры ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (!validateId(id)) {
            throw new IllegalArgumentException("Ошибка: ID должен быть > 0. Получено: " + id);
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!validateName(name)) {
            throw new IllegalArgumentException("Ошибка: некорректное имя (2-255 символов).");
        }
        this.name = name.trim();
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        if (!validateInn(inn)) {
            throw new IllegalArgumentException("Ошибка: некорректный ИНН (10 или 12 цифр). Получено: " + inn);
        }
        this.inn = inn.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (!validatePhone(phone)) {
            throw new IllegalArgumentException("Ошибка: некорректный телефон. Получено: " + phone);
        }
        this.phone = phone.trim();
    }

    // --- Строковое представление и сравнение ---

    @Override
    public String toString() {
        return String.format("SupplierShort [#%d]: %s | ИНН: %s | Тел: %s", id, name, inn, phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierShort that = (SupplierShort) o;
        return id == that.id && Objects.equals(inn, that.inn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, inn);
    }
}
