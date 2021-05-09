package com.globant.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class ChooseReturningProcess extends BasePage {

    WebDriver driver;

    @FindBy(css = "ul[data-test-id=\"listings\"]")
    private WebElement resultListReturning;

    @FindBy(css = "button[data-test-id=\"select-button\"]")
    private WebElement continueButton;

    @FindBy(css = "li[aria-label=\"Review your trip\"]")
    private WebElement reviewTripLabel;

    @FindBy(css = "a[data-test-id=\"forcedChoiceNoThanks\"]")
    private WebElement popUpNoThanks;

    public ChooseReturningProcess(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Method to select the third flight, get a list of the result and the select it
     * @return
     */

    public String selectThirdFlight() {

        getWaitMedium().until(ExpectedConditions.visibilityOfAllElements(resultListReturning));
        List<WebElement> columns = resultListReturning.findElements(By.cssSelector("button[data-test-id=\"select-link\"]"));
        getWaitMedium();
        columns.get(2).click();

        getWaitShort().until(ExpectedConditions.visibilityOfAllElements(continueButton));
        continueButton.click();

        try {
            getWaitShort().until(ExpectedConditions.visibilityOf(popUpNoThanks));
            popUpNoThanks.click();

        } catch (Exception e) {

        }
        getNewWindows();
        getWaitMedium().until(ExpectedConditions.visibilityOf(reviewTripLabel));
        String labelReturningFlight = reviewTripLabel.getText();
        return labelReturningFlight;
    }

    /**
     * Method to change the windows to review trip page
     */
    public void getNewWindows() {

        for (String winHandleBefore : driver.getWindowHandles()) {
            driver.switchTo().window(winHandleBefore);
            driver.manage().window().maximize();
        }
    }
}
