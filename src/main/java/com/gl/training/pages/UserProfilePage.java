package com.gl.training.pages;


import com.gl.training.entities.User;
import com.gl.training.utils.DataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertTrue;

public class UserProfilePage extends Page<UserProfilePage> {
    private User user;

    @FindBy(xpath = "//div[@id='main-panel-content']/h1")
    private WebElement userName;

    @FindBy(xpath = "//div[@id='main-panel-content']/div[2]")
    private WebElement userId;

    @FindBy(xpath = "//a[contains(@href, '/delete')]")
    private WebElement deleteButton;

    public UserProfilePage(WebDriver wd, User user) {
        super(wd);
        this.user=user;
    }

    public UserProfilePage(WebDriver wd) {
        super(wd);
    }

    public UserProfileDeletePage clickDelete() {
        deleteButton.click();
        return new UserProfileDeletePage(wd, user);
    }

    @Override
    public String getPageURL() {
        return DataProvider.getBaseUrl()+"/user/"+user.getName();
    }

    @Override
    public void checkUniqueElements() throws Error {
        assertTrue(userName.isDisplayed());
        assertTrue(userId.isDisplayed());
    }
}
