package ru.csi.testingtask.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Dmitrii_Khodakovskii on 12/26/2016.
 */
public class MailLetterPage {

    public static class MailLetterPageProperties {
        public static final By MAIL_SUBJECT_LABEL = By.className("mail-Message-Toolbar-Subject-Wrapper");
        public static final By MAIL_SENDER_LABEL = By.className("mail-Message-Sender-Email");
        public static final By MAIL_TEXT = By.className("b-message-body__content");
    }

    private WebDriver driver;
    private WebElement subject;
    private WebElement sender;
    private WebElement mailText;

    public MailLetterPage(WebDriver driver) {
        this.driver = driver;
        this.subject = this.driver.findElement(MailLetterPageProperties.MAIL_SUBJECT_LABEL);
        this.sender = this.driver.findElement(MailLetterPageProperties.MAIL_SENDER_LABEL);
        this.mailText = this.driver.findElement(MailLetterPageProperties.MAIL_TEXT);
    }

    public String getSubjectText() {
        return this.subject.getText();
    }

    public String getSenderName() {
        return this.sender.getText();
    }

    public String getLetterText() {
        return this.mailText.getText();
    }

}
