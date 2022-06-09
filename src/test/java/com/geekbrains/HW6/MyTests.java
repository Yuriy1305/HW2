package com.geekbrains.HW6;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import java.io.ByteArrayInputStream;

@Epic("Тесты поиска")
public class MyTests {
    static WebDriver driverTests;

    @Feature("Поиск на странице")
    @BeforeAll
    static void initDriver() {
        WebDriverManager.chromedriver().setup();
        driverTests = new ChromeDriver();
        driverTests.get("http://automationpractice.com");
    }

    @ParameterizedTest
    @Story("Позитивные тесты поисков: ")
    @CsvSource({"Woman", "Blouse", "Dresses", "Printed dresses", "T - shirts"})
    void positiveSearchTest(String searchText) {
        new SearchPage(driverTests).search(searchText);
        Assertions.assertTrue(driverTests.findElement(By.xpath("//*[@id=\"center_column\"]/h1/span[2][not (contains(text(),'No results '))]"))
                .isDisplayed());
    }

    @Test
    @Story("Негативный тест поиска")
    void negativeSearchTest() {
        new SearchPage(driverTests).search("Women");
        Allure.addAttachment("Копия экрана для негативного теста",
                new ByteArrayInputStream(((TakesScreenshot) driverTests).getScreenshotAs(OutputType.BYTES)));

        Assertions.assertTrue(driverTests.findElement(By.xpath("//*[@id=\"center_column\"]/p[contains(text(),'No results ')]"))
                .isDisplayed());
       }

    @AfterAll
    static void siteQuit() {
//        Thread.sleep(5000);
         driverTests.quit();
    }
}