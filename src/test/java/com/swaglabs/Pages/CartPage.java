package com.swaglabs.Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

   // @FindBy(xpath = "//div[@class='inventory_item_name']")
    //private WebElement backpackLabel;

    @FindBy(xpath = "//div[contains(text(),'Sauce Labs Backpack')]")
    private WebElement backpackLabel;

    @FindBy(xpath = "//div[contains(text(),'Sauce Labs Bolt T-Shirt')]")
    private WebElement boltTshirtLabel;

    @FindBy(xpath = "//div[contains(text(),'Sauce Labs Onesie')]")
    private WebElement onesieLabel;

    @FindBy(xpath = "//a[@class='btn_action checkout_button']")
    private WebElement checkoutButton;



    //div[contains(text(),'Sauce Labs Backpack')]
    //div[contains(text(),'Sauce Labs Bolt T-Shirt')]
    //div[contains(text(),'Sauce Labs Onesie')]


    //div[contains(text(),'Sauce Labs Bolt T-Shirt')]

    public WebDriver driver;


    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    public String verifyBackpackinCart() {
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	wait.until(ExpectedConditions.visibilityOf(backpackLabel));
    	//System.out.println(backpackLabel.getText());
        return backpackLabel.getText();
    }

    public String verifyBoltTshirtinCart() {
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	wait.until(ExpectedConditions.visibilityOf(boltTshirtLabel));
    	//System.out.println(boltTshirtLabel.getText());
        return boltTshirtLabel.getText();
    }

    public String verifyOnesieinCart() {
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	wait.until(ExpectedConditions.visibilityOf(onesieLabel));
    	//System.out.println(onesieLabel.getText());
        return onesieLabel.getText();
    }

    public CheckoutPage checkout() {

    	checkoutButton.click();
    	return PageFactory.initElements(driver, CheckoutPage.class);

    }



}
