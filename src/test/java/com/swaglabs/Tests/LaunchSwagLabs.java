package com.swaglabs.Tests;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import com.swaglabs.Pages.LoginPage;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.UnexpectedException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Shadab Siddiqui on 11/21/18.
 */

public class LaunchSwagLabs extends TestBase {

    /**
     * Runs a simple test verifying Sign In.
     *
     * @throws InvalidElementStateException
     * @throws InterruptedException
     */
    @Test(dataProvider = "hardCodedBrowsers")
    public void LaunchSwagLabsTest(String browser, String version, String os, Method method)
            throws MalformedURLException, InvalidElementStateException, UnexpectedException, InterruptedException {

        //create webdriver session
        this.createDriver(browser, version, os, method.getName());
        WebDriver driver = this.getWebDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("/*@visual.init*/", "LaunchSwabLabs");

        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  	    // driver.manage().window().maximize();


        this.annotate("Visiting Swag Labs Login page...");
        LoginPage page = LoginPage.visitPage(driver);
        //Assert.assertTrue(page.verifyLoginPage().contains("LOGIN"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Actions actions = new Actions(driver);
        WebElement elementLocator1 = driver.findElement(By.cssSelector("#login_credentials > h4"));
        actions.doubleClick(elementLocator1).perform();

        AssertJUnit.assertTrue(page.verifyLoginPage());
        js.executeScript("/*@visual.snapshot*/", "verify home");
    }

}
