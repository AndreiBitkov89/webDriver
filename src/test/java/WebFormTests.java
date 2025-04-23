
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.DataBuilder;

import static org.junit.jupiter.api.Assertions.*;

@Feature("Web page tests")
public class WebFormTests extends BaseTest {

    DataBuilder data = DataBuilder.builder()
            .text("Andrei")
            .pass("secret123")
            .longText("Long story short")
            .build();

    @Test
    public void testInputField() {
        mainPage.goToPage("Chapter 3","web-form.html");
        webForm.inputValue(data.getText());
        assertEquals(data.getText(), webForm.getInputField().getAttribute("value"));
    }

    @Test
    public void testPasswordFields() {
        mainPage.goToPage("Chapter 3","web-form.html");
        webForm.inputPassword(data.getPass());
        assertEquals(data.getPass(), webForm.getPasswordField().getAttribute("value"));

    }

    @Test
    public void testTextField() {
        mainPage.goToPage("Chapter 3","web-form.html");
        webForm.inputTextInTextArea(data.getLongText());
        assertEquals(data.getLongText(), webForm.getTextAreaLocator().getAttribute("value"));
    }

    @Test
    public void testDisabledField() {
        mainPage.goToPage("Chapter 3","web-form.html");
        webForm.checkDisabledField(data.getText());
        assertEquals("", webForm.getDisabledInputField().getAttribute("value"));

    }

    @ParameterizedTest
    @ValueSource(strings = {"One", "Two", "Three"})
    public void testDropdownSelect(String option) {
        mainPage.goToPage("Chapter 3","web-form.html");
        webForm.dropdownHandle(option);
    }

    @ParameterizedTest
    @ValueSource(strings = {"San Francisco", "New York", "Seattle", "Los Angeles", "Chicago"})
    public void testValidCitySelection(String city) {
        mainPage.goToPage("Chapter 3","web-form.html");
        webForm.validationCitySelect(city);

        assertEquals(city, webForm.getCitySelector().getAttribute("value"));
    }


    @Test
    public void testUploadFile() {
        mainPage.goToPage("Chapter 3","web-form.html");
        webForm.uploadFile("src/test/resources/img.png");

        assertTrue(webForm.getReceivedNote().isDisplayed());
    }

}



