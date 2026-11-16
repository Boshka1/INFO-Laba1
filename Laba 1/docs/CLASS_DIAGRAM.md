# Лабораторная работа №1. Пункт 10: Диаграмма классов UML

## Диаграмма классов предметной области (Вариант 7: Нотариальная контора)

Ниже представлена полная диаграмма классов, отображающая структуру созданных классов, модификаторы доступа (`+` public, `-` private, `#` protected), типы данных, методы валидации, перегруженные конструкторы и связь наследования.

---

### 1. UML Диаграмма (Mermaid)

```mermaid
classDiagram
    class ValidationUtils {
        <<utility>>
        -ValidationUtils()
        +isBlank(String value)$ boolean
        +matchesRegex(String value, String regex)$ boolean
        +checkLength(String value, int min, int max)$ boolean
    }

    class ClientShort {
        #int id
        #String name
        #String activityType
        #String phone
        -String PHONE_REGEX$
        +ClientShort(int id, String name, String activityType, String phone)
        +ClientShort(ClientShort other)
        +ClientShort(String shortCsv)
        +validateId(int id)$ boolean
        +validateName(String name)$ boolean
        +validateActivityType(String activityType)$ boolean
        +validatePhone(String phone)$ boolean
        +getId() int
        +setId(int id) void
        +getName() String
        +setName(String name) void
        +getActivityType() String
        +setActivityType(String activityType) void
        +getPhone() String
        +setPhone(String phone) void
        +toString() String
        +equals(Object o) boolean
        +hashCode() int
    }

    class Client {
        -String address
        -String inn
        -String email
        -String contactPerson
        -String INN_REGEX$
        -String EMAIL_REGEX$
        -String CONTACT_PERSON_REGEX$
        +Client(int id, String name, String activityType, String phone, String address, String inn, String email, String contactPerson)
        +Client(ClientShort clientShort, String address, String inn, String email, String contactPerson)
        +Client(String data)
        -initFromCsv(String csvLine) void
        -initFromJson(String json) void
        -parseSimpleJson(String json)$ Map
        +validateAddress(String address)$ boolean
        +validateInn(String inn)$ boolean
        +validateEmail(String email)$ boolean
        +validateContactPerson(String contactPerson)$ boolean
        +getAddress() String
        +setAddress(String address) void
        +getInn() String
        +setInn(String inn) void
        +getEmail() String
        +setEmail(String email) void
        +getContactPerson() String
        +setContactPerson(String contactPerson) void
        +toString() String
        +toShortString() String
        +equals(Object o) boolean
        +hashCode() int
    }

    Client --|> ClientShort : Наследование (extends)
    Client ..> ValidationUtils : Зависимость (использование)
    ClientShort ..> ValidationUtils : Зависимость (использование)
```

---

### 2. Описание элементов диаграммы

1. **`ClientShort` (Базовый класс / Краткая модель):**
   * Хранит ключевые идентифицирующие атрибуты клиента (`id`, `name`, `activityType`, `phone`).
   * Поля объявлены с модификатором `#` (`protected`), что делает их доступными в классе-наследнике без нарушения инкапсуляции от внешнего мира.
   * Содержит статические методы валидации базовых полей.
   * Предоставляет перегруженные конструкторы: базовый, копирующий (из другого `ClientShort` или `Client`) и из краткой строки CSV.

2. **`Client` (Класс-наследник / Полная модель):**
   * Наследует `id`, `name`, `activityType`, `phone` и их геттеры/сеттеры/валидаторы от `ClientShort`.
   * Добавляет приватные поля (`-` `private`): `address`, `inn`, `email`, `contactPerson`.
   * В конструкторах вызывает `super(...)` для бесконфликтной и единой инициализации базовой части.
   * Реализует нетривиальные перегрузки конструктора для парсинга полного CSV и JSON.
   * Переопределяет `toString()` (полный отчет), `toShortString()` (делегирование в суперкласс) и `equals`/`hashCode`.

3. **`ValidationUtils` (Утилитный класс):**
   * Имеет приватный конструктор, запрещающий создание объектов.
   * Содержит статические методы (`$`), устраняющие дублирование логики регулярных выражений и проверки строк в классах предметной области.
