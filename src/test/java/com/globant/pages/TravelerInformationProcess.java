package com.globant.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * This class validates the information traveler page
 */
public class TravelerInformationProcess extends BasePage {

    WebDriver driver;

    @FindBy(css = "h2.faceoff-module-title")
    private WebElement whoTravelerLabel;

    @FindBy(css = "#flightInsuranceContainer > section > header > h2")
    private WebElement protectFlightLabel;

    @FindBy(css = ".module-title-urgency")
    private WebElement wayToPayLabel;

    @FindBy(css = "h3.faceoff-module-title")
    private WebElement sendInformationLabel;

    @FindBy(css = ".review-and-book-header")
    private WebElement reviewBookTripLabel;

    @FindBy(css = "input[id=\"firstname[0]\"]")
    private WebElement inputFirstName;

    @FindBy(css = "input[data-tealeaf-name=\"middleName\"]")
    private WebElement inputMiddleName;

    @FindBy(css = "input[id=\"lastname[0]\"]")
    private WebElement inputLastName;

    @FindBy(css = "select[class=\"cko-field always-include gb-whitelist alpha3CountryCode\"]")
    private WebElement selectorCountry;

    @FindBy(css = "input[id=\"phone-number[0]\"]")
    private WebElement inputPhoneNumber;

    @FindBy(css = "input[id=\"gender_male[0]\"]")
    private WebElement inputGenderMale;

    @FindBy(css = "input[id=\"gender_female[0]\"]")
    private WebElement inputGenderFemale;

    @FindBy(css = "select[data-custom-rules=\"validateDateOfBirth\"]")
    private WebElement selectorDayofBirth;

    @FindBy(css = "button[id=\"complete-booking\"]")
    private WebElement continueBookingButton;

    @FindBy(css = "a[class=\"error-link\"]")
    private WebElement errorMessage;

    public TravelerInformationProcess(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Method to validate the labels
     * @return  true when the labels are visible
     */
    public boolean validateAllLabels() {

        boolean allLabelsVisible = true;

        allLabelsVisible &= whoTravelerLabel.isDisplayed();
        allLabelsVisible &= protectFlightLabel.isDisplayed();
        allLabelsVisible &= wayToPayLabel.isDisplayed();
        allLabelsVisible &= sendInformationLabel.isDisplayed();
        allLabelsVisible &= reviewBookTripLabel.isDisplayed();

        return allLabelsVisible;

    }

    /**
     * Method to validate all fields are available of "who travelling" section
     * @return
     */
    public boolean filloutWhoTravel() {

        boolean whoTravellingFields = true;

        whoTravellingFields &= inputFirstName.isEnabled();
        whoTravellingFields &= inputMiddleName.isEnabled();
        whoTravellingFields &= inputLastName.isEnabled();
        whoTravellingFields &= selectorCountry.isEnabled();
        whoTravellingFields &= inputPhoneNumber.isEnabled();
        whoTravellingFields &= inputGenderFemale.isEnabled();
        whoTravellingFields &= inputGenderMale.isEnabled();
        whoTravellingFields &= selectorDayofBirth.isEnabled();

        return whoTravellingFields;

    }

    /**
     * Method to validate error message if any field is missing to fill out
     * @return
     */

    public String validateErrorMessage() {
        getWaitShort().until(ExpectedConditions.elementToBeClickable(continueBookingButton));
        continueBookingButton.click();

        getWaitShort().until(ExpectedConditions.elementToBeClickable(errorMessage));

        return errorMessage.getText();
    }


}
