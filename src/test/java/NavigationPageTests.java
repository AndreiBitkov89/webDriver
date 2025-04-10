import Helpers.Navigation;
import io.qameta.allure.Feature;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

@Feature("Navigation Page tests")
public class NavigationPageTests extends BaseTest {

    @ParameterizedTest
    @EnumSource(value = Navigation.class, names = {"FIRST", "SECOND", "THIRD"})
    public void testNavigationPage(Navigation navigation) {
        mainPage.goToPage("Chapter 3", "navigation1.html");
        navigationPage.navigateAndCheckText(navigation);

    }


}



