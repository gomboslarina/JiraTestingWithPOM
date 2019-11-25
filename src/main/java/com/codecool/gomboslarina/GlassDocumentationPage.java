package com.codecool.gomboslarina;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;
import java.util.stream.IntStream;

public class GlassDocumentationPage extends BasePage{

    GlassDocumentationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[.='Versions']")
    private WebElement versionLink;

    @FindBy(xpath = "//a[.='5.0']")
    private WebElement version5Link;

    @FindBy(xpath = "//tbody[@class='items ui-sortable']//span[@class='aui-lozenge aui-lozenge-current aui-lozenge-subtle']")
    private WebElement releaseStatus;

    @FindBy(xpath = "//div[.='22/Oct/19']")
    private WebElement versionDate;

    @FindBy(xpath = "//div[.='Check this version in glass']")
    private WebElement versionDescription;

    @FindBy(xpath = "//a[.='Permissions']")
    private WebElement glassPermissionLink;

    @FindBy(xpath = "//*[@id='glass-permissions-panel']/div/table/tbody")
    private WebElement permissionTable;

    private String pageUrl = "https://jira.codecool.codecanvas.hu/projects/PP4?selectedItem=com.codecanvas.glass:glass";

    public String getPageUrl() {
        return pageUrl;
    }

    void gotToGlassVersionPage() {
        waitForElementToBeClickable(versionLink);
        versionLink.click();
    }

    String getVersionNumber() {
        waitForElementToBeVisible(version5Link);
        return version5Link.getText();
    }

    String getReleaseStatus() {
        waitForElementToBeVisible(releaseStatus);
        return releaseStatus.getText();
    }

    String getVersionDate() {
        waitForElementToBeVisible(versionDate);
        return versionDate.getText();
    }

    String getVersionDescription() {
        waitForElementToBeVisible(versionDescription);
        return versionDescription.getText();
    }

    List<String> getGlassVersionAttributes() {
        List<String> versionAttributes = new ArrayList<>();
        gotToGlassVersionPage();
        versionAttributes.add(getVersionNumber());
        versionAttributes.add(getReleaseStatus());
        versionAttributes.add(getVersionDate());
        versionAttributes.add(getVersionDescription());
        return versionAttributes;
    }

    void goToGlassPermissionPage() {
        waitForElementToBeClickable(glassPermissionLink);
        glassPermissionLink.click();
    }


    // This shows the permission ticks from the glass doc page:
    Map<String, List<Boolean>> getGlassPagePermissions() {
        List<WebElement> tableRows = permissionTable.findElements(By.cssSelector("tr.permtr"));
        //System.out.println(tableRows);
        Map<String, List<Boolean>> permissions = new HashMap<>();
        for (WebElement row : tableRows) {
            List<Boolean> permissionTicks = new ArrayList<>();
            List<WebElement> iconElements = row.findElements(By.cssSelector("td.td-icon"));
            IntStream.range(0, iconElements.size()).forEach(i -> {
                try {
                    iconElements.get(i).findElement(By.cssSelector(".glass-true-icon"));
                    permissionTicks.add(true);
                } catch (Exception exception) {
                    permissionTicks.add(false);
                }
            });
            System.out.println("GLassPermissionPage");
            System.out.println(row.findElement(By.cssSelector(".title")).getText());
            System.out.println(Arrays.toString(permissionTicks.toArray()));
            permissions.put(row.findElement(By.cssSelector(".title")).getText(), permissionTicks);
        }
        return permissions;
    }
}
