package com.globant.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class BookingProcess extends BasePage {

    WebDriver driver;

    @FindBy(css = "a[aria-controls=\"wizard-flight-pwa\"]")
    private WebElement flightsOption;

    @FindBy(css = "a[aria-controls=\"wizard-flight-tab-roundtrip\"]")
    private WebElement roundTripOption;

    @FindBy(css = "div[id=\"adaptive-menu\"]")
    private WebElement passengerOption;


    @FindBy(css = "input[id=\"adult-input-0\"]")
    private WebElement passengerAdults;

    @FindBy(css = "button[data-testid=\"guests-done-button\"]")
    private WebElement passangerDone;

    @FindBy(css = ".adultStepInput.uitk-flex.uitk-flex-align-items-center.uitk-flex-justify-content-space-between.uitk-step-input.uitk-step-input-mounted > .uitk-flex.uitk-flex-item.uitk-step-input-controls > button:nth-of-type(2)")
    private WebElement increaseAdults;

    @FindBy(css = "div[id=\"preferred-class-input\"]")
    private WebElement categoryFlight;

    @FindBy(css = ".uitk-menu-container[aria-hidden='false'] a:nth-of-type(1) > .uitk-menu-list-item-label")
    private WebElement economicClass;

    @FindBy(css = "button[aria-label=\"Leaving from\"]")
    private WebElement leavingFrom;

    @FindBy(css = "[data-index='0'] > .uitk-button")
    private WebElement originOption;

    @FindBy(css = "button[aria-label=\"Going to\"]")
    private WebElement goingTo;

    @FindBy(css = "[data-index='0'][data-stid='location-field-leg1-destination-result-item'] .is-subText")
    private WebElement destinationOption;

    @FindBy(css = "button[id=\"d1-btn\"]")
    private WebElement departingDate;

    @FindBy(css = ".uitk-date-picker-menu-pagination-container > button:nth-of-type(2)")
    private WebElement nextMonth;

    @FindBy(css = "#wizard-flight-tab-roundtrip > div.uitk-layout-grid.uitk-layout-grid-gap-three.uitk-layout-grid-columns-small-4.uitk-layout-grid-columns-medium-6.uitk-layout-grid-columns-large-12.uitk-spacing.uitk-spacing-padding-block-three > div.uitk-layout-grid-item.uitk-layout-grid-item-columnspan-small-4.uitk-layout-grid-item-columnspan-medium-6.uitk-layout-grid-item-columnspan-large-4 > div > div > div:nth-child(1) > div > div.uitk-date-picker-menu-container.uitk-date-picker-menu-container-double.uitk-menu-container.uitk-menu-open.uitk-menu-pos-left.uitk-menu-container-autoposition.uitk-menu-container-text-nowrap > div > div.uitk-calendar > div.uitk-date-picker-menu-months-container > div:nth-child(2) > table > tbody")
    private WebElement bodyCalendarDeparture;

    @FindBy(css = "#wizard-flight-tab-roundtrip > div.uitk-layout-grid.uitk-layout-grid-gap-three.uitk-layout-grid-columns-small-4.uitk-layout-grid-columns-medium-6.uitk-layout-grid-columns-large-12.uitk-spacing.uitk-spacing-padding-block-three > div.uitk-layout-grid-item.uitk-layout-grid-item-columnspan-small-4.uitk-layout-grid-item-columnspan-medium-6.uitk-layout-grid-item-columnspan-large-4 > div > div > div:nth-child(2) > div > div.uitk-date-picker-menu-container.uitk-date-picker-menu-container-double.uitk-menu-container.uitk-menu-open.uitk-menu-pos-left.uitk-menu-container-autoposition.uitk-menu-container-text-nowrap > div > div.uitk-calendar > div.uitk-date-picker-menu-months-container > div:nth-child(1) > table > tbody")
    private WebElement bodyCalendarArrive;

    @FindBy(css = "button[data-stid=\"apply-date-picker\"]")
    private WebElement saveDate;

    @FindBy(css = "button[id=\"d2-btn\"]")
    private WebElement returningDate;

    @FindBy(css = "button[data-testid=\"submit-button\"]")
    private WebElement submitSearch;

    @FindBy(css = "[aria-label='Flying from Las Vegas, NV (LAS-McCarran Intl.)']")
    private WebElement resultLeavingFrom;

    @FindBy(css = "[aria-label='Flying to Los Angeles, CA (LAX-Los Angeles Intl.)']")
    private WebElement resultGoingTo;

    private static final int DURATION_FLIGHT = 10;
    private static final String TOTAL_PASSENGER = "1";


    public BookingProcess(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Method to select one passenger
     */
    public void selectPassenger() {
        passengerOption.click();

        if (!TOTAL_PASSENGER.equals(passengerAdults.getAttribute("value"))) {
            for (int i = 1; i < Integer.parseInt(TOTAL_PASSENGER); i++) {
                getWaitShort().until(ExpectedConditions.visibilityOf(increaseAdults));
                increaseAdults.click();
            }
        }
        passangerDone.click();
    }


    /**
     * Method to select an economy category
     */
    public void selectCategoryFlight() {
        categoryFlight.click();
        getWaitShort().until(ExpectedConditions.elementToBeClickable(economicClass));
        economicClass.click();
    }


    /**
     * Method to select departing day
     */
    public void selectDayFromDatePicker(WebElement calendarComponent, String day) {
        List<WebElement> columns = calendarComponent.findElements(By.tagName("button"));
        for (WebElement cell : columns) {
            if (cell.getAttribute("data-day").equals(day)) {
                getWaitMedium();
                cell.click();
                break;
            }
        }
        saveDate.click();
    }

    /**
     * Method to search Flight from LA to LAX
     */
    public String searchFlight(String flyingFrom, String flyingto) {

        getWaitMedium().until(ExpectedConditions.elementToBeClickable(flightsOption));
        flightsOption.click();
        roundTripOption.click();
        selectPassenger();
        getWaitMedium();
        selectCategoryFlight();

        leavingFrom.click();
        leavingFrom.sendKeys(flyingFrom);
        getWaitMedium().until(ExpectedConditions.elementToBeClickable(originOption));
        originOption.click();

        goingTo.click();
        goingTo.sendKeys(flyingto);
        getWaitMedium().until(ExpectedConditions.elementToBeClickable(destinationOption));
        destinationOption.click();

        departingDate.click();
        getWaitMedium().until(ExpectedConditions.visibilityOf(nextMonth));
        nextMonth.click();
        String today = DateUtils.getCurrentDay();
        selectDayFromDatePicker(bodyCalendarDeparture, today);

        returningDate.click();
        String dayArrive = String.valueOf(Integer.parseInt(DateUtils.getCurrentDay()) + DURATION_FLIGHT);
        selectDayFromDatePicker(bodyCalendarArrive, dayArrive);

        getWaitShort().until(ExpectedConditions.elementToBeClickable(submitSearch));
        submitSearch.click();

        getWaitShort().until(ExpectedConditions.visibilityOf(resultLeavingFrom));

        return resultLeavingFrom.getText();
    }

}
