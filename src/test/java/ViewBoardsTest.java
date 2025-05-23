

import org.example.pages.SignInPage;
import org.example.pages.BoardsPage;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ViewBoardsTest {
    private WebDriver driver;
    private SignInPage signInPage;
    private BoardsPage boardsPage;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1053, 1064));
        signInPage = new SignInPage(driver);
        boardsPage = new BoardsPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testViewBoardsNavigation() {
        // Step 1: Sign in
        signInPage.navigateTo()
                .enterEmail("shakibislam599@gmail.com")
                .enterPassword("12345678")
                .clickSignIn();

        System.out.println("After login URL: " + driver.getCurrentUrl());

        // Step 2: Navigate to Boards view
        boardsPage.openBoardsNav()
                .clickViewAllBoards();

        // Step 3: Wait for board list to appear
        By boardListContainer = By.cssSelector(".boards-wrapper"); // <- update selector based on actual HTML

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(boardListContainer));

        Assert.assertTrue(
                "Expected board list to be visible",
                driver.findElement(boardListContainer).isDisplayed()
        );
    }
}
