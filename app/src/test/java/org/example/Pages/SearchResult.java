package org.example.Pages;

import org.example.SeleniumWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchResult {
    WebDriver driver;

    @FindBy(css = ".a-size-medium.a-color-base.a-text-normal") List<WebElement> items;

    @FindBy(css = ".a-size-medium.a-color-base") WebElement noResultItem;

    SeleniumWrapper seleniumWrapper = new SeleniumWrapper();

    public SearchResult(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public boolean verifyValidSearchResult(String item){
        boolean status = false;
        try {
            String[] productName = item.split(" ");
            for(WebElement element:items){
                ArrayList<String> list = new ArrayList<>();
                if(element.getText().contains(productName[0])){
                    list.add(element.getText());
                    WebElement priceElement = element.findElement(By.xpath("//ancestor::div[@class='a-section a-spacing-small a-spacing-top-small']//child::span[@class='a-price-whole']"));
                    String price = priceElement.getText();
                    list.add(price);
                    status = true;
                }
                System.out.println(list);
            }
            return status;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception");
            return status;
        }
    }

    public boolean verifyInvalidSearchResult(String item){
        boolean status = false;
        try{
            status = noResultItem.getText().contains("No results for");
            System.out.println(noResultItem.getText()+" "+item);
            return status;
        }catch (Exception e){
            System.out.println("Test case fail");
            return status;
        }
    }




}
