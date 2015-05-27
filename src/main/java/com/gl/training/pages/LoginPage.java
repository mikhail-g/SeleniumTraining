package com.gl.training.pages;

import org.openqa.selenium.WebElement;

public class LoginPage {
    public WebElement loginForm;
    public WebElement userField;
    public WebElement passwordField;
    public String loginUrlPart = "/login?from=%2F";
    public String formLocatorName = "login";
    public String userLocatorName = "j_username";
    public String passwordLocatorName = "j_password";

    public LoginPage() {
    }

    public void submitLogin(String name, String password) {
        userField.clear();
        userField.sendKeys(name);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginForm.submit();
    }
}