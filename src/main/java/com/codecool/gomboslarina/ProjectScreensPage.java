package com.codecool.gomboslarina;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;

public class ProjectScreensPage extends BasePage {

    private String screenPageUrl = "https://jira2.codecool.codecanvas.hu/plugins/servlet/project-config/TIA/screens";

    @FindBy(xpath = "//*[@id=\"project-config-screens-10031\"]/div[1]/h3/span[2]")
    private WebElement bugScreenLink;

    @FindBy(xpath = "//*[@id=\"project-config-screens-10031\"]/div[2]/div[2]/table/tbody/tr[1]/td[2]/a")
    private WebElement tableLink;

    @FindBy(xpath = "//*[@id=\"tab-10160\"]/tbody/tr['.aui-restfultable-readonly .aui-restfultable-row']")
    private List<WebElement> tableBodyRows;


    public ProjectScreensPage(WebDriver driver) {
        super(driver);
    }

    void goToScreenTable() {
        navigateToPage(screenPageUrl);
        waitForElementToBeClickable(bugScreenLink);
        bugScreenLink.click();
        waitForElementToBeClickable(tableLink);
        tableLink.click();
    }

    public HashMap<String, String> createHashMapFromTable() {
        goToScreenTable();
        HashMap<String, String> tableRowMap = new HashMap<>();
        for (WebElement element : tableBodyRows) {
            WebElement e = element.findElement(By.cssSelector(".field-name-cell"));
            WebElement eSibling = e.findElement(By.xpath("./following-sibling::td"));
            tableRowMap.put(e.getText(), eSibling.getText());
        }
        return tableRowMap;
    }

}
