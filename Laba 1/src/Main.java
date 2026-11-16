import models.Client;

/**
 * Главный класс для демонстрации работы с сущностью Client.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа №1 (ПИС) ===");
        System.out.println("Вариант 7: Нотариальная контора");
        System.out.println("Студент: Дражин Н.О.\n");

        Client client = new Client(
                1,
                "ООО \"СтройИнвест\"",
                "Строительство и девелопмент",
                "+7 (999) 123-45-67",
                "г. Краснодар, ул. Красная, д. 120",
                "2310123456",
                "info@stroyinvest.ru",
                "Иванов Иван Иванович"
        );

        System.out.println("Успешно создан объект клиента через инкапсулированные методы:");
        System.out.println("ID: " + client.getId());
        System.out.println("Название: " + client.getName());
        System.out.println("Вид деятельности: " + client.getActivityType());
        System.out.println("Телефон: " + client.getPhone());
        System.out.println("ИНН: " + client.getInn());
    }
}
