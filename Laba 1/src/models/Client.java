package models;

/**
 * Класс, представляющий сущность "Клиент нотариальной конторы" (Client).
 * Выполнен пункт 3: построение полного класса с инкапсуляцией ВСЕХ полей.
 */
public class Client {

    // -------------------------------------------------------------
    // ИНКАПСУЛЯЦИЯ: Все поля имеют модификатор доступа private.
    // Прямой доступ к ним из других классов запрещен.
    // Чтение и запись возможны только через публичные геттеры и сеттеры.
    // -------------------------------------------------------------
    private int id;
    private String name;
    private String activityType;
    private String phone;
    private String address;
    private String inn;
    private String email;
    private String contactPerson;

    /**
     * Полный конструктор для инициализации всех полей сущности.
     * Значения устанавливаются через сеттеры для соблюдения единой точки доступа.
     */
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

    // --- ГЕТТЕРЫ И СЕТТЕРЫ (МЕТОДЫ ДОСТУПА) ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
}
