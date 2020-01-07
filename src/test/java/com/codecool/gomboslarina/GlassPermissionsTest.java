package com.codecool.gomboslarina;

import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GlassPermissionsTest extends BasePageTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private ProjectPermissionsPage projectPermissionsPage;
    private ProjectPage projectPage;
    private GlassDocumentationPage glassDocumentationPage;

    @BeforeAll
    void setup() {
        super.setUp("linux", "chrome");
        loginPage = new LoginPage(grid.getDriver());
        dashboardPage = new DashboardPage(grid.getDriver());
        projectPermissionsPage = new ProjectPermissionsPage(grid.getDriver());
        projectPage = new ProjectPage(grid.getDriver());
        glassDocumentationPage = new GlassDocumentationPage(grid.getDriver());
        loginPage.successfulLogin();
        dashboardPage.goToPrivateProjectPage();
        grid.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test // Pass - remote: pass
    void areGlassPermissionsParallelWithProjectPermissions() {
        Assertions.assertTrue(projectPermissionsPage.areProjectAndGlassPermissionsEqual(projectPage, glassDocumentationPage));
    }

    @AfterAll
    void closeTests() {
        dashboardPage.logOut();
        super.shutDown();
    }
}
