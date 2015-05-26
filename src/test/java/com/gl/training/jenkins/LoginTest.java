package com.gl.training.jenkins;


import com.gl.training.BaseTestNG;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.gl.training.utils.CommonOperations.verifyCurrentUrl;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTestNG {

    WebElement loginForm;
    WebElement userField;
    WebElement passwordField;

    private String loginUrlPart = "/login?from=%2F";
    private String loginErrorUrlPart = "/loginError";
    private String loginSuccessUrlPart = "/securityRealm/createAccount";

    private String formLocatorName = "login";
    private String userLocatorName = "j_username";
    private String passwordLocatorName = "j_password";
    private String loggedInFullNameLocatorXpath = "//a[@class='model-link inside inverse']/b";


    @BeforeMethod
    public void setUp(){
        driver.manage().deleteAllCookies();
        driver.get(baseUrl + loginUrlPart);
        loginForm = driver.findElement(By.name(formLocatorName));
        userField = driver.findElement(By.name(userLocatorName));
        passwordField = driver.findElement(By.name(passwordLocatorName));
    }

    @Test(dataProvider = "negativeLoginData")
    public void negativeTest(String name, String password, String logMessage){
        log(logMessage);
        submitLogin(name, password);
        verifyCurrentUrl(driver, baseUrl + loginErrorUrlPart);
        WebElement txtErrorMessage = driver.findElement(By.xpath("//div[@id='main-panel-content']/div"));
        String actualErrorMessage = (txtErrorMessage.getText().split("\n"))[0];
        String expectedErrorMessage = "Invalid login information. Please try again.";
        assertEquals(actualErrorMessage, "Invalid login information. Please try again.",
                "Error message is: '" + actualErrorMessage + "', but expected: '" + expectedErrorMessage + "'!");
    }

    @Test(dataProvider = "positiveLoginData")
    public void positiveTest(String name, String password, String logMessage){
        log(logMessage);
        submitLogin(name, password);
        verifyCurrentUrl(driver, baseUrl + loginSuccessUrlPart);


    }



    public void submitLogin(String name, String password){
        userField.clear();
        userField.sendKeys(name);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginForm.submit();
    }

    @DataProvider
    public Object[][] positiveLoginData(){
        return new Object[][]{
                {alphaNum, anyCharSet, "All allowed characters validation"}
        };
    }

    @DataProvider
    public Object[][] negativeLoginData(){
        return new Object[][]{
                {lessThreeChar, lessThreeChar, "Failed sign up user login"},
                {lessThreeChar, anyCharSet, "Login with wrong name"},
                {alphaNum, lessThreeChar, "Login with wrong password"},
                {emptyText, anyCharSet, "Login with empty name"},
                {alphaNum, emptyText, "Login with empty password"}
        };
    }

}
