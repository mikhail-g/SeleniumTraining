package com.gl.training;

import com.gl.training.entities.User;
import com.gl.training.utils.web.WebDriverController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.util.Date;

public class BaseTestNG {

    protected final Logger log = LogManager.getLogger(this);
    protected WebDriver driver = null;

    protected String text = "asdfga";
    protected String alphaNum = "2TEst42";
    protected String specChar = "!@#$%^&*()_+";
    protected String anyCharSet = " Aa2!@#$%^&*()_+-={}[]:;<>,./?\"|\\";
    protected String lessThreeChar = "as";
    protected String longText50 = "asdfghjkloasdfghjkloasdfghjkloasdfghjkloasdfghjkloasdfghjklo";
    protected String emptyText = "";
    protected String startsFromSpace = "  11Dd";
    protected String localizationText = "АукцЫон";
    private String validEmail = "a@b.com";
    private String admin = "admin";

    private String uniqueName;

    @BeforeClass
    public void startDriver(){
        driver = WebDriverController.getDriver();
        setUniqueName();
    }

    @AfterClass
    public void shutDown(){
        driver.quit();
    }

    public String setUniqueName(){
        String name = "User#"+new Date().getTime();
        log.info("Unique name is: "+ name);
        return uniqueName = name;
    }

    public String getUniqueName() {
        return uniqueName;
    }


    protected void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    @DataProvider
    public Object[][] positiveLoginData(){
        return new Object[][]{
                {admin, admin, "All allowed characters validation"}
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

    @DataProvider
    public Object[][] positiveSignUpData(){
        return new Object[][]{
                {new User(setUniqueName(), specChar, getUniqueName(), validEmail), specChar, "Valid sign up"}
        };
    }

    @DataProvider
    public Object[][] negativeSignUpData(){
        return new Object[][]{
                {new User("", specChar, setUniqueName(), validEmail), specChar, "User name is required", "Invalid sign up. Empty username field"},
                {new User(setUniqueName(), specChar, getUniqueName(), validEmail), "", "Password didnt match", "Invalid sign up. Empty confirm password field"},
                {new User(setUniqueName(), specChar, getUniqueName(), text), specChar, "Invalid e-mail address", "Invalid sign up. Invalid email field"}
        };
    }
}
