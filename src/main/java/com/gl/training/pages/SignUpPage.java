package com.gl.training.pages;

import com.gl.training.entities.User;
import com.gl.training.pages.pageparts.Header;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public SignUpPage(WebDriver wd){
        super(wd);
        this.header = new Header(wd);
    }

    public Header getHeader() {
        return header;
    }

    private Header header;

    @Override
    public String getPageURL() {
        return getBaseUrl() + getSignUpUrlPart();
    }

    @Override
    protected void checkUniqueElements() throws Error {
        //TODO add
    }

    //url:
    private static String signUpUrlPart = "/signup";

    public static String getSignUpUrlPart() {
        return signUpUrlPart;
    }

    public SignUpPage submitSignUp(String name, String password, String confirmPassword, String fullName, String email) {
        log.info("Try to sign up with name: '" + name + "', password: '" + password +
                "', confirm password: '" + confirmPassword + "', full name: '" + fullName + "', email: '" + email + "'");
        sendKeys(userNameField, name);
        sendKeys(passwordField, password);
        sendKeys(confirmPasswordField, confirmPassword);
        sendKeys(fullNameField, fullName);
        sendKeys(emailField, email);
        signUpForm.submit();
        return this;
    }

    public SignUpPage submitSignUp(User user, String confirmPassword){
        return submitSignUp(user.getName(), user.getPassword(), confirmPassword, user.getFullName(), user.getEmail());
    }

    public SignUpPage waitForPageLoaded() {
        waitForElementClickable(userNameField);
        return this;
    }
}