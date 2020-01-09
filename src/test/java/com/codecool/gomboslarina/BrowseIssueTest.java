package com.codecool.gomboslarina;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BrowseIssueTest extends BasePageTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private ProjectPage projectPage;
    private IssuePage issuePage;

    @BeforeAll
    void setup() {
        super.setUp("linux", "chrome", this.getClass().getName());
        loginPage = new LoginPage(grid.getDriver());
        dashboardPage = new DashboardPage(grid.getDriver());
        projectPage = new ProjectPage(grid.getDriver());
        issuePage = new IssuePage(grid.getDriver());
        loginPage.successfulLogin();
        capability.setCapability("name",this.getClass().getName());
    }

    @BeforeEach
    void goToSearchIssuePage() {
        dashboardPage.goToSearchIssuesPage();
    }


    // Pass - remote: pass
    @ParameterizedTest
    @CsvFileSource(resources = "/BrowseProject.csv", numLinesToSkip = 1)
    void isIssueListAvailableOnProjectPage(String project) {
        Assertions.assertTrue(projectPage.checkIssueListIsAvailable(project));
    }

    // Fail - remote: fail
    @ParameterizedTest
    @CsvFileSource(resources = "/BrowseIssue.csv", numLinesToSkip = 1)
    void areSpecificIssuesAvailable(String issueName) {
        capability.setCapability("name", "areSpecificIssuesAvailable");
        Assertions.assertTrue(issuePage.searchForIssues(issueName));
    }

    @AfterAll
    void CloseTests() {
        dashboardPage.logOut();
        shutDown();
    }
}
