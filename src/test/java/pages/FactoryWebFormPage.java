package pages;

import components.FooterComponent;
import components.HeaderComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class FactoryWebFormPage extends BasePage {

    @FindBy(css = "input#my-text-id")
    @CacheLookup
    WebElement inputText;

    @FindBy(css = "input[type='password']")
    @CacheLookup
    WebElement inputPassword;

    @FindBy(css = "button[type='submit']")
    @CacheLookup
    WebElement submitButton;

    @FindBy(className = "lead")
    @CacheLookup
    WebElement receivedBox;


    @FindBy(linkText = "Return to index")
    @CacheLookup
    WebElement mainPageButton;

    HeaderComponent header;
    FooterComponent footer;

    public FactoryWebFormPage(String browser) {
        super(browser);
        PageFactory.initElements(driver, this);
        visit("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        header = new HeaderComponent(driver);
        footer = new FooterComponent(driver);
    }

    public FactoryWebFormPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        header = new HeaderComponent(driver);
        footer = new FooterComponent(driver);
    }

    public HeaderComponent header() {
        return header;
    }

    public FooterComponent footer() {
        return footer;
    }

    public void inputText(String text) {
        type(inputText, text);

    }

    public void inputPassword(String text) {
        type(inputPassword, text);
    }

    public void submit() {
        click(submitButton);
    }

    public MainPage returnToMainPage() {
        click(mainPageButton);
        assertThat(driver.getCurrentUrl()).isEqualTo("https://bonigarcia.dev/selenium-webdriver-java/index.html");
        return new MainPage(driver);
    }

    public boolean submitIsSuccess(String text) {
        return driver.getCurrentUrl().contains(text);
    }

    public boolean receivedBoxPresent() {
        return isDisplayed(receivedBox);
    }




}
