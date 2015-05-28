package com.gl.training.jenkins;

import com.gl.training.BaseTestNG;
import com.gl.training.pages.SignUpPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.gl.training.Settings.getBaseUrl;
import static com.gl.training.utils.CommonOperations.log;
import static com.gl.training.utils.CommonOperations.verifyCurrentUrl;
import static org.testng.Assert.assertEquals;

public class SignUpTest extends BaseTestNG {

    private SignUpPage signUpPage;

    private String signUpResultUrl = "http://seltr-kbp1-1.synapse.com:8080/securityRealm/createAccount";
    private String signUpSuccessUrlPart = "/securityRealm/createAccount";
    private String successMessageLocator = "//div[@id='main-panel-content']/h1";

    private String loggedInFullNameLocatorXpath = "//a[@class='model-link inside inverse']/b";

    private String expectedErrorMessage = "Invalid login information. Please try again.";

    @BeforeMethod
    public void setUp() {
        DeleteAllCookies();
        signUpPage = new SignUpPage(driver).get();
    }

    @Test(dataProvider = "negativeSignUpData")
    public void negativeTest(String name, String password, String confirmPassword,
                             String fullName, String email, String logMessage) {
        log.info("Test: " + logMessage);
        signUpPage.submitSignUp(name, password, confirmPassword, fullName, email);
        verifyCurrentUrl(driver, signUpResultUrl);
        WebElement txtErrorMessage = driver.findElement(By.xpath("//div[@id='main-panel-content']/div"));
        String actualErrorMessage = (txtErrorMessage.getText().split("\n"))[0];
        assertEquals(actualErrorMessage, expectedErrorMessage,
                "Error message is: '" + actualErrorMessage + "', but expected: '" + expectedErrorMessage + "'!");
    }

    @Test(dataProvider = "positiveSignUpData")
    public void positiveTest(String name, String password, String confirmPassword,
                             String fullName, String email, String logMessage) {
        log.info("Test: " + logMessage);

        signUpPage.submitSignUp(name, password, confirmPassword, fullName, email);
        verifyCurrentUrl(driver, signUpResultUrl);
    }

}
