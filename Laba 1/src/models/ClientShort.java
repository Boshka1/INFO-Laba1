package models;

import utils.ValidationUtils;

import java.util.Objects;

/**
 * Базовый класс, содержащий краткую версию данных сущности "Клиент" (ClientShort).
 * Выполнены пункты 8 и 9: содержит базовые общие поля, их валидацию, геттеры/сеттеры
 * и служит суперклассом для полного Client.
 */
public class ClientShort {

    // Защищенные поля доступны в классе-наследнике Client
    protected int id;
    protected String name;
    protected String activityType;
    protected String phone;

    private static final String PHONE_REGEX = "^(\\+7|8)[\\s\\-]?\\(?\\d{3}\\)?[\\s\\-]?\\d{3}[\\s\\-]?\\d{2}[\\s\\-]?\\d{2}$";

    /**
     * Конструктор 1: инициализация краткой версии по ключевым полям.
     */
    public ClientShort(int id, String name, String activityType, String phone) {
        setId(id);
        setName(name);
        setActivityType(activityType);
        setPhone(phone);
    }

    /**
     * Конструктор 2 (копирующий): создание краткой версии на основе другого объекта ClientShort
     * или его наследника Client (благодаря полиморфизму).
     */
    public ClientShort(ClientShort other) {
        if (other == null) {
            throw new IllegalArgumentException("Ошибка: объект клиента не может быть null.");
        }
        setId(other.getId());
        setName(other.getName());
        setActivityType(other.getActivityType());
        setPhone(other.getPhone());
    }

    /**
     * Конструктор 3: создание из краткой строки CSV ("id;name;activityType;phone").
     */
    public ClientShort(String shortCsv) {
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
        setActivityType(parts[2].trim());
        setPhone(parts[3].trim());
    }

    // --- Валидация общих полей ---

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

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        if (!validateActivityType(activityType)) {
            throw new IllegalArgumentException("Ошибка: некорректный вид деятельности (2-150 символов).");
        }
        this.activityType = activityType.trim();
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
        return String.format("ClientShort [#%d]: %s | %s | Тел: %s", id, name, activityType, phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientShort that = (ClientShort) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
