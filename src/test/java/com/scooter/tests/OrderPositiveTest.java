package com.scooter.tests;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.scooter.tests.pageobject.MainPage;
import com.scooter.tests.pageobject.OrderPage;
import static org.junit.Assert.assertTrue;
import com.scooter.tests.pageobject.Constants;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class OrderPositiveTest{

    private final String name;
    private final String surname;
    private final String address;
    private final int stationIndex;
    private final String phone;
    private final String date;
    private final int duration;
    private final String color;
    private final String comment;
    private final int indexButton;
    private WebDriver driver;

    public OrderPositiveTest(String name, String surname,
                             String address, int stationIndex, String phone,
                             String date, int duration, String color,String comment, int indexButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stationIndex = stationIndex;
        this.phone = phone;
        this.date = date;
        this.duration = duration;
        this.color = color;
        this.comment = comment;
        this.indexButton = indexButton;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Павел", "Зотов", "Первый адрес", 25, "+77023456789", "04.06.24", 0,"серый", "Верхний этаж", 0},
                {"Олег", "Долгих", "Главный адрес", 35, "+71223456700", "02.05.24", 2,"черный", "Коментарий новый", 0},
                {"Леон", "Измайлов", "Иной адрес", 13, "+79223456789", "04.03.24", 2,"черный", "Нижний этаж", 1},

        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(Constants.URL);
        driver.manage().window().maximize();
    }
    //Проверка позитивного сценария по кнопкам "Заказать"
    @Test
    public void orderScooterPositiveCase() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickGetCookie();
        if (indexButton == 0) {
            mainPage.clickOrderButtonTop();
            OrderPage orderPage = new OrderPage(driver);
            orderPage.fillOrderFormFirstPage(name, surname, address, stationIndex, phone);
            orderPage.fillOrderFormSecondPage(date, duration, color, comment);
            orderPage.clickConfirmOrderButton();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            MatcherAssert.assertThat(orderPage.getTextAboutOrderResult(), containsString("Заказ оформлен"));
        } else {
            mainPage.clickOrderButtonBottom();
            OrderPage orderPage = new OrderPage(driver);
            boolean addressInput = orderPage.getPhoneInput().isDisplayed();
            assertTrue( "на странице нет поля для ввода номера телефона",addressInput);

        }

    }

    @After
    public void teardown() {
        driver.quit();
    }
}