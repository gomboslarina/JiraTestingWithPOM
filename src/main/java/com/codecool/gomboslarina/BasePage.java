package com.codecool.gomboslarina;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BasePage {

    static String driverPath = System.getenv("DRIVERPATH");
    WebDriver webDriver;
    String url = "https://jira.codecool.codecanvas.hu/";
    private String username = System.getenv("USERNAME");
    private String password = System.getenv("PASSWORD");
    private String email = System.getenv("EMAIL");
    private String incorrectPassword = System.getenv("INCORRECT_PASSWORD");
    private String incorrectUsername = System.getenv("INCORRECT_USERNAME");

    private static final int TIMEOUT = 5;
    private static final int POLLING = 100;

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor javascriptExecutor;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
         javascriptExecutor = (JavascriptExecutor) driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
    }

    protected void navigateToPage(String url) {
        driver.navigate().to(url);
    }

    protected void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForElementToDisappear(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

//    protected void waitForElementNotToExist() {
//        wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated()))
//    }


    public void handleAlert() {
        try {
            Alert alt = driver.switchTo().alert();
            alt.accept();
        } catch(NoAlertPresentException ignored) {
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getIncorrectPassword() {
        return incorrectPassword;
    }

    public String getIncorrectUsername() {
        return incorrectUsername;
    }


}


//    protected Wait<WebDriver> fluentWait;
//        fluentWait = new FluentWait<WebDriver>(driver)
//                .withTimeout(Duration.ofSeconds(TIMEOUT))
//                .pollingEvery(Duration.ofSeconds(POLLING))
//                .ignoring(StaleElementReferenceException.class);
//    public void waitForLoad(WebDriver driver) {
//        ExpectedCondition<Boolean> pageLoadCondition = new
//                ExpectedCondition<Boolean>() {
//                    public Boolean apply(WebDriver driver) {
//                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
//                    }
//                };
//        WebDriverWait wait = new WebDriverWait(driver, 30);
//        wait.until(pageLoadCondition);
//    }
