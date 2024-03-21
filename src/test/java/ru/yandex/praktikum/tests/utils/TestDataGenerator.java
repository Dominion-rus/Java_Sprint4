package ru.yandex.praktikum.tests.utils;

import java.util.Random;
public class TestDataGenerator {
    private TestDataGenerator() {
        throw new AssertionError();
    }

    public static String randomName() {
        String[] firstNames = {"Джон", "Ваня", "Оля", "Иван", "Лиза", "София", "Игорь"};
        Random random = new Random();
        return firstNames[random.nextInt(firstNames.length)];

    }

    public static String randomLastName() {
        String[] lastNames = {"До", "Смит", "Ли", "Зайко", "Джавко", "Питонко", "Сишарпко"};
        Random random = new Random();
        return lastNames[random.nextInt(lastNames.length)];

    }

    public static String randomPhoneNumber() {
        Random random = new Random();

        int areaCode = random.nextInt(900) + 100;
        int firstThreeDigits = random.nextInt(900) + 100;
        int lastFourDigits = random.nextInt(9000) + 1000;

        return String.format("+7%d%d%d", areaCode, firstThreeDigits, lastFourDigits);
    }
}
