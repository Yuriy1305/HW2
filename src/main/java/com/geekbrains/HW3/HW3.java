package com.geekbrains.HW3;
// Lomtev Yuriy, 03/05/2022
// Напишите минимум 1 придуманный вами сценарий (чем больше, тем лучше) на любой сайт с помощью java+selenium.
// Пытаюсь реализовать сценарий тест-кейса с ДЗ 1:
//1 Войти на сайт http://automationpractice.com/index.php--Успешно загрузилась основная страница сайта
//2 Ввести в строке поиска (Search):”Women” и нажать значок поиска (лупу)--Ничего не найдено
//3 Ввести в строке поиска (Search):”Woman” и нажать значок поиска (лупу)--Открывается страница женской одежды
//4 Ввести в строке поиска (Search):”Blouse” и нажать значок поиска (лупу)--Открывается страница с блузками
//5 Ввести в строке поиска (Search):”Dresses” и нажать значок поиска (лупу)--Открывается страница со всеми вариантами женской одежды
//6 Ввести в строке поиска (Search):”Printed dresses” и нажать значок поиска (лупу)--Открывается страница с одеждой с принтами
//7 Ввести в строке поиска (Search):”T-shirts” и нажать значок поиска (лупу)--Открывается страница с футболками

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class HW3 {
    public static void main(String[] args) {
        String[] whatSearch = new String[]{"Women", "Woman", "Blouse", "Dresses", "Printed dresses", "T-shirts"};
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Yuriy\\myWebTests\\src\\main\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
// п.1 сценария:
        driver.get("http://automationpractice.com");
// п.2 сценария:
        WebElement searchBox = driver.findElement(By.id("search_query_top"));
        searchBox.sendKeys(whatSearch[0]);
        searchBox.submit();
        if (driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[contains(text(),'No results ')]")).isDisplayed()) {
            System.out.println("1-й пункт проверки поиска пройден: " + whatSearch[0] + " не найдено.");
        }
// пп.3-7 сценария, т.к. они одинаковые - цикл длиной массива строк, но без уже проверенного первого/0-го элемента:
        for (int i = 1; i < whatSearch.length; i++) {
            WebElement searchBoxI = driver.findElement(By.id("search_query_top"));
            searchBoxI.clear();
            searchBoxI.sendKeys(whatSearch[i]);
            searchBoxI.submit();
            if (driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1/span[2][not (contains(text(),'No results '))]")).isDisplayed()) {
                System.out.println((i + 1) + "-й пункт проверки поиска пройден: " + whatSearch[i] + " найдено.");
            }
        }
        driver.quit();
    }
}
