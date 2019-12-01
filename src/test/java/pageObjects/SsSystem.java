package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SsSystem {

    WebDriver driver;

    public SsSystem(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.simarksupplies.com/");
    }

    public boolean isLoggedIn() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return driver.findElement(By.className("BannerSideLink")).getText().contains("Logout");
    }

    public boolean isLoggedOut() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return driver.findElement(By.className("BannerSideLink")).getText().contains("Login/Register");
    }

    public boolean isInProductDetails() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        // PageTitle_Header2
        List<WebElement> foundElements = driver.findElements(By.xpath("//*[contains(@id,'section_title')]"));
        if(foundElements.size() != 2){
            return false;
        } else {
            return true;
        }
//        return driver.findElement(By.id("ctl00_MainContent_header2_ProductName")).isDisplayed();
    }

    public boolean isInResultsList() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return driver.findElement(By.className("PageTitle_Header1")).getText().contains("Showing");
    }

    public boolean isInHeadingResultsList() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return driver.findElement(By.className("PageTitle_Header1")).getText().contains("New Products");
    }

    public boolean isInShoppingCart() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return driver.findElement(By.className("PageTitle_Header1")).getText().contains("View Shopping Cart");
    }

//    public boolean isInCheckout(){ return checkOut; }

    public void loggingIn() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.className("BannerSideLink")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.id("ctl00_MainContent_txt_UserName")).sendKeys("testingcps3230");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.id("ctl00_MainContent_txt_Password")).sendKeys("what is the time69");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.id("ctl00_MainContent_btn_Login")).click();
    }

    public void loggingOut() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.className("BannerSideLink")).click();
    }

    public void searchProduct() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.id("ctl00_txt_SearchBox")).clear();
        driver.findElement(By.id("ctl00_txt_SearchBox")).sendKeys("Eminent 6030 30m Patch Cat5E Grey");
        driver.findElement(By.id("ctl00_btn_Search")).click();
    }

    public void searchCategory() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.className("treeview_NodeImageCSS")).click();
    }

    public void searchProductHeading(int num) {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        List<WebElement> productList = driver.findElements(By.className("HyperLink_MenuLink"));
        // PC Systems (3) Notebooks (4). Always 5 headings. Offers are seasonal, ez. black friday
        productList.get(num).click();
    }

    public void buyFromList_ViewCart() {
        List<WebElement> productList = driver.findElements(By.xpath("//*[contains(@id,'btn_Buy')]"));
        productList.get(0).click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.findElement(By.name("ctl00$ctl18$ctl00$ctl01")).click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    public void buyFromList_ViewCart_ModifyQty() {
        List<WebElement> qtyList = driver.findElements(By.className("Products_TextBox_Quantity"));
        qtyList.get(0).clear();
        qtyList.get(0).sendKeys("2");
        List<WebElement> productList = driver.findElements(By.xpath("//*[contains(@id,'btn_Buy')]"));
        productList.get(0).click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.findElement(By.name("ctl00$ctl18$ctl00$ctl01")).click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    public void goToCart() {
        if(driver.findElements(By.name("div_ShoppingCartSummary_ViewCartContainer")).size() != 1){
            driver.get("https://www.simarksupplies.com/ViewCart.aspx");
        } else {
            driver.findElement(By.name("ctl00$ctl18$ctl00$ctl01")).click();
            driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        }
    }

    public void viewProductDetails() {
        List<WebElement> productList = driver.findElements(By.className("Products_Name"));
        productList.get(0).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public void buyFromProductDetails_ViewCart() {
        driver.findElement(By.id("ctl00_MainContent_btn_Buy")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.name("ctl00$ctl18$ctl00$ctl01")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public void buyFromProductList_ModifyQty_ViewCart() {
        List<WebElement> qtyList = driver.findElements(By.className("txt_ProductDetails_Quantity"));
        qtyList.get(0).clear();
        qtyList.get(0).sendKeys("2");
        driver.findElement(By.id("ctl00_MainContent_btn_Buy")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.name("ctl00$ctl18$ctl00$ctl01")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public void removeFirstItem_FromCart(){
        List<WebElement> checkoutList = driver.findElements(By.className("Products_TextBox_Quantity"));
        if(checkoutList.size() > 0){
            checkoutList.get(0).clear();
            checkoutList.get(0).sendKeys("0");
            driver.findElement(By.id("ctl00_MainContent_btn_UpdateCart")).click();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }
    }
}