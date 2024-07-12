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

    @Test(description = "Search for valid product")
    public void searchValidProduct(){
        boolean status = false;

        Home home = new Home(driver);

        home.navigateToHomePage();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.amazon.in/");

        SearchResult searchResult = new SearchResult(driver);

        // search the valid item
        status =  home.searchItem("laptop");

        Assert.assertTrue(status,"Not able to search the item");

        status = searchResult.verifyValidSearchResult("Laptop");

        Assert.assertTrue(status,"Mismatch in search result");
    }

    @Test(description = "Search for invalid product")
    public void searchInvalidProduct(){
        boolean status = false;

        Home home = new Home(driver);

        home.navigateToHomePage();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.amazon.in/");

        //search the Invalid item
        status = home.searchItem("oihgfr urhgoir ;hrgf sr uharfo huerf %# hrfor");

        Assert.assertTrue(status,"Not able to search the item");

        SearchResult searchResult = new SearchResult(driver);

        status = searchResult.verifyInvalidSearchResult("oihgfr urhgoir ;hrgf sr uharfo huerf %# hrfor");

        Assert.assertTrue(status,"Mismatch in search result");
    }

    @AfterSuite
    public void closeDriver(){
        driver.quit();        
    } 

}
