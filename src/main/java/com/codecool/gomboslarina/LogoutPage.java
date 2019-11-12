package com.codecool.gomboslarina;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage {
    @FindBy(xpath = "//a[.='Log In']")
    private WebElement loginLink;

}
