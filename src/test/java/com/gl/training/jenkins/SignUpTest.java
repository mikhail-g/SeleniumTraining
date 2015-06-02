package com.gl.training.jenkins;

import com.gl.training.BaseTestNG;
import com.gl.training.entities.User;
import com.gl.training.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.gl.training.utils.CommonOperations.verifyCurrentUrl;
import static com.gl.training.utils.DataProvider.getAdminUser;
import static com.gl.training.utils.DataProvider.getBaseUrl;
import static org.testng.Assert.assertEquals;

public class SignUpTest extends BaseTestNG {

    private SignUpPage signUpPage;


    private String negativeSignUpUrlPart = "/securityRealm/createAccount";
    private String positiveSignUpUrlPart = "/securityRealm/createAccount";
    private String successMessageLocator = "//div[@id='main-panel-content']/h1";
    private String loginErrorUrlPart = "/loginError";

    private String loggedInFullNameLocatorXpath = "//a[@class='model-link inside inverse']/b";
    private List<User> createdUsers = new ArrayList<>();


    @BeforeMethod
    public void setUp() {
        deleteAllCookies();
        signUpPage = new SignUpPage(driver).get();
        signUpPage.waitForPageLoaded();
    }

    @Test(dataProvider = "negativeSignUpData")
    public void negativeTest(User user, String confirmPassword, String expectedErrorMessage, String logMessage) {
        log.info("Test: " + logMessage);
        signUpPage.submitSignUp(user, confirmPassword);
        verifyCurrentUrl(driver, getBaseUrl()+negativeSignUpUrlPart);
        WebElement txtErrorMessage = driver.findElement(By.xpath("//div[@id='main-panel-content']/div"));
        String actualErrorMessage = (txtErrorMessage.getText().split("\n"))[0];
        assertEquals(actualErrorMessage, expectedErrorMessage,
                "Error message is: '" + actualErrorMessage + "', but expected: '" + expectedErrorMessage + "'!");
    }

    @Test(dataProvider = "positiveSignUpData")
    public void positiveTest(User user, String confirmPassword, String logMessage) {
        createdUsers.add(user);
        log.info("Test: " + logMessage);
        signUpPage.submitSignUp(user, confirmPassword);
        verifyCurrentUrl(driver, getBaseUrl()+ positiveSignUpUrlPart);
    }

    @Test(dependsOnMethods = "positiveTest")
    public void deleteUser(){
        LoginPage loginPage = new LoginPage(driver).get();
        loginPage.submitLogin(getAdminUser());
        for (int i = 0; i < createdUsers.size(); i++) {
            User user = createdUsers.iterator().next();
                    log.info("Try to delete created users");
            signUpPage.getHeader().submitTextToSearch(user.getName());
            UserProfilePage userProfilePage = new UserProfilePage(driver, user);
            UserProfileDeletePage userProfileDeletePage = userProfilePage.clickDelete();
            HomePage homePage = userProfileDeletePage.submitDeletion();
            homePage.checkUniqueElements();
        }
    }
}
