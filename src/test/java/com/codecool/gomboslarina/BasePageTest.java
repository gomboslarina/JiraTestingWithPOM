package com.codecool.gomboslarina;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

class BasePageTest {
    private static String driverPath = System.getenv("DRIVERPATH");
    private WebDriver driver;
    Grid grid;
    DesiredCapabilities capability;

/*
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
*/

    public void setUp(String platform, String browser, String testName) {
        grid = new Grid();
        grid.setupEnvironment(platform, browser, testName);
//        grid.getDriver().manage().window().maximize();
        capability = grid.getCapability();
    }

    public void verifiedLogin() {
        LoginPage loginPage = new LoginPage(grid.getDriver());
        loginPage.successfulLogin();
        DashboardPage dashboardPage = new DashboardPage(grid.getDriver());
        dashboardPage.checkIfUserIsLoggedIn();
    }
/*
 public void verifiedLogin() {
     LoginPage loginPage = new LoginPage(getDriver());
     loginPage.successfulLogin();
     DashboardPage dashboardPage = new DashboardPage(getDriver());
     dashboardPage.checkIfUserIsLoggedIn();
 }
 */

    public void logout() {
        DashboardPage dashboardPage = new DashboardPage(grid.getDriver());
        dashboardPage.logOut();
    }

 /*
 public void logout() {
     DashboardPage dashboardPage = new DashboardPage(getDriver());
     dashboardPage.logOut();
 }

  */
    public void shutDown() {
        if (null != grid.getDriver()) {
            grid.getDriver().close();
            grid.getDriver().quit();
        }
    }

    public void shutDown2() {
        if (null != getDriver()) {
            getDriver().close();
            getDriver().quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void implicitlyWait() {
        grid.getDriver().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }
}
