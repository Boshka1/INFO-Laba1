package models;

/**
 * Класс, представляющий сущность "Поставщик автозапчастей" (Supplier).
 * Выполнен пункт 3: построение полного класса с инкапсуляцией ВСЕХ полей.
 */
public class Supplier {

    // -------------------------------------------------------------
    // ИНКАПСУЛЯЦИЯ: Все поля имеют модификатор доступа private.
    // Прямой доступ к ним из других классов запрещен.
    // Чтение и запись возможны только через публичные геттеры и сеттеры.
    // -------------------------------------------------------------
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
     * Значения устанавливаются через сеттеры для соблюдения единой точки доступа.
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

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
}
