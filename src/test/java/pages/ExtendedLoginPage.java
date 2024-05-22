package pages;

import org.openqa.selenium.By;

public class ExtendedLoginPage extends BasePage {

    By loginInput = By.id("username");
    By passwordInput = By.id("password");
    By submitButton = By.xpath("//button[@type='submit']");

    By successBox = By.id("success");
    By invalidCredentialsBox = By.id("invalid");

    public ExtendedLoginPage(String browser) {
        super(browser);
        visit("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");
    }
    public void with(String username, String password) {
        type(loginInput, username);
        type(passwordInput, password);
        click(submitButton);
    }
    public boolean successBoxPresent() {
        return isDisplayed(successBox);
    }

    public boolean invalidCredentialsBoxPresent() {
        return isDisplayed(invalidCredentialsBox);
    }
}
