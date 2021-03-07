package stepdefs;

import Pages.MainPage;
import cucumber.api.Scenario;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class TestStepdefs extends MainPage implements En {
    public TestStepdefs() {
        Given("^I open the browser to navigate to the website$", () -> {
            MainPage mainpage = PageFactory.initElements(driver, MainPage.class);
            mainpage.navigateToWebsite();
        });

        When("^I enter the first set of gold bars from below, three in each bowl$", (DataTable dataTable) -> {
            MainPage mainpage = PageFactory.initElements(driver, MainPage.class);
            mainpage.weighFirstSet(dataTable);
        });

        And("^I enter one gold bar on each side using the values below if the previous weighing was equal otherwise using the values from the unequal set$", (DataTable dataTable) -> {
            MainPage mainpage = PageFactory.initElements(driver, MainPage.class);
            mainpage.weighFinalSet(dataTable);
        });

        Then("^I will click the number of the gold bar that is fake and validate the message on the alert '(.*?)'$", (String expectedText) -> {
            MainPage mainpage = PageFactory.initElements(driver, MainPage.class);
            mainpage.validateAlert(expectedText);
        });
    }

    @cucumber.api.java.Before
    public void openBrowser () {
        System.setProperty("webdriver.chrome.driver", "src/test/resource/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    @cucumber.api.java.After
    public void closeBrowser (Scenario scenario){
        byte[] screenshot = (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
        driver.quit();
    }
}
