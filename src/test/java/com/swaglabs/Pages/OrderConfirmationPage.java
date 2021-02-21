package com.swaglabs.Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderConfirmationPage {

    @FindBy(xpath = "//h2[@class='complete-header']")
    private WebElement thankyouMessage; 
    
    public WebDriver driver;
    

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    
    public boolean verfiyOrderConfirmationPage() {
       WebDriverWait wait = new WebDriverWait(driver, 30);
       wait.until(ExpectedConditions.visibilityOf(thankyouMessage));
       return true;
       
    }
    
    public String verifyThankyouMessage() {
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	wait.until(ExpectedConditions.visibilityOf(thankyouMessage));
    	//System.out.println(thankyouMessage.getText());
        return thankyouMessage.getText();
    }
 
}
