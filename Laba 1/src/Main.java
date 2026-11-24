import models.Client;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа №1 (ПИС) ===");
        System.out.println("Вариант 7: Нотариальная контора");
        System.out.println("Студент: Дражин Н.О.\n");

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
        System.out.println("Клиент: " + validClient.getName() + " | ИНН: " + validClient.getInn());

        try {
            new Client(
                    2,
                    "ИП Смирнов",
                    "Торговля",
                    "+7 (999) 123-45-67",
                    "г. Краснодар, ул. Ленина, д. 5",
                    "12345",
                    "smirnov@mail.ru",
                    "Смирнов Алексей"
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Исключение при невалидном ИНН: " + e.getMessage());
        }
    }
}
