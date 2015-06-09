package com.gl.training.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertEquals;

public class CommonOperations {

    public static void verifyCurrentUrl(WebDriver driver, String expectedUrl){
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.length() > 0 && currentUrl.charAt(currentUrl.length()-1)=='/') {
            currentUrl = currentUrl.substring(0, currentUrl.length()-1);
        }
        assertEquals(currentUrl, expectedUrl,
                "Current URL is: '" + currentUrl + "', but expected URL is: '" + expectedUrl + "'!");
    }

    public static WebElement sendKeys(WebElement we, String text){
        return sendKeys(we, text, true);
    }

    public static WebElement sendKeys(WebElement we, String text, boolean doClear) {
        if (doClear) {
            we.clear();
        }
        we.sendKeys(text);
        return we;
    }


}
