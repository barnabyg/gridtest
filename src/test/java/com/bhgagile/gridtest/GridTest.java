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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
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
        // In this example, the parameter generator returns a List of
        // arrays. Each array has two elements: { datum, expected }.
        // These data are hard-coded into the class, but they could be
        // generated or loaded in any way you like.
        return Arrays.asList(new Object[][] {{"firefox"}, {"chrome"}});
    }

    /**
     * Simple example test.
     */
    @Test
    public void gridTest() {

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

        WebDriver driver = null;

        try {
            driver = new RemoteWebDriver(new URL(
                    "http://server1.bhgagile.com:4444/wd/hub"), capability);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        driver.get("http://www.google.co.uk");

        driver.quit();
    }
}
