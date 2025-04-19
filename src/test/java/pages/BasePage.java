package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private WebElement title;


    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @Step("Check title of opened page")
    public void checkTitle(String expectedPageTitle) {
        title = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("h1.display-6")));
        assertTrue(title.isDisplayed(), "The title is not displayed");
        assertEquals(title.getText(), expectedPageTitle, "texts don't match");
    }
}
