package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

    public boolean isLoggedIn(){ return loggedIn; }

    public boolean isLoggedOut(){ return loggedOut; }

    public boolean isInRegistrationOrLogin(){ return regLogin; }

    public boolean isInProductDetails(){ return prodDetails; }

    public boolean isInResultsList(){ return resultList; }

    public boolean isInShoppingCart(){ return shopCart; }

    public boolean isInCheckout(){ return checkOut; }

    public void loggingIn() throws Exception{
        if(!loggedIn && loggedOut){
            sleep(2);
            driver.findElement(By.className("BannerSideLink")).click();
            sleep(2);
            driver.findElement(By.id("ctl00_MainContent_txt_UserName")).sendKeys("testingcps3230");
            driver.findElement(By.id("ctl00_MainContent_txt_Password")).sendKeys("what is the time69");
            driver.findElement(By.id("ctl00_MainContent_btn_Login")).click();
            sleep(2);
            loggedIn = true;
            loggedOut = false;
        } else {
            throw new IllegalStateException();
        }
    }
}
