package com.gl.training.pages.pageparts;

import com.gl.training.pages.Page;
import org.openqa.selenium.WebDriver;


public class ManageJenkinsPage extends Page<ManageJenkinsPage> {
    public ManageJenkinsPage(WebDriver wd) {
        super(wd);
    }

    @Override
    public String getPageURL() {
        return null;
    }

    @Override
    protected void checkUniqueElements() throws Error {

    }
}
