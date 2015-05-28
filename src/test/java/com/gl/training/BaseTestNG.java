package com.gl.training;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.util.Date;

public class BaseTestNG {

    protected final Logger log = LogManager.getLogger(this);
    protected FirefoxDriver driver = null;

    protected String text = "asdfga";
    protected String alphaNum = "2TEst42";
    protected String specChar = "!@#$%^&*()_+";
    protected String anyCharSet = " Aa2!@#$%^&*()_+-={}[]:;<>,./?\"|\\";
    protected String lessThreeChar = "as";
    protected String longText50 = "asdfghjkloasdfghjkloasdfghjkloasdfghjkloasdfghjkloasdfghjklo";
    protected String emptyText = "";
    protected String startsFromSpace = "  11Dd";
    protected String localizationText = "АукцЫон";

    private String uniqueName;

    @BeforeClass
    public void startDriver(){
        driver = new FirefoxDriver();
        setUniqueName();
    }

    @AfterClass
    public void shutDown(){
        driver.quit();
    }

    public String setUniqueName(){
        String name = alphaNum+new Date().getTime();
        log.info("Unique name is: "+ name);
        return uniqueName = name;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    @DataProvider
    public Object[][] positiveLoginData(){
        return new Object[][]{
                {getUniqueName(), anyCharSet, "All allowed characters validation"}
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
                {getUniqueName(), anyCharSet, anyCharSet, getUniqueName(), "@", "Sign up with valid data"}
        };
    }

    @DataProvider
    public Object[][] negativeSignUpData(){
        return new Object[][]{
                {alphaNum, alphaNum, alphaNum, alphaNum, alphaNum, "Sign up with invalid data"}
        };
    }

    protected void DeleteAllCookies() {
        driver.manage().deleteAllCookies();
    }
}
