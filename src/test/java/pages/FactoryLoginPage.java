package pages;

import components.FooterComponent;
import components.HeaderComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FactoryLoginPage extends BasePage {

    @FindBy(id = "username")
    @CacheLookup
    WebElement loginInput;

    @FindBy(id = "password")
    @CacheLookup
    WebElement passwordInput;

    @FindBy(css = "button")
    @CacheLookup
    WebElement submitButton;

    @FindBy(id = "success")
    @CacheLookup
    WebElement successBox;

    @FindBy(id = "invalid")
    @CacheLookup
    WebElement invalidCredentialsBox;

    HeaderComponent header;
    FooterComponent footer;

    public FactoryLoginPage(String browser) {
        super(browser);
        PageFactory.initElements(driver, this);
        header = new HeaderComponent(driver);
        footer = new FooterComponent(driver);
        visit("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");
    }

    public FactoryLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        header = new HeaderComponent(driver);
        footer = new FooterComponent(driver);
    }

    @Step("Factory login with username and password")
    public void with(String username, String password) {
        type(loginInput, username);
        type(passwordInput, password);
        click(submitButton);
    }

    @Step("Factory get success box is present")
    public boolean successBoxPresent() {
        return isDisplayed(successBox);
    }

    @Step("Factory get invalid credential box is present")
    public boolean invalidCredentialsBoxPresent() {
        return isDisplayed(invalidCredentialsBox);
    }

    public HeaderComponent header() {
        return header;
    }

    public FooterComponent footer() {
        return footer;
    }
}
