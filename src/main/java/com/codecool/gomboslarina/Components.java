package com.codecool.gomboslarina;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

public class Components extends BasePage {
    List<String> requiredComponents = new ArrayList<>();

    @FindBy(xpath = "//table[@id='components-table']/tbody[@class='items']//td[@class='components-table__name']")
    private List<WebElement> componentNames;

    @FindBy(xpath = "//table[@id='components-table']/tbody[@class='header']/tr/th")
    private List<WebElement> projectComponentHeaders;

    public Components(WebDriver driver) {
        super(driver);
        requiredComponents.add("Component");
        requiredComponents.add("Lead");
        requiredComponents.add("Default assignee");
    }

    public TreeMap<String, String> findComponent(String url, String compName, List<WebElement> componentHeaders) {
        navigateToPage(url);
        TreeMap<String, String> component = new TreeMap<>();
        List<WebElement> tableData = new ArrayList<>();
        for (WebElement componentName : componentNames) {
            if (componentName.getText().equals(compName)) {
                tableData.add(componentName);
                tableData.addAll(componentName.findElements(By.xpath("following-sibling::*")));
            }
        }
        for (int i = 0; i < componentHeaders.size(); i++) {
            component.put(componentHeaders.get(i).getText(), tableData.get(i).getText());
        }
        return component;
    }
    public List<String> getComponentData(String url, String compName, List<WebElement> headers) {
        TreeMap component = findComponent(url, compName, headers);
        List<String> componentData = new ArrayList<>();
        for (String requiredComponent : requiredComponents) {
            componentData.add((String) component.get(requiredComponent));
        }
        return componentData;
    }

    public List<WebElement> getProjectComponentHeaders() {
        return projectComponentHeaders;
    }

}
