package com.codecool.gomboslarina;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;
import java.util.stream.IntStream;

public class GlassDocumentationPage extends Components {

    GlassDocumentationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[.='Versions']")
    private WebElement versionLink;

    @FindBy(xpath = "//a[.='TestVersion']")
    private WebElement testVersionLink;

    @FindBy(xpath = "//span[@class='aui-lozenge aui-lozenge-current aui-lozenge-subtle']")
    private WebElement releaseStatus;

    @FindBy(xpath = "//div[.='21/Jan/20']")
    private WebElement versionDate;

    @FindBy(xpath = "//div[.='Check this version in glass']")
    private WebElement versionDescription;

    @FindBy(xpath = "//a[.='Permissions']")
    private WebElement glassPermissionLink;

    @FindBy(xpath = "//*[@id='glass-permissions-panel']/div/table/tbody")
    private WebElement permissionTable;

    @FindBy(xpath = "//aui-dropdown-menu[@id='dropdown-issuetypes']//aui-item-link")
    private List<WebElement> issueTypesGlass;

    @FindBy(xpath = "//*[@id='components-table']/thead/tr/th")
    private List<WebElement> glassComponentHeaders;

    private String pageUrl = "https://jira2.codecool.codecanvas.hu/projects/TIA?selectedItem=com.codecanvas.glass:glass";

    @FindBy(xpath = "//*[@id='glass-permissions-panel']/div/table/tbody/tr[21]")
    private WebElement glassViewPermissionsTableRow;

    @FindBy(xpath = "//*[@id=\"glass-notifications-panel\"]/div/table/tbody/tr[1]")
    private WebElement notificationsTableRow;

    @FindBy(xpath = "//*[@id='glass-notifications-nav']/a")
    WebElement notificationsLink;

    public String getPageUrl() {
        return pageUrl;
    }

    boolean[] getGlassTableRowTicks(int column, WebElement table) {
        List<WebElement> tickHolders = table.findElements(By.cssSelector(".td-icon"));
        boolean[] ticks = new boolean[column];
        for (int i = 0; i < column; i++) {
            try {
                tickHolders.get(i).findElement(By.cssSelector(".glass-true-icon"));
                ticks[i] = true;
            } catch (Exception e) {
                ticks[i] = false;
            }
        }
        return ticks;
    }

    void gotToGlassVersionPage() {
        waitForElementToBeClickable(versionLink);
        versionLink.click();
    }

    String getVersionNumber() {
        waitForElementToBeVisible(testVersionLink);
        return testVersionLink.getText();
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
        versionAttributes.add(getVersionNumber());
        versionAttributes.add(getReleaseStatus());
        versionAttributes.add(getVersionDate());
        versionAttributes.add(getVersionDescription());
        return versionAttributes;
    }

    public void goToGlassPermissionPage() {
        navigateToPage(pageUrl);
        waitForElementToBeClickable(glassPermissionLink);
        glassPermissionLink.click();
    }

    public void goToNotificationsPage() {
        navigateToPage(pageUrl);
        waitForElementToBeClickable(notificationsLink);
        notificationsLink.click();
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
            // System.out.println("GLassPermissionPage");
            // System.out.println(row.findElement(By.cssSelector(".title")).getText());
            // System.out.println(Arrays.toString(permissionTicks.toArray()));
            if (row.findElement(By.cssSelector(".title")).getText().equals("Glass View permission")) {
                permissions.put(row.findElement(By.cssSelector(".title")).getText(), permissionTicks);
            }
        }
        return permissions;
    }

    List findIssueTypesOnGlass() {
        navigateToPage("https://jira2.codecool.codecanvas.hu/projects/TIA?selectedItem=com.codecanvas.glass:glass");
        List<String> issueTypes = new ArrayList<>();
        for (WebElement issueType : issueTypesGlass
        ) {
            issueTypes.add(issueType.getAttribute("data-issue-type"));
        }
        Collections.sort(issueTypes);
        return issueTypes;
    }

    public List<WebElement> getGlassComponentHeaders() {
        return glassComponentHeaders;
    }

    public WebElement getGlassViewPermissionsTableRow() {
        return glassViewPermissionsTableRow;
    }

    public WebElement getNotificationsTableRow() {
        return notificationsTableRow;
    }
}
