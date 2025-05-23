

import org.example.pages.SignInPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;

public class SignInTest {
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
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testBasicSignIn() {
        // Navigate and perform sign in without credentials (guest flow)
        signInPage.navigateTo()
                .signInWithoutCredentials()
                .signOut();
    }

    @Test
    public void testSignInWithCredentials() {
        signInPage.navigateTo();

        Assert.assertTrue("Email field should be visible", signInPage.isEmailFieldPresent());
        Assert.assertTrue("Password field should be visible", signInPage.isPasswordFieldPresent());

        signInPage.enterEmail("shakibislam599@gmail.com")
                .enterPassword("12345678")
                .clickSignIn()
                .signOut();
    }

    @Test

    public void testSignInWithOnlyEmail() {
        signInPage.navigateTo();

        Assert.assertTrue("Email field should be visible", signInPage.isEmailFieldPresent());
        Assert.assertTrue("Password field should be visible", signInPage.isPasswordFieldPresent());

        // Clear the password field explicitly
        signInPage.enterPassword("");  // ensures no residual value

        // Enter only the email
        signInPage.enterEmail("shakibislam1599@gmail.com");

        // Capture the current URL before clicking sign in
        String beforeClickURL = driver.getCurrentUrl();

        // Try to sign in (expect to stay on the same page due to missing password)
        signInPage.clickSignInSuppressRedirect();

        // Capture URL after click
        String afterClickURL = driver.getCurrentUrl();

        // Assertions
        Assert.assertEquals("Expected to stay on the same page due to form validation.", beforeClickURL, afterClickURL);
        Assert.assertTrue("Expected password field to remain empty after click.", signInPage.getPasswordValue().isEmpty());
    }

}
