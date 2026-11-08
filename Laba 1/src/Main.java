import models.Supplier;

/**
 * Главный класс для демонстрации работы с сущностью Supplier.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа №1 (ПИС) ===");
        System.out.println("Вариант 8: Фирма по продаже запчастей");
        System.out.println("Студент: Дражин Н.О.\n");

        // 1. Успешное создание валидного объекта
        try {
            Supplier validSupplier = new Supplier(
                    1,
                    "ООО \"Авто-Лидер\"",
                    "7701234567",
                    "1027700132195",
                    "+7 (999) 123-45-67",
                    "sales@auto-leader.ru",
                    "г. Москва, ул. Автомобильная, д. 10",
                    "Иванов Иван Иванович"
            );
            System.out.println("1. Объект успешно создан:");
            System.out.println("   Поставщик: " + validSupplier.getName() + " | ИНН: " + validSupplier.getInn());
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка валидации: " + e.getMessage());
        }

        // 2. Попытка создать объект с невалидным ИНН (должна выбросить исключение)
        System.out.println("\n2. Проверка защиты: попытка создать объект с некорректным ИНН (\"12345\"):");
        try {
            Supplier invalidSupplier = new Supplier(
                    2,
                    "ООО \"Брак\"",
                    "12345", // некорректный ИНН
                    "1027700132195",
                    "+7 (999) 123-45-67",
                    "info@brak.ru",
                    "г. Краснодар, ул. Северная, д. 1",
                    "Петров Петр"
            );
            System.out.println("Объект создан (ошибка защиты!)");
        } catch (IllegalArgumentException e) {
            System.out.println("   Перехвачено ожидаемое исключение: " + e.getMessage());
            System.out.println("   -> Создание некорректного объекта успешно заблокировано!");
        }
    }
}
