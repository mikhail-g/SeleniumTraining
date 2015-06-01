package com.gl.training.pages;

import com.gl.training.pages.Page;
import com.gl.training.utils.CommonOperations;
import com.gl.training.utils.DataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.gl.training.utils.CommonOperations.sendKeys;
import static com.gl.training.utils.DataProvider.getBaseUrl;

public class SignUpPage extends Page{

    private String signUpUrlPart = "/signup";

    @FindBy(xpath = "//form[@action='/securityRealm/createAccount']")
    WebElement signUpForm;

    @FindBy(name = "username")
    WebElement userNameField;

    @FindBy(name = "password1")
    WebElement passwordField;

    @FindBy(name = "password2")
    WebElement confirmPasswordField;

    @FindBy(name = "fullname")
    WebElement fullNameField;

    @FindBy(name = "email")
    WebElement emailField;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageURL() {
        return getBaseUrl() + getSignUpUrlPart();
    }

    @Override
    protected void checkUniqueElements() throws Error {

    }

    public String getSignUpUrlPart() {
        return signUpUrlPart;
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