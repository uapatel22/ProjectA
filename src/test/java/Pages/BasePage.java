package Pages;

import org.openqa.selenium.WebElement;


public class BasePage {

    public static void setValue(WebElement ele, String value) {
        if (ele.isEnabled()) {
            ele.click();
            ele.sendKeys(new CharSequence[]{value});
        }
    }
}
