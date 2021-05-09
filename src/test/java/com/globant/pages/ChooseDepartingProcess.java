package com.globant.pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChooseDepartingProcess extends BasePage {

    WebDriver driver;

    @FindBy(css = "select[id=\"listings-sort\"]")
    private WebElement dropdownSortBy;

    @FindBy(css = "ul[data-test-id=\"listings\"]")
    private WebElement resultList;

    @FindBy(css = "button[data-test-id=\"select-button\"]")
    private WebElement continueButton;

    @FindBy(css = "div[class=\"uitk-text uitk-type-left uitk-type-300 uitk-type-bold uitk-step-indicator-step-details-wrapper\"]")
    private WebElement labelReturning;


    private static final String SORT_BY_LEAST_DURATION = "DURATION_INCREASING";

    public ChooseDepartingProcess(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Method to validate if the all results are visible
     * @return
     */
    public boolean validateButtonVisible() {
        boolean allButtonsVisible = true;

        getWaitMedium().until(ExpectedConditions.elementToBeClickable(resultList));
        List<WebElement> columns = resultList.findElements(By.cssSelector("button[data-test-id=\"select-link\"]"));
        for (WebElement cell : columns) {
            getWaitShort().until(ExpectedConditions.elementToBeClickable(cell));
            allButtonsVisible &= cell.isDisplayed();
        }

        return allButtonsVisible;
    }

    /**
     * Method to validate if all result have duration
     * @return
     */

    public boolean validationFlightDuration() {

        getWaitShort().until(ExpectedConditions.visibilityOfAllElements(resultList));
        List<WebElement> columns = resultList.findElements(By.className("uitk-type-200 uitk-text-emphasis-theme"));
        for (WebElement cell : columns) {
            getWaitMedium().until(ExpectedConditions.visibilityOf(cell));
            if (StringUtils.isBlank(cell.getText())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to validate if all result have detail
     * @return
     */
    public boolean validationFlightDetail() {

        getWaitShort().until(ExpectedConditions.visibilityOf(resultList));
        List<WebElement> columns = resultList.findElements(By.className("data-test-id=\"flight-operated\""));
        for (WebElement cell : columns) {
            getWaitMedium().until(ExpectedConditions.visibilityOf(cell));
            if (StringUtils.isBlank(cell.getText())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to sort all result by duration shortest
     * @return
     */

     public boolean sortByDurationShortest() {

        getWaitShort().until(ExpectedConditions.elementToBeClickable(dropdownSortBy));
        dropdownSortBy.click();

        Select sortByDropDown = new Select(dropdownSortBy);
        sortByDropDown.selectByValue(SORT_BY_LEAST_DURATION);
        getWaitShort();

        try {
            getWaitMedium().until(ExpectedConditions.visibilityOfAllElements(resultList));
            List<WebElement> columns = resultList.findElements(By.cssSelector("div[data-test-id=\"journey-duration\"]"));
            getWaitShort();
            List<Integer> listMinutes = new ArrayList<>();
            for (WebElement cell : columns) {
                int totalMinutes = getTotalMinutes(cell);
                getWaitShort();
                listMinutes.add(totalMinutes);

                List<Integer> listMinutesSorted = listMinutes.stream().sorted().collect(Collectors.toList());
                for (int i = 0; i < listMinutes.size(); i++) {
                    if (listMinutes.get(i) != listMinutesSorted.get(i)) {
                        return false;
                    }
                }
            }
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            getWaitShort();
        }
        return true;
    }

    /**
     * Method to select the first flight, it has the duration shortest
     * @return
     */
    public String selectFirstFlight() {

        getWaitMedium().until(ExpectedConditions.visibilityOfAllElements(resultList));
        List<WebElement> columns = resultList.findElements(By.cssSelector("button[data-test-id=\"select-link\"]"));
        getWaitMedium().until(ExpectedConditions.elementToBeClickable(columns.get(0)));
        columns.get(0).click();

        getWaitMedium().until(ExpectedConditions.visibilityOfAllElements(continueButton));
        continueButton.click();

        getWaitLarge().until(ExpectedConditions.visibilityOf(labelReturning));
        String labelReturningFlight = labelReturning.getText();
        getWaitShort();

        return labelReturningFlight;
    }

    /**
     * Method to get duration, convert duration to minutes
     * @param cell
     * @return
     */

    private int getTotalMinutes(WebElement cell) {
        String timeText = cell.getText();
        String[] times = timeText.split(" ");
        times[0] = times[0].replace("h", "");
        times[1] = times[1].replace("m", "");
        int totalMinutes = (Integer.parseInt(times[0]) * 60) + (Integer.parseInt(times[1]));
        return totalMinutes;
    }


}
