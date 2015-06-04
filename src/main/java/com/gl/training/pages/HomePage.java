package com.gl.training.pages;


import com.gl.training.pages.pageparts.Header;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.gl.training.utils.DataProvider.getBaseUrl;
import static org.testng.Assert.assertTrue;

public class HomePage extends Page<HomePage>{

    private Header header;

    @FindBy(className = "dashboard")
    private WebElement dashboard;

    public HomePage(WebDriver wd) {
        super(wd);
        this.header = new Header(wd);
    }

    public Header getHeader() {
        return header;
    }

    @Override
    public String getPageURL() {
        return getBaseUrl();
    }

    @Override
    public void checkUniqueElements() throws Error {
        assertTrue(dashboard.isDisplayed());
    }
}
