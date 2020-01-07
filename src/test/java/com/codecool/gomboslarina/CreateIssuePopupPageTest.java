package com.codecool.gomboslarina;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateIssuePopupPageTest extends BasePageTest { // remote: if these running parallel, they fail because elementclickintercepted exception
    private CreateIssuePopupPage createIssuePopupPage;
    private IssuePage issuePage;
    private DashboardPage dashboardPage;

    @BeforeAll
    public void setup() {
        super.setUp("linux", "chrome");
        verifiedLogin();
        dashboardPage = new DashboardPage(grid.getDriver());
        createIssuePopupPage = new CreateIssuePopupPage(grid.getDriver());
        issuePage = new IssuePage(grid.getDriver());
    }

    @AfterAll
    public void closeTests() {
        super.shutDown();
    }

    // Pass - remote: pass
    @ParameterizedTest
    @CsvFileSource(resources = "/CreateIssue.csv")
    public void createIssueHappyPath(String projectName, String issueType, String summary) {
        dashboardPage.clickOnCreateButton();
        createIssuePopupPage.createIssue(projectName, issueType, summary);
        createIssuePopupPage.clickOnIssuePopupLink();
        Assertions.assertEquals(projectName, issuePage.getIssueProject());
        Assertions.assertEquals(issueType, issuePage.getIssueType());
        Assertions.assertEquals(summary, issuePage.getSummary());
        issuePage.deleteIssue();
    }

    // Toucan, Jeti fail, 3 Coala pass, 1 fail - remote: same
    @ParameterizedTest
    @CsvFileSource(resources = "/CheckIssueTypes.csv")
    public void checkIssuetypes(String projectName, String issueType) {
        dashboardPage.clickOnCreateButton();
        createIssuePopupPage.addExpectedValues(projectName, issueType);
        String currentProject = createIssuePopupPage.getCurrentProject();
        String currentIssueType = createIssuePopupPage.getCurrentIssueType();
        createIssuePopupPage.cancelCreatingIssue();
        Assertions.assertEquals(projectName, currentProject);
        Assertions.assertEquals(issueType, currentIssueType);
    }

    // Pass - remote: pass
    @Test
    public void createIssueWithoutRequiredField(){
        dashboardPage.clickOnCreateButton();
        Assertions.assertTrue(createIssuePopupPage.checkIfErrorMessageAppearsWithoutSummary());
    }
}