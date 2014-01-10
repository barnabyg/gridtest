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

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

/**
 * @author Barnaby Golden
 *
 */
public final class GridTest {

    @Test
    public void gridTest() {

        Selenium selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://www.google.com");
        DesiredCapabilities capability = DesiredCapabilities.firefox();

        WebDriver driver = null;

        try {
            driver = new RemoteWebDriver(new URL("http://server1.bhgagile.com:4444/wd/hub"), capability);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        driver.get("http://www.google.co.uk");

        driver.quit();
    }
}
