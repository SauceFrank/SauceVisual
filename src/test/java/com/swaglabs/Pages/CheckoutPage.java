package com.swaglabs.Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    @FindBy(xpath = "//div[@class='checkout_info_wrapper']")
    private WebElement checkoutPageHeader;

	@FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement firstNameTextBox;

	@FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement lastNameTextBox;

	@FindBy(xpath = "//input[@placeholder='Zip/Postal Code']")
    private WebElement zipCodeTextBox;

	@FindBy(xpath = "//input[@value='CONTINUE']")
    private WebElement continueButton;



	public WebDriver driver;


    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckoutOverviewPage clickContinue() {

    	continueButton.click();
    	return PageFactory.initElements(driver, CheckoutOverviewPage.class);

    }

    public boolean verfiyCheckoutPage() {
       WebDriverWait wait = new WebDriverWait(driver, 30);
       wait.until(ExpectedConditions.visibilityOf(checkoutPageHeader));
       return true;

    }

    public void enterUserDetails(String firstName, String lastName, String zipCode) {
    	firstNameTextBox.sendKeys(firstName);
    	lastNameTextBox.sendKeys(lastName);
    	zipCodeTextBox.sendKeys(zipCode);
        //loginButton.click();
        //return PageFactory.initElements(driver, SwagLabsInventoryPage.class);
    }



}
