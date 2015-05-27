package com.gl.training.pages;

import com.gl.training.Settings;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;

import static com.gl.training.Settings.*;
import static com.gl.training.utils.CommonOperations.*;

public class SignUpPage extends LoadableComponent<SignUpPage> {

    private WebDriver driver;

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
        this.driver=driver;
    }

    //url:
    String signUpUrlPart = "/signup";

    /*//locator:
    String formSignUpLocatorXpath = "//form[@action='/securityRealm/createAccount']";
    String inputUserNameLocatorName = "username";
    String inputPasswordLocatorName = "password1";
    String inputConfirmPasswordLocatorName = "password2";
    String inputFullNameLocatorName = "fullname";
    String inputEmailLocatorName = "email";*/

    @Override
    protected void load() {
        driver.get(getExpectedUrl());
    }

    @NotNull
    public String getExpectedUrl() {
        return getBaseUrl() + signUpUrlPart;
    }

    @Override
    protected void isLoaded() throws Error {

    }

    public void submitSignUp(String name, String password, String confirmPassword, String fullName, String email) {
        sendKeys(userNameField, name);
        sendKeys(passwordField, password);
        sendKeys(confirmPasswordField, confirmPassword);
        sendKeys(fullNameField, fullName);
        sendKeys(emailField, email);
        signUpForm.submit();
    }
}