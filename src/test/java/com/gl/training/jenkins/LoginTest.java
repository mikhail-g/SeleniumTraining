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
    private String baseUrl = "http://seltr-kbp1-1.synapse.com:8080";
    private String loginUrlPart = "/login?from=%2F";

    private String formLoginLocatorName = "login";
    private String inputUserLocatorName = "j_username";
    private String inputPasswordLocatorName = "j_password";

    private String text = "test";
    private String expectedUrl = "http://dou.ua/search/?q="+text;
    private String loginErrorUrl = "/loginError";



    @BeforeClass
    public void setUp(){
        driver = new FirefoxDriver();
    }

    @Test
    public void myFirstTest(){
        driver.get(baseUrl+loginUrlPart);

        WebElement loginForm = driver.findElementByClassName(formLoginLocatorName);
        WebElement userField = loginForm.findElement(By.name(inputUserLocatorName));
        WebElement passwordField = loginForm.findElement(By.name(inputPasswordLocatorName));

        userField.clear();
        userField.sendKeys(text);

        passwordField.clear();
        passwordField.sendKeys(text);
        loginForm.submit();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.equals(baseUrl+loginErrorUrl), "Current baseUrl is: " + currentUrl + ", but expected baseUrl is: " + expectedUrl);
    }

    @AfterClass
    public void shutDown(){
        driver.quit();
    }
}
