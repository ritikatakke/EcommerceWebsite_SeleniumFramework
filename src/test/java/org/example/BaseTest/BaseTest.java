package org.example.BaseTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.example.POM.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class BaseTest {
    WebDriver driver;
    public LoginPage login;

    public WebDriver initializeDriver(){
        String browser = "EdgeDriver";
        if(browser.equals("GoogleChrome"))
        {
            driver = new ChromeDriver();

        } else if (browser.equals("EdgeDriver"))
        {
            driver = new EdgeDriver();

        }
        else if(browser.equals("FireFox")){
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
         login = new LoginPage(driver);
        login.goTo("https://magento.softwaretestingboard.com/");
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





}
