import models.Client;

/**
 * Главный класс для демонстрации работы с сущностью Client.
 */
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

        // 1. Вывод полной версии объекта
        System.out.println("1. ПОЛНАЯ ВЕРСИЯ ОБЪЕКТА (toString):");
        System.out.println(c1);

        // 2. Вывод краткой версии объекта
        System.out.println("\n2. КРАТКАЯ ВЕРСИЯ ОБЪЕКТА (toShortString):");
        System.out.println(c1.toShortString());
        System.out.println(c2.toShortString());

        // 3. Сравнение объектов на равенство
        System.out.println("\n3. СРАВНЕНИЕ ОБЪЕКТОВ НА РАВЕНСТВО (equals):");
        System.out.println("   c1 равен c1Duplicate (одинаковые ID и ИНН)? -> " + c1.equals(c1Duplicate));
        System.out.println("   c1 равен c2? -> " + c1.equals(c2));
        System.out.println("   hashCode c1: " + c1.hashCode());
        System.out.println("   hashCode c1Duplicate: " + c1Duplicate.hashCode());
    }
}
