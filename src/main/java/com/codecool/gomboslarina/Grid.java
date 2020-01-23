package com.codecool.gomboslarina;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.RemoteWebDriver;

public class Grid {

    private WebDriver driver;
    private String nodeURL;
    private DesiredCapabilities capability;

    Grid() {
        PropertiesReader reader = new PropertiesReader();
        nodeURL = reader.getNodeUrl();
    }

    public void setupEnvironment(String platform, String browser, String testName) {
        this.capability = DesiredCapabilities.chrome();
        switch (browser) {
            case "firefox":
                capability = DesiredCapabilities.firefox();
                break;
            case "chrome":
                capability = DesiredCapabilities.chrome();
                break;
            case "ie":
                capability = DesiredCapabilities.internetExplorer();
                break;
            case "safari":
                capability = DesiredCapabilities.safari();
                break;
        }

        switch (platform) {
            case "mac":
                capability.setPlatform(Platform.MAC);
                break;
            case "linux":
                capability.setPlatform(Platform.LINUX);
                break;
            case "windows":
                capability.setPlatform(Platform.WINDOWS);
                break;
        }
        capability.setCapability("name", testName);
        try {
            driver = new RemoteWebDriver(new URL(nodeURL), capability);
        } catch (MalformedURLException me) {
            System.out.println(me.toString());
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public DesiredCapabilities getCapability() {
        return capability;
    }
}