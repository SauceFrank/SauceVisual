package com.swaglabs.Tests;

import org.json.JSONObject;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.MutableCapabilities;

import org.testng.ITestResult;
import org.testng.annotations.DataProvider;

import javax.net.ssl.HttpsURLConnection;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.lang.*;
import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.Map;


/**
 * Simple TestNG test which demonstrates being instantiated via a DataProvider
 * in order to supply multiple browser combinations.
 *
 * @author Shadab Siddiqui originally, modified by Alex Griffen
 */
public class TestBase {

        public String buildTag = System.getenv("BUILD_TAG");

        public String githubBuildTag = System.getenv("GITHUB_RUN_NUMBER");

        public String username = System.getenv("SAUCE_USERNAME");

        public String accesskey = System.getenv("SAUCE_ACCESS_KEY");

        public String accesskeyHeadless = System.getenv("SAUCE_ACCESS_KEY_HEADLESS"); //use only with Headless RemoteWebdriver url

    /**
     * ThreadLocal variable which contains the {@link WebDriver} instance which
     * is used to perform browser interactions with.
     */
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    /**
     * ThreadLocal variable which contains the Sauce Job Id.
     */
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();

    /**
     * DataProvider that explicitly sets the browser combinations to be used.
     *
     * @param testMethod
     * @return Two dimensional array of objects with browser, version, and
     * platform information
     */
    @DataProvider(name = "hardCodedBrowsers", parallel = true)
    public static Object[][] sauceBrowserDataProvider(Method testMethod) {
        return new Object[][]{

//            new Object[]{"firefox", "latest", "Windows 10"},
//            new Object[]{"firefox", "latest", "Windows 7"},
//            new Object[]{"firefox", "latest-1", "Windows 10"},
//            new Object[]{"firefox", "latest-2", "Windows 10"},

            new Object[]{"chrome", "latest", "Windows 10"},
            new Object[]{"chrome", "latest-1", "Windows 10"},
            new Object[]{"chrome", "latest-2", "Windows 10"},

            new Object[]{"chrome", "latest", "Windows 7"},


//            new Object[]{"MicrosoftEdge", "latest", "Windows 10"},
//            new Object[]{"MicrosoftEdge", "latest-1", "Windows 10"},
//            new Object[]{"MicrosoftEdge", "latest-2", "Windows 10"},


//            new Object[]{"chrome", "latest", "Windows 10"},
//            new Object[]{"chrome", "latest-1", "Windows 10"},
//            new Object[]{"chrome", "latest-2", "Windows 10"},


                // Mac OS
//            new Object[]{"safari", "latest", "macOS 10.15"},
//            new Object[]{"safari", "13.0", "macOS 10.15"},
//            new Object[]{"safari", "latest-2", "macOS 10.11"},

//            new Object[]{"safari", "latest", "macOS 10.14"},
//            new Object[]{"safari", "12.0", "macOS 10.14"},

//                new Object[]{"chrome", "latest", "macOS 10.15"},
//                new Object[]{"chrome", "latest-1", "macOS 10.15"},
//                new Object[]{"chrome", "latest-2", "macOS 10.15"},

//            new Object[]{"chrome", "latest", "macOS 10.14"},
//            new Object[]{"chrome", "latest-1", "macOS 10.14"},
//            new Object[]{"chrome", "latest-2", "macOS 10.14"},

//            new Object[]{"firefox", "latest", "macOS 10.15"},
//            new Object[]{"firefox", "latest-1", "macOS 10.15"},
//            new Object[]{"firefox", "latest-2", "macOS 10.15"},

//            new Object[]{"firefox", "latest", "macOS 10.14"},
//            new Object[]{"firefox", "latest-1", "macOS 10.14"},
//            new Object[]{"firefox", "latest-2", "macOS 10.14"},
                // }

                // EmuSim
//                new Object[]{"Chrome","10.0", "Android"},

                /**
                 *** use these when running headless
                 **/

//            new Object[]{"chrome", "latest", "Linux"},
//            new Object[]{"chrome", "latest-1", "Linux"},
//            new Object[]{"chrome", "latest-2", "Linux"},

//            new Object[]{"firefox", "latest", "Linux"},
//            new Object[]{"firefox", "latest-1", "Linux"},
//            new Object[]{"firefox", "latest-2", "Linux"},

        };
    }


    /**
     * @return the {@link WebDriver} for the current thread
     */
    public WebDriver getWebDriver() {
        return webDriver.get();
    }

    /**
     *
     * @return the Sauce Job id for the current thread
     */
    public String getSessionId() {
        return sessionId.get();
    }

    /**
     * Constructs a new {@link RemoteWebDriver} instance which is configured to
     * use the capabilities defined by the browser, version and os parameters,
     * and which is configured to run against ondemand.us-west-1.saucelabs.com, using the
     * username and access key populated by the authorization
     * instance.
     *
     * @param browser Represents the browser to be used as part of the test run.
     * @param version Represents the version of the browser to be used as part
     * of the test run.
     * @param os Represents the operating system to be used as part of the test
     * run.
     * @param methodName Represents the name of the test case that will be used
     * to identify the test on Sauce.
     * @return
     * @throws MalformedURLException if an error occurs parsing the url
     */
    protected void createDriver(String browser, String version, String os, String methodName)
            throws MalformedURLException {

//        System.setProperty("http.proxyHost", "localhost");
//        System.setProperty("http.proxyPort", "8080");
//
//        System.setProperty("https.proxyHost", "localhost");
//        System.setProperty("https.proxyPort", "8080");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        // set desired capabilities to launch appropriate browser on Sauce
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        capabilities.setCapability(CapabilityType.VERSION, version);
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, os);


            MutableCapabilities sauceVisual = new MutableCapabilities();
            sauceVisual.setCapability("apiKey", System.getenv("SCREENER_API_KEY"));
            sauceVisual.setCapability("projectName", "SwagLabs");
            sauceVisual.setCapability("viewportSize", "1920x1080");
//            sauceVisual.setCapability("viewportSize", "640x360");
//            sauceVisual.setCapability("viewportSize", "1024x768"); // You can test multiple viewport sizes at the same time
            sauceVisual.setCapability("branch", "swaglabs/fullSuite");
            sauceVisual.setCapability("baseBranch", "swaglabs/master");

        capabilities.setCapability("sauce:visual", sauceVisual);

//        Map<String, Object> deviceMetrics = new HashMap<>();
//        deviceMetrics.put("width", 360);
//        deviceMetrics.put("height", 640);
//        deviceMetrics.put("pixelRatio", 3.0);
//
//        Map<String, Object> mobileEmulation = new HashMap<>();
//        mobileEmulation.put("deviceMetrics", deviceMetrics);
//        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
//
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        MutableCapabilities sauce = new MutableCapabilities();
        sauce.setCapability("username", username);
        sauce.setCapability("accessKey", accesskey);
        sauce.setCapability("name", methodName + " password logging enabled");
//        sauce.setCapability(CapabilityType.BROWSER_NAME, browser);
//        sauce.setCapability(CapabilityType.VERSION, version);
//        sauce.setCapability(CapabilityType.PLATFORM_NAME, os);


//        sauce.setCapability("chromeOptions", chromeOptions);
//        sauce.setCapability("extendedDebugging",true);
//        sauce.setCapability("capturePerformance",true);

        sauce.setCapability("commandTimeout", 600);

        //Getting the build name.
        // Using the Jenkins ENV var or Github Action ENV var. You can use your own. If it is not set test will run without a build id.

        if (buildTag != null) {
            sauce.setCapability("build", buildTag);
        } else if (githubBuildTag != null) {
            sauce.setCapability("build", githubBuildTag);
        } else {
            sauce.setCapability("build", "no build found");
        }
        capabilities.setCapability("sauce:options", sauce);

        System.out.println(capabilities);

        // Launch remote browser and set it as the current thread
        webDriver.set(new RemoteWebDriver(
                        new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub"), // Sauce full VMs
//                        new URL("https://hub.screener.io:443/wd/hub"), // Screener full VMs
                        capabilities)
        );

        // set current sessionId
        String id = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
        sessionId.set(id);
//        String message = String.format("SauceOnDemandSessionID=%1$s job-name=%2$s", sessionId, System.getenv("JOB_NAME"));
//        System.out.println(message);
    }

    /**
     * Method that gets invoked after test. Dumps browser log and Closes the
     * browser
     */
    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        ((JavascriptExecutor) webDriver.get()).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed")); //sauce:context
        webDriver.get().quit();
    }

    protected void annotate(String text) {
        ((JavascriptExecutor) webDriver.get()).executeScript("sauce:context=" + text);
    }

    protected void stopLog() {
        ((JavascriptExecutor) webDriver.get()).executeScript("sauce: disable log" );
    }

    protected void startLog() {
        ((JavascriptExecutor) webDriver.get()).executeScript("sauce: enable log" );
    }


}