package com.codecool.gomboslarina;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateIssuePopupPageTest extends BasePageTest {
    private CreateIssuePopupPage createIssuePopupPage;
    private IssuePage issuePage;
    private DashboardPage dashboardPage;

    @BeforeAll
    public void setup() {
        super.setUp();
        login();
        dashboardPage = new DashboardPage(getDriver());
        createIssuePopupPage = new CreateIssuePopupPage(getDriver());
        issuePage = new IssuePage(getDriver());
    }

    @AfterAll
    public void closeTests() {
        super.shutDown();
    }

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

    @ParameterizedTest
    @CsvFileSource(resources = "/CheckIssueTypes.csv")
    public void checkIssuetypes(String projectName, String issueType) {
        dashboardPage.clickOnCreateButton();
        createIssuePopupPage.addExpectedValues(projectName, issueType);
        Assertions.assertEquals(projectName, createIssuePopupPage.getCurrentProject());
        Assertions.assertEquals(issueType, createIssuePopupPage.getCurrentIssueType());
        createIssuePopupPage.cancelCreatingIssue();
//        createIssuePopupPage.handleAlert();
    }
}