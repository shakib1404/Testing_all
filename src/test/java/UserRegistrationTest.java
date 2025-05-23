

import org.example.pages.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;

public class UserRegistrationTest {
    private WebDriver driver;
    private SignInPage signInPage;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(897, 1063));
        signInPage = new SignInPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testUserRegistrationAndSignIn() {
        // Register new user
        signInPage.navigateTo()
                .clickCreateAccount()
                .enterFirstName("Shakib")
                .enterLastName("Islam")
                .enterEmail("shakibislam155599@gmail.com")
                .enterPassword("12345678")
                .enterConfirmPassword("12345678")
                .clickSignUp()
                .signOut();

        // Sign in with registered credentials
        signInPage.navigateTo()
                .enterEmail("shakibislam15599@gmail.com")
                .enterPassword("12345678")
                .clickSignIn()
                .signOut();
    }
}
