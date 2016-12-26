package ru.csi.testingtask.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.csi.testingtask.framework.elements.AccountElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitrii_Khodakovskii on 12/23/2016.
 */
public class MailBoxPage {

    public static class MailBoxPageProperties {
        public static final By INBOX_MAIL_LABEL = By.className("mail-FolderList-Item_inbox");
        public static final By MAIL_SUBJECTS = By.className("mail-MessageSnippet-Item_subject");
    }

    private WebDriver driver;
    private AccountElement accountElement;
    private WebElement inboxMail;
    private List<WebElement> mailSubjects;

    public MailBoxPage(WebDriver driver) {
        this.driver = driver;
        this.accountElement = new AccountElement(this.driver);
        this.inboxMail = this.driver.findElement(MailBoxPageProperties.INBOX_MAIL_LABEL);
        this.mailSubjects = this.driver.findElements(MailBoxPageProperties.MAIL_SUBJECTS);
    }

    public AccountElement getAccountElement () {
        return accountElement;
    }

    public String getInboxTitle() {
        return this.inboxMail.getText();
    }

    public List<WebElement> getMailSubjects () {
        if (mailSubjects == null)
            return new ArrayList<WebElement>();
        return mailSubjects;
    }
}
