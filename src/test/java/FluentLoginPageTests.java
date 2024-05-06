import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.FluentLoginPage;

import static org.assertj.core.api.Assertions.assertThat;

class FluentLoginPageTests {
    FluentLoginPage loginPage;

    @BeforeEach
    void setup() {
        loginPage = new FluentLoginPage("chrome");
    }

    @AfterEach
    void teardown() {
        loginPage.quit();
    }

    @Test
    void testLoginSuccess() {
        loginPage
                .with("user", "user")
                .successBoxPresent()
                .invalidCredentialsBoxIsNotPresent();
    }

    @Test
    void testLoginFailure() {
        loginPage
                .with("test", "test")
                .invalidCredentialsBoxPresent()
                .successBoxIsNotPresent();
    }
}