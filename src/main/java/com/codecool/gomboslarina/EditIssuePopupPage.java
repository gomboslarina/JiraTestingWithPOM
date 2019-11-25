package com.codecool.gomboslarina;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.security.Key;

public class EditIssuePopupPage extends BasePage {
    @FindBy(xpath = "//input[@id='summary']")
    private WebElement summaryField;

    @FindBy(xpath = "//input[@id='edit-issue-submit']")
    private WebElement editIssueSumbit;

    @FindBy(xpath = "//div[@id='aui-flag-container']//div[@class='aui-message aui-message-success success closeable shadowed aui-will-close']")
    private WebElement issueUpdatedMessage;


    public EditIssuePopupPage(WebDriver driver) {
        super(driver);
    }

    public void editIssue(String summaryUpdate) {
        waitForElementToBeClickable(summaryField);
        summaryField.clear();
        summaryField.sendKeys(summaryUpdate);
        editIssueSumbit.click();
        waitForElementToDisappear(issueUpdatedMessage);
    }
}
