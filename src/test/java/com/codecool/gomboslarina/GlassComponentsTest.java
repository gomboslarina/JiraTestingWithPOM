package com.codecool.gomboslarina;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GlassComponentsTest extends BasePageTest {
    ProjectComponentsPage projectComponentsPage;
    GlassDocumentationPage glassDocumentationPage;

    @BeforeAll
    public void setup() {
        setUp();
        login();
        projectComponentsPage = new ProjectComponentsPage(getDriver());
        glassDocumentationPage = new GlassDocumentationPage(getDriver());
    }

    @AfterAll
    public void closeTests() {
        shutDown();
    }

    @Test
    public void createComponentWithAllTheData() {
        List<String> expectedComponents = projectComponentsPage.getComponentData(
                "https://jira2.codecool.codecanvas.hu/projects/TIA?selectedItem=com.atlassian.jira.jira-projects-plugin:components-page",
                "TestComponent");
        List<String> glassComponents = glassDocumentationPage.getComponentData("https://jira2.codecool.codecanvas.hu/projects/TIA?selectedItem=com.codecanvas.glass:glass",
                "TestComponent");
        Assertions.assertIterableEquals(expectedComponents, glassComponents);
    }

}