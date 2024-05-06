package pages;

import components.FooterComponent;
import components.HeaderComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class MainPage extends BasePage {
    @FindBy(linkText = "Login form")
    @CacheLookup
    WebElement loginFormButton;

    @FindBy(linkText = "Web form")
    @CacheLookup
    WebElement webFormButton;

    HeaderComponent header;
    FooterComponent footer;

    public MainPage(String browser) {
        super(browser);
        PageFactory.initElements(driver, this);
        this.header = new HeaderComponent(driver);
        this.footer = new FooterComponent(driver);
        visit("https://bonigarcia.dev/selenium-webdriver-java/");
    }

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        header = new HeaderComponent(driver);
        footer = new FooterComponent(driver);
    }
    public FactoryLoginPage openLoginPage() {
        click(loginFormButton);
        assertThat(driver.getCurrentUrl()).isEqualTo("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");
        return new FactoryLoginPage(driver);
    }

    public FactoryWebFormPage openWebFormPage() {
        click(webFormButton);
        assertThat(driver.getCurrentUrl()).isEqualTo("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        return new FactoryWebFormPage(driver);
    }

    public HeaderComponent header() {
        return header;
    }
    public FooterComponent footer() {
        return footer;
    }
}
