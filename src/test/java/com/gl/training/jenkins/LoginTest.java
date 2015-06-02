package com.gl.training.jenkins;


import com.gl.training.BaseTestNG;
import com.gl.training.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.gl.training.utils.CommonOperations.verifyCurrentUrl;
import static com.gl.training.utils.DataProvider.getBaseUrl;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTestNG {

    private LoginPage loginPage;


    private String loginErrorUrlPart = "/loginError";
    private String loginSuccessUrlPart = "/securityRealm/createAccount";

    private String loggedInFullNameLocatorXpath = "//a[@class='model-link inside inverse']/b";


    @BeforeMethod
    public void setUp(){
        driver.manage().deleteAllCookies();
        loginPage = new LoginPage(driver);
        loginPage.get();
    }

    @Test(dataProvider = "negativeLoginData")
    public void negativeTest(String name, String password, String logMessage){
        log.info(logMessage);
        loginPage.submitLogin(name, password);
        verifyCurrentUrl(driver, getBaseUrl() + loginErrorUrlPart);
        WebElement txtErrorMessage = driver.findElement(By.xpath("//div[@id='main-panel-content']/div"));
        String actualErrorMessage = (txtErrorMessage.getText().split("\n"))[0];
        String expectedErrorMessage = "Invalid login information. Please try again.";
        assertEquals(actualErrorMessage, "Invalid login information. Please try again.",
                "Error message is: '" + actualErrorMessage + "', but expected: '" + expectedErrorMessage + "'!");
    }

    @Test(dataProvider = "positiveLoginData")
    public void positiveTest(String name, String password, String logMessage){
        log.info(logMessage);
        loginPage.submitLogin(name, password);
        verifyCurrentUrl(driver, getBaseUrl() + loginSuccessUrlPart);


    }

}
