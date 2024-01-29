package com.scooter.tests.pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class OrderPage {
    private final WebDriver driver;

    //Ввод имени
    private final By inputName = By.xpath("//input[@placeholder='* Имя']");
    //Ввод фамилии
    private final By inputSurname = By.xpath("//input[@placeholder='* Фамилия']");
    //Ввод адреса
    private final By inputAddress = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");

    //Инпут станции метро
    private final By inputStation = By.xpath("//input[@placeholder='* Станция метро']");
    //Список всех станций метро
    private final By liStation = By.xpath("//li[@class='select-search__row']");

    //Номер телефона
    private final By inputPhoneNumber = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка "далее"
    private final By buttonNext = By.xpath("//button[text()='Далее']");
    // Выбор даты доставки
    private final By inputDate = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //Список  со сроками аренды
    private final By durationOptions = By.xpath("//div[@class='Dropdown-option']");
    //Выбор срока аренды
    private final By inputDuration = By.xpath("//span[@class='Dropdown-arrow']");
    //Цвет самоката "Черный"
    private final By colorBlack= By.id("black");

    //Цвет самоката "Серый"
    private final By colorGrey = By.id("grey");

    //Поле с комментарием
    private final By inputComment = By.xpath("//input[@placeholder='Комментарий для курьера']");

    //Кнопка Заказать
    private final By orderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    //Кнопка "Хотите оформить заказ?"
    private final By confirmOrderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Да']");
    //Заголовок с номером заказа
    private final By headerOrderResult = By.xpath("//div[@class='Order_ModalHeader__3FDaJ']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Заполнить поле с именем
    public void enterName(String name) {
        driver.findElement(inputName).clear();
        driver.findElement(inputName).sendKeys(name);
    }

    //Заполнить поле с фамилией
    public void enterSurname(String surname) {
        driver.findElement(inputSurname).clear();
        driver.findElement(inputSurname).sendKeys(surname);
    }

    //Заполнить поле с адресом
    public void enterAddress(String address) {
        driver.findElement(inputAddress).clear();
        driver.findElement(inputAddress).sendKeys(address);
    }

    //Выбрать станцию метро из выпадающего списка
    public void enterStation(int stationNumber) {
        driver.findElement(inputStation).click();
        List<WebElement> stationList = driver.findElements(liStation);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", stationList.get(stationNumber));
        stationList.get(stationNumber).click();
    }

    //Заполнить поле с телефоном
    public void enterPhone(String phone) {
        driver.findElement(inputPhoneNumber).clear();
        driver.findElement(inputPhoneNumber).sendKeys(phone);
    }

    //Кликнуть на кнопку "Далее"
    public void clickOnNextButton() {
        driver.findElement(buttonNext).click();
    }

    //Общий флоу для шагов на первой странице формы заказа
    public void fillOrderFormFirstPage(String name, String surname, String address, int stationIndex, String phone) {
        enterName(name);
        enterSurname(surname);
        enterAddress(address);
        enterStation(stationIndex);
        enterPhone(phone);
        clickOnNextButton();
    }

    //Выбрать дату доставки
    public void enterDate(String date) {
        driver.findElement(inputDate).clear();
        driver.findElement(inputDate).sendKeys(date);
    }

    //Выбрать срок аренды
    public void enterDuration(int index) {
        driver.findElement(inputDuration).click();
        List<WebElement> durationList = driver.findElements(durationOptions);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", durationList.get(index));
        durationList.get(index).click();
    }

    // Выбор цвета самоката
    public void setBlackColor(){
        driver.findElement(colorBlack).click();
    }

    public void setGrayColor(){
        driver.findElement(colorGrey).click();
    }

    public void setColor(String color){
        if(color.equals("черный")){
            setBlackColor();
        } else {
            setGrayColor();
        }
    }

    //Написать комментарий
    public void enterComment(String comment) {
        driver.findElement(inputComment).clear();
        driver.findElement(inputComment).sendKeys(comment);
    }

    //Нажать кнопку "Заказать"
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    //Общий флоу для заполнения второй страницы заказа
    public void fillOrderFormSecondPage(String date, int durationIndex, String color, String comment) {
        enterDate(date);
        enterDuration(durationIndex);
        setColor(color);
        enterComment(comment);
        clickOrderButton();
    }

    //Нажать на кнопку подтвердить заказ
    public void clickConfirmOrderButton() {
        driver.findElement(confirmOrderButton).click();
    }

    //Получить текст с результатами заказа
    public String getTextAboutOrderResult() {
        return driver.findElement(headerOrderResult).getText();
    }

}