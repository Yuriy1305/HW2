package com.geekbrains.HW5;
// Lomtev Yuriy, 19/05/2022
// Перенесите сценарии из 3 дз (можно писать новые тесты, в том числе сменив сайт) в тесты
// (методы с аннотацией @Test), используйте webDriverWait для ожиданий, в конце тестов добавьте Assertion-ы

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MySiteTest {
    static WebDriver webDriver;
    static WebDriverWait webDriverWait;
    static WebElement searchBox;

    @BeforeAll
    static void initDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        webDriver.get("http://automationpractice.com");
    }

    @BeforeEach
    void searchAndClear() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_query_top")));
        searchBox = webDriver.findElement(By.id("search_query_top"));
        searchBox.clear();
    }

    @ParameterizedTest
    @CsvSource({"Woman", "Blouse", "Dresses", "Printed dresses", "T - shirts"})
    void positiveSearchTest(String searchText) {
        searchBox.sendKeys(searchText);
        searchBox.submit();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"center_column\"]/h1/span[2][not (contains(text(),'No results '))]")));
        Assertions.assertTrue(webDriver.findElement(
                By.xpath("//*[@id=\"center_column\"]/h1/span[2][not (contains(text(),'No results '))]"))
                .isDisplayed());
    }

    @Test
    void negativeSearchTest() {
        searchBox.sendKeys("Women");
        searchBox.submit();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"center_column\"]/p[contains(text(),'No results ')]")));
        Assertions.assertTrue(webDriver.findElement(By.xpath("//*[@id=\"center_column\"]/p[contains(text(),'No results ')]"))
                .isDisplayed());
    }

    @AfterAll
    static void siteQuit() {
//        Thread.sleep(5000);
        webDriver.quit();
    }

}
