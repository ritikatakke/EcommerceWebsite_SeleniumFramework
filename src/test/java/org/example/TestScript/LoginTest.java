package org.example.TestScript;

import org.example.BaseTest.BaseTest;
import org.example.POM.CheckOutPage;
import org.example.POM.ConfirmationPage;
import org.example.POM.ProductList;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LoginTest extends BaseTest {
    String SearchProduct ="shirt";
    String fit ="XS";

    String productName ="Circe Hooded Ice Fleece";

    @Test(dataProvider = "getData")
    public void loginWithWrongCredentials(HashMap<String,String> input){
        login.login(input.get("email"),input.get("password"));
        String Actual_Error= login.getLoginError();
        String Expected_Error ="The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        Assert.assertEquals(Actual_Error,Expected_Error);

    }

    @Test
    public void submitOrder() throws InterruptedException {
        login.login("rtritika04@gmail.com","Abcd@123");
       ProductList  productList = login.SearchProduct(SearchProduct);
        productList.getProducts();
        //productList.getProduct(productName);
        productList.addToCart(fit);
        CheckOutPage checkOutPage= productList.goToCheckout();
        ConfirmationPage confirmationPage = checkOutPage.fillCheckOutForm("ibm","sv Road","NewYork","Delaware","12345","United States","123456789",false);
        //confirmationPage.confirmationMsg();
        String actualOutput = confirmationPage.confirmationMsg();
        String expected = "We'll email you an order confirmation with details and tracking info.";
        Assert.assertgit Equals(actualOutput,expected);
    }

    @DataProvider
    public Object[][] getData() throws IOException
    {


        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//org/example//DataReader//LoginData.json");
        return new Object[][]  {{data.get(0)}, {data.get(1) } };

    }



}
