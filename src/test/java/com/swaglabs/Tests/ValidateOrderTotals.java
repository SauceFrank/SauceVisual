package com.swaglabs.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import com.swaglabs.Pages.CartPage;
import com.swaglabs.Pages.CheckoutOverviewPage;
import com.swaglabs.Pages.CheckoutPage;
import com.swaglabs.Pages.InventoryPage;
import com.swaglabs.Pages.LoginPage;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;

/**
 * Created by Shadab Siddiqui on 11/21/18.
 */

public class ValidateOrderTotals extends TestBase {

    /**
     * Runs a simple test verifying Sign In.
     *
     * @throws InvalidElementStateException
     * @throws InterruptedException
     */
    @Test(dataProvider = "hardCodedBrowsers")
    public void ValidateOrderTotalsTest(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException, InterruptedException {

        //create webdriver session
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();

        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  	    // driver.manage().window().maximize();

        this.annotate("Visiting Swag Labs Login page...");
        LoginPage page = LoginPage.visitPage(driver);

        this.annotate("Greet Sign In To Swag Labs Page...");
        InventoryPage inventory = page.enterCredentials("performance_glitch_user", "secret_sauce");

        this.annotate("View Product Inventory...");
        AssertJUnit.assertTrue(inventory.viewInventory().contains("Products"));

        this.annotate("Add To Cart Backpack...");
        inventory.clickAddToCartBackpack();

        this.annotate("Add To Cart Bolt Tshirt...");
        inventory.clickAddToCartBoltTshirt();

        this.annotate("Add To Cart Onesie...");
        inventory.clickAddToCartOnesie();

        this.annotate("Add To Cart Tshirt (Red)...");
        inventory.clickAddToCartTshirtRed();;

        this.annotate("Add To Cart Fleece Jacket...");
        inventory.clickAddToCartFleeceJacket();;

        this.annotate("Add To Cart Bike Light...");
        inventory.clickAddToCartBikeLight();

        this.annotate("Go To Cart...");
        CartPage cart = inventory.goToCart();

        this.annotate("Verify Cart Page...");
        AssertJUnit.assertTrue(cart.verifyBackpackinCart().contains("Sauce Labs Backpack"));
       // Assert.assertTrue(cart.verifyBackpackinCart().contains("Sauce Labs Backpack"));

        this.annotate("Go to Checkout...");
        CheckoutPage checkoutPage = cart.checkout();

        this.annotate("Asserting that we are on Checkout page...");
        AssertJUnit.assertTrue(checkoutPage.verfiyCheckoutPage());

        this.annotate("Enter User details...");
        checkoutPage.enterUserDetails("Tom", "Jones", "12345");

        this.annotate("Continue to Checkout Overview Page...");
        CheckoutOverviewPage overviewPage = checkoutPage.clickContinue();

        this.annotate("Verify Checkout Overview Page...");
        AssertJUnit.assertTrue(overviewPage.verfiyCheckoutOverviewPage());

        this.annotate("Verify Item total...");
        AssertJUnit.assertTrue(overviewPage.verifyItemTotal().contains("$129.94"));

        this.annotate("Verify Tax...");
        AssertJUnit.assertTrue(overviewPage.verifyTax().contains("$10.40"));

        this.annotate("Verify Total...");
        AssertJUnit.assertTrue(overviewPage.verifyTotal().contains("$140.34"));

    }

}
