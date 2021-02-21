package com.swaglabs.Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {



    @FindBy(xpath = "//input[@placeholder='Username']") // enable to have tests pass
    private WebElement usernameTextBox;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//input[@class='btn_action']")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement lockedOutMessage;

    @FindBy(xpath = "//pre[@id='login_credentials']")
    private WebElement loginCredentials;


    public WebDriver driver;
//    public static String url = "https://www.saucedemo.com/";
    public static String url = "https://24103f7ba9e3.ngrok.io/";

    public static LoginPage visitPage(WebDriver driver) {
    	LoginPage page = new LoginPage(driver);
        page.visitPage();
        return page;
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void visitPage() {
        this.driver.get(url);
    }

    public static void loggingControl(WebDriver driver, boolean log){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        if (true == log)
            js.executeScript("sauce: enable log");
        else
            js.executeScript("sauce: disable log");
    }

    public InventoryPage enterCredentials(String username, String password) throws InterruptedException {
    	//Thread.sleep(1000);
    	usernameTextBox.sendKeys(username);
    	//Thread.sleep(1000);
//        loggingControl(this.driver, false);
//        ((JavascriptExecutor)driver).executeScript("sauce:context=disabling logs for password entry");
//        ((JavascriptExecutor)driver).executeScript("sauce: disable log");
    	passwordTextBox.sendKeys(password);
//        ((JavascriptExecutor)driver).executeScript("sauce: enable log");
//        loggingControl(this.driver, true);
//        ((JavascriptExecutor)driver).executeScript("sauce:context=enabling logs after password entry");
    	Thread.sleep(1000);
    	//loginCredentials.click();
    	//Thread.sleep(4000);
    	loginButton.click();
        return PageFactory.initElements(driver, InventoryPage.class);
    }



    public boolean verifyLoginPage() {
    	WebDriverWait wait = new WebDriverWait(driver, 20);
    	wait.until(ExpectedConditions.visibilityOf(loginButton));
    	//System.out.println("Value is: "+loginButton.getText());
        return true;
    }

    public String verifyLockedOutMessage() {
    	WebDriverWait wait = new WebDriverWait(driver, 20);
    	wait.until(ExpectedConditions.visibilityOf(lockedOutMessage));
    	System.out.println(lockedOutMessage.getText());
      System.out.println("*~*~ here's the lockedOutMessage ~*~*");
        return lockedOutMessage.getText();
    }

    public String verifyLoginCredentialsText() {
    	WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(loginCredentials));
        return loginCredentials.getText();
    }

}
