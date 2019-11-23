package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ssPageObject {

    WebDriver driver;

    public ssPageObject() {

    }

    public void sleep(int seconds){
        try{
            Thread.sleep(seconds * 1000);
        } catch(Exception e){
            // do nothing
        }
    }

    public ssPageObject(WebDriver driver){
        this.driver = driver;
    }

    public void get(){
        driver.get("https://www.simarksupplies.com/");
        sleep(2);
    }

    public void loginUser(String username, String password){
        driver.findElement(By.className("BannerSideLink")).click();
        driver.findElement(By.id("ctl00_MainContent_txt_UserName")).sendKeys(username);
        driver.findElement(By.id("ctl00_MainContent_txt_Password")).sendKeys(password);
        driver.findElement(By.id("ctl00_MainContent_btn_Login")).click();
        sleep(2);
    }

    public void validateUser_valid(){
        assertTrue(driver.findElement(By.className("BannerSideLink")).getText().contains("Logout"));
    }

    public void validateUser_invalid(){
        assertTrue(driver.findElement(By.id("ctl00_MainContent_lbl_Message")).getText().contains("Invalid login name and/or password."));
    }

    public void productSearch_ProductExists(String item){
        driver.findElement(By.id("ctl00_txt_SearchBox")).sendKeys(item);
        driver.findElement(By.id("ctl00_btn_Search")).click();

    }

    public void productSearchExists_ReturnFirst(){
        List<WebElement> productList = driver.findElements(By.className("Products_Name"));
        productList.get(0).click();
        sleep(2);
    }

    public void productSearchExists_ProductDetails(){
        assertTrue(driver.findElement(By.id("ctl00_MainContent_header2_ProductName")).getText().contains("Eminent 6030 30m Patch Cat5E Grey"));
    }

    public void checkShoppingCart_Empty(){
        assertTrue(driver.findElement(By.className("div_ShoppingCartSummary_NoItemsMsg")).getText().contains("Your cart is currently empty. Add an item by clicking the Buy button."));
    }

    public void buyProduct(){
        driver.findElement(By.id("ctl00_MainContent_btn_Buy")).click();
        sleep(2);
    }

    public void checkShoppingCart_OneItemQuantity(int num){
        assertTrue(driver.findElement(By.className("cell_ShoppingCartSummary_OrderedQuantity")).getText().equals("x"+num));
    }

    // To edit quantity
    public void productQuantityEdit(int num){
        driver.findElement(By.className("txt_ProductDetails_Quantity")).sendKeys("" + num);
    }

    public void checkShoppingCart_AllItemQuantity(int num){
        List<WebElement> productList = driver.findElements(By.className("cell_ShoppingCartSummary_OrderedQuantity"));
        assertTrue(productList.size() == num);
        sleep(2);
    }

    public void removeAnyOneProduct_FromCart(){
        driver.findElement(By.className("cell_ShoppingCartSummary_RemoveContainer")).click();
        sleep(2);
    }

    public void repeatingPurchases(String item){
        productSearch_ProductExists(item);
        productSearchExists_ReturnFirst();
        buyProduct();
        sleep(2);
    }

}