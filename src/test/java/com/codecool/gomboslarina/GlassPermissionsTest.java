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
        super.setUp();
        loginPage = new LoginPage(getDriver());
        dashboardPage = new DashboardPage(getDriver());
        projectPermissionsPage = new ProjectPermissionsPage(getDriver());
        projectPage = new ProjectPage(getDriver());
        glassDocumentationPage = new GlassDocumentationPage(getDriver());
        loginPage.successfulLogin();
        dashboardPage.goToPrivateProjectPage();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    void areGlassPermissionsParallelWithProjectPermissions() {
        Assertions.assertTrue(projectPermissionsPage.areProjectAndGlassPermissionsEqual(projectPage, glassDocumentationPage));
    }

    @AfterAll
    void closeTests() {
        dashboardPage.logOut();
        super.shutDown();
    }
}
