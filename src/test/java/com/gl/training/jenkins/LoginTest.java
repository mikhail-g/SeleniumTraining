package com.gl.training.jenkins;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest {

    WebElement loginForm;
    WebElement userField;
    WebElement passwordField;

    private FirefoxDriver driver;
    private String baseUrl = "http://seltr-kbp1-1.synapse.com:8080";
    private String loginUrlPart = "/login?from=%2F";
    private String loginErrorUrlPart = "/loginError";

    private String alphaNum = "TEst12";
    private String specChar = "!@#$%^&*()_+";
    private String longText50 = "asdfghjkloasdfghjkloasdfghjkloasdfghjkloasdfghjkloasdfghjklo";
    private String localizationText = "АукцЫон";
    private String emptyText = "";
    private String startsFromSpace = "  11Dd";
    private String text = "asdfga";

    private String formLocatorName = "login";
    private String userLocatorName = "j_username";
    private String passwordLocatorName = "j_password";


    @BeforeClass
    public void startDriver(){
        driver = new FirefoxDriver();
    }

    @BeforeMethod
    public void setUp(){
        driver.get(baseUrl + loginUrlPart);
        loginForm = driver.findElement(By.name(formLocatorName));
        userField = driver.findElement(By.name(userLocatorName));
        passwordField = driver.findElement(By.name(passwordLocatorName));
    }

    @Test
    public void negativeTest(){
        submitLogin(alphaNum, specChar);
        verifyCurrentUrl(baseUrl + loginErrorUrlPart);
        WebElement txtErrorMessage = driver.findElement(By.xpath("//div[@id='main-panel-content']/div"));
        String actualErrorMessage = (txtErrorMessage.getText().split("\n"))[0];
        String expectedErrorMessage = "Invalid login information. Please try again.";
        assertEquals(actualErrorMessage, "Invalid login information. Please try again.",
                "Error message is: '"+actualErrorMessage+"', but expected: '"+expectedErrorMessage+"'!");
    }

    @Test
    public void positiveTest(){
        submitLogin(alphaNum, specChar);
        verifyCurrentUrl(baseUrl + loginErrorUrlPart);
    }

    public void verifyCurrentUrl(String expectedUrl){
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.equals(expectedUrl),
                "Current URL is: '" + currentUrl + "', but expected URL is: '" + expectedUrl + "'!");
    }

    public void submitLogin(String name, String password){
        userField.clear();
        userField.sendKeys(name);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginForm.submit();
    }

    @AfterClass
    public void shutDown(){
        driver.quit();
    }
}
