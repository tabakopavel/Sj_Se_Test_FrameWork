package com.epam.framework.core.driver;

import com.epam.framework.utils.Propertiess;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pavel_Tabako on 10/3/2017.
 */
public class Driver {

    private static WebDriver driver;
    private static WebDriver input_driver;
    private static String pageLoadStatus = null;
    private static JavascriptExecutor js;
    private  FirefoxProfile profile = new FirefoxProfile();


    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver input_driver) {
        driver = input_driver;
    }

    public static boolean isElementPresent(By locator) {
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> elements = getDriver().findElements(locator);
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return elements.size() > 0 && elements.get(0).isDisplayed();
    }

    public static void init() {
       // Propertiess.init();
        if (System.getProperty("test.browser").equalsIgnoreCase("Firefox")) {
            input_driver = new FirefoxDriver();
        } else if (System.getProperty("test.browser").equalsIgnoreCase("Chrome")) {
            input_driver = new ChromeDriver();
        } else if (System.getProperty("test.browser").equalsIgnoreCase("Opera")) {
            input_driver = new OperaDriver();
        }

        input_driver.manage().window().maximize();
        input_driver.manage().timeouts().implicitlyWait(Integer.parseInt(System.getProperty("test.timeout")), TimeUnit.SECONDS);
        //input_driver.manage().window().setPosition(new Point(Integer.parseInt(System.getProperty("test.pointx")), Integer.parseInt(System.getProperty("test.pointy"))));
        //input_driver.manage().window().setSize(new Dimension(Integer.parseInt(System.getProperty("test.width")), Integer.parseInt(System.getProperty("test.height"))));

        Driver.setDriver(input_driver);


    }

    public static void waitForAjax() {

        try {
            WebDriverWait driverWait = new WebDriverWait(Driver.getDriver(), Long.parseLong(System.getProperty("test.jstimeout")));
            ExpectedCondition<Boolean> expectation;
            expectation = new ExpectedCondition<Boolean>() {

                public Boolean apply(WebDriver driverjs) {

                    JavascriptExecutor js = (JavascriptExecutor) driverjs;
                    return js.executeScript("return((window.jQuery != null) && (jQuery.active == 0))").equals("true");
                }
            };
            driverWait.until(expectation);
        } catch (TimeoutException exTimeout) {

            // exTimeout.printStackTrace();
        } catch (WebDriverException exWebDriverException) {

            // exWebDriverException.printStackTrace();
        }

    }

    public static void waitForPageToLoad() {
        do {
            js = (JavascriptExecutor) Driver.getDriver();
            pageLoadStatus = (String) js.executeScript("return document.readyState");
            System.out.print(".");
        } while (!pageLoadStatus.equals("complete"));
        System.out.println();
        System.out.println("Page Loaded.");
    }

    public static boolean waitForJSandJQueryToLoad() {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) getDriver()).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) getDriver()).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public static ExpectedCondition<Boolean> jQueryAJAXCallsHaveCompleted() {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).executeScript("return (window.jQuery != null) && (jQuery.active == 0);");
            }
        };
    }

    public static void clean() {
        Driver.getDriver().quit();
    }

}
