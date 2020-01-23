package com.codecool.gomboslarina;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GlassIssueTypesTest extends BasePageTest {
    GlassDocumentationPage glassDocumentationPage;
    ProjectIssueTypesPage projectIssueTypesPage;

    @BeforeAll
    public void setUp() {
        super.setUp("linux", "chrome", this.getClass().getName());
        login();
        glassDocumentationPage = new GlassDocumentationPage(getDriver());
        projectIssueTypesPage = new ProjectIssueTypesPage(getDriver());
    }

    @AfterAll
    public void closeTests() {
        super.shutDown();
    }

    @Test
    void checkIssueTypes() {
        Assertions.assertIterableEquals(projectIssueTypesPage.findIssueTypes(), glassDocumentationPage.findIssueTypesOnGlass());
    }

}