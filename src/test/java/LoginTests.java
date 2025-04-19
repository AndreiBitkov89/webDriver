import org.junit.jupiter.api.Test;
import utils.User;

public class LoginTests extends BaseTest {

    User validUser = User.builder().
            login("user").password("user").build();
    User invalidUser = User.builder().
            login("user123").password("user").build();

    @Test
    public void shouldLogin() {
        mainPage.goToPage("Chapter 7", "login-form.html");
        loginPage.login(validUser).validateLogin();
    }

    @Test
    public void shouldShowError() {
        mainPage.goToPage("Chapter 7", "login-form.html");
        loginPage.login(invalidUser).validateError();
    }
}



