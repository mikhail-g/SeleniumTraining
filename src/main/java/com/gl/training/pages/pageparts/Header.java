package com.gl.training.pages.pageparts;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header {

    @FindBy(name = "search")
    private WebElement searchForm;

    private WebElement searchField = searchForm.findElement(By.id("search-box"));

//    @FindBys(xpath = "//div[@id='search-box-completion']/id")
//    private


}
