package com.codecool.gomboslarina;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

public class ProjectPermissionsPage extends BasePage {

    public ProjectPermissionsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='project-config-panel-permissions']")
    private WebElement permissionTable;

    // with this method we get all the data we need from the permission table as a list of list of strings. Maybe it should be Hashmap.
    List<List<String>> getTableDataOfTableRows() {
        waitForElementToBeVisible(permissionTable);
        List<WebElement> tableRows = permissionTable.findElements(By.cssSelector("tr[data-permission-key]"));
        try {
            Thread.sleep(10);

        } catch (InterruptedException e) {
            System.out.println(e);
        }
        List<List<String>> permissionRowsWithData = new ArrayList<>();
        for (WebElement row : tableRows) {
            List<String> data = new ArrayList<>();
            // add permission name to list:
            data.add(row.findElement(By.cssSelector("td[data-headers='project-permissions'] > p.title")).getText());
            // add user permissions to list
            for (WebElement we: row.findElements(By.cssSelector("dl.types"))) {
                data.add(we.getText());
            }
            permissionRowsWithData.add(data);
        }
        return permissionRowsWithData;
    }

    //this is the hashmap variate from the project page permissions:
    Map<String, List<Boolean>> createMapFromProjectPagePermissions() {
        List<List<String>> permissions = getTableDataOfTableRows();
        Map<String, List<Boolean>> permissionsMap = new HashMap<>();
        List<Boolean> booleans = new ArrayList<>();
        for (List<String> permRow : permissions) {
            for (int i = 1; i < permRow.size(); i++) {
                if (permRow.get(i).contains("Application access")) {
                    permissionsMap.get(permRow.get(0)).set(i, true);
                }
                if (permRow.get(i).contains("Project Role")) {
                    permissionsMap.get(permRow.get(0)).set(i, true);
                }
                if (permRow.get(i).contains("Assignee")) {
                    permissionsMap.get(permRow.get(0)).set(i, true);
                }
                if (permRow.get(i).contains("Reporter")) {
                    permissionsMap.get(permRow.get(0)).set(i, true);
                }
            }
        }
        return permissionsMap;
    }

}

    /*

    @FindBy(xpath = "//p[.='Administer Projects']")
    WebElement administerProjects;

    @FindBy(xpath = "//p[.='Browse Projects']")
    WebElement browseProjects;

    @FindBy(xpath = "//p[.='Glass View permission']")
    WebElement glassViewPermission;

    @FindBy(xpath = "//p[.='Manage Sprints']")
    WebElement manageSprints;

    @FindBy(xpath = "//p[.='View Development Tools']")
    WebElement viewDevelopmentTools;

    @FindBy(xpath = "//p[.='View Read-Only Workflow']")
    WebElement viewReadOnlyWorkflow;

    @FindBy(xpath = "//p[.='Assignable User']")
    WebElement assignableUser;

    @FindBy(xpath = "//p[.='Assign Issues']")
    WebElement assignIssues;

    @FindBy(xpath = "//p[.='Close Issues']")
    WebElement closeIssues;

    @FindBy(xpath = "//p[.='Create Issues']")
    WebElement createIssues;

    @FindBy(xpath = "//p[.='Delete Issues']")
    WebElement deleteIssues;

    @FindBy(xpath = "//p[.='Link Issues']")
    WebElement linkIssues;

    @FindBy(xpath = "//p[.='Modify Reporter']")
    WebElement modifiyReporter;

    @FindBy(xpath = "//p[.='Move Issues']")
    WebElement moveIssues;

    @FindBy(xpath = "//p[.='Resolve Issues']")
    WebElement resolveIssues;

    @FindBy(xpath = "//p[.='Schedule Issues']")
    WebElement scheduleIssues;

    @FindBy(xpath = "//p[.='Set Issue Security']")
    WebElement setIssueSecurity;

    @FindBy(xpath = "//p[.='Transition Issues']")
    WebElement transitionIssues;

    @FindBy(xpath = "//p[.='Manage Watchers']")
    WebElement manageWatchers;

    @FindBy(xpath = "//p[.='View Voters and Watchers']")
    WebElement viewVotersAndWatchers;

    @FindBy(xpath = "//p[.='Add Comments']")
    WebElement addComments;

    @FindBy(xpath = "//p[.='Delete All Comments']")
    WebElement deleteAllComments;

    @FindBy(xpath = "//p[.='Delete Own Comments']")
    WebElement deleteOwnComments;

    @FindBy(xpath = "//p[.='Edit All Comments']")
    WebElement editAllComments;

    @FindBy(xpath = "//p[.='Edit Own Comments']")
    WebElement editOwnComments;

    @FindBy(xpath = "//p[.='Create Attachments']")
    WebElement createAttachments;

    @FindBy(xpath = "//p[.='Delete All Attachments']")
    WebElement deleteAllAttachments;

    @FindBy(xpath = "//p[.='Delete Own Attachments']")
    WebElement deleteOwnAttachments;

    @FindBy(xpath = "//p[.='Delete All Worklogs']")
    WebElement deleteAllWorklogs;

    @FindBy(xpath = "//p[.='Delete Own Worklogs']")
    WebElement deleteOwnWorklogs;

    @FindBy(xpath = "//p[.='Edit All Worklogs']")
    WebElement editAllWorklogs;

    @FindBy(xpath = "//p[.='Edit Own Worklogs']")
    WebElement editOwnWorklogs;

    @FindBy(xpath = "//p[.='Work On Issues']")
    WebElement workOnIssues;

     */

