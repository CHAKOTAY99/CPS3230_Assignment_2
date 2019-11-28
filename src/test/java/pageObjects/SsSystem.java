package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

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
        return driver.findElement(By.className("BannerSideLink")).getText().contains("Logout");
    }

    public boolean isLoggedOut(){
        return driver.findElement(By.className("BannerSideLink")).getText().contains("Login/Register");
    }

    public boolean isInRegistrationOrLogin(){ return regLogin; }

    public boolean isInProductDetails(){ return prodDetails; }

    public boolean isInResultsList(){ return resultList; }

    public boolean isInShoppingCart(){ return shopCart; }

    public boolean isInCheckout(){ return checkOut; }

    public void loggingIn() throws Exception {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.className("BannerSideLink")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.id("ctl00_MainContent_txt_UserName")).sendKeys("testingcps3230");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.id("ctl00_MainContent_txt_Password")).sendKeys("what is the time69");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.id("ctl00_MainContent_btn_Login")).click();
    }

    public void loggingOut() throws Exception {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.className("BannerSideLink")).click();
    }
}