package com.swaglabs.Tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.html5.LocationContext;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import com.swaglabs.Pages.InventoryPage;
import com.swaglabs.Pages.LoginPage;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Shadab Siddiqui on 11/21/18.
 */

public class LoginValidUser extends TestBase {

    /**
     * Runs a simple test verifying Sign In.
     *
     * @throws InvalidElementStateException
     * @throws InterruptedException
     */
    @Test(dataProvider = "hardCodedBrowsers")
    public void LoginValidUserTest(String browser, String version, String os, Method method)
        throws MalformedURLException, InvalidElementStateException, UnexpectedException, InterruptedException {

        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("/*@visual.init*/", "LoginValidUser");

        this.annotate("Visiting Swag Labs Login page...");
        LoginPage page = LoginPage.visitPage(driver);

        this.annotate("Greet Sign In To Swag Labs Page...");

        this.annotate("Disable log to hide text password");
        this.stopLog();
        InventoryPage inventory = page.enterCredentials("performance_glitch_user", "secret_sauce");
        this.startLog();
        this.annotate("Enable log to after text password");

        this.annotate("View Product Inventory...");
        AssertJUnit.assertTrue(inventory.viewInventory().contains("Products"));
        js.executeScript("/*@visual.snapshot*/", "verify successful login");

//        Map response = (Map)((JavascriptExecutor) driver).executeScript("/*@visual.end*/");
//        Assert.assertTrue((Boolean)response.get("passed"), (String)response.get("message"));

    }
//    }

}
