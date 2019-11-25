package com.codecool.gomboslarina;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage{

    @FindBy(xpath = "//a[@id='header-details-user-fullname']")
    private WebElement avatarPicture;

    @FindBy(xpath = "//a[@id='view_profile']")
    private WebElement viewProfile;

    @FindBy(xpath = "//a[@id='log_out']")
    private WebElement logoutLink;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void openProfilePage() {
        waitForElementToBeClickable(avatarPicture);
        avatarPicture.click();
        waitForElementToBeClickable(viewProfile);
        viewProfile.click();

    }

    public void logOut() {
        waitForElementToBeClickable(avatarPicture);
        avatarPicture.click();
        waitForElementToBeClickable(logoutLink);
        logoutLink.click();
    }


}
