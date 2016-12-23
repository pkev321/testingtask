package ru.csi.testingtask.framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Dmitrii_Khodakovskii on 12/23/2016.
 */
public class AccountElement {

    private WebDriver driver;
    private WebElement parent;
    private WebElement popUpMenu;
    private WebElement exitButton;

    public AccountElement(WebDriver driver) {
        this.driver = driver;
        this.parent = this.driver.findElement(By.className("mail-User-Name"));
//        this.popUpMenu = this.parent.findElement(By.className("_nb-popup-content"));
    }

    public String getMailUserName() {
        return this.parent.getText();
    }

}
