package com.codecool.gomboslarina;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CreateIssuePopupPage extends BasePage {
    @FindBy(xpath = "//input[@id='project-field']")
    private WebElement projectField;

    @FindBy(xpath = "//input[@id='issuetype-field']")
    private WebElement issueTypeField;

    @FindBy(xpath = "//input[@id='summary']")
    private WebElement summaryField;

    @FindBy(xpath = "//input[@id='create-issue-submit']")
    private WebElement createIssueSumbit;

    @FindBy(xpath = "//a[@class = 'issue-created-key issue-link']")
    private WebElement issueCreatedLink;

    @FindBy(xpath = "//div[@id='create-issue-dialog']//a[@class='cancel']")
    private WebElement cancelButton;

    public CreateIssuePopupPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Fill different fields of the modal - Project name;
     */

    public void addProjectName(String projectName) {
        waitForElementToAppear(projectField);
        projectField.click();
        projectField.sendKeys(projectName);
        projectField.sendKeys(Keys.TAB);
        try {
            waitForElementToDisappear(projectField);
        } catch (Exception ex) {
        }
        waitForElementToAppear(projectField);
    }

    /**
     * Fill different fields of the modal - Issue type;
     */

    public void addIssueType(String issueType) {
        waitForElementToAppear(issueTypeField);
        issueTypeField.click();
        issueTypeField.sendKeys(issueType);
        issueTypeField.sendKeys(Keys.TAB);
        try {
            waitForElementToDisappear(issueTypeField);
        } catch (Exception ex) {
        }
        waitForElementToAppear(issueTypeField);
    }

    /**
     * Fill different fields of the modal - Summary;
     */

    public void addSummary(String summary) {
        waitForElementToAppear(summaryField);
        summaryField.click();
        summaryField.sendKeys(summary);
    }

    /**
     * Create new issue - filling out the following fields: Project name, Issue type, Summary;
     */

    public void createIssue(String projectName, String issueType, String summary) {
        addProjectName(projectName);
        addIssueType(issueType);
        addSummary(summary);
        waitForElementToBeClickable(createIssueSumbit);
        createIssueSumbit.click();
    }

    /**
     * Success message popup - link navigates to issue page;
     */

    public void clickOnIssuePopupLink() {
        waitForElementToBeClickable(issueCreatedLink);
        issueCreatedLink.click();
    }

    /**
     * Cancel creating issue - closing modal;
     */

    public void cancelCreatingIssue() {
        waitForElementToAppear(cancelButton);
        cancelButton.click();
        waitForElementToDisappear(cancelButton);
    }

    /**
     * Checking available selections - filling out the following fields: Project name, Issue type;
     */


    public void addExpectedValues(String projectName, String issueType) {
        addProjectName(projectName);
        addIssueType(issueType);
    }

    public String getCurrentProject() {
        return projectField.getAttribute("value");
    }

    public String getCurrentIssueType() {
        return issueTypeField.getAttribute("value");
    }
}
