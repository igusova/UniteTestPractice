import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import steps.AllureSteps;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Story("Download")
class DownloadTests {
    WebDriver driver;
    AllureSteps allureSteps = new AllureSteps();

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testDownloadHttpClient() throws IOException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/download.html");

        WebElement pngLink = driver.findElement(By.xpath("(//a)[2]"));
        File pngFile = new File(".", "webdrivermanager.png");
        allureSteps.download(pngLink.getAttribute("href"), pngFile);
        assertThat(pngFile).exists();

        WebElement pdfLink = driver.findElement(By.xpath("(//a)[3]"));
        File pdfFile = new File(".", "webdrivermanager.pdf");
        allureSteps.download(pdfLink.getAttribute("href"), pdfFile);
        assertThat(pdfFile).exists();

        WebElement pngLinkJupiter = driver.findElement(By.xpath("(//a)[4]"));
        File pngJupiter = new File(".", "selenium-jupiter.png");
        allureSteps.download(pngLinkJupiter.getAttribute("href"), pngJupiter);
        assertThat(pngJupiter).exists();

        WebElement pdfLinkJupiter = driver.findElement(By.xpath("(//a)[5]"));
        File pdfJupiter = new File(".", "selenium-jupiter.pdf");
        allureSteps.download(pdfLinkJupiter.getAttribute("href"), pdfJupiter);
        assertThat(pdfJupiter).exists();
    }
}