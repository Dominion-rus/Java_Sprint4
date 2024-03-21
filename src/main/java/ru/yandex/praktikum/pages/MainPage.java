package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.pages.EnvConfig.BASE_URL;
import static ru.yandex.praktikum.pages.EnvConfig.DEFAULT_TIMEOUT;

public class MainPage {
    private final WebDriver driver;

    private final By BUTTON_ACCEPT_COOKIE = By.id("rcc-confirm-button");

    private final By QUESTION_ABOUT_TEXT_PATH = By.xpath("//div[text()='Вопросы о важном']");

    private final By SECTION_FAQ = By.xpath(".//div[starts-with(@class,'Home_FAQ')]");

    private final By IMAGE_SCOOTER = By.xpath(".//img[@alt = 'Scooter blueprint']");

    private final By LOCK_ORDER_BUTTON_TOP = By.xpath(".//button[@class='Button_Button__ra12g']");

    private final By LOCK_ORDER_BUTTON_BOTTOM =
            By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    private final By LOGO_YANDEX = By.xpath(".//img[@alt='Yandex']");

    private final By LOGO_SAMOKAT = By.xpath(".//img[@alt='Scooter']");

    private final String QUASTION_ABOUT_TEXT = "Вопросы о важном";

    private final String SCRIPT = "arguments[0].scrollIntoView();";

    private final String YANDEX_URL = "https://dzen.ru/?yredirect=true";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage openUrl(){
        driver.get(BASE_URL);
        return this;
    }

    public MainPage yandexLink() {
        driver.findElement(LOGO_YANDEX).click();
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.urlToBe(YANDEX_URL));
        assertEquals("URL после нажатия на лого яндекс не совпадает", YANDEX_URL, driver.getCurrentUrl());

        return this;
    }
    public MainPage samokatLink() {
        driver.findElement(LOGO_SAMOKAT).click();

        assertEquals("URL после нажатия на лого самоката не совпадает", BASE_URL, driver.getCurrentUrl());

        return this;
    }

    public boolean isElementExist(By locatorBy) {
        try {
            driver.findElement(locatorBy);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public MainPage acceptCookie(){
        if (isElementExist(BUTTON_ACCEPT_COOKIE))
            driver.findElement(BUTTON_ACCEPT_COOKIE).click();
        return this;
    }

    public MainPage scrollToFaq(){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(SECTION_FAQ));
        actions.perform();
        return this;
    }

    public MainPage checkAboutFaqText(){
        WebElement element = driver.findElement(QUESTION_ABOUT_TEXT_PATH);
        assertEquals("Текст раздела Вопросы о важном не совпадает",QUASTION_ABOUT_TEXT,element.getText());
        return this;
    }

    public MainPage waitForLoadPage() {
        WebElement imageElement = driver.findElement(IMAGE_SCOOTER);
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(IMAGE_SCOOTER));
        ((JavascriptExecutor)driver).executeScript(SCRIPT, imageElement);
        return this;
    }


    public MainPage checkQuestionsAndAnswers(String question,String answer) {
        By questionPath = By.xpath("//div[text()='" + question + "']");

        WebElement questionElement = driver.findElement(questionPath);
        new WebDriverWait(driver,Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(questionPath));

        assertEquals("Текст вопросa не совпадает", question, questionElement.getText());

        questionElement.click();

        By answerPath = By.xpath("//div[p[contains(text(),'" + answer + "')]]");
        WebElement answerElement = driver.findElement(answerPath);
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(answerPath));

        assertEquals("Текст  ответа не совпадает", answer, answerElement.getText());

        return this;
    }

    public MainPage clickOrder(int indexButton) {
        switch (indexButton) {
            case 0:
                driver.findElement(LOCK_ORDER_BUTTON_TOP).click();
                break;
            case 1:
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                WebElement buttonOrder = driver.findElement(LOCK_ORDER_BUTTON_BOTTOM);
                new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (buttonOrder.isDisplayed()));
                buttonOrder.click();
                break;
        }
        return this;
    }


}
