package stepDefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.ssPageObject;

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

    @When("I log in using valid credentials as {string} with {string}")
    public void userLogin(String username, String password) throws Throwable {
        ssPageObject.loginUser(username, password);
    }

    @Then("I should be logged in")
    public void verifyLogin_Success() throws Throwable {
        ssPageObject.validateUser_valid();
    }

    @When("I log in using invalid credentials as {string} with {string}")
    public void i_log_in_using_invalid_credentials(String username, String password) {
        ssPageObject.loginUser(username, password);
    }

    @Then("I should not be logged in")
    public void i_should_not_be_logged_in() {
        ssPageObject.validateUser_invalid();
    }

    @Given("I am a logged in user on the website as {string} with {string}")
    public void i_am_a_logged_in_user_on_the_website(String username, String password) {
        ssPageObject.get();
        ssPageObject.loginUser(username, password);
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
        ssPageObject.checkShoppingCart_OneItemQuantity(num);
    }

    @When("I add {int} products to my shopping cart")
    public void i_add_num_products_products_to_my_shopping_cart(int num_products) {
        String[] productsArray = {
                "Eminent 6030 30m Patch Cat5E Grey",
                "Eminent 6005 5m Patch CAT5E Grey",
                "Eminent 6001 1m Patch CAT5E Grey",
                "Eminent 6035 25m Patch CAT5E Grey",
                "CANON PG-40",
                "CANON PG-50",
                "CANON CL-41",
                "Eminent IB5500 0.5m Cat5E Patch Red",
                "TPLink TL-ANT24EC5S 5mts Ant ext",
                "HDMI Cable 19pin Male to Male"
        };

        for(int i = 0; i < num_products; i++){
            ssPageObject.repeatingPurchases(productsArray[i]);
        }
    }

    @Then("my shopping cart should contain {int} items")
    public void my_shopping_cart_should_contain_num_products_items(int num_products) {
        ssPageObject.checkShoppingCart_AllItemQuantity(num_products);
    }

    @Given("my shopping cart has {int} products")
    public void my_shopping_cart_has_products(Integer int1) {
        i_add_num_products_products_to_my_shopping_cart(int1);
    }

    @When("I remove the first product in my cart")
    public void i_remove_the_first_product_in_my_cart() {
        ssPageObject.removeAnyOneProduct_FromCart();
    }
}