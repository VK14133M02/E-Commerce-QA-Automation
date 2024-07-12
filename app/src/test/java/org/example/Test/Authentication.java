package org.example.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.example.DriverSingleton;
import org.example.Pages.Home;
import org.example.Pages.Login;
import org.example.ReportSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Authentication {
    WebDriver driver; 

    ExtentReports report;
    ExtentTest test;

    String mob;
    String pass;
    

    @BeforeSuite(alwaysRun = true)
    public void createDriver() throws IOException{
        DriverSingleton driverSingleton = new DriverSingleton();
        driver = driverSingleton.getInstence();   
        
        Properties prop = new Properties();
        FileInputStream input = new FileInputStream("C:\\Users\\Vikram Kumar\\Desktop\\Assignment\\E-Commerce-QA-Automation\\application.properties");

        prop.load(input);

        this.mob = prop.getProperty("MOB");
        this.pass = prop.getProperty("PASS");

        ReportSingleton reportSingleton = ReportSingleton.getInstanceOfSingletonReport();
        report = reportSingleton.getReport();
        test = report.startTest("Authentication");
    }

    @Test(description = "Login with valid credential")
    public void validLogin() throws IOException{
        boolean status = false;

        Home home = new Home(driver);
        home.navigateToHomePage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.amazon.in/");
        test.log(LogStatus.INFO,"Screenshot: "+ test.addScreenCapture(ReportSingleton.captureScreenShot(driver)));

        home.clickOnLoginButton();
        // System.out.println("The current URL is "+driver.getCurrentUrl());

        Login login = new Login(driver);
        
        status = login.enterValidMobileNum(mob);

        Assert.assertTrue(status,"Not able to pass the mobile number");
        test.log(LogStatus.INFO,"Screenshot: "+ test.addScreenCapture(ReportSingleton.captureScreenShot(driver)));


        status = login.enterValidPassword(pass);

        Assert.assertTrue(status,"Not able to pass the password");
        test.log(LogStatus.INFO,"Screenshot: "+ test.addScreenCapture(ReportSingleton.captureScreenShot(driver)));


        home.signOut();
    }


    @Test(description = "Login with Invalid Credential")
    public void inValidLogin() throws IOException{
        boolean status = false;

        Home home = new Home(driver);
        home.navigateToHomePage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.amazon.in/");
        test.log(LogStatus.INFO,"Screenshot: "+ test.addScreenCapture(ReportSingleton.captureScreenShot(driver)));

        home.clickOnLoginButton();
        // System.out.println("The current URL is "+driver.getCurrentUrl());

        Login login = new Login(driver);

        status = login.enterInvalidMobileNum("0123456789");
        Assert.assertTrue(status,"Error in login with invlaid mobile number");
        test.log(LogStatus.INFO,"Screenshot: "+ test.addScreenCapture(ReportSingleton.captureScreenShot(driver)));

    }

    @AfterSuite
    public void closeDriver(){
        driver.quit();        

        report.endTest(test);
        report.flush();
    } 
}
