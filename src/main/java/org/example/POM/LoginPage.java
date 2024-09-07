package org.example.POM;

import org.example.BaseClass.BaseClass;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BaseClass {
    WebDriver driver;


    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(linkText="Sign In")
    WebElement signIn;
    @FindBy(id="email")
    WebElement username;

    @FindBy(id="pass")
    WebElement password;
    @FindBy(id="send2")
    WebElement submit;
    @FindBy(id="search")
    WebElement search;
    @FindBy(css="a.showcart")
    WebElement cart;
    @FindBy(id="top-cart-btn-checkout")
    WebElement checkout;

    @FindBy(css="div[data-ui-id=message-error]")
    WebElement errorMsg;

    @FindBy(css="div  span.logged-in")
    WebElement welcomeMsg;







    public void goTo(String url)
    {
        driver.get(url);

    }

    public String checkWelcomeMsg(){
     waitForWebElementToAppear(welcomeMsg);
     return welcomeMsg.getText();
    }

    public void login(String email,String pass){

        signIn.click();
        username.sendKeys(email);
        password.sendKeys(pass);
        submit.click();
    }

    public String  getLoginError(){
    waitForWebElementToAppear(errorMsg);
    return errorMsg.getText();
    }

    public ProductList SearchProduct(String searchAttire)
    {
     search.click();
     search.sendKeys(searchAttire);
     search.sendKeys(Keys.ENTER);
     ProductList productList = new ProductList(driver);
     return productList;
    }










}

