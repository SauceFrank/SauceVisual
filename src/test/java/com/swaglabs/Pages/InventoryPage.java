package com.swaglabs.Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {

    @FindBy(css = "#inventory_container")
    private WebElement productInventory;  

    @FindBy(css = "#add-to-cart-sauce-labs-backpack")
    private WebElement addToCartBackpackButton;
    
    @FindBy(css = "#add-to-cart-sauce-labs-bolt-t-shirt")
    private WebElement addToCartBoltTshirtButton;  
    
    @FindBy(css = "#add-to-cart-sauce-labs-onesie")
    private WebElement addToCartOnesieButton;  
    
    @FindBy(css = "#add-to-cart-test\\.allthethings\\(\\)-t-shirt-\\(red\\)")
    private WebElement addToCartTshirtRedButton;
    
    @FindBy(css = "#add-to-cart-sauce-labs-fleece-jacket")
    private WebElement addToCartFleeceJacketButton;
    
    @FindBy(css = "#add-to-cart-sauce-labs-bike-light")
    private WebElement addToCartBikeLightButton;
    
    @FindBy(css = "#shopping_cart_container > a")
//    @FindBy(css = "#shopping_cart_container > a > svg > path")
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
