package com.codecool.gomboslarina;

import org.junit.jupiter.api.*;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GlassVersionTest extends BasePageTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private GlassDocumentationPage glassDocumentationPage;
    private ProjectPage projectPage;

    @BeforeAll
    void setup() {
        super.setUp("linux", "chrome", this.getClass().getName().substring(26));
        loginPage = new LoginPage(grid.getDriver());
        glassDocumentationPage = new GlassDocumentationPage(grid.getDriver());
        dashboardPage = new DashboardPage(grid.getDriver());
        projectPage = new ProjectPage(grid.getDriver());
        loginPage.successfulLogin();
        dashboardPage.goToPrivateProjectPage();
        projectPage.clickOnGlassLink();
    }

    // Pass - remote: pass
    @Test
    void isGlassVersionUpToDate() {
        List<String> glassVersion = glassDocumentationPage.getGlassVersionAttributes();
        projectPage.clickOnReleaseLink();
        List<String> version = projectPage.getVersionAttributes();
        Assertions.assertArrayEquals(glassVersion.toArray(), version.toArray());
    }

    @AfterAll
    void closeTest() {
        dashboardPage.logOut();
        shutDown();
    }

}