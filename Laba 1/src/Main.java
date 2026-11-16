import models.Client;
import models.ClientShort;

/**
 * Главный класс для демонстрации работы с иерархией наследования (Client extends ClientShort).
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа №1 (ПИС) ===");
        System.out.println("Вариант 7: Нотариальная контора");
        System.out.println("Студент: Дражин Н.О.\n");

        // 1. Создание полного объекта Client (наследник)
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

        // 2. Создание краткого объекта ClientShort (базовый класс) из полного
        ClientShort shortFromFull = new ClientShort(fullClient);

        // 3. Создание полного объекта на базе краткого (расширение)
        Client extendedClient = new Client(
                shortFromFull,
                "г. Краснодар, ул. Северная, д. 10",
                "2310123456",
                "support@stroyinvest.ru",
                "Смирнова Анна"
        );

        System.out.println("1. ДЕМОНСТРАЦИЯ ИЕРАРХИИ НАСЛЕДОВАНИЯ:");
        System.out.println("   Класс-родитель : " + ClientShort.class.getSimpleName());
        System.out.println("   Класс-наследник: " + Client.class.getSimpleName());
        System.out.println("   Client является экземпляром ClientShort? " + (fullClient instanceof ClientShort));

        System.out.println("\n2. ПОЛНОЕ ПРЕДСТАВЛЕНИЕ ОБЪЕКТА:");
        System.out.println(fullClient);

        System.out.println("\n3. КРАТКОЕ ПРЕДСТАВЛЕНИЕ ОБЪЕКТА (toShortString):");
        System.out.println("   " + fullClient.toShortString());

        System.out.println("\n4. ПРЕДСТАВЛЕНИЕ ОБЪЕКТА CLIENTSHORT:");
        System.out.println("   " + shortFromFull);

        System.out.println("\n5. ОБЪЕКТ, СОЗДАННЫЙ РАСШИРЕНИЕМ КРАТКОЙ ВЕРСИИ:");
        System.out.println(extendedClient);
    }
}
