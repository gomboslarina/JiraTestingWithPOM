package com.codecool.gomboslarina;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GlassScreenTest extends BasePageTest {

    private LoginPage loginPage;
    private GlassDocumentationPage glassDocumentationPage;
    private DashboardPage dashboardPage;
    private ProjectScreensPage projectScreensPage;

    @BeforeAll
    void setup() {
        super.setUp();
        loginPage = new LoginPage(getDriver());
        glassDocumentationPage = new GlassDocumentationPage(getDriver());
        dashboardPage = new DashboardPage(getDriver());
        projectScreensPage = new ProjectScreensPage(getDriver());
        loginPage.successfulLogin();
    }

    @Test
    void areTablesEqual() {
        for (String s : projectScreensPage.createHashMapFromTable().values()) {
            System.out.println(s);
        }
    }


    @AfterAll
    void closeTest() {
        dashboardPage.logOut();
        shutDown();
    }

}
