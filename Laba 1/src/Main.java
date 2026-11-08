import models.Supplier;

/**
 * Главный класс для демонстрации работы с сущностью Supplier.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа №1 (ПИС) ===");
        System.out.println("Вариант 8: Фирма по продаже запчастей");
        System.out.println("Студент: Дражин Н.О.\n");

        // 1. Создание через базовый полный конструктор
        Supplier s1 = new Supplier(
                1,
                "ООО \"Авто-Лидер\"",
                "7701234567",
                "1027700132195",
                "+7 (999) 123-45-67",
                "sales@auto-leader.ru",
                "г. Москва, ул. Автомобильная, д. 10",
                "Иванов Иван Иванович"
        );
        System.out.println("1. Создан через полный конструктор: " + s1.getName() + " (ID: " + s1.getId() + ")");

        // 2. Создание через перегруженный конструктор из строки CSV
        String csv = "2;АО \"ДетальКомплект\";2310123456;1032304152637;+7 (861) 222-33-44;info@detal.ru;г. Краснодар, ул. Северная, д. 45;Сидоров Алексей";
        Supplier s2 = new Supplier(csv);
        System.out.println("2. Создан через перегрузку (CSV строка): " + s2.getName() + " (ИНН: " + s2.getInn() + ")");

        // 3. Создание через перегруженный конструктор из строки JSON
        String json = "{"
                + "\"id\": 3,"
                + "\"name\": \"ООО \\\"МоторПлюс\\\"\","
                + "\"inn\": \"7801987654\","
                + "\"ogrn\": \"1057812345678\","
                + "\"phone\": \"88005553535\","
                + "\"email\": \"support@motorplus.ru\","
                + "\"address\": \"г. Санкт-Петербург, Московский пр., д. 100\","
                + "\"contactPerson\": \"Васильев Николай\""
                + "}";
        Supplier s3 = new Supplier(json);
        System.out.println("3. Создан через перегрузку (JSON строка): " + s3.getName() + " (Email: " + s3.getEmail() + ")");
    }
}
