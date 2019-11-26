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

    private String projectPermissionsUrl = "https://jira.codecool.codecanvas.hu/plugins/servlet/project-config/PP4/permissions";

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
        for (List<String> permRow : permissions) {
            List<Boolean> permissionTicks = new ArrayList<>();
            permissionTicks.add(false);
            for (int i = 1; i < permRow.size(); i++) {
                if (permRow.get(i).contains("Application access".toLowerCase())) {
                    permissionTicks.add(true);
                    permissionTicks.set(0, true);
                } else {
                    permissionTicks.add(false);
                }
                if (permRow.get(i).contains("Project Role".toLowerCase())) {
                    permissionTicks.add(true);
                    permissionTicks.set(0, true);
                } else {
                    permissionTicks.add(false);
                }
                if (permRow.get(i).contains("Assignee".toLowerCase())) {
                    permissionTicks.add(true);
                    permissionTicks.set(0, true);
                } else {
                    permissionTicks.add(false);
                }
                if (permRow.get(i).contains("Reporter".toLowerCase())) {
                    permissionTicks.add(true);
                    permissionTicks.set(0, true);
                } else {
                    permissionTicks.add(false);
                }
                permissionsMap.put(permRow.get(0), permissionTicks);
                // System.out.println(permRow.get(0));
                // System.out.println(Arrays.toString(permissionTicks.toArray()));
            }
        }
        return permissionsMap;
    }

    boolean areProjectAndGlassPermissionsEqual(ProjectPage projectPage, GlassDocumentationPage glassDocumentationPage) {
        goToPermissions();
        Map<String, List<Boolean>> projectPermissions = createMapFromProjectPagePermissions();
        projectPage.clickOnGlassLink();
        glassDocumentationPage.goToGlassPermissionPage();
        Map<String, List<Boolean>> glassPermissions = glassDocumentationPage.getGlassPagePermissions();
        int sizeOfGlassPerm = glassPermissions.keySet().size();
        int booleanListCounter = 0;
        for (String projectKey : projectPermissions.keySet()) {
            for (String glassKey : glassPermissions.keySet()) {
                if (projectKey.equals(glassKey)) {
                    if (compareBooleanLists(projectPermissions.get(projectKey), glassPermissions.get(glassKey))) {
                        booleanListCounter++;
                    } else {
                        System.out.println("Project permission and glass permission does not equal.\nGlass permission: " + glassKey +
                                "\n" + glassPermissions.get(glassKey) + "\n Project permission: " + projectKey + "\n" + projectPermissions.get(projectKey));
                    }
                }
            }
        }
        return booleanListCounter == sizeOfGlassPerm;
    }

    boolean compareBooleanLists(List<Boolean> a, List<Boolean> b) {
        if (a.size() != b.size()) {
            System.out.println("The size of the lists are not the same.");
            return false;
        }
        int listSize = a.size();
        int size = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) == b.get(i)) {
                size++;
            }
        }
        return size == listSize;
    }

    private void goToPermissions() {
        navigateToPage(projectPermissionsUrl);
    }

}