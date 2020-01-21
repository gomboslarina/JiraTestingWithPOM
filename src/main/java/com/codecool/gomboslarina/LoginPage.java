package com.codecool.gomboslarina;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//*[@id='login-form-username']")
    private WebElement loginUsername;

    @FindBy(xpath = "//*[@id='login-form-password']")
    private WebElement loginPassWord;

    @FindBy(xpath = "//*[@id='login-form-submit']")
    private WebElement loginSubmitButton;

    @FindBy(xpath = "//div[@id='usernameerror']")
    private WebElement errorMessage;

    @FindBy(xpath = "//input[@id='login-form-remember-me']")
    private WebElement rememberMeCheckbox;


    public LoginPage(WebDriver driver) {
        super(driver);
        String loginDashboardPageUrl = "https://jira2.codecool.codecanvas.hu/login.jsp";
        navigateToPage(loginDashboardPageUrl);
    }

    public void successfulLogin() {
        login(getUsername(),getPassword());
    }

    public void login(String user, String psw) {
        waitForElementToBeClickable(loginUsername);
        loginUsername.sendKeys(user);
        waitForElementToBeClickable(loginPassWord);
        loginPassWord.sendKeys(psw);
        waitForElementToBeClickable(loginSubmitButton);
        loginSubmitButton.click();
    }

    public boolean checkIfErrorMessageAppears() {
        try {
            waitForElementToAppear(errorMessage);
            return true;
            } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean checkIfCheckboxIsSelected() {
        waitForElementToBeClickable(rememberMeCheckbox);
        rememberMeCheckbox.click();
        return rememberMeCheckbox.isSelected();
    }

}
