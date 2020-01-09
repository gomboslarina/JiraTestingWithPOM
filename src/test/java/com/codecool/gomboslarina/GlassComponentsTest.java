package com.codecool.gomboslarina;

import org.junit.jupiter.api.*;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GlassComponentsTest extends BasePageTest {

    private ProjectComponentsPage projectComponentsPage;
    private GlassDocumentationPage glassDocumentationPage;
    private PropertiesReader propertiesReader;

    @BeforeAll
    public void setup() {
        super.setUp("linux", "chrome", this.getClass().getName().substring(26));
        projectComponentsPage = new ProjectComponentsPage(grid.getDriver());
        glassDocumentationPage = new GlassDocumentationPage(grid.getDriver());
        propertiesReader = new PropertiesReader();
        verifiedLogin();
    }

    @AfterAll
    public void closeTests() {
        shutDown2();
    }

    // Pass - remote: pass
    @Test
    public void createComponentWithAllTheData() {
        List<String> expectedComponents = projectComponentsPage.getComponentData(
                propertiesReader.getComponentsUrl(),
                propertiesReader.getComponentsName());
        List<String> glassComponents = glassDocumentationPage.getComponentData(propertiesReader.getGlassComponentsUrl(),
                propertiesReader.getComponentsName());
        Assertions.assertIterableEquals(expectedComponents, glassComponents);
    }

}