package org.example.Test;

import org.example.DriverSingleton;
import org.example.Pages.Home;
import org.example.Pages.SearchResult;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SearchProduct {
    WebDriver driver; 

    @BeforeSuite(alwaysRun = true)
    public void createDriver(){
        DriverSingleton driverSingleton = new DriverSingleton();
        driver = driverSingleton.getInstence();                  
    }

    @Test
    public void searchProduct(){
        boolean status = false;

        Home home = new Home(driver);

        home.navigateToHomePage();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.amazon.in/");

        status =  home.searchItem("laptop");

        Assert.assertTrue(status,"Not able to search the item");

        SearchResult searchResult = new SearchResult(driver);
        status = searchResult.verifySearchResult("Laptop");

        Assert.assertTrue(status,"Mismatch in search result");
    }

    @AfterSuite
    public void closeDriver(){
        driver.quit();        
    } 

}
