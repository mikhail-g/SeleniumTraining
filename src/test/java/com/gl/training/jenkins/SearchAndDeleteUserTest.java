package com.gl.training.jenkins;


import com.gl.training.BaseTestNG;
import com.gl.training.entities.User;
import com.gl.training.pages.HomePage;
import com.gl.training.pages.LoginPage;
import com.gl.training.pages.UserProfileDeletePage;
import com.gl.training.pages.UserProfilePage;
import org.testng.annotations.Test;

import static com.gl.training.utils.DataProvider.getAdminUser;

public class SearchAndDeleteUserTest extends BaseTestNG{



    @Test
    public void searchForUser(){
        LoginPage loginPage = new LoginPage(driver).get();
        loginPage.submitLogin(getAdminUser());

        log.info("Try to open profile of created users");
        UserProfilePage userProfilePage = loginPage.getHeader().clickSearchResult("User", "User#");
        userProfilePage.checkUniqueElements();
    }

    @Test
    public void deleteUser(){
        for (int i = 0; i < 20; i++) {
            LoginPage loginPage = new LoginPage(driver).get();
            loginPage.submitLogin(getAdminUser());

            log.info("Try to open profile of created users");
            UserProfilePage userProfilePage = loginPage.getHeader().clickSearchResult("User", "User#");
            userProfilePage.checkUniqueElements();
            log.info("Try to delete created users");
            UserProfileDeletePage userProfileDeletePage = userProfilePage.clickDelete();
            HomePage homePage = userProfileDeletePage.submitDeletion();
            homePage.checkUniqueElements();
        }
    }
}
