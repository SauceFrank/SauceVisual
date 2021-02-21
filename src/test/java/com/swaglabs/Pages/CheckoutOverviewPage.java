package com.swaglabs.Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutOverviewPage {

    @FindBy(xpath = "//div[@class='cart_desc_label']")
    private WebElement checkoutOverviewPageHeader;

    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    private WebElement itemTotalLabel;

    @FindBy(xpath = "//div[@class='summary_tax_label']")
    private WebElement taxLabel;

    @FindBy(xpath = "//div[@class='summary_total_label']")
    private WebElement totalLabel;

    @FindBy(xpath = "//a[@class='btn_action cart_button']")
    private WebElement finishButton;



    public WebDriver driver;


    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public boolean verfiyCheckoutOverviewPage() {
       WebDriverWait wait = new WebDriverWait(driver, 30);
       wait.until(ExpectedConditions.visibilityOf(checkoutOverviewPageHeader));
       return true;

    }

    public String verifyItemTotal() {
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	wait.until(ExpectedConditions.visibilityOf(itemTotalLabel));
    	//System.out.println(itemTotalLabel.getText());
        return itemTotalLabel.getText();
    }

    public String verifyTax() {
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	wait.until(ExpectedConditions.visibilityOf(taxLabel));
    	//System.out.println(taxLabel.getText());
        return taxLabel.getText();
    }

    public String verifyTotal() {
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	wait.until(ExpectedConditions.visibilityOf(totalLabel));
    	//System.out.println(totalLabel.getText());
        return totalLabel.getText();
    }

    public OrderConfirmationPage clickFinish() {
    	finishButton.click();
    	return PageFactory.initElements(driver, OrderConfirmationPage.class);

    }


}
