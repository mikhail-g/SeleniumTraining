package com.gl.training.utils;


import com.gl.training.entities.User;

public class DataProvider {

    private static final String baseUrl = "http://seltr-kbp1-1.synapse.com:8080";

    public static final int Pause = 10;

    public static String getBaseUrl() {
        return baseUrl;
    }

    private static User adminUser;

    private static User setAdminUser(){
        return adminUser = new User("admin", "admin");
    }

    public static User getAdminUser(){
        if (null == adminUser){setAdminUser();}
        return adminUser;
    }
}
