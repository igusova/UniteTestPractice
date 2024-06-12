import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("smoke")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SelenideLoginPageTest {

    @BeforeAll
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        Configuration.browserCapabilities = options;

    }

    @Test
    void successfulLoginTest(){
        open("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");
        SelenideElement subTitle = $(By.className("display-6"));
        SelenideElement loginInput = $(By.id("username"));
        SelenideElement passwordInput = $(By.id("password"));
        SelenideElement submitButton = $(By.xpath("//button[@type='submit']"));

        loginInput.sendKeys("user");
        passwordInput.sendKeys("user");
        String textBeforeClick = subTitle.getText();

        submitButton.click();

        subTitle.shouldHave(text("Login form"));
        WebElement successMessage = $(By.id("success"));
        assertThat(successMessage.isDisplayed()).isTrue();


    }

    @Test
    void successfulLoginWithPOMTest() {
        LoginPage loginPage = new LoginPage();

        loginPage.successfullSignIn();
        assertThat(loginPage.SuccessMessageIsPresent()).isTrue();

    }
}
