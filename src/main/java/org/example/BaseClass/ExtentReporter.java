package org.example.BaseClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {

    public static ExtentReports getExtentReporter(){
        String path = System.getProperty("user.dir")+"//Reports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Ecommerse Website");

       ExtentReports extent = new ExtentReports();
       extent.attachReporter(reporter);

        return extent;


    }
}
