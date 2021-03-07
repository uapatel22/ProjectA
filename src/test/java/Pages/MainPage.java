package Pages;

import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.cucumber.datatable.DataTable;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;


public class MainPage extends BasePage implements En {
    private static final Logger logger = LoggerFactory.getLogger(MainPage.class);

    public static WebDriver driver;

    public String firstResult,secondResult;

    @FindBy(xpath = "//button[@id='reset' and normalize-space()='Reset']")
    WebElement resetButton;

    @FindBy(xpath = "//button[@id='weigh']")
    WebElement weighButton;

    @FindBy(xpath = "//input[@id='left_0']")
    WebElement leftBowlFirst;

    @FindBy(xpath = "//input[@id='left_1']")
    WebElement leftBowlMiddle;

    @FindBy(xpath = "//input[@id='left_2']")
    WebElement leftBowlLast;

    @FindBy(xpath = "//input[@id='right_0']")
    WebElement rightBowlFirst;

    @FindBy(xpath = "//input[@id='right_1']")
    WebElement rightBowlMiddle;

    @FindBy(xpath = "//input[@id='right_2']")
    WebElement rightBowlLast;

    @FindBy(xpath = "//button[@id='coin_0']")
    WebElement goldBarZero;

    @FindBy(xpath = "//button[@id='coin_1']")
    WebElement goldBarOne;

    @FindBy(xpath = "//button[@id='coin_2']")
    WebElement goldBarTwo;

    @FindBy(xpath = "//button[@id='coin_3']")
    WebElement goldBarThree;

    @FindBy(xpath = "//button[@id='coin_4']")
    WebElement goldBarFour;

    @FindBy(xpath = "//button[@id='coin_5']")
    WebElement goldBarFive;

    @FindBy(xpath = "//button[@id='coin_6']")
    WebElement goldBarSix;

    @FindBy(xpath = "//button[@id='coin_7']")
    WebElement goldBarSeven;

    @FindBy(xpath = "//button[@id='coin_8']")
    WebElement goldBarEight;

    @FindBy(xpath = "//div[@class='game-info']//li")
    WebElement firstWeighingResults;

    @FindBy(xpath = "//div[@class='game-info']//following-sibling::li")
    WebElement secondWeighingResults;

    public void navigateToWebsite(){
        String url = new ReadPropertyFile().getProperty(new File("testconfig.properties"), "url");
        driver.get(url);
    }

    public void weighFirstSet(DataTable dataTable) {
        List<List<String>> goldBarsTable = dataTable.asLists();
        for (int i = 0; i < goldBarsTable.size(); i++) {
            String key = goldBarsTable.get(i).get(0);
            String value = goldBarsTable.get(i).get(1);
            switch (key) {
                case "goldBar0": {
                    setValue(leftBowlFirst, value);
                    break;
                }
                case "goldBar1": {
                    setValue(leftBowlMiddle, value);
                    break;
                }
                case "goldBar2": {
                    setValue(leftBowlLast, value);
                    break;
                }
                case "goldBar3": {
                    setValue(rightBowlFirst, value);
                    break;
                }
                case "goldBar4": {
                    setValue(rightBowlMiddle, value);
                    break;
                }
                case "goldBar5": {
                    setValue(rightBowlLast, value);
                    break;
                }
                default:
                    logger.info("" + key + "is not found in the statement, please use a valid key");
                    break;
            }
        }
        weighButton.click();
    }

    public void weighFinalSet(DataTable dataTable) {
        firstResult = firstWeighingResults.getText();
        logger.info("The result of the first weighing:" + " " + firstResult);
        if (firstResult.contains("=")) {
            firstSetEqual(dataTable);
        }
        if (firstResult.contains(">")) {
            firstSetGreaterThan();
        }
        if (firstResult.contains("<")) {
            firstSetLessThan();
        }
    }

    public void firstSetEqual(DataTable dataTable) {
        resetButton.click();

        List<List<String>> goldBarsTable = dataTable.asLists();
        for (int i = 0; i < goldBarsTable.size(); i++) {
            String key = goldBarsTable.get(i).get(0);
            String value = goldBarsTable.get(i).get(1);
            switch (key) {
                case "goldBar6": {
                    setValue(leftBowlFirst, value);
                    break;
                }
                case "goldBar7": {
                    setValue(rightBowlFirst, value);
                    break;
                }
                default:
                    logger.info("" + key + "is not found in the statement, please use a valid key");
                    break;
            }
        }

        weighButton.click();
        secondResult = secondWeighingResults.getText();
        logger.info("The result of the second weighing:" + " " + secondResult);
        resetButton.click();

        if (secondResult.contains("=")) {
            goldBarEight.click();
            logger.info("The fake gold bar is number 8 which has been clicked after two weighings");
        }
        if (secondResult.contains(">")) {
            goldBarSeven.click();
            logger.info("The fake gold bar is number 7 which has been clicked after two weighings");
        }
        if (secondResult.contains("<")) {
            goldBarSix.click();
            logger.info("The fake gold bar is number 6 which has been clicked after two weighings");
        }
    }

    public void firstSetGreaterThan() {
        resetButton.click();
        setValue(leftBowlFirst, "3");
        setValue(rightBowlFirst, "4");
        weighButton.click();
        secondResult = secondWeighingResults.getText();
        logger.info("The result of the second weighing:" + " " + secondResult);

        if (secondResult.contains("=")) {
            goldBarFive.click();
            logger.info("The fake gold bar is number 5 which has been clicked after two weighings");
        }
        if (secondResult.contains(">")) {
            goldBarFour.click();
            logger.info("The fake gold bar is number 4 which has been clicked after two weighings");
        }
        if (secondResult.contains("<")) {
            goldBarThree.click();
            logger.info("The fake gold bar is number 3 which has been clicked after two weighings");
        }
    }

    public void firstSetLessThan() {
        resetButton.click();
        setValue(leftBowlFirst, "0");
        setValue(rightBowlFirst, "1");
        weighButton.click();
        secondResult = secondWeighingResults.getText();
        logger.info("The result of the second weighing:" + " " + secondResult);

        if (secondResult.contains("=")) {
            goldBarTwo.click();
            logger.info("The fake gold bar is number 2 which has been clicked after two weighings");
        }
        if (secondResult.contains(">")) {
            goldBarOne.click();
            logger.info("The fake gold bar is number 1 which has been clicked after two weighings");
        }
        if (secondResult.contains("<")) {
            goldBarZero.click();
            logger.info("The fake gold bar is number 0 which has been clicked after two weighings");
        }
    }
    public void validateAlert(String expectedMessage){
        String actualMessage= driver.switchTo().alert().getText();
        logger.info("The expected message on the alert is:" + " " + expectedMessage);
        logger.info("The actual message on the alert is:" + " " + actualMessage);
        Assert.assertEquals(actualMessage, expectedMessage);
        driver.switchTo().alert().accept();
    }
}

