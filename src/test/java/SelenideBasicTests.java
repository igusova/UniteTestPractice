import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.fpmi.Constants.BASE_URL;

@Tag("selenium")
class SelenideBasicTests {

    @BeforeEach
    void start() {
        open(BASE_URL);

    }

    @AfterEach
    void close() {

    }

    @Test
    void openUrlTest(){
        Assertions.assertEquals("Hands-On Selenium WebDriver with Java", title());
    }


    @Test
    void openWebFormTest(){
        String xpath = "//a[@href = 'web-form.html']";
        SelenideElement webFormButton = $(By.xpath(xpath));
        webFormButton.click();
        SelenideElement title = $(By.xpath("//h1[@class = 'display-6']"));
        Assertions.assertEquals("Web form", title.getText());
    }
}
