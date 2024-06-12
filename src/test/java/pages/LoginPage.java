package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    WebDriver driver;

    private static final String VALID_USER = "user";
    private static final String VALID_PASSWORD = "user";

    By loginInput = By.id("username");
    By passwordInput = By.id("password");
    By submitButton = By.xpath("//button[@type='submit']");


    public LoginPage() {
        open("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");
    }

    public void successfullSignIn() {
        $(loginInput).sendKeys(VALID_USER);
        $(passwordInput).sendKeys(VALID_PASSWORD);
        $(submitButton).click();
    }

    public void successfullSignIn(String user, String password) {
        driver.findElement(loginInput).sendKeys(user);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(submitButton).click();
    }

    public boolean SuccessMessageIsPresent() {
        return $(By.id("success")).isDisplayed();
    }
}
