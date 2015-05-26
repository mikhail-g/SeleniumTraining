package com.gl.training.utils;


import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;

public class CommonOperations {

    //TODO make static and move to util
    public static void verifyCurrentUrl(WebDriver driver, String expectedUrl){
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.equals(expectedUrl),
                "Current URL is: '" + currentUrl + "', but expected URL is: '" + expectedUrl + "'!");
    }
}
