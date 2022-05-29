package com.geekbrains.HW6;
// Lomtev Yuriy, 18/05/2022
// Проведите рефакторинг ваших тестов (минимум одного) в соответствии с PageObject

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    WebDriver driver;
    WebDriverWait webDriverWait;
//    Actions action;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
}