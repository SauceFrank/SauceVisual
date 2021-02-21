package com.swaglabs.Tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import com.swaglabs.Pages.CartPage;
import com.swaglabs.Pages.InventoryPage;
import com.swaglabs.Pages.LoginPage;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;

/**
 * Created by Shadab Siddiqui on 11/21/18.
 */

public class AddToCartSingleItem extends TestBase {

    /**
     * Runs a simple test verifying Sign In.
     *
     * @throws InvalidElementStateException
     * @throws InterruptedException
     */
    @Test(dataProvider = "hardCodedBrowsers")
    public void AddToCartSingleItemTest(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException, InterruptedException {

        //create webdriver session
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("/*@visual.init*/", "AddToCartSingleItem");

        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  	    // driver.manage().window().maximize();

        this.annotate("Visiting Swag Labs Login page...");
        LoginPage loginPage = LoginPage.visitPage(driver);
        js.executeScript("/*@visual.snapshot*/", "Home");

        this.annotate("Greet Sign In To Swag Labs Page...");
        InventoryPage inventoryPage = loginPage.enterCredentials("performance_glitch_user", "secret_sauce");

        this.annotate("View Product Inventory...");
        AssertJUnit.assertTrue(inventoryPage.viewInventory().contains("Products"));
        js.executeScript("/*@visual.snapshot*/", "products");

        this.annotate("Add To Cart Backpack...");
        inventoryPage.clickAddToCartBackpack();

        this.annotate("Go To Cart...");
        CartPage cart = inventoryPage.goToCart();
        js.executeScript("/*@visual.snapshot*/", "verify backpack is in cart");

        this.annotate("Verify Backpack Item In Cart...");
        AssertJUnit.assertTrue(cart.verifyBackpackinCart().contains("Sauce Labs Backpack"));

    }

}
