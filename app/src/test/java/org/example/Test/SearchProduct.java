package org.example.Test;

import java.io.IOException;

import org.example.DriverSingleton;
import org.example.Pages.Home;
import org.example.Pages.SearchResult;
import org.example.ReportSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SearchProduct {
    WebDriver driver; 

    ExtentReports report;
    ExtentTest test;

    @BeforeSuite(alwaysRun = true)
    public void createDriver(){
        DriverSingleton driverSingleton = new DriverSingleton();
        driver = driverSingleton.getInstence();     
        
        ReportSingleton reportSingleton = ReportSingleton.getInstanceOfSingletonReport();
        report = reportSingleton.getReport();
        test = report.startTest("SearchProduct");
    }

    @Test(description = "Search for valid product")
    public void searchValidProduct() throws IOException{
        boolean status = false;

        Home home = new Home(driver);

        home.navigateToHomePage();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.amazon.in/");
        test.log(LogStatus.INFO,"Screenshot: "+ test.addScreenCapture(ReportSingleton.captureScreenShot(driver)));


        SearchResult searchResult = new SearchResult(driver);

        // search the valid item
        status =  home.searchItem("laptop");

        Assert.assertTrue(status,"Not able to search the item");
        test.log(LogStatus.INFO,"Screenshot: "+ test.addScreenCapture(ReportSingleton.captureScreenShot(driver)));


        status = searchResult.verifyValidSearchResult("Laptop");

        Assert.assertTrue(status,"Mismatch in search result");
        test.log(LogStatus.INFO,"Screenshot: "+ test.addScreenCapture(ReportSingleton.captureScreenShot(driver)));

    }

    @Test(description = "Search for invalid product")
    public void searchInvalidProduct() throws IOException{
        boolean status = false;

        Home home = new Home(driver);

        home.navigateToHomePage();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.amazon.in/");
        test.log(LogStatus.INFO,"Screenshot: "+ test.addScreenCapture(ReportSingleton.captureScreenShot(driver)));


        //search the Invalid item
        status = home.searchItem("oihgfr urhgoir ;hrgf sr uharfo huerf %# hrfor");

        Assert.assertTrue(status,"Not able to search the item");

        SearchResult searchResult = new SearchResult(driver);
        test.log(LogStatus.INFO,"Screenshot: "+ test.addScreenCapture(ReportSingleton.captureScreenShot(driver)));


        status = searchResult.verifyInvalidSearchResult("oihgfr urhgoir ;hrgf sr uharfo huerf %# hrfor");

        Assert.assertTrue(status,"Mismatch in search result");
        test.log(LogStatus.INFO,"Screenshot: "+ test.addScreenCapture(ReportSingleton.captureScreenShot(driver)));

    }

    @AfterSuite
    public void closeDriver(){
        driver.quit();       
        
        report.endTest(test);
        report.flush();
    } 

}
