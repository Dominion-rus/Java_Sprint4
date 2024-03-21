package ru.yandex.praktikum.tests.utils;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.pages.MainPage;
public class UrlTest {
    @Rule
    public DriverRule driverRule = new DriverRule();

    @Test
    public void checkUrlLogo() {
        WebDriver driver = driverRule.getDriver();
        MainPage mainPage = new MainPage(driver)
                .openUrl()
                .waitForLoadPage()
                .acceptCookie()
                .samokatLink()
                .yandexLink();

    }
}
