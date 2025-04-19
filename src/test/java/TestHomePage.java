import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@Feature("Home page tests")
public class TestHomePage extends BaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv", numLinesToSkip = 1, delimiter = ';')
    void openPageTest(String chapterTitle, String link, String expectedPageTitle) {
        mainPage.goToPage(chapterTitle, link);
        mainPage.checkTitle(expectedPageTitle);
    }

    @Test
    void openFramePage() {
        mainPage.goToPage("Chapter 4", "frames.html");
        driver.switchTo().frame(framePage.getFrame());
        mainPage.checkTitle("Frames");

    }

}
