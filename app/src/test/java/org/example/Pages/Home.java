package org.example.Pages;

import java.time.Duration;

import org.example.SeleniumWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {

    WebDriver driver;

    String home_page_URL = "https://www.amazon.in/";

    @FindBy(xpath = "//a[@data-nav-ref='nav_ya_signin']") WebElement loginButton;

    @FindBy(id = "twotabsearchtextbox") WebElement searchBox;

    @FindBy(id = "nav-search-submit-button") WebElement searchButton;

    @FindBy(xpath = "//a[@data-nav-role='signin']") WebElement signInButton;

    SeleniumWrapper seleniumWrapper = new SeleniumWrapper();


    public Home(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void navigateToHomePage(){
        seleniumWrapper.navigteToURL(driver, home_page_URL);
    }

    public void clickOnLoginButton(){
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ap_email_login")));
    }

    public boolean searchItem(String item){
        boolean status = false;
        try {
            seleniumWrapper.sendKey(searchBox, item);

            seleniumWrapper.click(searchButton, driver);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            String[] items = item.split(" ");            

            wait.until(ExpectedConditions.urlContains(items[0]));

            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return status;
        }
    }

    public void signOut(){
        try{
            Actions actions = new Actions(driver);
            actions.moveToElement(signInButton).build().perform();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
