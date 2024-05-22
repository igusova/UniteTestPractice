import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.FactoryLoginPage;
import pages.FactoryWebFormPage;
import pages.MainPage;

import static org.assertj.core.api.Assertions.assertThat;

class MainLoginPageTests {
    MainPage mainPage;

    @BeforeEach
    void setup() {
        mainPage = new MainPage("chrome");
    }

    @AfterEach
    void teardown() {
        mainPage.quit();
    }

    @Test
    void testLoginSuccess() {
        FactoryLoginPage login = mainPage.openLoginPage();
        login.with("user", "user");
        assertThat(login.successBoxPresent()).isTrue();
        assertThat(login.invalidCredentialsBoxPresent()).isFalse();
    }

    @Test
    void testLoginFailure() {
        FactoryLoginPage login = mainPage.openLoginPage();
        login.with("test", "test");
        assertThat(login.successBoxPresent()).isFalse();
        assertThat(login.invalidCredentialsBoxPresent()).isTrue();
    }

    @Test
    void testMainPageTitlesAndFooter() {
        assertThat(mainPage.header().getTitleText()).isEqualTo("Hands-On Selenium WebDriver with Java");
        assertThat(mainPage.header().getSubTitleText()).isEqualTo("Practice site");
        assertThat(mainPage.footer().getCopyrightText()).isEqualTo("Copyright © 2021-2023 Boni García");
    }

    @Test
    void testLoginPageTitlesAndFooter() {
        FactoryLoginPage login = mainPage.openLoginPage();
        assertThat(login.header().getTitleText()).isEqualTo("Hands-On Selenium WebDriver with Java");
        assertThat(login.header().getSubTitleText()).isEqualTo("Practice site");
        assertThat(login.footer().getCopyrightText()).isEqualTo("Copyright © 2021-2023 Boni García");
    }

    @Test
    void testWebFormPageTitlesAndFooter() {
        FactoryWebFormPage webForm = mainPage.openWebFormPage();
        assertThat(webForm.header().getTitleText()).isEqualTo("Hands-On Selenium WebDriver with Java");
        assertThat(webForm.header().getSubTitleText()).isEqualTo("Practice site");
        assertThat(webForm.footer().getCopyrightText()).isEqualTo("Copyright © 2021-2023 Boni García");
    }
}