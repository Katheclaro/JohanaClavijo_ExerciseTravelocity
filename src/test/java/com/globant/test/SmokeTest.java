package com.globant.test;

import com.globant.pages.*;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * This class only includes tests with asserts
 *
 */
@Listeners(ListenerClass.class)
public class SmokeTest extends BaseTest {

    @Test(priority = 1, description = "Booking flight")
    @Parameters({"leavingfrom","goingto", "browser" })
    public void makeBooking(String flyingFrom, String flyingTo, String browser) {
        BookingProcess bookingProcess = getBookingProcess();
        String result = bookingProcess.searchFlight(flyingFrom, flyingTo);
        Assert.assertEquals("Las Vegas, NV (LAS-McCarran Intl.)", result);

    }

    @Test(priority = 2, description = "Validate results")
    public void resultVisible(){
        ChooseDepartingProcess chooseDepartingFlight = getDepartingFlight();
        boolean allButtonVisible = chooseDepartingFlight.validateButtonVisible();
        boolean durationVisible = chooseDepartingFlight.validationFlightDuration();
        boolean detailFlight = chooseDepartingFlight.validationFlightDetail();

        Assert.assertTrue(allButtonVisible);
        Assert.assertTrue(durationVisible);
        Assert.assertTrue(detailFlight);

    }

    @Test(priority = 3, description = "Sort by Duration")
    public void getListSorted(){
        ChooseDepartingProcess chooseDepartingFlight = getDepartingFlight();
        boolean validateListSorted = chooseDepartingFlight.sortByDurationShortest();

        Assert.assertTrue(validateListSorted);
    }

    @Test(priority = 4, description = "Choose departing flight")
    public void selectDepartingFlight(){
        ChooseDepartingProcess chooseDepartingFlight = getDepartingFlight();
        String labelReturningFlight = chooseDepartingFlight.selectFirstFlight();

        Assert.assertEquals("Choose returning flight",labelReturningFlight);
    }

    @Test(priority = 5, description = "Choose returning flight")
    public void selectReturningFlight(){
        ChooseReturningProcess chooseReturningProcess = getReturningFlight();
        String labelReviewTrip = chooseReturningProcess.selectThirdFlight();

        Assert.assertEquals("Review your trip",labelReviewTrip);
    }

    @Test(priority = 6, description = "Validate details is visible")
    public void validateDetailVisible(){
        ReviewTripProcess reviewTripProcess = getReviewTrip();
        boolean detailVisible = reviewTripProcess.validateDetailTrip();

        Assert.assertTrue(detailVisible);
    }

    @Test(priority = 7, description = "Checkout Process")
    public void selectCheckOut(){
        ReviewTripProcess reviewTripProcess = getReviewTrip();
        String labelAfterCheckOut = reviewTripProcess.continueBooking();

        Assert.assertEquals("Secure booking - only takes a few minutes!",labelAfterCheckOut);
    }

    @Test(priority = 8, description = "Validate details of the trip")
    public void validateDetailTrip(){
        TravelerInformationProcess validateDetailTrip = getTravelerInformation();
        boolean allDetailVisible = validateDetailTrip.validateAllLabels();

        Assert.assertTrue(allDetailVisible);
    }

    @Test(priority = 9, description = "Validate fields of who's travelling")
    public void validateFieldsWhoTravelling(){
        TravelerInformationProcess validateDetailTrip = getTravelerInformation();
        boolean fieldsareEnabled = validateDetailTrip.filloutWhoTravel();

        Assert.assertTrue(fieldsareEnabled);
    }

    @Test(priority = 10, description = "Validate error message")
    public void validateErrorMessage(){
        TravelerInformationProcess validateDetailTrip = getTravelerInformation();
        String getErrorMessage = validateDetailTrip.validateErrorMessage();

        Assert.assertEquals("Jump to the first invalid field.",getErrorMessage);
    }

}
