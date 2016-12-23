package ru.csi.testingtask.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Dmitrii_Khodakovskii on 12/23/2016.
 */
public class LoginPage {

    private WebDriver driver;
    private WebElement usernameField;
    private WebElement passwordField;
    private WebElement signInButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        init(driver);
    }

    public void init(WebDriver driver) {
        this.usernameField = driver.findElement(By.name("login"));
        this.passwordField = driver.findElement(By.name("passwd"));
        this.signInButton = driver.findElement(By.xpath("//button[contains(@class, \'nb-button\') and @type=\'submit\']"));
    }

    public void setUserName(String userName) {
        if (userName == null || userName.length() == 0)
            throw new IllegalArgumentException("Предан не верный параметр");
        this.usernameField.sendKeys(userName);
    }

    public void setPassword(String password) {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Предан не верный параметр");
        this.passwordField.sendKeys(password);
    }

    public void clickSignInButton() {
        this.signInButton.click();
    }

}
