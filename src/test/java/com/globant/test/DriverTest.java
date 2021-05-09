package com.globant.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverTest {
    public  WebDriver driver;

    /**
     * This class selects the browser
     * @param browser
     */

    public DriverTest(String browser) {

        switch (browser) {
            case ("remoteFirefox"):
                try {
                    driver = new RemoteWebDriver(new URL("https://www.travelocity.com/"), DesiredCapabilities.firefox());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "./src/test/java/resources/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "./src/test/java/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            default:
                break;
        }
    }


    public WebDriver getDriver() {
        return this.driver;
    }
}
