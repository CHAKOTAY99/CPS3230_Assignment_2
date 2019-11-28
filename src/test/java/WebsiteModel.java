import enums.WebsiteStates;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.SsSystem;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

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
    public void loginUser() throws Exception {
        // Update the SUT
        systemUnderTest.loggingIn();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);


        // Updating Model
        modelState = WebsiteStates.LOGGED_IN_USER;
        loggedOut = false;
        loggedIn = true;

        // Checking correspondence between the model and SUT
        assertEquals("The model's user login state does not match the SUT's user login state", loggedIn, systemUnderTest.isLoggedIn());
        assertEquals("The model's user logout state does not match te SUT's user logout state", loggedOut, systemUnderTest.isLoggedOut());
    }

    public boolean logoutUserGuard() { return getState().equals(WebsiteStates.LOGGED_IN_USER); }
    @Action
    public void logoutUser() throws Exception {
        // Update the SUT
        systemUnderTest.loggingOut();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        // Update Model
        modelState = WebsiteStates.LOGGED_OUT_USER;
        loggedOut = true;
        loggedIn = false;

        // Checking correspondence between the model and SUT
        assertEquals("The model's user login state does not match the SUT's user logout state", loggedIn, systemUnderTest.isLoggedIn());
        assertEquals("The model's user logout state does not match te SUT's user logout state", loggedOut, systemUnderTest.isLoggedOut());
    }
}
