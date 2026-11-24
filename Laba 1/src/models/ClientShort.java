package models;

import utils.ValidationUtils;

import java.util.Objects;

public class ClientShort {

    private int id;
    private String name;
    private String activityType;
    private String phone;

    private static final String PHONE_REGEX = "^(\\+7|8)[\\s\\-]?\\(?\\d{3}\\)?[\\s\\-]?\\d{3}[\\s\\-]?\\d{2}[\\s\\-]?\\d{2}$";

    public ClientShort(int id, String name, String activityType, String phone) {
        setId(id);
        setName(name);
        setActivityType(activityType);
        setPhone(phone);
    }

    public ClientShort(ClientShort other) {
        this(requireNonNullClient(other).getId(),
                other.getName(),
                other.getActivityType(),
                other.getPhone());
    }

    public ClientShort(String shortCsv) {
        this(parseShortCsv(shortCsv));
    }

    private ClientShort(String[] parts) {
        this(parseId(parts[0]), parts[1], parts[2], parts[3]);
    }

    private static ClientShort requireNonNullClient(ClientShort other) {
        if (other == null) {
            throw new IllegalArgumentException("Ошибка: объект клиента не может быть null.");
        }
        return other;
    }

    private static String[] parseShortCsv(String shortCsv) {
        if (ValidationUtils.isBlank(shortCsv)) {
            throw new IllegalArgumentException("Ошибка: строка не может быть пустой.");
        }
        String[] parts = shortCsv.trim().split(";", -1);
        if (parts.length != 4) {
            throw new IllegalArgumentException("Ошибка: ожидалось 4 поля через ';', получено: " + parts.length);
        }
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        return parts;
    }

    protected static int parseId(String rawId) {
        if (ValidationUtils.isBlank(rawId)) {
            throw new IllegalArgumentException("Ошибка: ID не может быть пустым.");
        }
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
