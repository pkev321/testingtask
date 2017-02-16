package ru.csi.testingtask.test;


import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import ru.csi.testingtask.framework.elements.AccountElement;
import ru.csi.testingtask.framework.pages.LoginPage;
import ru.csi.testingtask.framework.pages.MailBoxPage;
import ru.csi.testingtask.framework.pages.MailLetterPage;

import java.io.IOException;

/**
 * Created by Dmitrii_Khodakovskii on 12/23/2016.
 */

@CucumberOptions(features = "src/test/resources/features/")
public class InputMailCheck extends AbstractTestNGCucumberTests {

    private WebDriver driver;
    private LoginPage loginPage;
    private MailBoxPage mailBoxPage;
    private MailLetterPage letterPage;

    @BeforeSuite
    public void systemSettings () throws IOException {
        if(System.getProperty("os.name").startsWith("Mac"))
            System.setProperty("webdriver.gecko.driver", "/Users/khodakovskiidmitrii/Documents/LevelUp/QA_Automation_02032017/testingtask/src/test/resources/drivers/geckodriver");
        else if (System.getProperty("os.name").startsWith("Window"))
            System.setProperty("webdriver.gecko.driver", getClass().getClassLoader().getResource("drivers/geckodriver.exe").getPath());

    }

    @Before
    public void setUp () {
        driver = new FirefoxDriver();
    }

    @Given("^^Пользователь открывает страницу почтового сервера: \"([^\"]*)\"$")
    public void openMail (String url) {
        driver.get(url);
        loginPage = new LoginPage(driver);
    }

    @When("^Пользователь вводит имя пользователя \"([^\"]*)\" и пароль \"([^\"]*)\"$")
    public void login (String username, String password) throws InterruptedException {
        loginPage.setUserName(username);
        loginPage.setPassword(password);
        loginPage.clickSignInButton();

        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(AccountElement.AccountElementProperties.AUTHENTICATED_USER));

        mailBoxPage = new MailBoxPage(driver);
        Assert.assertEquals(username + "@yandex.ru", mailBoxPage.getAccountElement().getMailUserName());
    }

    @When("^Пользователь открывает входящее письмо с темой \"([^\"]*)\"$")
    public void userOpenInputMail (String subject) {
        if (subject == null || subject.length() == 0)
            throw new IllegalArgumentException("Предан не верный параметр");

        for (WebElement mailSubject : mailBoxPage.getMailSubjects())
            if (mailSubject.getText().trim().toLowerCase().equals(subject.trim().toLowerCase()))
                mailSubject.click();

        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(MailLetterPage.MailLetterPageProperties.MAIL_TEXT));

        letterPage = new MailLetterPage(driver);
        Assert.assertNotNull(letterPage.getLetterText());
    }

    @Then("^Пользователь проверяет, что отпавитель \"([^\"]*)\"$")
    public void checkSender (String sender) {
        if (sender == null || sender.length() == 0)
            throw new IllegalArgumentException("Предан не верный параметр");
        Assert.assertEquals(sender.trim().toLowerCase(), letterPage.getSenderName());
    }

    @Then("^Тема письма \"([^\"]*)\"$")
    public void checkLetterSubject (String subject) {
        if (subject == null || subject.length() == 0)
            throw new IllegalArgumentException("Предан не верный параметр");

        Assert.assertEquals(subject.trim().toLowerCase(), letterPage.getSubjectText().trim().toLowerCase());
    }

    @Then("^Текст письма не пустой$")
    public void checkLetterText () {
        Assert.assertNotNull(letterPage.getLetterText());
        Assert.assertTrue(letterPage.getLetterText().length() != 0);
    }

    @After
    public void stopService () {
        mailBoxPage.getAccountElement().clickByMailUser();
        mailBoxPage.getAccountElement().clickExitButton();
        driver.quit();
    }

}
