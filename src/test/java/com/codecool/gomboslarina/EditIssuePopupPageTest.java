package com.codecool.gomboslarina;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.text.SimpleDateFormat;
import java.util.Date;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EditIssuePopupPageTest extends BasePageTest {
    private IssuePage issuePage;
    private EditIssuePopupPage editIssuePopupPage;

    @BeforeAll
    void setup() {
        super.setUp();
        login();
        issuePage = new IssuePage(getDriver());
        editIssuePopupPage = new EditIssuePopupPage(getDriver());
    }

    @AfterAll
    void closeTests() {
        super.shutDown();
    }

    @Test
    public void editIssueHappyPath() {
        issuePage.navigateToPage("https://jira.codecool.codecanvas.hu/browse/MTP-746");
        String updatedSummary = "Testing editing issue:" + new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
        issuePage.openEditIssue();
        editIssuePopupPage.editIssue(updatedSummary);
        Assertions.assertEquals(updatedSummary, issuePage.getSummary());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/EditIssue.csv")
    public void checkIfIssuesAreEditable(String issue) {
        issuePage.navigateToPage(issue);
        Assertions.assertTrue(issuePage.checkPermissionToEditIssue());
    }
}