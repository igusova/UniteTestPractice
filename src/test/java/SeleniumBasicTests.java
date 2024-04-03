import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class SeleniumBasicTests {

    @Test
    void openUrlTest(){
        String url = "https://bonigarcia.dev/selenium-webdriver-java/index.html";
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        Assertions.assertEquals("Hands-On Selenium WebDriver with Java", driver.getTitle());
        driver.close();
    }
}
