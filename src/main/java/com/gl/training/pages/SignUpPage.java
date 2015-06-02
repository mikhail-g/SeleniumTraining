package com.gl.training.pages;

import org.openqa.selenium.WebDriver;

<<<<<<< HEAD
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static com.gl.training.Settings.getBaseUrl;
import static com.gl.training.utils.CommonOperations.sendKeys;

public class SignUpPage extends Page<SignUpPage> {

    @FindBy(xpath = "//form[@action='/securityRealm/createAccount']")
    private WebElement signUpForm;

    @FindBy(name = "username")
    private WebElement userNameField;

    @FindBy(name = "password1")
    private WebElement passwordField;

    @FindBy(name = "password2")
    private WebElement confirmPasswordField;

    @FindBy(name = "fullname")
    private WebElement fullNameField;

    @FindBy(name = "email")
    private WebElement emailField;

    public SignUpPage(WebDriver driver){
        super(driver);
    }

    @Override
    public String getPageURL() {
        return getBaseUrl() + getSignUpUrlPart();
    }

    @Override
    protected void checkUniqueElements() throws Error {
        //TODO add
    }

    //url:
    private String signUpUrlPart = "/signup";

    @Override
    protected void load() {
        wd.get(getExpectedUrl());
    }

    @NotNull
    public String getExpectedUrl() {
        return getBaseUrl() + signUpUrlPart;
    }

    @Override
    protected void isLoaded() throws Error {
        String url = getPageURL();
        Assert.assertTrue(url.startsWith(getExpectedUrl()), "Expected URL: " + getExpectedUrl() + " actual URL: " + url);
    }

    public String getSignUpUrlPart() {
        return signUpUrlPart;
    }

    public void submitSignUp(String name, String password, String confirmPassword, String fullName, String email) {
        log.info("Try to sign up with name: '" + name + "', password: '" + password +
                "', confirm password: '" + confirmPassword+"', full name: '" + fullName+"', email: '"+email+"'");
        sendKeys(userNameField, name);
        sendKeys(passwordField, password);
        sendKeys(confirmPasswordField, confirmPassword);
        sendKeys(fullNameField, fullName);
        sendKeys(emailField, email);
        signUpForm.submit();
    }
}