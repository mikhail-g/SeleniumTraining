package com.gl.training.utils;

import java.lang.invoke.MethodHandles;

public class Strings {

    protected String getClassName() {
        String[] split = Thread.currentThread().getStackTrace()[1].getClassName().split("\\.");
        return MethodHandles.lookup().lookupClass().getSimpleName();
    }

    protected String getClassName2(){
        return MethodHandles.lookup().lookupClass().getSimpleName();
    }

    String getSuper2() {
        return getClass().getSuperclass().getSimpleName();
    }

    static String splitCamelCase(String s) {
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }

    private static String LOG_TAG = Strings.class.getSimpleName();

    protected String getPageName(){
        return splitCamelCase(String.class.getSimpleName());
    }

    public static class LogUtil {
        public static String getLogString(Class<?> clazz, String message) {
            return clazz.getSimpleName() + message;
        }

    }

    String logMessage1 =LogUtil.getLogString(Strings.class, " is not opened!");
}
