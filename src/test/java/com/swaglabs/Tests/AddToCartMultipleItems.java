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
 * Created by Shadab Siddiqui on 11/21/18. Edited by Alex Griffen.
 */

public class AddToCartMultipleItems extends TestBase {

    /**
     * Runs a simple test verifying Sign In.
     *
     * @throws InvalidElementStateException
     * @throws InterruptedException
     */
    @Test(dataProvider = "hardCodedBrowsers")
    public void AddToCartMultipleItemsTest(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException, InterruptedException {

        //create webdriver session
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("/*@visual.init*/", "AddToCartMultipleItems");
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  	    // driver.manage().window().maximize();

        this.annotate("Visiting Swag Labs Login page...");
        LoginPage page = LoginPage.visitPage(driver);

        this.annotate("Greet Sign In To Swag Labs Page...");
        InventoryPage inventory = page.enterCredentials("performance_glitch_user", "secret_sauce");
//        InventoryPage inventory = page.enterCredentials("standard_user", "secret_sauce");

        this.annotate("View Product Inventory...");
        AssertJUnit.assertTrue(inventory.viewInventory().contains("Products"));

        this.annotate("Add To Cart Backpack...");
        inventory.clickAddToCartBackpack();
        js.executeScript("/*@visual.snapshot*/", "add backpack");

        this.annotate("Add To Cart Bolt Tshirt...");
        inventory.clickAddToCartBoltTshirt();
        js.executeScript("/*@visual.snapshot*/", "add bolt shirt");

        this.annotate("Add To Cart Onesie...");
        inventory.clickAddToCartOnesie();
        js.executeScript("/*@visual.snapshot*/", "add onesie");

        this.annotate("Go To Cart...");
        CartPage cart = inventory.goToCart();

        this.annotate("Verify Backpack In Cart...");
        AssertJUnit.assertTrue(cart.verifyBackpackinCart().contains("Sauce Labs Backpack"));

        this.annotate("Verify Bolt T-shirt In Cart...");
        AssertJUnit.assertTrue(cart.verifyBoltTshirtinCart().contains("Sauce Labs Bolt T-Shirt"));

        this.annotate("Verify Onesie In Cart...");
        AssertJUnit.assertTrue(cart.verifyOnesieinCart().contains("Sauce Labs Onesie"));
        js.executeScript("/*@visual.snapshot*/", "see backpack, shirt, and onesie");

    }

}
