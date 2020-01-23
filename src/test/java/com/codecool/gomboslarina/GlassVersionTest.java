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
        super.setUp("linux", "chrome", this.getClass().getName());
        loginPage = new LoginPage(getDriver());
        glassDocumentationPage = new GlassDocumentationPage(getDriver());
        dashboardPage = new DashboardPage(getDriver());
        projectPage = new ProjectPage(getDriver());
        loginPage.successfulLogin();
        //dashboardPage.goToPrivateProjectPage();
        loginPage.navigateToPage("https://jira2.codecool.codecanvas.hu/projects/TIA?selectedItem=com.codecanvas.glass:glass");
        glassDocumentationPage.gotToGlassVersionPage();
        //projectPage.clickOnGlassLink();
    }

    @Test
    void isGlassVersionUpToDate() {
        List<String> glassVersion = glassDocumentationPage.getGlassVersionAttributes();
        //projectPage.clickOnReleaseLink();
        glassDocumentationPage.navigateToPage("https://jira2.codecool.codecanvas.hu/plugins/servlet/project-config/TIA/administer-versions");
        List<String> version = projectPage.getVersionAttributes();
        Assertions.assertArrayEquals(glassVersion.toArray(), version.toArray());
    }

    @AfterAll
    void closeTest() {
        dashboardPage.logOut();
        shutDown();
    }

}
