package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.User;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(css = "input[name=\"username\"]")
    @CacheLookup
    private WebElement inputName;
    @FindBy(css = "input[name=\"password\"]")
    @CacheLookup
    private WebElement inputPassword;
    @FindBy(css = "*[type=\"submit\"]")
    @CacheLookup
    private WebElement submit;
    @FindBy(css = "#success")
    @CacheLookup
    private WebElement successNote;
    @FindBy(css="#invalid")
    @CacheLookup
    private WebElement errorNote;

    @Step("Login client")
    public LoginPage login(User user) {
        Assertions.assertTrue(inputName.isDisplayed());
        inputName.sendKeys(user.getLogin());
        inputPassword.sendKeys(user.getPassword());
        submit.click();
        return this;
    }

    @Step("Validate message after login")
    public void validateLogin() {
        wait.until(ExpectedConditions.visibilityOf(successNote));
        Assertions.assertEquals("Login successful", successNote.getText());
    }

    @Step("Validate error message after unsuccessful login")
    public void validateError() {
        Assertions.assertTrue(errorNote.isDisplayed());
        Assertions.assertEquals("Invalid credentials", errorNote.getText());
    }

}
