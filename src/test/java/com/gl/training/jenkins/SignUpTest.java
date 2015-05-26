package com.gl.training.jenkins;


import com.gl.training.BaseTestNG;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import static com.gl.training.utils.CommonOperations.verifyCurrentUrl;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignUpTest extends BaseTestNG {

    WebElement signUpForm;
    WebElement userNameField;
    WebElement passwordField;
    WebElement confirmPasswordField;
    WebElement fullNameField;
    WebElement emailField;
    //url:
    private String signUpUrlPart = "/signup";
    private String loginErrorUrlPart = "/loginError";
    private String signUpSuccessUrlPart = "/securityRealm/createAccount";

    //locator:
    private String formSignUpLocatorXpath = "//form[@action='/securityRealm/createAccount']";
    private String inputUserNameLocatorName = "username";
    private String inputPasswordLocatorName = "password1";
    private String inputConfirmPasswordLocatorName = "password2";
    private String inputFullNameLocatorName = "fullname";
    private String inputEmailLocatorName = "email";
    private String successMessageLocator = "//div[@id='main-panel-content']/h1";
    private String loggedInFullNameLocatorXpath = "//a[@class='model-link inside inverse']/b";

    @BeforeMethod
    public void setUp() {
        driver.manage().deleteAllCookies();
        driver.get(baseUrl + signUpUrlPart);
        signUpForm = driver.findElement(By.xpath(formSignUpLocatorXpath));
        userNameField = driver.findElement(By.name(inputUserNameLocatorName));
        passwordField = driver.findElement(By.name(inputPasswordLocatorName));
        confirmPasswordField = driver.findElement(By.name(inputConfirmPasswordLocatorName));
        fullNameField = driver.findElement(By.name(inputFullNameLocatorName));
        emailField = driver.findElement(By.name(inputEmailLocatorName));
    }

    @Test(dataProvider = "negativeSignUpData")
    public void negativeTest(String name, String password, String confirmPassword,
                             String fullName, String email, String logMessage) {
        log(logMessage);
        submitSignUp(name, password, confirmPassword, fullName, email);
        verifyCurrentUrl(driver, baseUrl + loginErrorUrlPart);
        WebElement txtErrorMessage = driver.findElement(By.xpath("//div[@id='main-panel-content']/div"));
        String actualErrorMessage = (txtErrorMessage.getText().split("\n"))[0];
        String expectedErrorMessage = "Invalid login information. Please try again.";
        assertEquals(actualErrorMessage, "Invalid login information. Please try again.",
                "Error message is: '" + actualErrorMessage + "', but expected: '" + expectedErrorMessage + "'!");
    }

    @Test(dataProvider = "positiveSignUpData")
    public void positiveTest(String name, String password, String confirmPassword,
                             String fullName, String email, String logMessage) {
        log(logMessage);
        submitSignUp(name, password, confirmPassword, fullName, email);
        verifyCurrentUrl(driver, baseUrl + loginErrorUrlPart);
    }

    public void submitSignUp(String name, String password, String confirmPassword, String fullName, String email) {
        sendKeys(userNameField, name);
        sendKeys(passwordField, password);
        sendKeys(confirmPasswordField, confirmPassword);
        sendKeys(fullNameField, fullName);
        sendKeys(emailField, email);
        signUpForm.submit();
    }

    @DataProvider
    public Object[][] positiveSignUpData(){
        return new Object[][]{
                {}
        };
    }

    @DataProvider
    public Object[][] negativeSignUpData(){
        return new Object[][]{
                {}
        };
    }

}
