package com.geekbrains.HW6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tests {
static WebDriver driverTests;

    @BeforeAll
    static void initDriver() {
        WebDriverManager.chromedriver().setup();
        driverTests = new ChromeDriver();
        driverTests.get("http://automationpractice.com");
    }

    @ParameterizedTest
    @CsvSource({"Woman", "Blouse", "Dresses", "Printed dresses", "T - shirts"})
    void positiveSearchTest(String searchText) {
        new SearchPage(driverTests).search(searchText);
        Assertions.assertTrue(driverTests.findElement(By.xpath("//*[@id=\"center_column\"]/h1/span[2][not (contains(text(),'No results '))]"))
                .isDisplayed());
    }

    @Test
    void negativeSearchTest() {
        new SearchPage(driverTests).search("Women");
        Assertions.assertTrue(driverTests.findElement(By.xpath("//*[@id=\"center_column\"]/p[contains(text(),'No results ')]"))
                .isDisplayed());
    }

    @AfterAll
    static void siteQuit() {
//        Thread.sleep(5000);
        driverTests.quit();
    }
}