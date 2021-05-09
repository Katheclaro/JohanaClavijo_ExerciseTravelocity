package com.globant.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ReviewTripProcess extends BasePage {

    WebDriver driver;

    @FindBy(css = ".uitk-text-emphasis-theme.uitk-type-500")
    private WebElement tripTotal;

    @FindBy(css = "button[data-test-id=\"goto-checkout-button\"]")
    private WebElement checkOutButton;

    @FindBy(css = "[data-test-id='flight-review-0'] .uitk-heading-4")
    private WebElement departingCity;

    @FindBy(css = "[data-test-id='flight-review-1'] .uitk-heading-4")
    private WebElement returningCity;

    @FindBy(css = "h1[class=\"flights generic-header\"]")
    private WebElement verificationCheckOut;


    public ReviewTripProcess(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Method to validate if the user completed Departing and Returning Processes
     * @return
     */
    public boolean validateDetailTrip() {

        boolean allDetailVisible = true;

        getWaitMedium().until(ExpectedConditions.visibilityOf(tripTotal));
        allDetailVisible &= tripTotal.isDisplayed();
        allDetailVisible &= departingCity.isDisplayed();
        allDetailVisible &= departingCity.isDisplayed();

        return allDetailVisible;
    }

    /**
     * Method to validate if possible continue Booking
     * @return
     */

    public String continueBooking() {
        getWaitShort().until(ExpectedConditions.visibilityOf(checkOutButton));
        checkOutButton.click();

        getWaitShort().until(ExpectedConditions.visibilityOf(verificationCheckOut));
        String getVerificationCheckOut = verificationCheckOut.getText();

        return getVerificationCheckOut;

    }
}
