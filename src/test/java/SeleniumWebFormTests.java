import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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
        WebElement webFormTextInputXpath = driver.findElement(By.xpath(TEXT_INPUT_XPATH));
        webFormTextInputXpath.clear();
        webFormTextInputXpath.sendKeys(TEXT);
        assertEquals(TEXT, webFormTextInputXpath.getAttribute("value"));
    }

    @Test
    @DisplayName("Ввод пароля в текстовое поле")
    void checkInputPassword() {
        WebElement webFormPasswordInputCSS = driver.findElement(By.cssSelector(PASSWORD_INPUT_CSS));
        webFormPasswordInputCSS.sendKeys(PASSWORD);
        assertEquals(PASSWORD, webFormPasswordInputCSS.getAttribute("value"));
        WebElement webFormPasswordInputXPATH = driver.findElement(By.xpath(PASSWORD_INPUT_XPATH));
        webFormPasswordInputXPATH.clear();
        webFormPasswordInputXPATH.sendKeys(PASSWORD);
        assertEquals(PASSWORD, webFormPasswordInputXPATH.getAttribute("value"));
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
    @DisplayName("Работа с разными локаторами")
    void checkSelect() {
        WebElement webFormSelect = driver.findElement(By.cssSelector(SELECT));
        Select mySelect = new Select(webFormSelect);
        mySelect.selectByIndex(2);
        WebElement webFormInputList= driver.findElement(By.cssSelector("input[name='my-datalist']"));
        webFormInputList.sendKeys("New");
        WebElement webFormInputFile = driver.findElement(By.cssSelector("input[type='file']"));
        WebElement webFormInputColor = driver.findElement(By.cssSelector("input[type='color']"));
        WebElement webFormInputDate = driver.findElement(By.cssSelector("[name='my-disabled']"));
        WebElement webFormRange = driver.findElement(By.cssSelector("input[type='range']"));
        WebElement webFormTextarea = driver.findElement(By.cssSelector("textarea.form-control"));
        webFormTextarea.sendKeys("Hello, world!");
        assertEquals("Hello, world!", webFormTextarea.getAttribute("value"));
    }

}
