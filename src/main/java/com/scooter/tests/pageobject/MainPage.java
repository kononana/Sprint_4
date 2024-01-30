package com.scooter.tests.pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private final WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // кнопка куки
    private final By buttonAcceptCookie = By.id("rcc-confirm-button");
    //Секция с вопросами
    private final By questionSection = By.className("accordion");
    //Список вопросов
    private final By questionList = By.className("accordion__item");
    //Список ответов
    private final By answerList = By.xpath(".//div[@class='accordion__panel']/p");

    //Кнопка заказать вверху страницы
    private final By orderButtonTop = By.className("Button_Button__ra12g");

    //Кнопка заказать внизу страницы
    private final By orderButtonBottom = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    //Прокрутить до блока с вопросами
    public void scrollToQuestionList() {
        WebElement element = driver.findElement(questionSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //Получить все элементы вопросов в списке
    public List<WebElement> questionElements() {
        return driver.findElements(questionList);
    }

    //Получить все элементы ответов в списке
    public List<WebElement> answerElements() {
        return driver.findElements(answerList);
    }

    //Нажать на кнопку вверху страницы
    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    //Нажать на кнопку внизу страницы
    public void clickOrderButtonBottom() {
        WebElement element = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(orderButtonBottom).click();
    }

    // принять куки
    public void clickGetCookie() {
            driver.findElement(buttonAcceptCookie).click();
    }

    //Явное ожидание элемента на странице
    public WebElement waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }


}