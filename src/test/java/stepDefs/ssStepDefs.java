package stepDefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.ssPageObject;

import java.util.List;

public class ssStepDefs {
    WebDriver driver;
    ssPageObject ssPageObject;



    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/dev/trees/CPS3230_Assignment_2/additionalFiles/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        ssPageObject = new ssPageObject(driver);
    }


    @After
    public void teardown() {
        driver.quit();
    }


    @Given("I am a user on the website")
    public void iAmOnWebsite() throws Throwable{
        ssPageObject.get();
    }

    @When("I log in using valid credentials")
    public void userLogin() throws Throwable {
        ssPageObject.loginUser("testingcps3230", "what is the time69");
    }

    @Then("I should be logged in")
    public void verifyLogin_Success() throws Throwable {
        ssPageObject.validateUser_valid();
    }

    @When("I log in using invalid credentials")
    public void i_log_in_using_invalid_credentials() {
        ssPageObject.loginUser("TestPassword", "TestPassword");
    }

    @Then("I should not be logged in")
    public void i_should_not_be_logged_in() {
        ssPageObject.validateUser_invalid();
    }

    @Given("I am a logged in user on the website")
    public void i_am_a_logged_in_user_on_the_website() {
        ssPageObject.get();
        ssPageObject.loginUser("testingcps3230", "what is the time69");
    }

    @When("I search for a product")
    public void i_search_for_a_product() {
        ssPageObject.productSearch_ProductExists("Eminent 60");
    }

    @When("I select the first product in the list")
    public void i_select_the_first_product_in_the_list() {
        ssPageObject.productSearchExists_ReturnFirst();
    }

    @Then("I should see the product details")
    public void i_should_see_the_product_details() {
        ssPageObject.productSearchExists_ProductDetails();
    }

    @Given("my shopping cart is empty")
    public void my_shopping_cart_is_empty() {
        ssPageObject.checkShoppingCart_Empty();
    }

    @When("I view the details of a product")
    public void i_view_the_details_of_a_product() {
        i_search_for_a_product();
        i_select_the_first_product_in_the_list();
        i_should_see_the_product_details();
    }

    @When("I choose to buy the product")
    public void i_choose_to_buy_the_product() {
        ssPageObject.buyProduct();
    }

    @Then("my shopping cart should contain {int} item")
    public void my_shopping_cart_should_contain_item(Integer num) {
        ssPageObject.checkShoppingCart_ItemQuantity(num);
    }

    @When("I add {int} products to my shopping cart")
    public void i_add_num_products_products_to_my_shopping_cart(int num_products) {
        ssPageObject.productSearch_ProductExists("Eminent 60");
        ssPageObject.productSearchExists_ReturnFirst();
        ssPageObject.productSearchExists_ProductDetails();
        ssPageObject.productQuantityEdit(num_products);
        ssPageObject.buyProduct();
    }

    @Then("my shopping cart should contain {int} items")
    public void my_shopping_cart_should_contain_num_products_items(int num_products) {
        ssPageObject.checkShoppingCart_ItemQuantity(num_products);
    }
}