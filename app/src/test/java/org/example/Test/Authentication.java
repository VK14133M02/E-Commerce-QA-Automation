package org.example.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.example.DriverSingleton;
import org.example.ReportSingleton;
import org.example.Pages.Home;
import org.example.Pages.Login;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class Authentication {
    WebDriver driver; 

    String mob;
    String pass;
    

    @BeforeSuite(alwaysRun = true)
    public void createDriver() throws IOException{
        DriverSingleton driverSingleton = new DriverSingleton();
        driver = driverSingleton.getInstence();   
        
        Properties prop = new Properties();
        FileInputStream input = new FileInputStream("C:\\Users\\hp\\Desktop\\Assignment\\E-Commerce-QA-Automation\\application.properties");

        prop.load(input);

        this.mob = prop.getProperty("MOB");
        this.pass = prop.getProperty("PASS");
    }

    @Test
    public void login(){
        boolean status = false;

        Home home = new Home(driver);
        home.navigateToHomePage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.amazon.in/");

        home.clickOnLoginButton();
        // System.out.println("The current URL is "+driver.getCurrentUrl());

        Login login = new Login(driver);
        
        status = login.enterMobileNum(mob);

        Assert.assertTrue(status,"Not able to pass the mobile number");

        status = login.enterPassword(pass);
    }

    @AfterSuite
    public void closeDriver(){
        driver.quit();        
    } 
}
