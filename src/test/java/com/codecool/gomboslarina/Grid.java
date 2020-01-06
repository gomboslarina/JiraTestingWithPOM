package com.codecool.gomboslarina;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Grid {

    private WebDriver driver;
    private String baseURL = System.getenv("BASEURL"); // NOT YET working with env vars, need to write the url!!!
    private String nodeURL = System.getenv("NODEURL"); // NOT YET working with env vars, need to write the url!!!

    public void setupMacFox() throws MalformedURLException {
        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setBrowserName("firefox");
        capability.setPlatform(Platform.MAC);
        try {
            driver = new RemoteWebDriver(new URL(nodeURL), capability);
        } catch (MalformedURLException me) {
            System.out.println(me.toString());
        }
    }

    public void setupMacSafari() throws MalformedURLException {
        DesiredCapabilities capability = DesiredCapabilities.safari();
        capability.setBrowserName("safari");
        capability.setPlatform(Platform.MAC);
        try {
            driver = new RemoteWebDriver(new URL(nodeURL), capability);
        } catch (MalformedURLException me) {
            System.out.println(me.toString());
        }
    }

    public void setupMacChrome() throws MalformedURLException {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setBrowserName("chrome");
        capability.setPlatform(Platform.MAC);
        try {
            driver = new RemoteWebDriver(new URL(nodeURL), capability);
        } catch (MalformedURLException me) {
            System.out.println(me.toString());
        }
    }

    public void setupLinuxFox() throws MalformedURLException {
        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setBrowserName("firefox");
        capability.setPlatform(Platform.LINUX);
        try {
            driver = new RemoteWebDriver(new URL(nodeURL), capability);
        } catch (MalformedURLException me) {
            System.out.println(me.toString());
        }
    }

    public void setupLinuxChrome() {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setBrowserName("chrome");
        capability.setPlatform(Platform.LINUX);
        try {
            driver = new RemoteWebDriver(new URL(nodeURL), capability);
        } catch (MalformedURLException me) {
            System.out.println(me.toString());
        }
    }

    public void setupWinIE() throws MalformedURLException {
        DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
        capability.setBrowserName("internetExplorer");
        capability.setPlatform(Platform.WINDOWS);
        try {
            driver = new RemoteWebDriver(new URL(nodeURL), capability);
        } catch (MalformedURLException me) {
            System.out.println(me.toString());
        }
    }

    public void setupWinFox() throws MalformedURLException {
        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setBrowserName("firefox");
        capability.setPlatform(Platform.WINDOWS);
        try {
            driver = new RemoteWebDriver(new URL(nodeURL), capability);
        } catch (MalformedURLException me) {
            System.out.println(me.toString());
        }
    }

    public void setupWinChrome() throws MalformedURLException {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setBrowserName("chrome");
        capability.setPlatform(Platform.WINDOWS);
        try {
            driver = new RemoteWebDriver(new URL(nodeURL), capability);
        } catch (MalformedURLException me) {
            System.out.println(me.toString());
        }
    }

    public String getBaseURL() {
        return baseURL;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
