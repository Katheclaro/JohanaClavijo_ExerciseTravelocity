package com.globant.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriverWait getWaitLarge() {
        wait = new WebDriverWait(driver, 60);
        return wait;
    }

    public WebDriverWait getWaitMedium() {
        wait = new WebDriverWait(driver, 30);
        return wait;
    }

    public WebDriverWait getWaitShort() {
        wait = new WebDriverWait(driver, 5);
        return wait;
    }

    public void dispose() {
        if (driver != null) {
            driver.quit();
        }
    }


}
