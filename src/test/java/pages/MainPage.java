package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage extends BasePage {

    private String actualURL;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Go to page")
    public void goToPage(String chapterTitle, String name) {
        WebElement chapter = driver.findElement(By.xpath("//div[h5[contains(text(), '" + chapterTitle + "')]]"));
        chapter.findElement(By.cssSelector("a[href='" + name + "']")).click();
        actualURL = driver.getCurrentUrl();
        assertTrue(actualURL.contains(name), "URL don't match");
    }

    @Step("Go to main page")
    public MainPage goToMain(){
        return this;
    }



}
