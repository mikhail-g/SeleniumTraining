package com.gl.training.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.gl.training.utils.DataProvider.Pause;

public abstract class Page<T extends Page<T>> extends LoadableComponent<T> {

    protected final Logger log = LogManager.getLogger(this);
    protected final WebDriver wd;

    public Page(WebDriver wd) {
        this.wd = wd;
        PageFactory.initElements(wd, this);
    }

    public abstract String getPageURL();

    protected abstract void checkUniqueElements() throws Error;

    @Override
    protected void load() {
        log.info("Loading page: {}", getPageURL());
        wd.get(getPageURL());
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertThat("Wrong page URL", wd.getCurrentUrl(), Matchers.equalToIgnoringCase(getPageURL()));
        checkUniqueElements();
    }

    public WebElement waitForElementClickable(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(wd, Pause);
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected void sleep(Integer pause) {
        try {
            Thread.sleep(pause * 1000);
        } catch (InterruptedException ignored) {
        }
    }

//    public WebElement waitForElementPresent(WebElement we){
//        try {
//            WebDriverWait wait = new WebDriverWait(wd, 10);
//            wait.until(ExpectedConditions.presenceOfElementLocated(byLocator(locator)));
//            wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator(locator)));
//            wait.until(ExpectedConditions.elementToBeClickable(byLocator(locator)));
//        } catch (Exception e) {
//            throw e;
//        }
//        return we;
//    }


}
