import models.Client;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа №1 (ПИС) ===");
        System.out.println("Вариант 7: Нотариальная контора");
        System.out.println("Студент: Дражин Н.О.\n");

        Client c1 = new Client(
                1,
                "ООО \"СтройИнвест\"",
                "Строительство",
                "+7 (999) 123-45-67",
                "г. Краснодар, ул. Красная, д. 120",
                "2310123456",
                "info@stroyinvest.ru",
                "Иванов Иван Иванович"
        );
        System.out.println("Полный конструктор: " + c1.getName() + " (ID: " + c1.getId() + ")");

        String csv = "2;АО \"КубаньАгро\";Сельское хозяйство;+7 (861) 222-33-44;г. Краснодар, ул. Северная, д. 45;2311987654;agro@kuban.ru;Сидоров Алексей";
        Client c2 = new Client(csv);
        System.out.println("CSV конструктор: " + c2.getName() + " (ИНН: " + c2.getInn() + ")");

        String json = "{"
                + "\"id\": 3,"
                + "\"name\": \"ООО \\\"АйтиСофт\\\"\","
                + "\"activityType\": \"Разработка ПО\","
                + "\"phone\": \"88005553535\","
                + "\"address\": \"г. Москва, Варшавское шоссе, д. 1\","
                + "\"inn\": \"7701987654\","
                + "\"email\": \"support@itsoft.ru\","
                + "\"contactPerson\": \"Васильев Николай\""
                + "}";
        Client c3 = new Client(json);
        System.out.println("JSON конструктор: " + c3.getName() + " (Email: " + c3.getEmail() + ")");
    }
}
