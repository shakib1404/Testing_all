package org.example.pages;



import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class BoardDetailsPage extends BasePage {
    private final By boardTitle = By.cssSelector("#\\31-ranna h4");  // board with ID = 1-ranna
    private final By addMemberButton = By.cssSelector("li > .add-new");
    private final By memberEmailField = By.id("crawljax_member_email");
    private final By submitButton = By.cssSelector("button");

    public BoardDetailsPage(WebDriver driver) {
        super(driver);
    }

    public BoardDetailsPage openBoard() {
        waitForElement(boardTitle).click();
        return this;
    }

    public BoardDetailsPage openAddMemberForm() {
        waitForElement(addMemberButton).click();
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(addMemberButton)).perform();
        actions.moveToElement(driver.findElement(By.tagName("body")), 0, 0).perform();
        return this;
    }

    public BoardDetailsPage inviteMemberByEmail(String email) {
        waitForElement(memberEmailField).sendKeys(email);
        waitForElement(submitButton).click();
        return this;
    }
}

