package ru.csi.testingtask.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.csi.testingtask.framework.elements.AccountElement;

/**
 * Created by Dmitrii_Khodakovskii on 12/23/2016.
 */
public class MailBoxPage {

    private WebDriver driver;
    private AccountElement accountElement;
    private WebElement inboxMail;

    public MailBoxPage(WebDriver driver) {
        this.driver = driver;
        this.accountElement = new AccountElement(this.driver);
        this.inboxMail = this.driver.findElement(By.className("mail-FolderList-Item_inbox"));
    }

    public AccountElement getAccountElement () {
        return accountElement;
    }

    public String getInboxTitle() {
        return this.inboxMail.getText();
    }

}
