package org.example.Pages;

import java.time.Duration;

import javax.swing.tree.ExpandVetoException;

import org.example.SeleniumWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
    WebDriver driver;

    @FindBy(id="ap_email_login") WebElement mobInputBox;

    @FindBy(className = "a-button-input") WebElement continueButton;

    @FindBy(id = "ap_password") WebElement passInputBox;

    @FindBy(id="signInSubmit") WebElement signInButton;

    SeleniumWrapper seleniumWrapper = new SeleniumWrapper();


    public Login(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public boolean enterMobileNum(String mobileNumber){
        boolean status = false;
        try {

            mobInputBox.sendKeys(mobileNumber);

            seleniumWrapper.click(continueButton, driver);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            wait.until(ExpectedConditions.urlToBe("https://www.amazon.in/ap/signin"));

            status = driver.getCurrentUrl().equalsIgnoreCase("https://www.amazon.in/ap/signin");

            return status;
        } catch (Exception e) {
            // TODO: handle exception
            return status;
        }
    }

    public boolean enterPassword(String password){
        boolean status = false;
        try {
            passInputBox.sendKeys(password);

            seleniumWrapper.click(signInButton, driver);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            wait.until(ExpectedConditions.urlContains("amazon.in"));

            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return status;
        }
    }

}
