package org.example.POM;

import org.example.BaseClass.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage  extends BaseClass {
      WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="div.checkout-success p:nth-child(2)")
    WebElement confirmationMsg;

    public String confirmationMsg(){
        waitForWebElementToAppear(confirmationMsg);
        return confirmationMsg.getText();
    }
}
