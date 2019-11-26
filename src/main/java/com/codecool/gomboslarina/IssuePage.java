package com.codecool.gomboslarina;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class IssuePage extends BasePage {
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

    @FindBy(xpath = "//div[@id='issue-content']//div[@class='issue-error']")
    private WebElement issueError;


    public IssuePage(WebDriver driver) {
        super(driver);
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

    public boolean checkPermissionToEditIssue() {
        try {
            waitForElementToAppear(issueError);
            System.out.println("You don't have permission to view the issue");
            return false;
        } catch (Exception e) {
            try {
                waitForElementToAppear(summaryValue);
                waitForElementToBeClickable(editIssueButton);
                System.out.println(true);
                return true;
            } catch (Exception ex) {
                System.out.println("You don't have permission to edit the issue");
                return false;
            }
        }
    }
}
