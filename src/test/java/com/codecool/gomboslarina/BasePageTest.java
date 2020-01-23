package com.codecool.gomboslarina;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

class BasePageTest {
    private WebDriver driver;
    private Grid grid;
    private DesiredCapabilities capability;

    public void setUp(String platform, String browser, String testName) {
        grid = new Grid();
        grid.setupEnvironment(platform, browser, testName);
        driver = grid.getDriver();
//        driver.manage().window().maximize();
        capability = grid.getCapability();
    }

    public void login() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.successfulLogin();
        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.checkIfUserIsLoggedIn();
    }

    public void logout() {
        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.logOut();
    }

    public void shutDown() {
        if (null != driver) {
            driver.close();
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void implicitlyWait() {
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }
}
