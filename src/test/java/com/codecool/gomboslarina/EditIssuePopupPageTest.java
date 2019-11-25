package com.codecool.gomboslarina;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EditIssuePopupPageTest extends BasePageTest {
    private IssuePage issuePage;
    private EditIssuePopupPage editIssuePopupPage;
    private DashboardPage dashboardPage;

    @BeforeEach
    void setup() {
        super.setUp();
        login();
        issuePage = new IssuePage(getDriver());
        editIssuePopupPage = new EditIssuePopupPage(getDriver());
    }

//    @AfterEach
//    void closeTests() {
//        super.shutDown();
//    }

    @Test
    public void editIssueHappyPath() {
        issuePage.navigateToPage("https://jira.codecool.codecanvas.hu/browse/MTP-746");
        String currentSummary = issuePage.getSummary();
        issuePage.openEditIssue();
        editIssuePopupPage.editIssue("Testing editing issue");
        Assertions.assertEquals("Testing editing issue", issuePage.getSummary());
        issuePage.openEditIssue();
        editIssuePopupPage.editIssue(currentSummary);
    }

}