import models.Supplier;

/**
 * Главный класс для демонстрации работы с сущностью Supplier.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа №1 (ПИС) ===");
        System.out.println("Вариант 8: Фирма по продаже запчастей");
        System.out.println("Студент: Дражин Н.О.\n");

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

        Supplier s2 = new Supplier("2;АО \"ДетальКомплект\";2310123456;1032304152637;+7 (861) 222-33-44;info@detal.ru;г. Краснодар, ул. Северная, д. 45;Сидоров Алексей");

        // Копия s1 с теми же id и inn (другие контактные лица могут обновиться)
        Supplier s1Duplicate = new Supplier(
                1,
                "ООО \"Авто-Лидер Групп\"",
                "7701234567",
                "1027700132195",
                "+7 (999) 123-45-67",
                "sales2@auto-leader.ru",
                "г. Москва, ул. Автомобильная, д. 10",
                "Петров Петр"
        );

        // 1. Вывод полной версии объекта
        System.out.println("1. ПОЛНАЯ ВЕРСИЯ ОБЪЕКТА (toString):");
        System.out.println(s1);

        // 2. Вывод краткой версии объекта
        System.out.println("\n2. КРАТКАЯ ВЕРСИЯ ОБЪЕКТА (toShortString):");
        System.out.println(s1.toShortString());
        System.out.println(s2.toShortString());

        // 3. Сравнение объектов на равенство
        System.out.println("\n3. СРАВНЕНИЕ ОБЪЕКТОВ НА РАВЕНСТВО (equals):");
        System.out.println("   s1 равен s1Duplicate (одинаковые ID и ИНН)? -> " + s1.equals(s1Duplicate));
        System.out.println("   s1 равен s2? -> " + s1.equals(s2));
        System.out.println("   hashCode s1: " + s1.hashCode());
        System.out.println("   hashCode s1Duplicate: " + s1Duplicate.hashCode());
    }
}
