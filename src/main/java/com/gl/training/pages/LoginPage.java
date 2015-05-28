package com.gl.training.pages;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static com.gl.training.Settings.getBaseUrl;
import static com.gl.training.utils.CommonOperations.sendKeys;

public class LoginPage extends Page<LoginPage> {

//    private WebDriver driver;


    @FindBy(name = "login")
    private WebElement loginForm;

    @FindBy(name = "j_username")
    private WebElement userField;

    @FindBy(name = "j_password")
    private WebElement passwordField;

    private static String loginUrlPart = "/login";

    @NotNull
    public static String getExpectedUrl() {
        return getBaseUrl() + loginUrlPart;
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageURL() {
        return wd.getCurrentUrl();
    }

    @Override
    protected void checkUniqueElements() throws Error {
        //TODO add
    }

    public void submitLogin(String name, String password) {
        log.info("Try to login with name: '" + name + "', password: '" + password + "'");
        sendKeys(userField, name);
        sendKeys(passwordField, password);
        loginForm.submit();
    }

    @Override
    protected void load() {
        wd.get(
                getExpectedUrl());
    }

    @Override
    protected void isLoaded() throws Error {
        try{
//            URL actualUrl = new URL(wd.getCurrentUrl());
            String url = wd.getCurrentUrl();
//            actualUrl.
        Assert.assertTrue(url.equals(getExpectedUrl()), "Expected URL: "+getExpectedUrl()+" actual URL: " + url);
        }catch(Exception e){throw new Error(this.getClass()+" is not loaded");}
    }

}