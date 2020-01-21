package com.codecool.gomboslarina;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectIssueTypesPage extends BasePage {
    @FindBy(xpath = "//div[@id='project-config-webpanel-summary-issuetypes']//span[@class='project-config-list-label']/span[@class='project-config-issuetype-name']")
    List<WebElement> issueTypesElements;

    public ProjectIssueTypesPage(WebDriver driver) {
        super(driver);
    }

    public List findIssueTypes() {
        navigateToPage("https://jira2.codecool.codecanvas.hu/plugins/servlet/project-config/TIA/summary");
        List<String> issueTypes = new ArrayList<>();
        for (WebElement issueType : issueTypesElements
        ) {
            issueTypes.add(issueType.getText());
        }
        Collections.sort(issueTypes);
        return issueTypes;
    }
}
