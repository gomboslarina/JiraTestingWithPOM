package com.codecool.gomboslarina;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.HashMap;
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
        glassDocumentationPage.goToGlassPermissionPage();
        //dashboardPage.goToPrivateProjectPage();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    void areGlassPermissionsParallelWithProjectPermissions() {
        Assertions.assertTrue(projectPermissionsPage.areProjectAndGlassPermissionsEqual(projectPage, glassDocumentationPage));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/glassPermissions.csv", numLinesToSkip = 1)
    void areGlassViewPermissionsCorrect(String user, boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6) {
        boolean[] ticks = {b1, b2, b3, b4, b5, b6};
        if (user.equals("superuser")) {
            Assertions.assertArrayEquals(ticks, glassDocumentationPage.getPermissionTicks(user));
        }
    }

    @AfterAll
    void closeTests() {
        dashboardPage.logOut();
        super.shutDown();
    }
}
