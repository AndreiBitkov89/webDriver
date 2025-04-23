import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeaderComponentTests extends BaseTest{
    @Test
    public void checkHeaderStructure(){
        Assertions.assertTrue(headerComponent.getIcon().isDisplayed());
        Assertions.assertTrue(headerComponent.getTitle().isDisplayed());
        Assertions.assertTrue(headerComponent.getSubTitle().isDisplayed());
    }

    @Test
    public void checkTitleText(){
        Assertions.assertEquals("Hands-On Selenium WebDriver with Java", headerComponent.getTitleText());

    }

    @Test
    public void checkSubTitleText(){
        Assertions.assertEquals("Practice site", headerComponent.getSubTitleText());
    }

    @Test
    public void checkIconLink(){
        Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/img/hands-on-icon.png", headerComponent.getIcon().getAttribute("src"));
    }

    @Test
    public void checkRedirectToGit(){
        headerComponent.getIcon().click();
        Assertions.assertEquals("https://github.com/bonigarcia/selenium-webdriver-java", driver.getCurrentUrl());
    }
}
