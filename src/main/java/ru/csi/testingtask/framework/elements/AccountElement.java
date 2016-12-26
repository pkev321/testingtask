package ru.csi.testingtask.framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.csi.testingtask.framework.pages.MailLetterPage;

/**
 * Created by Dmitrii_Khodakovskii on 12/23/2016.
 */
public class AccountElement {

    public static class AccountElementProperties {
        public static final By AUTHENTICATED_USER = By.className("mail-User-Name");
        public static final By POPUP_MENU = By.className("_nb-popup-content");
        public static final By EXIT_BUTTON = By.xpath("//a[text()=\'Выход\']");
    }

    private WebDriver driver;
    private WebElement parent;
    private WebElement popUpMenu;
    private WebElement exitButton;

    public AccountElement(WebDriver driver) {
        this.driver = driver;
        this.parent = this.driver.findElement(AccountElementProperties.AUTHENTICATED_USER);
    }

    public void clickByMailUser() {
        parent.click();

        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(AccountElementProperties.POPUP_MENU));
        this.popUpMenu = this.driver.findElement(AccountElementProperties.POPUP_MENU);
        this.exitButton = this.popUpMenu.findElement(AccountElementProperties.EXIT_BUTTON);
    }

    public void clickExitButton() {
        this.exitButton.click();
    }


    public String getMailUserName() {
        return this.parent.getText();
    }

}
