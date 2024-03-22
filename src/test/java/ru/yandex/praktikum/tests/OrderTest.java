package ru.yandex.praktikum.tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.pages.OrderPage;
import ru.yandex.praktikum.tests.utils.DriverRule;

import static ru.yandex.praktikum.tests.utils.TestDataGenerator.*;

@RunWith(Parameterized.class)
public class OrderTest {
    @Rule
    public DriverRule driverRule = new DriverRule();

    private final int indexButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String dateOrder;
    private final String period;
    private final String color;
    private final String comment;

    private static final String RANDOM_NAME = randomName();
    private static final String RANDOM_SURNAME = randomLastName();
    private static final String RANDOM_PHONE = randomPhoneNumber();

    private static final String RANDOM_NAME_2 = randomName();
    private static final String RANDOM_SURNAME_2 = randomLastName();
    private static final String RANDOM_PHONE_2 = randomPhoneNumber();


    public OrderTest(int indexButton, String name, String surname, String address, String metro,
                     String phone, String dateOrder, String period, String color, String comment) {
        this.indexButton = indexButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.dateOrder = dateOrder;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Оформление заказа: " +
            "Способ вызова: {0}; " +
            "Имя: {1}; " +
            "Фамилия: {2}; " +
            "Адрес: {3}; " +
            "Метро: {4}; " +
            "Телефон: {5}; " +
            "Когда нужен: {6}; " +
            "Срок аренды: {7}; " +
            "Цвет: {8}; " +
            "Комментарий: {9}")
    public static Object[][] getTestData() {
        return new Object[][] {
                {0, RANDOM_NAME, RANDOM_SURNAME, "Москва", "Выставочная", RANDOM_PHONE,
                        "21.03.2024", "трое суток", "grey", "ТЕСТ 1"},
                {1, RANDOM_NAME_2, RANDOM_SURNAME_2, "Москва", "Беговая", RANDOM_PHONE_2,
                        "25.03.2024", "сутки", "black", "ТЕСТ 2"}
        };
    }

    @Test
    public void createOrder()  {
        WebDriver driver = driverRule.getDriver();
        MainPage mainPage = new MainPage(driver)
                .openUrl()
                .waitForLoadPage()
                .acceptCookie()
                .clickOrder(indexButton);

        OrderPage orderPage = new OrderPage(driver)
                .waitForLoadOrderPage()
                .setDataFieldsAndClickNext(name, surname, address, metro, phone)
                .waitForLoadRentPage()
                .setOtherFieldsAndClickOrder(dateOrder,period,color,comment)
                .checkCompleteOrder();

    }
}
