package com.gl.training.utils;

import org.jetbrains.annotations.NotNull;

public enum Browser {
    CHROME,
    FIREFOX,
    IE;

    private static Browser browser = null;

    @NotNull
    public static Browser getCurrentBrowser() {
        if (browser == null) {
            browser = Browser.valueOf(System.getProperty("browser", "chrome").toUpperCase());
        }
        return browser;
    }

    public Browser parseString(String text){
        if (null==text){
            throw new NullPointerException("Parsed text is null");
        }
        Browser result;
        int num = Browser.values().length;
        for (int i = 0; i < num; i++) {
            result = Browser.values()[i];
            if (result.name().equals(text)){
                return result;
            }
        }
        throw new IllegalArgumentException("No Enum specified for this string: '"+text+"'");
    }
}
