package com.codecool.gomboslarina;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginPageTest extends BasePageTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private ProfilePage profilePage;

    @BeforeEach
    void setup() {
        super.setUp("linux", "chrome", this.getClass().getName().substring(26));
        loginPage = new LoginPage(grid.getDriver());
        dashboardPage = new DashboardPage(grid.getDriver());
        profilePage = new ProfilePage(grid.getDriver());
    }

    @AfterEach
    void closeTests() {
        super.shutDown();
    }

    // Pass - remote: pass
    @Test
    void ShouldReturnTrueWithCorrectLoginDetails() {
        loginPage.login(loginPage.getUsername(), loginPage.getPassword());
        dashboardPage.openProfilePage();
        Assertions.assertEquals(loginPage.getUsername(), profilePage.checkUsername());
        Assertions.assertEquals(loginPage.getEmail(), profilePage.checkEmail());
        dashboardPage.logOut();
    }

    // Pass - remote: pass
    @Test
    void ShouldReturnErrorLoginMessage() {
        loginPage.login(loginPage.getIncorrectUsername(), loginPage.getIncorrectPassword());
        Assertions.assertTrue(loginPage.checkIfErrorMessageAppears());
    }

    // Pass - remote: pass
    @Test
    void ShouldReturnErrorWithEmptyLoginFields() {
        loginPage.login("", "");
        Assertions.assertTrue(loginPage.checkIfErrorMessageAppears());
    }

    // Pass - remote: pass
    @Test
    void ShouldRememberLoginData() {
        Assertions.assertTrue(loginPage.checkIfCheckboxIsSelected());
    }

}