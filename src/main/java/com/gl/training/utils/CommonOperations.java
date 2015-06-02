package com.gl.training.utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertTrue;

public class CommonOperations {



    public static void verifyCurrentUrl(WebDriver driver, String expectedUrl){
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.equals(expectedUrl),
                "Current URL is: '" + currentUrl + "', but expected URL is: '" + expectedUrl + "'!");
    }

    public static String sendKeys(WebElement we, String text, boolean doClearBefore){
        if (doClearBefore) {
            we.clear();
        }
        we.sendKeys(text);
        return text;
    }

    public static String sendKeys(WebElement we, String name){
        return sendKeys(we, name, true);
    }
}
