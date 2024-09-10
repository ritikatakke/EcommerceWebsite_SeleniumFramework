package org.example.BaseTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.example.POM.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    WebDriver driver;
    public LoginPage login;
    public ExtentReports extent;
    Properties prop = new Properties();
    FileReader reader;

    {
        try {

            reader = new FileReader(System.getProperty("user.dir")+"//src//main//resources//Property.properties");
            prop.load(reader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public WebDriver initializeDriver(){


        if( prop.getProperty("browser").equals("GoogleChrome"))
        {
            driver = new ChromeDriver();

        } else if ( prop.getProperty("browser").equals("EdgeDriver"))
        {
            driver = new EdgeDriver();

        }
        else if( prop.getProperty("browser").equals("FireFox")){
            driver = new FirefoxDriver();
        }
        else{
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
         return driver;

    }

    @BeforeMethod
    public LoginPage launch(){

        driver = initializeDriver();
        By by = By.id("");
         login = new LoginPage(driver);
        login.goTo(prop.getProperty("url"));
        return login;
    }

    @AfterMethod
    public void tearDown()
    {
       driver.quit();
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException
    {
        //read json to string
        String jsonContent = 	FileUtils.readFileToString(new File(filepath));

        //String to HashMap- Jackson Datbind

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;

        //{map, map}
    }

   public String getScreenshot(String testcaseName,WebDriver driver) throws IOException {
       TakesScreenshot ts = (TakesScreenshot) driver;
       File source = ts.getScreenshotAs(OutputType.FILE);
       File file = new File(System.getProperty("user.name")+"//Reports//"+testcaseName+".png");
       FileUtils.copyFile(source,file);
       return System.getProperty("user.name")+"//Reports//"+testcaseName+".png";

   }

}
