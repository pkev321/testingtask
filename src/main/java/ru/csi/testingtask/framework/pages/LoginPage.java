package ru.csi.testingtask.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Dmitrii_Khodakovskii on 12/23/2016.
 */
public class LoginPage {

    public static class LoginPageProperties {
       public static final By USER_NAME_FILED = By.name("login");
       public static final By PASSWORD_FIELD = By.name("passwd");
       public static final By SIGN_IN_BUTTON = By.xpath("//button[contains(@class, \'nb-button\') and @type=\'submit\']");
    }

    private WebDriver driver;
    private WebElement usernameField;
    private WebElement passwordField;
    private WebElement signInButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.usernameField = this.driver.findElement(LoginPageProperties.USER_NAME_FILED);
        this.passwordField = this.driver.findElement(LoginPageProperties.PASSWORD_FIELD);
        this.signInButton = this.driver.findElement(LoginPageProperties.SIGN_IN_BUTTON);
    }

    public void setUserName(String userName) {
        if (userName == null || userName.length() == 0)
            throw new IllegalArgumentException("Предан не верный параметр: username");
        this.usernameField.sendKeys(userName);
    }

    public void setPassword(String password) {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Предан не верный параметр: password");
        this.passwordField.sendKeys(password);
    }

    public void clickSignInButton() {
        this.signInButton.click();
    }

}
