package org.example.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SearchResult {
    WebDriver driver;

    @FindBy(css = ".a-size-medium.a-color-base.a-text-normal") WebElement itemName;

    public SearchResult(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public boolean verifySearchResult(String item){
        boolean status = false;
        try {
            String[] items = item.split(" ");            
            if(itemName.getText().contains(items[0])){
                status = true;
            }else{
                status = false;
            }
            return status;
        } catch (Exception e) {
            // TODO: handle exception
            return status;
        }
    }

}
