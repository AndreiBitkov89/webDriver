package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class WebFormPage extends BasePage {

    public WebFormPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(css = "#my-text-id")
    private WebElement inputField;

    @FindBy(css = "input[type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//label[contains(text(), 'Textarea')]/textarea")
    private WebElement textAreaLocator;

    @FindBy(xpath = "//label[contains(text(), 'Disabled input')]/input")
    private WebElement disabledInputField;

    @FindBy(xpath = "//label[contains(text(), 'Dropdown (select)')]/select")
    private WebElement dropDownSelect;

    @FindBy(xpath = "//label[contains(text(), 'Dropdown (datalist)')]/input")
    private WebElement citySelector;

    @FindBy(xpath = "//label[contains(text(), 'File input')]/input")
    private WebElement fileInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public WebElement getReceivedNote() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[text()='Received!']")
        ));
    }

    public WebElement getInputField() {
        return inputField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getDisabledInputField() {
        return disabledInputField;
    }

    @Step("Input value")
    public void inputValue(String text) {
        Assertions.assertTrue(inputField.isDisplayed());
        inputField.sendKeys(text);
    }

    @Step("Input password")
    public void inputPassword(String pass) {
        Assertions.assertTrue(passwordField.isDisplayed());
        passwordField.sendKeys(pass);
    }

    public WebElement getTextAreaLocator() {
        return textAreaLocator;
    }

    public WebElement getCitySelector() {
        return citySelector;
    }

    @Step("Input text")
    public void inputTextInTextArea(String text) {
        Assertions.assertTrue(textAreaLocator.isDisplayed());
        textAreaLocator.sendKeys(text);
    }

    @Step("Check disabled field")
    public void checkDisabledField(String text) {
        assertNotNull(disabledInputField.getAttribute("disabled"));
        assertThrows(ElementNotInteractableException.class, () -> {
            disabledInputField.sendKeys(text);
        }, "Ожидается ElementNotInteractableException при вводе в disabled-поле");
    }

    @Step("activate dropdown and choose option")
    public void dropdownHandle(String option) {
        Select select = new Select(dropDownSelect);
        select.selectByVisibleText(option);
        assertEquals(option, select.getFirstSelectedOption().getText());
    }


    @Step("Enter the city name and validate result")
    public void validationCitySelect(String city) {

        citySelector.sendKeys(city);

        List<WebElement> options = driver.findElements(By.cssSelector("datalist#my-options option"));
        List<String> values = options.stream().map(option -> option.getAttribute("value")).collect(Collectors.toList());

        assertTrue(values.contains(city), "Город '" + city + "' должен присутствовать в datalist");

    }

    @Step("Upload file")
    public void uploadFile(String path) {

        String filePath = Paths.get(path).toAbsolutePath().toString();
        fileInput.sendKeys(filePath);
        submitButton.click();

    }

}
