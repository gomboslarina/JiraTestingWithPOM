package com.codecool.gomboslarina;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GlassNotificationsTest extends BasePageTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private GlassDocumentationPage glassDocumentationPage;

    @BeforeAll
    void setup() {
        super.setUp();
        loginPage = new LoginPage(getDriver());
        dashboardPage = new DashboardPage(getDriver());
        glassDocumentationPage = new GlassDocumentationPage(getDriver());
        loginPage.successfulLogin();
        glassDocumentationPage.goToNotificationsPage();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/GlassNotifications.csv", numLinesToSkip = 1)
    void areGlassNotificationsCorrect(boolean b1, boolean b2, boolean b3) {
        boolean[] ticks = {b1, b2, b3};
        Assertions.assertArrayEquals(ticks, glassDocumentationPage.getGlassTableRowTicks(3, glassDocumentationPage.getNotificationsTableRow()));
    }

    @AfterAll
    void closeTests() {
        dashboardPage.logOut();
        super.shutDown();
    }

}
