package com.gl.training.pages.pageparts;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.gl.training.utils.CommonOperations.sendKeys;

public class Header {

    WebDriver driver;

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

    public Header enterTextToSearch(String text){
        sendKeys(searchField, text);
        return this;
    }

    public void submitTextToSearch(String text){
        enterTextToSearch(text);
        searchForm.submit();
    }

    private WebElement getSearchResult(String searchedText){
        for (int i = 0; i < results.size(); i++) {
            WebElement element = results.iterator().next();
            String text = element.getText();
            if(text.equals(searchedText)){
                return element;
            }
        }
        return null;
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
