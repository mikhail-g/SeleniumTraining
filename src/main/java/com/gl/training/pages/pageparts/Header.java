package com.gl.training.pages.pageparts;


import com.gl.training.entities.User;
import com.gl.training.pages.UserProfilePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.gl.training.utils.CommonOperations.sendKeys;

public class Header {

    protected final Logger log = LogManager.getLogger(this);
    WebDriver driver;

    private static final String locator = "//div";
    private static final String loc2 = locator+"[1]";

    @FindBy(xpath = locator)
    List<WebElement> listOfOptions;

    @FindBy(name = "search")
    private WebElement searchForm;

    @FindBy(id = "search-box")
    private WebElement searchField;

    @FindAll({@FindBy(xpath = "//div[@id='search-box-completion']//li")})
    private List<WebElement> results;

    public Header(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public Header selectAllOptiosForUser(String userName) {
        List<WebElement> elements = driver.findElements(By.cssSelector(String.format("tr[name='%s'] > td", userName)));
        elements
                .stream()
                .filter(el -> el.getText().isEmpty())
                .forEach(WebElement::click);
        return this;
    }

    public Header enterTextToSearch(String text){
        sendKeys(searchField, text);
        return this;
    }


    /**Opens User Profile Page by entering username of existing user
     * into search field in the page header
     *
     * @param text username of existing user
     * @return UserProfilePage
     */
    public UserProfilePage submitTextToSearch(String text){
        enterTextToSearch(text);
        searchForm.submit();
        return new UserProfilePage(driver, new User(text, text));
    }

    private WebElement getSearchResult(String searchedText){
        for (int i = 0; i < results.size(); i++) {
            WebElement element = results.iterator().next();
            String text = element.getText();
            if(text.contains(searchedText)){
                return element;
            }
        }
        log.warn("There is no result with text: '" + searchedText + "' among " + results.size() + " results");
        return null;
    }

    public UserProfilePage clickSearchResult(String textToSearch, String resultText){
        enterTextToSearch(textToSearch);
        WebElement searchResultItem = getSearchResult(resultText);
        if (searchResultItem == null) throw
                new AssertionError("No item found for text: '"+resultText+"' in search results drop-down");
        String name = searchResultItem.getText();
        searchResultItem.click();
        searchForm.submit();
        log.info("Try to open profile of users with name: '"+name+"'");
        return new UserProfilePage(driver);
    }

//    public Header search(String test){
//        Actions builder = new Actions(driver);
//        builder.sendKeys(searchField, test).keyDown(Keys.CONTROL).click(searchField).click(searchField).keyUp(Keys.CONTROL);
//        Action selectMultiple = builder.build();
//        selectMultiple.perform();
//        return this;
//    }

//    @FindBys(xpath = "//div[@id='search-box-completion']/id")
//    private


}
