package com.codecool.gomboslarina;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GlassIssueTypesTest extends BasePageTest {

    private GlassDocumentationPage glassDocumentationPage;
    private ProjectIssueTypesPage projectIssueTypesPage;

    @BeforeAll
    public void setUp() {
        super.setUp("linux", "chrome");
        verifiedLogin();
        glassDocumentationPage = new GlassDocumentationPage(grid.getDriver());
        projectIssueTypesPage = new ProjectIssueTypesPage(grid.getDriver());
    }

    @AfterAll
    public void closeTests() {
        super.shutDown();
    }

    // Pass - remote: pass
    @Test
    void checkIssueTypes() {
        Assertions.assertIterableEquals(projectIssueTypesPage.findIssueTypes(), glassDocumentationPage.findIssueTypesOnGlass());
    }

}