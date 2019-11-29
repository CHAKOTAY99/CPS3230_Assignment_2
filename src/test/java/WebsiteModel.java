import enums.WebsiteStates;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.SsSystem;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class WebsiteModel implements FsmModel {

    WebDriver driver;
    // Linking with the SUT
    private SsSystem systemUnderTest;
    // State Variables
    private WebsiteStates modelState = WebsiteStates.LOGGED_OUT_USER;
    private boolean loggedOut = true;
    private boolean loggedIn = false;
    private boolean regLogin = false;
    private boolean prodDetails = false;
    private boolean resultList = false;
    private boolean shopCart = false;
    private boolean checkOut = false;

    public WebsiteModel(WebDriver driver) {
        this.driver = driver;
        systemUnderTest = new SsSystem(driver);
    }

    // Method Implementations
    public WebsiteStates getState() {
        return modelState;
    }

    public void reset(boolean b) {

        if(driver.findElement(By.className("BannerSideLink")).getText().contains("Logout")){
            driver.findElement(By.className("BannerSideLink")).click();
        }

        modelState = WebsiteStates.LOGGED_OUT_USER;
        loggedOut = true;
        loggedIn = false;
        regLogin = false;
        prodDetails = false;
        resultList = false;
        shopCart = false;
        checkOut = false;
        if (b) {
            systemUnderTest = new SsSystem(driver);
        }
    }


    // Transitions including guards
    public boolean loginUserGuard() { return getState().equals(WebsiteStates.LOGGED_OUT_USER); }

    @Action
    public void loginUser() {
        // Update the SUT
        systemUnderTest.loggingIn();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);


        // Updating Model
        modelState = WebsiteStates.LOGGED_IN_USER;
        loggedIn = true;
        loggedOut = false;

        // Checking correspondence between the model and SUT
        assertTrue("The model's user login state does not match the SUT's user login state", systemUnderTest.isLoggedIn());
        assertFalse("The model's user logout state does not match te SUT's user logout state", systemUnderTest.isLoggedOut());
    }

    public boolean logoutUserGuard() { return getState().equals(WebsiteStates.LOGGED_IN_USER); }
    @Action
    public void logoutUser() {
        // Update the SUT
        systemUnderTest.loggingOut();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        // Update Model
        modelState = WebsiteStates.LOGGED_OUT_USER;
        loggedIn = false;
        loggedOut = true;

        // Checking correspondence between the model and SUT
        assertFalse("The model's user login state does not match the SUT's user login state", systemUnderTest.isLoggedIn());
        assertTrue("The model's user logout state does not match the SUT's user logout state", systemUnderTest.isLoggedOut());
    }

    public boolean searchListGuard() { return getState().equals(WebsiteStates.LOGGED_OUT_USER) || getState().equals(WebsiteStates.LOGGED_IN_USER); }
    @Action
    public void searchList() {
        // Update the SUT
        systemUnderTest.searchProduct();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        // Update Model
        modelState = WebsiteStates.RESULT_LIST;
        resultList = true;

        // Checking correspondence between the model and the SUT
        assertTrue("The model's search state does not match the SUT's search state", systemUnderTest.isInResultsList());
        assertTrue("The model's user state does not match the SUT's user state", systemUnderTest.isLoggedOut() || systemUnderTest.isLoggedIn());
    }
}