package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class FluentLoginPage extends BasePage {

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

    public FluentLoginPage(String browser) {
        super(browser);
        PageFactory.initElements(driver, this);
        visit("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");
    }

    public FluentLoginPage with(String username, String password) {
        type(loginInput, username);
        type(passwordInput, password);
        click(submitButton);
        return this;
    }
    public FluentLoginPage successBoxPresent() {
        assertThat(isDisplayed(successBox)).isTrue();
        return this;
    }

    public FluentLoginPage successBoxIsNotPresent() {
        assertThat(isDisplayed(successBox)).isFalse();
        return this;
    }

    public FluentLoginPage invalidCredentialsBoxPresent() {
        assertThat(isDisplayed(invalidCredentialsBox)).isTrue();
        return this;
    }

    public FluentLoginPage invalidCredentialsBoxIsNotPresent() {
        assertThat(isDisplayed(invalidCredentialsBox)).isFalse();
        return this;
    }
}
