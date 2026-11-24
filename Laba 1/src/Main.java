import models.Client;
import models.ClientShort;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа №1 (ПИС) ===");
        System.out.println("Вариант 7: Нотариальная контора");
        System.out.println("Студент: Дражин Н.О.\n");

        Client fullClient = new Client(
                1,
                "ООО \"СтройИнвест\"",
                "Строительство",
                "+7 (999) 123-45-67",
                "г. Краснодар, ул. Красная, д. 120",
                "2310123456",
                "info@stroyinvest.ru",
                "Иванов Иван Иванович"
        );

        ClientShort shortFromFull = new ClientShort(fullClient);

        Client extendedClient = new Client(
                shortFromFull,
                "г. Краснодар, ул. Северная, д. 10",
                "2310123456",
                "support@stroyinvest.ru",
                "Смирнова Анна"
        );

        Client csvClient = new Client(
                "2;АО \"КубаньАгро\";Сельское хозяйство;+7 (861) 222-33-44;" +
                        "г. Краснодар, ул. Северная, д. 45;2311987654;agro@kuban.ru;Сидоров Алексей"
        );
        Client jsonClient = new Client(
                "{\"id\": 3, \"name\": \"ООО \\\"АйтиСофт\\\"\", \"activityType\": \"Разработка ПО\", " +
                        "\"phone\": \"88005553535\", \"address\": \"г. Сочи, ул. Навагинская, д. 7\", " +
                        "\"inn\": \"2320555666\", \"email\": \"hello@itsoft.digital\", \"contactPerson\": \"Петров Пётр\"}"
        );

        System.out.println("1. ДЕМОНСТРАЦИЯ ИЕРАРХИИ НАСЛЕДОВАНИЯ:");
        System.out.println("   Класс-родитель : " + ClientShort.class.getSimpleName());
        System.out.println("   Класс-наследник: " + Client.class.getSimpleName());
        System.out.println("   Client является экземпляром ClientShort? " + (fullClient instanceof ClientShort));

        System.out.println("\n2. ПОЛНОЕ ПРЕДСТАВЛЕНИЕ ОБЪЕКТА:");
        System.out.println(fullClient);

        System.out.println("\n3. КРАТКОЕ ПРЕДСТАВЛЕНИЕ ОБЪЕКТА (toShortString):");
        System.out.println("   " + fullClient.toShortString());

        System.out.println("\n4. ПРЕДСТАВЛЕНИЕ ОБЪЕКТА CLIENTSHORT:");
        System.out.println("   " + shortFromFull);

        System.out.println("\n5. ОБЪЕКТЫ ИЗ CSV И JSON:");
        System.out.println("   CSV  -> " + csvClient.toShortString());
        System.out.println("   JSON -> " + jsonClient.toShortString());

        System.out.println("\n6. СРАВНЕНИЕ ОБЪЕКТОВ НА РАВЕНСТВО (equals и hashCode):");
        System.out.println("   fullClient.equals(extendedClient) : " + fullClient.equals(extendedClient));
        System.out.println("   fullClient.equals(csvClient)      : " + fullClient.equals(csvClient));
        System.out.println("   fullClient.equals(shortFromFull)  : " + fullClient.equals(shortFromFull));
        System.out.println("   hashCode fullClient / extended    : " + fullClient.hashCode() + " / " + extendedClient.hashCode());

        System.out.println("\n7. ПРОВЕРКА ЗАЩИТЫ ОТ НЕВАЛИДНЫХ ДАННЫХ:");
        try {
            new Client("{\"id\": -5, \"name\": \"Тест\"}");
        } catch (IllegalArgumentException e) {
            System.out.println("   Ожидаемо отклонено: " + e.getMessage());
        }
    }
}
