
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ElementsHandlingTests {

    WebDriver driver;
    String baseURL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
    String navigationUrl = "https://bonigarcia.dev/selenium-webdriver-java/navigation1.html";
    String dropdownUrl = "https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html";
    String dragAndDropUrl = "https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html";
    String text = "Hallo";
    String pass = "SecurePass";
    String textArea = "Long story short";


    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    public void testInputField() {
        driver.get(baseURL);

        WebElement inputField = driver.findElement(By.id("my-text-id"));
        inputField.sendKeys(text);

        assertEquals(text, inputField.getAttribute("value"));
    }

    @Test
    public void testPasswordFields() {
        driver.get(baseURL);

        WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        passwordField.sendKeys(pass);

        assertEquals(pass, passwordField.getAttribute("value"));

    }

    @Test
    public void testTextField() {
        driver.get(baseURL);

        WebElement textAreaLocator = driver.findElement(By.xpath("//label[contains(text(), 'Textarea')]/textarea"));
        textAreaLocator.sendKeys(textArea);

        assertEquals(textArea, textAreaLocator.getAttribute("value"));
    }

    @Test
    public void testDisabledField() {
        driver.get(baseURL);

        WebElement disabledInputField = driver.findElement(By.xpath("//label[contains(text(), 'Disabled input')]/input"));


        String attrValue = disabledInputField.getAttribute("disabled");
        assertNotNull(attrValue, "Поле должно иметь атрибут 'disabled'");

        assertThrows(ElementNotInteractableException.class, () -> {
            disabledInputField.sendKeys(text);
        }, "Ожидается ElementNotInteractableException при вводе в disabled-поле");
    }

    @ParameterizedTest
    @ValueSource(strings = {"One", "Two", "Three"})
    public void testDropdownSelect(String option) {
        driver.get(baseURL);

        WebElement dropDownSelect = driver.findElement(By.xpath("//label[contains(text(), 'Dropdown (select)')]/select"));
        Select select = new Select(dropDownSelect);
        select.selectByVisibleText(option);

        WebElement selectedOption = select.getFirstSelectedOption();
        assertEquals(option, selectedOption.getText());
    }

    @ParameterizedTest
    @ValueSource(strings = {"San Francisco", "New York", "Seattle", "Los Angeles", "Chicago"})
    public void testValidCityF(String city) {
        driver.get(baseURL);

        WebElement input = driver.findElement(By.xpath("//label[contains(text(), 'Dropdown (datalist)')]/input"));
        input.sendKeys(city);

        List<WebElement> options = driver.findElements(By.cssSelector("datalist#my-options option"));
        List<String> values = options.stream()
                .map(option -> option.getAttribute("value"))
                .collect(Collectors.toList());

        assertTrue(values.contains(city), "Город '" + city + "' должен присутствовать в datalist");

        assertEquals(city, input.getAttribute("value"));
    }


    @Test
    public void testUploadFile() {
        driver.get(baseURL);

        WebElement fileInput = driver.findElement(By.xpath("//label[contains(text(), 'File input')]/input"));

        String filePath = Paths.get("src/test/resources/img.png").toAbsolutePath().toString();
        fileInput.sendKeys(filePath);

        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement receivedNote = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[text()='Received!']")));

        assertTrue(receivedNote.isDisplayed());
    }


    @Test
    public void testCheckBox() {
        driver.get(baseURL);

        WebElement checkedCheckBox = driver.findElement(By.xpath("//*[@id='my-check-1']"));
        WebElement defaultCheckBox = driver.findElement(By.xpath("//*[@id='my-check-2']"));
        assertFalse(defaultCheckBox.isSelected());
        assertTrue(checkedCheckBox.isSelected());

        assertTrue(checkedCheckBox.isDisplayed());
        assertTrue(defaultCheckBox.isDisplayed());
        assertTrue(checkedCheckBox.isEnabled());
        assertTrue(defaultCheckBox.isEnabled());

        checkedCheckBox.click();
        defaultCheckBox.click();
        assertFalse(checkedCheckBox.isSelected());
        assertTrue(defaultCheckBox.isSelected());

    }


    @Test
    public void testRadioButtons() {
        driver.get(baseURL);

        WebElement firstRadio = driver.findElement(By.xpath("//*[@id='my-radio-1']"));
        WebElement secondRadio = driver.findElement(By.xpath("//*[@id='my-radio-2']"));
        assertFalse(secondRadio.isSelected());
        assertTrue(firstRadio.isSelected());

        assertTrue(firstRadio.isDisplayed());
        assertTrue(secondRadio.isDisplayed());
        assertTrue(firstRadio.isEnabled());
        assertTrue(secondRadio.isEnabled());

        firstRadio.click();
        secondRadio.click();
        assertFalse(firstRadio.isSelected());
        assertTrue(secondRadio.isSelected());

    }

    @Test
    public void testNavigationPage() {
        driver.get(navigationUrl);

        String firstText = "Lorem ipsum dolor sit amet";
        String secondText = "Ut enim ad minim veniam, quis nostrud exercitation";
        String thirdText = "Excepteur sint occaecat cupidatat non proident";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement textField = driver.findElement(By.cssSelector("p.lead"));
        assertTrue(textField.getText().contains(firstText));

        WebElement pageTwoLink = driver.findElement(By.xpath("//a[@href='navigation2.html']"));
        pageTwoLink.click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("p.lead"), secondText));
        textField = driver.findElement(By.cssSelector("p.lead"));
        assertTrue(textField.getText().contains(secondText));

        WebElement pageThreeLink = driver.findElement(By.xpath("//a[@href='navigation3.html']"));
        pageThreeLink.click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("p.lead"), thirdText));

    }

    @Test
    public void testDropdownMenuItemClick() {
        driver.get(dropdownUrl);

        WebElement dropdownButton = driver.findElement(By.id("my-dropdown-1"));
        dropdownButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement dropdownItem = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@class='dropdown-item' and text()='Another action']")
        ));

        assertTrue(dropdownItem.isDisplayed(), "Пункт дропдауна должен быть видим");
        dropdownItem.click();
        assertFalse(dropdownItem.isDisplayed(), "Пункт дропдауна должен быть закрыт");

        WebElement secondDropdownButton = driver.findElement(By.id("my-dropdown-2"));
        Actions actions = new Actions(driver);
        actions.contextClick(secondDropdownButton).perform();

        WebElement dropdownItemSecond = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@id='my-dropdown-2']/ancestor::div[contains(@class, 'dropdown')]//a[text()='Another action']")
        ));

        assertTrue(dropdownItemSecond.isDisplayed(), "Пункт дропдауна должен быть видим");
        dropdownItemSecond.click();
        assertFalse(dropdownItemSecond.isDisplayed(), "Пункт дропдауна должен быть закрыт");

        WebElement thirdDropdownButton = driver.findElement(By.id("my-dropdown-3"));
        actions.doubleClick(thirdDropdownButton).perform();

        WebElement dropdownItemThree = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@id='my-dropdown-3']/ancestor::div[contains(@class, 'dropdown')]//a[text()='Another action']")
        ));

        assertTrue(dropdownItemThree.isDisplayed(), "Пункт дропдауна должен быть видим");
        dropdownItemThree.click();
        assertFalse(dropdownItemThree.isDisplayed(), "Пункт дропдауна должен быть закрыт");


    }


    @Test
    public void testDragAndDrop() {
        driver.get(dragAndDropUrl);

        WebElement start = driver.findElement(By.cssSelector("div#draggable"));
        WebElement target = driver.findElement(By.cssSelector("div#target"));

        Actions actions = new Actions(driver);

        actions.clickAndHold(start)
                .moveToElement(target)
                .pause(Duration.ofMillis(500))
                .release()
                .perform();

        String style = start.getAttribute("style");

        System.out.println("Style: " + style);
        assertTrue(style.contains("left: 660px"));
        assertTrue(style.contains("top: 0px"));

    }


}



