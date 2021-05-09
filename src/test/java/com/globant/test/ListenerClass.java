package com.globant.test;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class ListenerClass implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("Test starting");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info("The test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info("The test failed");

    }

}
