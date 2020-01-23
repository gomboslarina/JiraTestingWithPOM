package com.codecool.gomboslarina;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GlassScreenTest extends BasePageTest {

    private LoginPage loginPage;
    private GlassDocumentationPage glassDocumentationPage;
    private DashboardPage dashboardPage;
    private ProjectScreensPage projectScreensPage;

    @BeforeAll
    void setup() {
        super.setUp("linux", "chrome", this.getClass().getName());
        loginPage = new LoginPage(getDriver());
        glassDocumentationPage = new GlassDocumentationPage(getDriver());
        dashboardPage = new DashboardPage(getDriver());
        projectScreensPage = new ProjectScreensPage(getDriver());
        loginPage.successfulLogin();
    }

    @Test // BUG -> Create issue screen
    void areTablesEqual() {
        Assertions.assertEquals(projectScreensPage.createHashMapFromTable(), glassDocumentationPage.getCreateIssueTable());
    }


    @AfterAll
    void closeTest() {
        dashboardPage.logOut();
        shutDown();
    }

}
