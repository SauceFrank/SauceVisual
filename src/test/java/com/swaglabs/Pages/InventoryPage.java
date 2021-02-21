package com.swaglabs.Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {

    @FindBy(xpath = "//div[@class='product_label']")
    private WebElement productInventory;  

    @FindBy(xpath = "//div//div[@class='inventory_list']//div[1]//div[3]//button[1]")
    private WebElement addToCartBackpackButton;
    
    @FindBy(xpath = "//div//div[@class='inventory_list']//div[3]//div[3]//button[1]")
    private WebElement addToCartBoltTshirtButton;  
    
    @FindBy(xpath = "//div//div[@class='inventory_list']//div[5]//div[3]//button[1]")
    private WebElement addToCartOnesieButton;  
    
    @FindBy(xpath = "//div//div[@class='inventory_list']//div[6]//div[3]//button[1]")
    private WebElement addToCartTshirtRedButton;
    
    @FindBy(xpath = "//div//div[@class='inventory_list']//div[4]//div[3]//button[1]")
    private WebElement addToCartFleeceJacketButton;
    
    @FindBy(xpath = "//div//div[@class='inventory_list']//div[2]//div[3]//button[1]")
    private WebElement addToCartBikeLightButton;
    
    @FindBy(xpath = "//*[contains(@class,'svg-inline--fa fa-shopping-cart fa-w-18 fa-3x')]")
    private WebElement cartIcon;
    
    @FindBy(xpath = "//a[@id='logout_sidebar_link']")
    private WebElement logoutLink; 
    
    @FindBy(xpath = "//button[contains(text(),'Open Menu')]")
    private WebElement menuButton; 
    
  
  

    public WebDriver driver;
    

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    

    public String viewInventory() {
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	wait.until(ExpectedConditions.visibilityOf(productInventory));
    	//System.out.println(productInventory.getText());
        return productInventory.getText();
    }
    
    public void clickAddToCartBackpack() {
    	
    	addToCartBackpackButton.click();
  	    
    }
    
    public void clickAddToCartBoltTshirt() {
    	
    	addToCartBoltTshirtButton.click();
  	    
    }
    
    public void clickAddToCartOnesie() {
    	
    	addToCartOnesieButton.click();
  	    
    }
    
    public void clickAddToCartTshirtRed() {
    	
    	addToCartTshirtRedButton.click();
  	    
    }

    public void clickAddToCartFleeceJacket() {
	
    	addToCartFleeceJacketButton.click();
	    
}

    public void clickAddToCartBikeLight() {
	
    	addToCartBikeLightButton.click();
	    
}

    
    public CartPage goToCart() {
    	
    	cartIcon.click();
    	    	
  	    return PageFactory.initElements(driver, CartPage.class);
  	    
    }
    
    public void clickMenuButton() {
    	
    	menuButton.click();
  	    
    }
    
    public void clickLogout() {
    	
    	logoutLink.click();
  	    
    }

  
}
