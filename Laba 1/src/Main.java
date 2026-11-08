import models.Supplier;
import models.SupplierShort;

/**
 * Главный класс для демонстрации работы с сущностями Supplier и SupplierShort.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа №1 (ПИС) ===");
        System.out.println("Вариант 8: Фирма по продаже запчастей");
        System.out.println("Студент: Дражин Н.О.\n");

        Supplier fullSupplier = new Supplier(
                1,
                "ООО \"Авто-Лидер\"",
                "7701234567",
                "1027700132195",
                "+7 (999) 123-45-67",
                "sales@auto-leader.ru",
                "г. Москва, ул. Автомобильная, д. 10",
                "Иванов Иван Иванович"
        );

        // Создание SupplierShort из полного объекта Supplier
        SupplierShort shortFromFull = new SupplierShort(fullSupplier);

        // Создание SupplierShort напрямую через конструктор
        SupplierShort shortDirect = new SupplierShort(2, "АО \"ДетальКомплект\"", "2310123456", "+7 (861) 222-33-44");

        // Создание SupplierShort из краткой строки
        SupplierShort shortFromString = new SupplierShort("3;ООО \"Мотор\";7801987654;88005553535");

        System.out.println("1. ПОЛНЫЙ ОБЪЕКТ SUPPLIER:\n" + fullSupplier);
        System.out.println("\n2. КРАТКИЕ ОБЪЕКТЫ SUPPLIERSHORT:");
        System.out.println("   Из полного объекта : " + shortFromFull);
        System.out.println("   Через конструктор  : " + shortDirect);
        System.out.println("   Из краткой строки  : " + shortFromString);
    }
}
