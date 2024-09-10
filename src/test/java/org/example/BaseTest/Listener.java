package org.example.BaseTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.example.BaseClass.ExtentReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listener extends BaseTest implements ITestListener {
      ExtentTest test;
    ExtentReports extent = ExtentReporter.getExtentReporter();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    //since we are running testcases parallely test object is ovrridden by another methood
    //so thread local will create one thread object of test per testcase
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);

    }


    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String filepath=null;
        try {
            filepath= getScreenshot(result.getMethod().getMethodName(),driver);
            extentTest.get().addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
