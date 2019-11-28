package com.codecool.gomboslarina;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProjectComponentsPage extends Components {
    @FindBy(xpath = "//form[@id='components-add__component']//input[@name='name']")
    private WebElement componentNameField;

    @FindBy(xpath = "//input[@id='leadUserName-field']")
    private WebElement leadNameField;

    @FindBy(xpath = "//form[@id='components-add__component']//input[@name='description']")
    private WebElement descriptionField;

    @FindBy(xpath = "//input[@id='assigneeType-field']")
    private WebElement assigneeTypeField;

    @FindBy(xpath = "//form[@id='components-add__component']//button[@class='aui-button aui-button-primary']")
    private WebElement addButton;


    public ProjectComponentsPage(WebDriver driver) {
        super(driver);
    }

    public void addComponentName(String componentName) {
        componentNameField.sendKeys(componentName);
    }


    public void addLead(String lead) {
        leadNameField.click();
        leadNameField.sendKeys(lead);
        leadNameField.sendKeys(Keys.TAB);
    }

    public void addDescription(String description) {
        descriptionField.sendKeys(description);
    }

    public void addAssignee(String assignee) {
        assigneeTypeField.click();
        assigneeTypeField.sendKeys(assignee);
        assigneeTypeField.sendKeys(Keys.TAB);
    }

    public void createComponentWithAllData(String componentName, String lead, String description, String assignee) {
        addComponentName(componentName);
        addLead(lead);
        addDescription(description);
        addAssignee(assignee);
        addButton.click();
    }
}
