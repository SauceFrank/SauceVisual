package com.swaglabs.Tests;

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

public class LoginLockedOutUser extends TestBase {

    /**
     * Runs a simple test verifying Sign In.
     *
     * @throws InvalidElementStateException
     * @throws InterruptedException
     */
    @Test(dataProvider = "hardCodedBrowsers")
    public void LoginLockedOutUserTest(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException, InterruptedException {

        //create webdriver session
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();

        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  	    // driver.manage().window().maximize();

        this.annotate("Visiting Swag Labs Login page...");
        LoginPage page = LoginPage.visitPage(driver);

        this.annotate("Greet Sign In To Swag Labs Page...");
        InventoryPage inventory = page.enterCredentials("locked_out_user", "secret_sauce");

        this.annotate("Verify Locked Out User Message...");
        AssertJUnit.assertTrue(page.verifyLockedOutMessage().contains("Epic sadface"));

    }

}
