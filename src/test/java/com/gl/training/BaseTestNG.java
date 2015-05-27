package com.gl.training;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class BaseTestNG {

    protected FirefoxDriver driver;

    protected String text = "asdfga";
    protected String alphaNum = "2TEst42";
    protected String specChar = "!@#$%^&*()_+";
    protected String anyCharSet = " Aa2!@#$%^&*()_+-={}[]:;<>,./?\"|\\";
    protected String lessThreeChar = "as";
    protected String longText50 = "asdfghjkloasdfghjkloasdfghjkloasdfghjkloasdfghjkloasdfghjklo";
    protected String emptyText = "";
    protected String startsFromSpace = "  11Dd";
    protected String localizationText = "АукцЫон";

    @BeforeClass
    public void startDriver(){
        driver = new FirefoxDriver();
    }

    @AfterClass
    public void shutDown(){
        driver.quit();
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

    @DataProvider
    public Object[][] positiveSignUpData(){
        return new Object[][]{
                {}
        };
    }

    @DataProvider
    public Object[][] negativeSignUpData(){
        return new Object[][]{
                {}
        };
    }

    protected void DeleteAllCookies() {
        driver.manage().deleteAllCookies();
    }
}
