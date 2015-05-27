package com.gl.training.pages;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import static com.gl.training.Settings.getBaseUrl;
import static com.gl.training.utils.CommonOperations.sendKeys;

public class LoginPage extends LoadableComponent<LoginPage> {

    private WebDriver driver;

    @FindBy(name = "login")
    private WebElement loginForm;

    @FindBy(name = "j_username")
    private WebElement userField;

    @FindBy(name = "j_password")
    private WebElement passwordField;

    private String loginUrlPart = "/login?from=%2F";

    @NotNull
    public String getExpectedUrl() {
        return getBaseUrl() + loginUrlPart;
    }

    public LoginPage(WebDriver driver) {
        this.driver=driver;
    }

    public void submitLogin(String name, String password) {
        sendKeys(userField, name);
        sendKeys(passwordField, password);
        loginForm.submit();
    }

    @Override
    protected void load() {
        driver.get(getExpectedUrl());
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.endsWith(loginUrlPart), "Expected URL: "+getExpectedUrl()+" actual URL: " + url);
    }
}