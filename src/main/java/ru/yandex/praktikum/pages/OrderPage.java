package ru.yandex.praktikum.pages;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static ru.yandex.praktikum.pages.EnvConfig.DEFAULT_TIMEOUT;

public class OrderPage {
    private final WebDriver driver;

    private final By ORDER_HEADER = By.xpath("//div[(text()= 'Для кого самокат')]");

    private final By RENT_HEADER = By.xpath("//div[(text()= 'Про аренду')]");

    private final By INPUT_NAME = By.xpath("//input[@placeholder ='* Имя']");

    private final By INPUT_SURNAME = By.xpath("//input[@placeholder ='* Фамилия']");

    private final By INPUT_ADDRESS = By.xpath("//input[@placeholder ='* Адрес: куда привезти заказ']");

    private final By INPUT_PHONE = By.xpath("//input[@placeholder ='* Телефон: на него позвонит курьер']");

    private final By INPUT_METRO = By.xpath("//input[@placeholder ='* Станция метро']");

    private final By INPUT_CALENDAR = By.xpath("//input[@placeholder ='* Когда привезти самокат']");

    private final By INPUT_PERIOD = By.className("Dropdown-placeholder");

    private final By INPUT_COMMENT = By.xpath("//input[@placeholder ='Комментарий для курьера']");

    private final By BUTTON_NEXT =
            By.xpath(".//div[starts-with(@class,'Order_NextButton')]//button[contains(text(), 'Далее')]");

    private final By BUTTON_ORDER =
            By.xpath(".//div[starts-with(@class,'Order_Buttons')]//button[contains(text(), 'Заказать')]");

    private final By BUTTON_YES = By.xpath("//button[contains(text(), 'Да')]");

    public By ORDER_PLACED = By.className("Order_ModalHeader__3FDaJ");

    private final String COMPLETE_ORDER_TEXT = "Заказ оформлен";


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderPage waitForLoadOrderPage() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(getTitleOrder()));
        return this;
    }

    public OrderPage setDataFieldsAndClickNext(String valueName, String valueSurname, String valueAddress,
                                          String valueMetro, String valuePhone) {
        getName().sendKeys(valueName);
        getSurname().sendKeys(valueSurname);
        getAddress().sendKeys(valueAddress);
        getMetro().sendKeys(valueMetro, Keys.ARROW_DOWN, Keys.ENTER);
        getPhoneNumber().sendKeys(valuePhone);
        getButtonNext().click();
        return this;
    }

    public OrderPage waitForLoadRentPage() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(getTitleRent()));
        return this;
    }

    public OrderPage setOtherFieldsAndClickOrder(String valueDateOrder,
                                            String valuePeriod, String valueColor, String valueComment) {
        getCalendar().sendKeys(valueDateOrder, Keys.ARROW_DOWN, Keys.ENTER);
        getPeriod().click();
        getDays(valuePeriod).click();
        getColor(valueColor).click();
        getComment().sendKeys(valueComment);
        getButtonOrder().click();
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT)).until(driver -> (getButtonYes().isDisplayed()));
        getButtonYes().click();
        return this;
    }

    public OrderPage checkCompleteOrder (){
        WebElement completeOrderElement = driver.findElement(ORDER_PLACED);
        MatcherAssert.assertThat("Нет окна (Заказ оформлен)",
                completeOrderElement.getText(), containsString(COMPLETE_ORDER_TEXT));
        return this;
    }

    public By getTitleOrder() {
        return ORDER_HEADER;
    }

    public By getTitleRent() {
        return RENT_HEADER;
    }

    public WebElement getName() {
        return driver.findElement(INPUT_NAME);
    }

    public WebElement getSurname() {
        return driver.findElement(INPUT_SURNAME);
    }

    public WebElement getAddress() {
        return driver.findElement(INPUT_ADDRESS);
    }

    public WebElement getPhoneNumber() {
        return driver.findElement(INPUT_PHONE);
    }

    public WebElement getMetro() {
        return driver.findElement(INPUT_METRO);
    }

    public WebElement getCalendar() {
        return driver.findElement(INPUT_CALENDAR);
    }

    public WebElement getPeriod() {
        return driver.findElement(INPUT_PERIOD);
    }

    public WebElement getDays(String valueDays) {
        return driver.findElement(By.xpath(".//div[@class='Dropdown-menu']/div[text()='"+valueDays+"']"));
    }

    public WebElement getColor(String colorName) {
        return driver.findElement(By.id(colorName));
    }

    public WebElement getComment() {
        return driver.findElement(INPUT_COMMENT);
    }

    public WebElement getButtonNext() {
        return driver.findElement(BUTTON_NEXT);
    }

    public WebElement getButtonOrder() {
        return driver.findElement(BUTTON_ORDER);
    }

    public WebElement getButtonYes() {
        return driver.findElement(BUTTON_YES);
    }

}
