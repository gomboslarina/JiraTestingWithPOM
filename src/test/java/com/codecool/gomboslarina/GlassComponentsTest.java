package com.codecool.gomboslarina;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GlassComponentsTest extends BasePageTest{
    GlassDocumentationPage glassDocumentationPage;

    @BeforeAll
    public void setup() {
        setUp();
        login();
        glassDocumentationPage = new GlassDocumentationPage(getDriver());
    }

    @AfterAll
    public void closeTests(){
        shutDown();
    }

    @Test
    public void componentTest(){
        glassDocumentationPage.navigateToPage("https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.codecanvas.glass:glass");
        glassDocumentationPage.findComponent("component check").forEach((key, value) -> System.out.println(key + " " + value));
    }

}