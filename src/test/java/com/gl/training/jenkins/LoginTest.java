package jenkins;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {

    private FirefoxDriver driver;
    private String url = "http://dou.ua/";
    private String text = "test";
    String resultsLocator = "class=gsc-results gsc-webResult";
    String expectedUrl = "http://dou.ua/search/?q="+text;


    @BeforeClass
    public void setUp(){
        driver = new FirefoxDriver();
    }

    @Test
    public void myFirstTest(){
        driver.get(url);

        WebElement searchForm = driver.findElementByClassName("search");
        WebElement searchField = searchForm.findElement(By.id("txtGlobalSearch"));
        searchField.clear();
        searchField.sendKeys(text);
        searchForm.submit();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.equals(expectedUrl), "Current url is: " + currentUrl + ", but expected url is: " + expectedUrl);
    }

    @AfterClass
    public void shutDown(){
        driver.quit();
    }
}
