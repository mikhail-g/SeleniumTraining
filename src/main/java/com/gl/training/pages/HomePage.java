package com.gl.training.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.gl.training.utils.DataProvider.getBaseUrl;
import static org.testng.Assert.assertTrue;

public class HomePage extends Page<HomePage>{

//    @FindAll()

    public HomePage(WebDriver wd) {
        super(wd);
    }

    @FindBy(className = "dashboard")
    private WebElement dashboard;

    @Override
    public String getPageURL() {
        return getBaseUrl();
    }

    @Override
    public void checkUniqueElements() throws Error {
        assertTrue(dashboard.isDisplayed());
    }
}
