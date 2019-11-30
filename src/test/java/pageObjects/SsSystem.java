package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SsSystem {

    WebDriver driver;

    private boolean loggedOut = true;
    private boolean loggedIn = false;
    private boolean regLogin = false;
    private boolean prodDetails = false;
    private boolean resultList = false;
    private boolean shopCart = false;
    private boolean checkOut = false;

    public SsSystem(WebDriver driver){
        this.driver = driver;
        driver.get("https://www.simarksupplies.com/");
    }

    public boolean isLoggedIn(){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return driver.findElement(By.className("BannerSideLink")).getText().contains("Logout");
    }

    public boolean isLoggedOut(){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return driver.findElement(By.className("BannerSideLink")).getText().contains("Login/Register");
    }

    public boolean isInRegistrationOrLogin(){ return regLogin; }

    public boolean isInProductDetails() { return prodDetails; }

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

    public boolean isInCheckout(){ return checkOut; }

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

    public void buyFromList_ViewCart(){
        List<WebElement> productList = driver.findElements(By.xpath("//*[contains(@id,'btn_Buy')]"));
        productList.get(0).click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.findElement(By.name("ctl00$ctl18$ctl00$ctl01")).click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    public void buyFromList_ViewCart_ModifyQty(){
        List<WebElement> qtyList = driver.findElements(By.className("Products_TextBox_Quantity"));
        qtyList.get(0).clear();
        qtyList.get(0).sendKeys("2");
        List<WebElement> productList = driver.findElements(By.xpath("//*[contains(@id,'btn_Buy')]"));
        productList.get(0).click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.findElement(By.name("ctl00$ctl18$ctl00$ctl01")).click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    public void goToCart(){
        if(driver.findElements(By.name("div_ShoppingCartSummary_ViewCartContainer")).size() != 1){
            driver.get("https://www.simarksupplies.com/ViewCart.aspx");
        } else {
            driver.findElement(By.name("ctl00$ctl18$ctl00$ctl01")).click();
            driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        }
    }
}
