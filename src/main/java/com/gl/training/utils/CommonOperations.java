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


    public static String formatFileSize(long size) {
        String hrSize;

        long b = size;
        long k = size/1024;
        long m = ((size/1024)/1024);
        long g = (((size/1024)/1024)/1024);
        long t = ((((size/1024)/1024)/1024)/1024);

        if ( t>1 ) {
            hrSize = t + " TB";
        } else if ( g>1 ) {
            hrSize = g + " GB";
        } else if ( m>1 ) {
            hrSize = m + " MB";
        } else if ( k>1 ) {
            hrSize = k + " KB";
        } else {
            hrSize = b + " B";
        }

        return hrSize;
    }

    public static String formatFileSize1(long size) {
        String hrSize;

        long b = size;
        long k = size/1024;
        double m = ((size/1024)/1024.0);
        double g = (((size/1024)/1024)/1024.0);
        double t = ((((size/1024)/1024)/1024)/1024.0);

        if ( t>1 ) {
            hrSize = t + " TB";
        } else if ( g>1 ) {
            hrSize = g + " GB";
        } else if ( m>1 ) {
            hrSize = m + " MB";
        } else if ( k>1 ) {
            hrSize = k + " KB";
        } else {
            hrSize = b + " B";
        }

        return hrSize;
    }
    
    public static void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
