package com.codecool.gomboslarina;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LogoutPageTest extends BasePageTest {
    private LogoutPage logoutPage;

    @BeforeEach
    public void setup() {
        super.setUp("linux", "chrome", this.getClass().getName().substring(26));
        verifiedLogin();
        logout();
        logoutPage = new LogoutPage(grid.getDriver());
    }

    @AfterEach
    public void closeTests() {
        super.shutDown();
    }

    // Pass - remote: pass
    @Test
    public void loginLinkAppearsIfUserLogsOut() {
        Assertions.assertTrue(logoutPage.checkIfUserIsLoggedOut());
    }

}