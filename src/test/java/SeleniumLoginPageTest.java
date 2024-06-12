import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

class SeleniumLoginPageTest {
    WebDriver driver;

    @BeforeEach
    void start() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void close() {
        driver.close();
    }

    @Test
    void successfulLoginTest(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");
        WebElement subTitle = driver.findElement(By.className("display-6"));
        WebElement loginInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));

        loginInput.sendKeys("user");
        passwordInput.sendKeys("user");
        String textBeforeClick = subTitle.getText();

        submitButton.click();

        assertThat(textBeforeClick).isEqualTo("Login form");
        WebElement successMessage = driver.findElement(By.id("success"));
        assertThat(successMessage.isDisplayed()).isTrue();


    }

    @Test
    void successfulLoginWithPOMTest() {
        LoginPage loginPage = new LoginPage();

        loginPage.successfullSignIn();
        assertThat(loginPage.SuccessMessageIsPresent()).isTrue();

    }
}
