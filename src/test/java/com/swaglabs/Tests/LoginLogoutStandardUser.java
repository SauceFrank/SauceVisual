package com.swaglabs.Tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import com.swaglabs.Pages.InventoryPage;
import com.swaglabs.Pages.LoginPage;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;

/**
 * Created by Shadab Siddiqui on 11/21/18.
 */

public class LoginLogoutStandardUser extends TestBase {

    /**
     * Runs a simple test verifying Sign In.
     *
     * @throws InvalidElementStateException
     * @throws InterruptedException
     */
    @Test(dataProvider = "hardCodedBrowsers")
    public void LoginLogoutTest(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException, InterruptedException {

        //create webdriver session
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("/*@visual.init*/", "LoginLogout");


        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  	    // driver.manage().window().maximize();

        this.annotate("Visiting Swag Labs Login page...");
        LoginPage page = LoginPage.visitPage(driver);

        this.annotate("Greet Sign In To Swag Labs Page...");
        this.annotate("Disabling Logging to hide plaintext credentials...");
        this.stopLog();
        InventoryPage inventory = page.enterCredentials("performance_glitch_user", "secret_sauce");
        this.startLog();
        this.annotate("Enabling Logging after passing credentials...");


        this.annotate("View Product Inventory...");
        AssertJUnit.assertTrue(inventory.viewInventory().contains("Products"));
        js.executeScript("/*@visual.snapshot*/", "verify successful login");

        this.annotate("Logging Out...");
        inventory.clickMenuButton();
        inventory.clickLogout();
        js.executeScript("/*@visual.snapshot*/", "verify successful logout");



    }

}
