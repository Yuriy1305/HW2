package com.geekbrains.HW6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "search_query_top")
    public WebElement searchField;

    public void search(String keys) {
        searchField.clear();
        searchField.sendKeys(keys);
        searchField.submit();
        }
}
