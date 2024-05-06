import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.FactoryWebFormPage;
import pages.MainPage;

import static org.assertj.core.api.Assertions.assertThat;

class FactoryWebFormPageTests {

    FactoryWebFormPage webForm;

    @BeforeEach
    void setup() {
        webForm = new FactoryWebFormPage("chrome");
    }

    @AfterEach
    void teardown() {
        webForm.quit();
    }

    @Test
    void testReturnToMainPage() {
        MainPage mainPage = webForm.returnToMainPage();
        assertThat(mainPage.header().getTitleText()).isEqualTo("Hands-On Selenium WebDriver with Java");
        assertThat(mainPage.header().getSubTitleText()).isEqualTo("Practice site");
    }

    @Test
    void testWebFormSubmit() {
        webForm.inputText("user");
        webForm.inputPassword("password");
        webForm.submit();
        assertThat(webForm.receivedBoxPresent()).isTrue();
        assertThat(webForm.submitIsSuccess("user")).isTrue();
        assertThat(webForm.submitIsSuccess("password")).isTrue();
    }


}
