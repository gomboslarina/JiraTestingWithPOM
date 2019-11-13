package com.codecool.gomboslarina;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class GlassDocumentationPage extends BasePage{

    public GlassDocumentationPage(WebDriver driver) {
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

    private String pageUrl = "https://jira.codecool.codecanvas.hu/projects/PP4?selectedItem=com.codecanvas.glass:glass";

    public String getPageUrl() {
        return pageUrl;
    }

    void gotToGlassVersionPage() {
        waitForElementToBeClickable(versionLink);
        versionLink.click();
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

    public List<String> getVersionAttributes() {
        List<String> versionAttributes = new ArrayList<>();
        gotToGlassVersionPage();
        versionAttributes.add(getReleaseStatus());
        versionAttributes.add(getVersionDate());
        versionAttributes.add(getVersionDescription());
        return versionAttributes;
    }


}
