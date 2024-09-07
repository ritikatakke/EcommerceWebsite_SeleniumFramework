package org.example.POM;

import com.beust.ah.A;
import org.example.BaseClass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class ProductList extends BaseClass {
    WebDriver driver;
    public ProductList(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }
    @FindBy(css="li.product-item")
    List<WebElement> products;
    @FindBy(css="div#option-label-size-143-item-166")
    WebElement sizes;
    @FindBy(css="div#option-label-color-93-item-57")
    WebElement colorElement;

    @FindBy(css="button#product-addtocart-button")
    WebElement addToCart;
    @FindBy(css="a.showcart")
    WebElement cart;
    @FindBy(id="top-cart-btn-checkout")
    WebElement checkout;
    @FindBy(xpath="//ol[@class='products list items product-items']/li[2]/div/a")
    WebElement prod;





    By productBy = By.cssSelector("a.product-item-link");
    By sizeBy = By.cssSelector("div.swatch-option");
    By colorBy = By.cssSelector("div.swatch-option");

    public List<WebElement> getProducts(){
        waitForWebElementToAppear(productBy);
        return products;

    }

    /*public WebElement getProduct(String productName){
        WebElement prod = products.stream().filter(product-> product.findElement(productBy).getText().equals(productName)).findFirst().orElse(null);
        prod.click();
        return prod;
    }*/

    public void addToCart(String fit) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Actions actions= new Actions(driver);
        actions.moveToElement(prod).perform();
        prod.click();
        Thread.sleep(20);
        sizes.click();
        colorElement.click();
        addToCart.click();
    }

    public CheckOutPage goToCheckout(){
        waitelementToBeClickable(cart);
        cart.click();
        checkout.click();

        CheckOutPage checkOutPage = new CheckOutPage(driver);
        return checkOutPage;

    }


}
