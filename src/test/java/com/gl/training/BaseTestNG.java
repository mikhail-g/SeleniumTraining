package com.gl.training;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

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
}
