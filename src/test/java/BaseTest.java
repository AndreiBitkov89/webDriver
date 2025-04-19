import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected String baseURL = "https://bonigarcia.dev/selenium-webdriver-java/";
    protected WebDriverWait wait;
    protected MainPage mainPage;
    protected WebFormPage webForm;
    protected NavigationPage navigationPage;
    protected FramePage framePage;
    ;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        mainPage = new MainPage(driver, wait);
        webForm = new WebFormPage(driver, wait);
        framePage =new FramePage(driver, wait);
        navigationPage = new NavigationPage(driver, wait);
        driver.get(baseURL);

    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
