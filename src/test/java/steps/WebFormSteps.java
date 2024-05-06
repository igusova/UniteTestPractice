package steps;

import org.openqa.selenium.WebElement;

public class WebFormSteps {

public void sendInputText(WebElement element, String text) {
    element.sendKeys(text);
    }
}
