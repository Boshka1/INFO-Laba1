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

        Client c2 = new Client("2;АО \"КубаньАгро\";Сельское хозяйство;+7 (861) 222-33-44;г. Краснодар, ул. Северная, д. 45;2311987654;agro@kuban.ru;Сидоров Алексей");

        Client c1Duplicate = new Client(
                1,
                "ООО \"СтройИнвест Групп\"",
                "Девелопмент",
                "+7 (999) 123-45-67",
                "г. Краснодар, ул. Красная, д. 120",
                "2310123456",
                "sales@stroyinvest.ru",
                "Петров Петр"
        );

        System.out.println("Полная версия (toString):");
        System.out.println(c1);

        System.out.println("\nКраткая версия (toShortString):");
        System.out.println(c1.toShortString());
        System.out.println(c2.toShortString());

        System.out.println("\nСравнение (equals и hashCode):");
        System.out.println("c1.equals(c1Duplicate): " + c1.equals(c1Duplicate));
        System.out.println("c1.equals(c2): " + c1.equals(c2));
        System.out.println("hashCode c1 / c1Duplicate: " + c1.hashCode() + " / " + c1Duplicate.hashCode());
    }
}
