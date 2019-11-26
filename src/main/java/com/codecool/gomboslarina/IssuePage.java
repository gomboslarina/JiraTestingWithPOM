package com.codecool.gomboslarina;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

public class IssuePage extends BasePage {

    public IssuePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@class='aui-button aui-button-primary search-button']")
    private WebElement searchBtn;

    @FindBy(xpath = "//a[@id = 'project-name-val']")
    private WebElement projectNameValue;

    @FindBy(xpath = "//span[@id = 'type-val']")
    private WebElement issueTypeValue;

    @FindBy(xpath = "//h1[@id = 'summary-val']")
    private WebElement summaryValue;

    @FindBy(xpath = "//a[@id='edit-issue']")
    private WebElement editIssueButton;

    @FindBy(xpath = "//a[@id = 'opsbar-operations_more']")
    private WebElement moreOptions;

    @FindBy(xpath = "//aui-item-link[@id='delete-issue']")
    private WebElement deleteMenuItem;

    @FindBy(xpath = "//textarea[@id='advanced-search']")
    private WebElement searchBar;

    public boolean searchForIssues(String issueName) {
        String previousUrl = driver.getCurrentUrl();
        waitForElementToBeClickable(searchBar);
        searchBar.clear();
        searchBar.sendKeys("issuekey =  " + issueName);
        waitForElementToBeClickable(searchBtn);
        searchBtn.click();
        String currentUrl = driver.getCurrentUrl();
        if (!previousUrl.equals(currentUrl)) {
            String elementText = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".issue-link[data-issue-key='" + issueName + "']"))).getText();
            return issueName.equals(elementText);
        }
        return false;

    }


    public ArrayList<String> getCurrentValues() {
        ArrayList<String> currentValues = new ArrayList<>();
        currentValues.add(getIssueProject());
        currentValues.add(getIssueType());
        currentValues.add(getSummary());
        return currentValues;
    }

    public String getIssueProject() {
        waitForElementToAppear(projectNameValue);
        return projectNameValue.getText();
    }

    public String getIssueType() {
        waitForElementToAppear(issueTypeValue);
        return issueTypeValue.getText();
    }

    public String getSummary() {
        try {
            waitForElementToDisappear(summaryValue);
        } catch (Exception ex) {
        }
        waitForElementToAppear(summaryValue);
        return summaryValue.getText();
    }

    public void openEditIssue() {
        waitForElementToBeClickable(editIssueButton);
        editIssueButton.click();
    }

    public void deleteIssue() {
        waitForElementToBeClickable(moreOptions);
        moreOptions.click();
        waitForElementToBeClickable(deleteMenuItem);
        deleteMenuItem.click();
    }
}
