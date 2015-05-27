package com.gl.training.jenkins;

import com.gl.training.BaseTestNG;
import com.gl.training.pages.SignUpPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import static com.gl.training.Settings.getBaseUrl;
import static com.gl.training.utils.CommonOperations.*;
import static com.gl.training.utils.CommonOperations.verifyCurrentUrl;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignUpTest extends BaseTestNG {

    private SignUpPage signUpPage;

    private String loginErrorUrlPart = "/loginError";
    private String signUpSuccessUrlPart = "/securityRealm/createAccount";
    private String successMessageLocator = "//div[@id='main-panel-content']/h1";

    private String loggedInFullNameLocatorXpath = "//a[@class='model-link inside inverse']/b";

    @BeforeMethod
    public void setUp() {
        DeleteAllCookies();
        signUpPage = new SignUpPage(driver);
        signUpPage.get();
    }

    @Test(dataProvider = "negativeSignUpData")
    public void negativeTest(String name, String password, String confirmPassword,
                             String fullName, String email, String logMessage) {
        log(logMessage);
        signUpPage.submitSignUp(name, password, confirmPassword, fullName, email);
        verifyCurrentUrl(driver, getBaseUrl() + loginErrorUrlPart);
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
        signUpPage.submitSignUp(name, password, confirmPassword, fullName, email);
        verifyCurrentUrl(driver, getBaseUrl() + loginErrorUrlPart);
    }

}
