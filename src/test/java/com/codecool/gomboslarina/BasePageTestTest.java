package com.codecool.gomboslarina;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BasePageTestTest extends BasePageTest {
    private LogoutPage logoutPage;

    @BeforeEach
    public void setup() {
        super.setUp();
        login();
        logout();
        logoutPage = new LogoutPage(getDriver());
    }

    @Test
    public void loginLinkAppearsIfUserLogsOut() {
        Assertions.assertTrue(logoutPage.checkIfUserIsLoggedOut());
    }

}