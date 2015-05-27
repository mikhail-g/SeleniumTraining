package com.gl.training;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class BaseTestNG {
    protected String alphaNum = "2TEst42";
    protected String anyCharSet = " Aa2!@#$%^&*()_+-={}[]:;<>,./?\"|\\";
    protected String lessThreeChar = "as";
    protected String emptyText = "";
    protected FirefoxDriver driver;
    protected String baseUrl = "http://seltr-kbp1-1.synapse.com:8080";
    protected String text = "asdfga";
    protected String specChar = "!@#$%^&*()_+";
    protected String longText50 = "asdfghjkloasdfghjkloasdfghjkloasdfghjkloasdfghjkloasdfghjklo";
    protected String localizationText = "АукцЫон";
    protected String startsFromSpace = "  11Dd";

    @BeforeClass
    public void startDriver(){
        driver = new FirefoxDriver();
    }

    @AfterClass
    public void shutDown(){
        driver.quit();
    }

    public void log(String message){
        System.out.println("[info]: " + message);
    }

    protected WebElement sendKeys(WebElement we, String text){
        return sendKeys(we, text, true);
    }

    protected WebElement sendKeys(WebElement we, String text, boolean doClear){
        if (doClear){we.clear();}
        we.sendKeys(text);
        return we;
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
}
