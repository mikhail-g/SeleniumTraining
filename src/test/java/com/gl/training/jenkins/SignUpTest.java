package com.gl.training.jenkins;


import com.gl.training.BaseTestNG;
import com.gl.training.pages.SignUpPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.gl.training.utils.CommonOperations.verifyCurrentUrl;
import static org.testng.Assert.assertEquals;

public class SignUpTest extends BaseTestNG {

    private SignUpPage signUpPage;
    private String loginErrorUrlPart = "/loginError";
    private String signUpSuccessUrlPart = "/securityRealm/createAccount";

    private String loggedInFullNameLocatorXpath = "//a[@class='model-link inside inverse']/b";

    @BeforeMethod
    public void setUp() {
        driver.manage().deleteAllCookies();
        signUpPage = new SignUpPage(driver);
        signUpPage.get();
    }

    @Test(dataProvider = "negativeSignUpData")
    public void negativeTest(String name, String password, String confirmPassword,
                             String fullName, String email, String logMessage) {
        log.info(logMessage);
        signUpPage.submitSignUp(name, password, confirmPassword, fullName, email);
        verifyCurrentUrl(driver, signUpPage.getPageURL());
        WebElement txtErrorMessage = driver.findElement(By.xpath("//div[@id='main-panel-content']/div"));
        String actualErrorMessage = (txtErrorMessage.getText().split("\n"))[0];
        String expectedErrorMessage = "Invalid login information. Please try again.";
        assertEquals(actualErrorMessage, "Invalid login information. Please try again.",
                "Error message is: '" + actualErrorMessage + "', but expected: '" + expectedErrorMessage + "'!");
    }

    @Test(dataProvider = "positiveSignUpData")
    public void positiveTest(String name, String password, String confirmPassword,
                             String fullName, String email, String logMessage) {
        log.info(logMessage);
        signUpPage.submitSignUp(name, password, confirmPassword, fullName, email);
        verifyCurrentUrl(driver, signUpPage.getPageURL());
    }

    String successMessageLocator = "//div[@id='main-panel-content']/h1";


}
