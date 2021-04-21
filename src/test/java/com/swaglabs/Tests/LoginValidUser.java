package com.swaglabs.Tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.html5.LocationContext;
import org.openqa.selenium.support.ui.WebDriverWait;
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

        js.executeScript("/*@visual.snapshot*/", "LoadPage");

        this.annotate("Disable log to hide text password");
        this.stopLog();
        InventoryPage inventory = page.enterCredentials("performance_glitch_user", "secret_sauce");
//        InventoryPage inventory = page.enterCredentials("standard_user", "secret_sauce");
        this.startLog();
        this.annotate("Enable log to after text password");

        this.annotate("View Product Inventory...");
        AssertJUnit.assertTrue(inventory.viewInventory().contains("sleek"));

        // Demo purposes only change all images to Joey from Friends
//        this.annotate("Who likes Friends?");
//        js.executeScript("document.querySelectorAll('.inventory_item_img').forEach(function (item) {item.src = 'https://i.pinimg.com/736x/46/91/c9/4691c9ec1ec3343a33aa1f8cca1915bb.jpg'})");
//        Thread.sleep(1000);

        //randomly remove various Item Names
//        this.annotate("Randomly remove various Item Names");
//        js.executeScript("var items = document.querySelectorAll('.inventory_item_name')\n" +
//                        "items[Math.floor(Math.random() * 5)].style.visibility = 'hidden'\n" +
//                        "items[Math.floor(Math.random() * 5)].style.visibility = 'hidden'\n" +
//                        "items[Math.floor(Math.random() * 5)].style.visibility = 'hidden'");
//        Thread.sleep(1000);

        //randomly change the height of an Item Name
//        this.annotate("Randomly change the height of an Item Name");
//        js.executeScript("var items = document.querySelectorAll('.inventory_item_name')\n" +
//                "items[Math.floor(Math.random() * 5)].style.height = '400px'");
//        Thread.sleep(1000);

        //disable css
//        this.annotate("Disable css scripts");
//        js.executeScript("for (var i = 0; i < document.styleSheets.length; i++) \n{     document.styleSheets[i].disabled = true;\n}");

        this.annotate("sleeping for a few seconds so humans can see the changes");
        Thread.sleep(5000);
        js.executeScript("/*@visual.snapshot*/", "verify successful login");
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        driver.wait(10);

        Map response = (Map)((JavascriptExecutor) driver).executeScript("/*@visual.end*/");
        Assert.assertTrue((Boolean)response.get("passed"), (String)response.get("message"));

    }
//    }

}
