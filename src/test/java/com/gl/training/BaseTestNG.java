package com.gl.training;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.util.Date;

public class BaseTestNG {

//    private String baseUrl = "http://seltr-kbp1-1.synapse.com:8080";

    protected String alphaNum = "2TEst42";

    protected FirefoxDriver driver;
    protected final Logger log = LogManager.getLogger(this);

    private String anyCharSet = " Aa2!@#$%^&*()_+-={}[]:;<>,./?\"|\\";
    private String lessThreeChar = "as";
    private String emptyText = "";
    private String text = "asdfga";
    private String specChar = "!@#$%^&*()_+";
    private String longText50 = "asdfghjkloasdfghjkloasdfghjkloasdfghjkloasdfghjkloasdfghjklo";
    private String localizationText = "АукцЫон";
    private String startsFromSpace = "  11Dd";
    private String validEmail = "a@b.com";


//    public String getBaseUrl() {
//        return baseUrl;
//    }

    @BeforeClass
    public void startDriver(){
        driver = new FirefoxDriver();
    }

    @AfterClass
    public void shutDown(){
        driver.quit();
    }

//    public void log(String message){
//        System.out.println("[info]: " + message);
//    }

    private String getUniqueUsername(){
        Date date = new Date();
        return "User#"+date;
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
        String uniqueName = getUniqueUsername();
        return new Object[][]{
                {uniqueName, specChar, specChar, uniqueName, validEmail, "Valid sign up"}
        };
    }

    @DataProvider
    public Object[][] negativeSignUpData(){
        String uniqueName = getUniqueUsername();
        return new Object[][]{
                {uniqueName, specChar, "", uniqueName, validEmail, "Invalid sign up. Empty confirm password field"}
        };
    }
}
