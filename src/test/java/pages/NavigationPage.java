package pages;

import Helpers.Navigation;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NavigationPage extends BasePage {
    public NavigationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(css = "p.lead")
    WebElement textField;

    @FindBy(xpath = "//a[@href='navigation1.html']")
    WebElement pageFirstLink;

    @FindBy(xpath = "//a[@href='navigation2.html']")
    WebElement pageTwoLink;

    @FindBy(xpath = "//a[@href='navigation3.html']")
    WebElement pageThreeLink;

    private final Map<Navigation, WebElement> navElements = Map.of(
            Navigation.FIRST, pageFirstLink,
            Navigation.SECOND, pageTwoLink,
            Navigation.THIRD, pageThreeLink
    );

    @Step("Pagination to page")
    public void navigateAndCheckText(Navigation navigation) {
        navElements.get(navigation).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("p.lead"), navigation.getText()));
        assertTrue(textField.getText().contains(navigation.getText()));
    }
}
