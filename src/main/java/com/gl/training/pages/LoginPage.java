package com.gl.training.pages;

import com.gl.training.pages.Page;
import com.gl.training.utils.CommonOperations;
import com.gl.training.utils.DataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.gl.training.utils.CommonOperations.sendKeys;
import static com.gl.training.utils.DataProvider.getBaseUrl;

public class LoginPage extends Page {

    private String loginUrlPart = "/login?from=%2F";

    @FindBy(name = "login")
    private WebElement loginForm;

    @FindBy(name = "j_username")
    private WebElement userField;

    @FindBy(name = "j_password")
    private WebElement passwordField;

    public LoginPage(WebDriver wd) {
        super(wd);
    }

    public String getLoginUrlPart() {
        return loginUrlPart;
    }

    @Override
    public String getPageURL() {
        return getBaseUrl() + getLoginUrlPart();
    }

    @Override
    protected void checkUniqueElements() throws Error {

    }

    public void submitLogin(String name, String password) {
        sendKeys(userField, name);
        sendKeys(passwordField, password);
        loginForm.submit();
    }


}