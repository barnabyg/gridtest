/*************************************************************************
 *  2013 BHGAGILE
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of BHGAGILE.
 */
package com.bhgagile.gridtest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author Barnaby Golden
 *
 */
@RunWith(Parameterized.class)
public final class GridTest {

    /**
     * Browser member.
     */
    private String browser;

    /**
     * Constructor for use with parameterised call.
     *
     * @param browser
     *            the browser to test
     */
    public GridTest(final String browser) {
        this.browser = browser;
    }

    /**
     * Generate the list of parameters.
     * @return list of arrays
     */
    @Parameters
    public static Collection<Object[]> generateData() {

        return Arrays.asList(new Object[][] {{"firefox"}, {"chrome"}});
    }

    /**
     * Simple example test.
     */
    @Test
    public void gridTest() {

        final WebDriver driver = getDriver();

        driver.get("http://www.google.co.uk");

        driver.quit();
    }

    /**
     * Get the appropriate web driver.
     * @return driver
     */
    private WebDriver getDriver() {

        WebDriver driver = null;

        if (usingGrid()) {
            driver = getGridDriver();
        } else {
            driver = getLocalDriver();
        }

        return driver;
    }

    /**
     * Remote grid driver.
     * @return driver
     */
    private WebDriver getGridDriver() {

        WebDriver driver = null;

        DesiredCapabilities capability;

        switch (browser) {
        case "firefox":
            capability = DesiredCapabilities.firefox();
            break;
        case "chrome":
            capability = DesiredCapabilities.chrome();
            break;
        default:
            capability = DesiredCapabilities.firefox();
            break;
        }


        try {
            driver = new RemoteWebDriver(new URL(
                    "http://server1.bhgagile.com:4444/wd/hub"), capability);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return driver;
    }

    /**
     * Local operation driver.
     * @return driver
     */
    private WebDriver getLocalDriver() {

        WebDriver driver = null;

        switch (browser) {
        case "firefox":
            driver = new FirefoxDriver();
            break;
        case "chrome":
            if (SystemUtils.IS_OS_LINUX) {
                System.setProperty(
                        "webdriver.chrome.driver",
             "/opt/jenkins/workspace/gridtest/src/test/resources/chromedriver");
            } else if (SystemUtils.IS_OS_WINDOWS) {
                System.setProperty(
                        "webdriver.chrome.driver",
             "C:\\docs\\git\\gridtest\\src\\test\\resources\\chromedriver.exe");
            }

            driver = new ChromeDriver();
            break;
        default:
            driver = new FirefoxDriver();
            break;
        }

        return driver;
    }

    /**
     * Flag to indicate if a grid should be used or if browsers
     * should be run locally.
     *
     * @return true if using grid else false
     */
    private boolean usingGrid() {

        final String testmode = System.getProperty("testmode");

        boolean mode = true;

        if (testmode == null || testmode.equalsIgnoreCase("local")) {
            mode = false;
        } else if (testmode.equalsIgnoreCase("grid")) {
            mode = true;
        }

        System.out.println("usingGrid=" + mode);

        return mode;
    }
}
