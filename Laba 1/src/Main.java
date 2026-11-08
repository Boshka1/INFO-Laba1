import models.Supplier;

/**
 * Главный класс для демонстрации работы с сущностью Supplier.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа №1 (ПИС) ===");
        System.out.println("Вариант 8: Фирма по продаже запчастей");
        System.out.println("Студент: Дражин Н.О.\n");

        Supplier supplier = new Supplier(
                1,
                "ООО \"Авто-Лидер\"",
                "7701234567",
                "1027700132195",
                "+7 (999) 123-45-67",
                "sales@auto-leader.ru",
                "г. Москва, ул. Автомобильная, д. 10",
                "Иванов Иван Иванович"
        );

        System.out.println("Успешно создан объект поставщика через инкапсулированные методы:");
        System.out.println("ID: " + supplier.getId());
        System.out.println("Название: " + supplier.getName());
        System.out.println("ИНН: " + supplier.getInn());
        System.out.println("Телефон: " + supplier.getPhone());
    }
}
