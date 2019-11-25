package com.codecool.gomboslarina;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class BasePage {

    String username = System.getenv("USERNAME");
    String password = System.getenv("PASSWORD");
    private String email = System.getenv("EMAIL");
    private String incorrectPassword = System.getenv("INCORRECT_PASSWORD");
    private String incorrectUsername = System.getenv("INCORRECT_USERNAME");

    private static final int TIMEOUT = 10;
    private static final int POLLING = 100;

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected FluentWait<WebDriver> fluentWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        fluentWait  = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofSeconds(POLLING))
                .ignoring(org.openqa.selenium.ElementClickInterceptedException.class);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
    }

    protected void navigateToPage(String url) {
        driver.navigate().to(url);
    }

    protected void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected boolean isElementVisible(WebElement element) {
        return element.isDisplayed();
    }

    protected void waitForElementToDisappear(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
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

    public void fluentlyWait (WebElement element) {
        fluentWait.until(ExpectedConditions.visibilityOf(element));
    }


    boolean areProjectAndGlassPermissionsEqual(ProjectPage projectPage, ProjectPermissionsPage projectPermissionsPage, GlassDocumentationPage glassDocumentationPage) {
        projectPage.goToPermissions();
        Map<String, List<Boolean>> projectPermissions = projectPermissionsPage.createMapFromProjectPagePermissions();
        projectPage.clickOnGlassLink();
        glassDocumentationPage.goToGlassPermissionPage();
        Map<String, List<Boolean>> glassPermissions = glassDocumentationPage.getGlassPagePermissions();
        int sizeOfGlassPerm = glassPermissions.keySet().size();
        for (String projectKey : projectPermissions.keySet()) {
            for (String glassKey : glassPermissions.keySet()) {
                if (projectKey.equals(glassKey)) {
                    if (compareBooleanLists(projectPermissions.get(projectKey), glassPermissions.get(glassKey))) {
                        sizeOfGlassPerm--;
                    } else {
                        System.out.println("Project permission and glass permission does not equal.");
                    }
                }
            }
        }
        return sizeOfGlassPerm == glassPermissions.keySet().size();
    }

    boolean compareBooleanLists(List<Boolean> a, List<Boolean> b) {
        int size = a.size();
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) == b.get(i)) {
                size--;
            }
        }
        return size == b.size();
    }
}