package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FooterComponent {
    private WebDriver driver;

    @FindBy(className = "text-muted")
    @CacheLookup
    private WebElement copyright;

    @FindBy(linkText = "Boni García")
    @CacheLookup
    WebElement copyrightLink;



    public FooterComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCopyrightText() {
        return copyright.getText();
    }

    public void clickCopyrightLink() {
        copyrightLink.click();
    }
}