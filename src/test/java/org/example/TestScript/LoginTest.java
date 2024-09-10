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

    LoginTest(){
        super();

    }
    String SearchProduct ="shirt";
    String fit ="XS";

    String productName ="Circe Hooded Ice Fleece";

    @Test(dataProvider = "getData")
    public void loginWithWrongCredentials(HashMap<String,String> input){
        //extent.createTest("loginWithWrongCredentials");
        login.login(input.get("email"),input.get("password"));


        String Actual_Error= login.getLoginError();
        String Expected_Error ="The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        Assert.assertEquals(Actual_Error,Expected_Error);

    }

/*

----------------DATA PROVIDE USING ARRAY----------------
    @Test(dataProvider = "arrayDataProvider")
    public void loginWithWrongCredentiaLArray(String email,String pass){
        login.login(email,pass);
        String Actual_Error= login.getLoginError();
        String Expected_Error ="The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        Assert.assertEquals(Actual_Error,Expected_Error);

    }

    @DataProvider
    public  Object[][] arrayDataProvider(){

        return new Object[][]  {{"rtritika03@gmail.com","riha@125"},{"p@gmail.com","1234"}};

    }
*/

   /* @DataProvider
    public Object[][] dataHashMap(){
        HashMap<Object,Object> map = new HashMap<Object,Object>();
        map.put("email","r@gmail.com");
        map.put("password","rttytay");

        HashMap<Object,Object> map1 = new HashMap<Object,Object>();
        map1.put("email","s@gmail.com");
        map1.put("password","dhettytay");

       return new Object[][] {{map},{map1}};
    }

    @Test(dataProvider = "dataHashMap")
    public void loginWithWrongCredentialArray(HashMap<String,String> input){
        login.login(input.get("email"),input.get("password"));
        String Actual_Error= login.getLoginError();
        String Expected_Error ="The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        Assert.assertEquals(Actual_Error,Expected_Error);

    }
*/

    @Test
    public void loginSucessfully(){
        login.login("rtritika03@gmail.com","Riha@1234");
        String Actual_Msg= login.checkWelcomeMsg();
        String Expected_Msg ="Welcome, Ritu20 Takke!";
        Assert.assertEquals(Actual_Msg,Expected_Msg);

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

        Assert.assertEquals(actualOutput,expected);
    }

    @DataProvider
    public Object[][] getData() throws IOException
    {


        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//org/example//DataReader//LoginData.json");
        return new Object[][]  {{data.get(0)}, {data.get(1) } };

    }



}
