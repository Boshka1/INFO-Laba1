import models.Client;
import models.ClientShort;

/**
 * Главный класс для демонстрации работы с сущностями Client и ClientShort.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа №1 (ПИС) ===");
        System.out.println("Вариант 7: Нотариальная контора");
        System.out.println("Студент: Дражин Н.О.\n");

        Client fullClient = new Client(
                1,
                "ООО \"СтройИнвест\"",
                "Строительство",
                "+7 (999) 123-45-67",
                "г. Краснодар, ул. Красная, д. 120",
                "2310123456",
                "info@stroyinvest.ru",
                "Иванов Иван Иванович"
        );

        // Создание ClientShort из полного объекта Client
        ClientShort shortFromFull = new ClientShort(fullClient.getId(), fullClient.getName(), fullClient.getActivityType(), fullClient.getPhone());

        // Создание ClientShort напрямую через конструктор
        ClientShort shortDirect = new ClientShort(2, "АО \"КубаньАгро\"", "Сельское хозяйство", "+7 (861) 222-33-44");

        // Создание ClientShort из краткой строки
        ClientShort shortFromString = new ClientShort("3;ООО \"АйтиСофт\";Разработка ПО;88005553535");

        System.out.println("1. ПОЛНЫЙ ОБЪЕКТ CLIENT:\n" + fullClient);
        System.out.println("\n2. КРАТКИЕ ОБЪЕКТЫ CLIENTSHORT:");
        System.out.println("   Из полного объекта : " + shortFromFull);
        System.out.println("   Через конструктор  : " + shortDirect);
        System.out.println("   Из краткой строки  : " + shortFromString);
    }
}
