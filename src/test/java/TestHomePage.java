import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class TestHomePage {

    WebDriver driver;
    String baseURL = "https://bonigarcia.dev/selenium-webdriver-java/";

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseURL);
    }

    @AfterEach
    void tearDown() {
            driver.quit();

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv", numLinesToSkip = 1, delimiter = ';')
    void openPageTest(String chapterTitle, String linkHref, String expectedPageTitle, boolean isFrame) {
        WebElement chapter = driver.findElement(By.xpath("//div[h5[text() = '" + chapterTitle + "']]"));
        WebElement link = chapter.findElement(By.cssSelector("a[href='" + linkHref + "']"));

        assertTrue(link.isDisplayed(), "The link is not displayed");

        link.click();
        String actualURL = driver.getCurrentUrl();

        assertEquals(actualURL, baseURL + linkHref, "URL don't match");

        if (isFrame) {
            WebElement frame = driver.findElement(By.cssSelector("frame[name='frame-header']"));
            driver.switchTo().frame(frame);
        }

        WebElement title = driver.findElement(By.cssSelector("h1.display-6"));
        assertTrue(title.isDisplayed(), "The title is not displayed");
        assertEquals(title.getText(), expectedPageTitle, "texts don't match");
    }
}
