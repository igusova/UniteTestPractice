import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fpmi.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class SeleniumWebFormTests {
    WebDriver driver;

    @BeforeEach
    void start() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);

    }

    @AfterEach
    void close() {
        driver.close();
    }

    @Test
    @DisplayName("Ввод текста в текстовое поле")
    void checkInputText() {
        WebElement webFormTextInputCSS= driver.findElement(By.cssSelector(TEXT_INPUT_CSS));
        webFormTextInputCSS.sendKeys(TEXT);
        assertEquals(TEXT, webFormTextInputCSS.getAttribute("value"));
    }

    @Test
    @DisplayName("Ввод пароля в текстовое поле")
    void checkInputPassword() {
        WebElement webFormPasswordInputCSS = driver.findElement(By.cssSelector(PASSWORD_INPUT_CSS));
        webFormPasswordInputCSS.sendKeys(PASSWORD);
        assertEquals(PASSWORD, webFormPasswordInputCSS.getAttribute("value"));
    }

    @Test
    @DisplayName("Ввод текста в текстовую область")
    void checkTextArea() {
        WebElement webFormTextarea = driver.findElement(By.cssSelector("textarea.form-control"));
        webFormTextarea.sendKeys("Hello, world!");
        assertEquals("Hello, world!", webFormTextarea.getAttribute("value"));
    }

    @Test
    @DisplayName("Работа с чек-боксами")
    void checkCheckBox() {
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(CHECK_BOX));
        for (WebElement checkBox:checkBoxes) {
            checkBox.click();
        }
        assertFalse(checkBoxes.get(0).isSelected());
        assertTrue(checkBoxes.get(1).isSelected());
    }

    @Test
    @DisplayName("Работа с radio button")
    void checkRadioButtom() {
        WebElement radio = driver.findElement(By.cssSelector(RADIO));
        radio.click();
        assertTrue(radio.isSelected());
    }

    @Test
    @DisplayName("Текстовое поле недоступно для ввода данных")
    void checkDisableInput() {
        WebElement webFormDisabledInput = driver.findElement(By.cssSelector(DISABLED_INPUT));
        assertThrows(ElementNotInteractableException.class, () -> webFormDisabledInput.sendKeys(TEXT));
        assertFalse(webFormDisabledInput.isEnabled());
    }

    @Test
    @DisplayName("Текстовое поле доступно только для чтения")
    void checkReadOnlyInput() {
        WebElement webFormReadOnly = driver.findElement(By.cssSelector(READ_ONLY_INPUT));
        webFormReadOnly.sendKeys(TEXT);
        assertEquals(READ_ONLY_TEXT, webFormReadOnly.getAttribute("value"));
    }

    @Test
    @DisplayName("Переход по ссылке")
    void checkLink() {
        WebElement webFormLink = driver.findElement(By.cssSelector(LINK));
        webFormLink.click();
        assertEquals("Hands-On Selenium WebDriver with Java", driver.getTitle());
    }

    @Test
    @DisplayName("Нажатие на кнопку")
    void checkButton() {
        WebElement webFormButton = driver.findElement(By.cssSelector(SUBMIT_BUTTON));
        webFormButton.click();
        assertEquals(SUBMIT_TEXT, driver.findElement(By.cssSelector(SUBMIT_PAGE_TEXT)).getText());
    }

    @Test
    @DisplayName("Работа с полем со списком")
    void checkSelect() {
        WebElement webFormSelect = driver.findElement(By.cssSelector(SELECT));
        Select mySelect = new Select(webFormSelect);
        mySelect.selectByIndex(2);
        assertEquals("Two",mySelect.getFirstSelectedOption().getText());
        Assertions.assertTrue(mySelect.getFirstSelectedOption().isSelected());
    }

    @Test
    @DisplayName("Загрузка файла")
    void fileUploadTest() {

        URL url = SeleniumWebFormTests.class.getClassLoader().getResource("example.txt");

        String absolutePath = null;
        if (url != null) {
            absolutePath = new File(url.getPath()).getAbsolutePath();
            System.out.println("Абсолютный путь к файлу: " + absolutePath);
        } else {
            System.out.println("Ресурс не найден.");
        }
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement fileUpload = driver.findElement(By.name("my-file"));
        fileUpload.sendKeys(absolutePath);
        WebElement submit = driver.findElement(By.xpath("//button[text()='Submit']"));
        submit.click();
        assertThat(driver.getCurrentUrl()).contains("example.txt");
    }

    @Test
    @DisplayName("Работа с полем дата")
    void checkDatePicker() {
        WebElement webFormInputDate = driver.findElement(By.cssSelector("input[name='my-date']"));
        webFormInputDate.click();
        WebElement curDate = driver.findElement(By.cssSelector(".day[data-date='1713830400000']"));
        curDate.click();
        assertEquals("04/23/2024", webFormInputDate.getAttribute("value"));
    }

    @Test
    void checkNavigate(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/navigation1.html");
        WebElement webFormNavigate = driver.findElement(By.cssSelector("[href*='navigation2']"));
        webFormNavigate.click();
        assertThat(driver.findElement(By.cssSelector(".lead")).getText()).contains("Ut enim ad minim veniam");
    }

    @Test
    void checkDropDown() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");
        WebElement dropdown1 = driver.findElement(By.id("my-dropdown-1"));
        new Actions(driver)
                .click(dropdown1)
                .perform();
        assertTrue(driver.findElement(By.xpath("//ul[1]")).isDisplayed());

        WebElement dropdown2 = driver.findElement(By.id("my-dropdown-2"));
        new Actions(driver)
                .contextClick(dropdown2)
                .perform();
        assertTrue(driver.findElement(By.cssSelector("#context-menu-2")).isDisplayed());

        WebElement dropdown3 = driver.findElement(By.id("my-dropdown-3"));
        new Actions(driver)
                .doubleClick(dropdown3)
                .perform();
        assertTrue(driver.findElement(By.cssSelector("#context-menu-3")).isDisplayed());
    }

    @Test
    void actionAPIDragAndDropTests() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html");
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("target"));
        new Actions(driver)
                .dragAndDrop(draggable, droppable)
                .perform();
        assertEquals(draggable.getLocation(),droppable.getLocation());

    }

}
