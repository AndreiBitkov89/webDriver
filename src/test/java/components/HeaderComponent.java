package components;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

public class HeaderComponent extends BasePage {
    public HeaderComponent(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(css = ".display-4")
    @CacheLookup
    private WebElement title;

    @FindBy(xpath = "//h5[text()='Practice site']")
    @CacheLookup
    private WebElement subTitle;

    @FindBy(className = "img-fluid")
    @CacheLookup
    private WebElement icon;

    @Step("Get icon element")
    public WebElement getIcon() {
        return icon;
    }

    @Step("Get title element")
    public WebElement getTitle() {
        return title;
    }

    @Step("Get subtitle element")
    public WebElement getSubTitle() {
        return subTitle;
    }

    @Step("Get text of title")
    public String getTitleText() {
        return title.getText();
    }

    @Step("Get text of subtitle")
    public String getSubTitleText() {
        return subTitle.getText();
    }
}
