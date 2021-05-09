package com.globant.test;

import com.globant.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class BaseTest {

    private DriverTest driverBrowser;
    private WebDriver driver;

    private BookingProcess bookingProcess;
    private ChooseDepartingProcess chooseDepartingProcess;
    private ChooseReturningProcess chooseReturningProcess;
    private ReviewTripProcess reviewTripProcess;
    private TravelerInformationProcess travelerInformationProcess;

    /**
     * Start the browser and the site before executing the tests
     * @param browser
     * @param url
     */
    @BeforeSuite(alwaysRun = true)
    @Parameters({"browser", "url"})
    public void beforeSuite(String browser, String url) {

        driverBrowser = new DriverTest(browser);
        driver = driverBrowser.getDriver();
        driver.manage().window().maximize();
        driver.get(url);

    }

    /**
     * Call the classes
     * @return
     */
    public BookingProcess getBookingProcess() {
        bookingProcess = new BookingProcess(driver);
        return bookingProcess;
    }

    public ChooseDepartingProcess getDepartingFlight() {
        chooseDepartingProcess = new ChooseDepartingProcess(driver);
        return chooseDepartingProcess;
    }

    public ChooseReturningProcess getReturningFlight() {
        chooseReturningProcess = new ChooseReturningProcess(driver);
        return chooseReturningProcess;
    }

    public ReviewTripProcess getReviewTrip() {
        reviewTripProcess = new ReviewTripProcess(driver);
        return reviewTripProcess;
    }

    public TravelerInformationProcess getTravelerInformation() {
        travelerInformationProcess = new TravelerInformationProcess(driver);
        return travelerInformationProcess;
    }

    /**
     * Close url after executing test
     */
    @AfterSuite(alwaysRun = true)
    public void afterSuit() {
        bookingProcess.dispose();
    }


}
