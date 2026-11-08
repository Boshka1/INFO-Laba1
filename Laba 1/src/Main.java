import models.Supplier;
import models.SupplierShort;

/**
 * Главный класс для демонстрации работы с иерархией наследования (Supplier extends SupplierShort).
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа №1 (ПИС) ===");
        System.out.println("Вариант 8: Фирма по продаже запчастей");
        System.out.println("Студент: Дражин Н.О.\n");

        // 1. Создание полного объекта Supplier (наследник)
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

        // 2. Создание краткого объекта SupplierShort (базовый класс) из полного
        SupplierShort shortFromFull = new SupplierShort(fullSupplier);

        // 3. Создание полного объекта на базе краткого (расширение)
        Supplier extendedSupplier = new Supplier(
                shortFromFull,
                "1027700132195",
                "support@auto-leader.ru",
                "г. Москва, ул. Складская, д. 5",
                "Смирнова Анна"
        );

        System.out.println("1. ДЕМОНСТРАЦИЯ ИЕРАРХИИ НАСЛЕДОВАНИЯ:");
        System.out.println("   Класс-родитель: " + SupplierShort.class.getSimpleName());
        System.out.println("   Класс-наследник: " + Supplier.class.getSimpleName());
        System.out.println("   Supplier является экземпляром SupplierShort? " + (fullSupplier instanceof SupplierShort));

        System.out.println("\n2. ПОЛНОЕ ПРЕДСТАВЛЕНИЕ ОБЪЕКТА:");
        System.out.println(fullSupplier);

        System.out.println("\n3. КРАТКОЕ ПРЕДСТАВЛЕНИЕ ОБЪЕКТА (toShortString):");
        System.out.println("   " + fullSupplier.toShortString());

        System.out.println("\n4. ПРЕДСТАВЛЕНИЕ ОБЪЕКТА SUPPLIERSHORT:");
        System.out.println("   " + shortFromFull);

        System.out.println("\n5. ОБЪЕКТ, СОЗДАННЫЙ РАСШИРЕНИЕМ КРАТКОЙ ВЕРСИИ:");
        System.out.println(extendedSupplier);
    }
}
