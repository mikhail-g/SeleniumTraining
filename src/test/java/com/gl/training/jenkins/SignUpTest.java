package com.gl.training.jenkins;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignUpTest {

    WebElement signUpForm;
    WebElement userNameField;
    WebElement passwordField;
    WebElement confirmPasswordField;
    WebElement fullNameField;
    WebElement emailField;

    private FirefoxDriver driver;
    private String baseUrl = "http://seltr-kbp1-1.synapse.com:8080";
    private String signUpUrlPart = "/signup";
    private String loginErrorUrlPart = "/loginError";

    private String formSignUpLocatorXpath = "//form[@action='/securityRealm/createAccount']";
    private String inputUserNameLocatorName = "username";
    private String inputPasswordLocatorName = "password1";
    private String inputConfirmPasswordLocatorName = "password2";
    private String inputFullNameLocatorName = "fullname";
    private String inputEmailLocatorName = "email";

    private String alphaNum = "TEst12";
    private String specChar = "!@#$%^&*()_+";
    private String longText50 = "asdfghjkloasdfghjkloasdfghjkloasdfghjkloasdfghjkloasdfghjklo";
    private String localizationText = "АукцЫон";
    private String emptyText = "";
    private String startsFromSpace = "  11Dd";
    private String text = "asdfg";


    @BeforeClass
    public void startDriver(){
        driver = new FirefoxDriver();
    }

    @BeforeMethod
    public void setUp(){
        driver.get(baseUrl + signUpUrlPart);
        signUpForm = driver.findElement(By.xpath(formSignUpLocatorXpath));
        userNameField = driver.findElement(By.name(inputUserNameLocatorName));
        passwordField = driver.findElement(By.name(inputPasswordLocatorName));
        confirmPasswordField = driver.findElement(By.name(inputConfirmPasswordLocatorName));
        fullNameField = driver.findElement(By.name(inputFullNameLocatorName));
        emailField = driver.findElement(By.name(inputEmailLocatorName));
    }

    @Test
    public void negativeTest(){
        submitSignUp(alphaNum, specChar);
        verifyCurrentUrl(baseUrl + loginErrorUrlPart);
        WebElement txtErrorMessage = driver.findElement(By.xpath("//div[@id='main-panel-content']/div"));
        String actualErrorMessage = (txtErrorMessage.getText().split("\n"))[0];
        String expectedErrorMessage = "Invalid login information. Please try again.";
        assertEquals(actualErrorMessage, "Invalid login information. Please try again.",
                "Error message is: '"+actualErrorMessage+"', but expected: '"+expectedErrorMessage+"'!");
    }

    @Test
    public void positiveTest(){
        submitSignUp(alphaNum, specChar);
        verifyCurrentUrl(baseUrl + loginErrorUrlPart);
    }

    public void verifyCurrentUrl(String expectedUrl){
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.equals(expectedUrl),
                "Current URL is: '" + currentUrl + "', but expected URL is: '" + expectedUrl + "'!");
    }

    public void submitSignUp(String name, String password){
        userNameField.clear();
        userNameField.sendKeys(name);
        passwordField.clear();
        passwordField.sendKeys(password);
        signUpForm.submit();
    }

    @AfterClass
    public void shutDown(){
        driver.quit();
    }
}
