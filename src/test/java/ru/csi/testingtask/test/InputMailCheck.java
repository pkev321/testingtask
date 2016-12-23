package ru.csi.testingtask.test;


import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import ru.csi.testingtask.framework.pages.LoginPage;
import ru.csi.testingtask.framework.pages.MailBoxPage;

import java.io.IOException;

/**
 * Created by Dmitrii_Khodakovskii on 12/23/2016.
 */

@CucumberOptions(features = "drivers\\features\\CheckInputMail.feature")
public class InputMailCheck extends AbstractTestNGCucumberTests {

    private WebDriver driver;
    private LoginPage loginPage;
    private MailBoxPage mailBoxPage;

    @BeforeSuite
    public void systemSettings() throws IOException {
        System.setProperty("webdriver.gecko.driver", getClass().getClassLoader().getResource("drivers/geckodriver.exe").getPath());
    }

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
    }

    @Given("^^Пользователь открывает страницу почтового сервера: \"([^\"]*)\"$")
    public void openMail(String url) {
        driver.get(url);
        loginPage = new LoginPage(driver);
    }

    @When("^Пользователь вводит имя пользователя \"([^\"]*)\" и пароль \"([^\"]*)\"$")
    public void login(String username, String password) throws InterruptedException {
        loginPage.setUserName(username);
        loginPage.setPassword(password);
        loginPage.clickSignInButton();

        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.className("mail-User-Name")));

        mailBoxPage = new MailBoxPage(driver);
        System.out.println(mailBoxPage.getAccountElement().getMailUserName());
        System.out.println(mailBoxPage.getInboxTitle());

    }

    @After
    public void stopService() {
//        driver.quit();
    }

}
