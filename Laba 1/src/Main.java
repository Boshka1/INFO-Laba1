import models.Client;

/**
 * Главный класс для демонстрации работы с сущностью Client.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа №1 (ПИС) ===");
        System.out.println("Вариант 7: Нотариальная контора");
        System.out.println("Студент: Дражин Н.О.\n");

        // 1. Успешное создание валидного объекта
        try {
            Client validClient = new Client(
                    1,
                    "ООО \"СтройИнвест\"",
                    "Строительство",
                    "+7 (999) 123-45-67",
                    "г. Краснодар, ул. Красная, д. 120",
                    "2310123456",
                    "info@stroyinvest.ru",
                    "Иванов Иван Иванович"
            );
            System.out.println("1. Объект успешно создан:");
            System.out.println("   Клиент: " + validClient.getName() + " | ИНН: " + validClient.getInn());
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        // 2. Попытка создать объект с невалидным ИНН (должна выбросить исключение)
        System.out.println("\n2. Проверка защиты: попытка создать объект с некорректным ИНН (\"12345\"):");
        try {
            Client invalidClient = new Client(
                    2,
                    "ИП Смирнов",
                    "Торговля",
                    "+7 (999) 123-45-67",
                    "г. Краснодар, ул. Ленина, д. 5",
                    "12345", // некорректный ИНН
                    "smirnov@mail.ru",
                    "Смирнов Алексей"
            );
            System.out.println("Объект создан (ошибка защиты!)");
        } catch (IllegalArgumentException e) {
            System.out.println("   Перехвачено ожидаемое исключение: " + e.getMessage());
            System.out.println("   -> Создание некорректного объекта успешно заблокировано!");
        }
    }
}
