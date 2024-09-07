package org.example.POM;

//import jdk.internal.org.jline.utils.Log;
import org.example.BaseClass.BaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOutPage extends BaseClass {
    WebDriver driver;
    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(name="company")
    WebElement companyE;
    @FindBy(name="street[0]")
    WebElement streetE;
    @FindBy(name="city")
    WebElement cityE;
    @FindBy(name="postcode")
    WebElement zipE;
    @FindBy(name="telephone")
    WebElement phoneE;
    @FindBy(xpath="//tbody/tr[1]/td[1]/input[@class=\"radio\"]")
    WebElement tableRate;
    @FindBy(xpath="//tbody/tr[2]/td[1]/input[@class=\"radio\"]")
    WebElement fixedRate;
    @FindBy(xpath="//button[@data-role=\"opc-continue\"]")
    WebElement nextBtn;
    @FindBy(name = "billing-address-same-as-shipping")
    WebElement moneyOrder;
    @FindBy(css ="button.checkout")
    WebElement placeOrder;



    By countryEle= By.name("country_id");
    By stateEle= By.name("region_id");

    public  void fillShipmentDetails(String company, String street, String city, String State, String zip, String country, String phone, boolean radioTableRate){
        companyE.sendKeys(company);
        streetE.sendKeys(street);
        cityE.sendKeys(city);
        Select drpState = new Select(driver.findElement(stateEle));
        drpState.selectByVisibleText(State);
        zipE.sendKeys(zip);
        Select drpCountry = new Select(driver.findElement(countryEle));
        drpCountry.selectByVisibleText(country);
        phoneE.sendKeys(phone);
        if (radioTableRate) {
            if (tableRate.isSelected()) {
                System.out.println("Table Rate Radio Button is Selected");
            } else {
                tableRate.click();
            }
        } else {
            fixedRate.click();
        }


    }


    public ConfirmationPage fillCheckOutForm(String company, String street, String city, String State, String zip, String country, String phone, boolean radioTableRate) {
        if(companyE.isDisplayed()){
            fillShipmentDetails( company, street, city,  State,  zip, country,  phone,  radioTableRate);
            nextBtn.click();

        }
        else {

            if (radioTableRate) {
                if (tableRate.isSelected()) {
                    System.out.println("Table Rate Radio Button is Selected");
                } else {
                    tableRate.click();
                }
            } else {
                fixedRate.click();
            }
            nextBtn.click();
        }
            //waitelementToBeClickable(moneyOrder);
            /*if ( moneyOrder.isSelected()) {
                System.out.println("selected");
            } else {
                moneyOrder.click();
            }
           // moneyOrder.click();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        /*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.checkout")));
        */
        waitelementToBeClickable(placeOrder);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", placeOrder);
        //((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('action primary checkout')");


        //waitForWebElementToAppear(placeOrder);
        //Actions action = new Actions(driver);
        //action.moveToElement(placeOrder).perform();

            //placeOrder.click();
             ConfirmationPage confirmationPage =new ConfirmationPage(driver);
             return confirmationPage;




    }





}
