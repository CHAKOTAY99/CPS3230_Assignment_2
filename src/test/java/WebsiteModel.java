import enums.WebsiteStates;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import org.openqa.selenium.WebDriver;
import pageObjects.SsSystem;

import static org.junit.Assert.assertEquals;

public class WebsiteModel implements FsmModel {

    WebDriver driver;
    // Linking with the SUT
    private SsSystem systemUnderTest;
    // State Variables
    private WebsiteStates modelWebsite = WebsiteStates.LOGGED_OUT_USER;
    private boolean loggedOut = true, loggedIn = false, regLogin = false,
            prodDetails = false, resultList = false, shopCart = false, checkOut = false;

    public WebsiteModel(WebDriver driver) {
        this.driver = driver;
        systemUnderTest = new SsSystem(driver);
    }

    // Method Implementations
    public WebsiteStates getState() {
        return modelWebsite;
    }

    public void reset(boolean b) {
        if (b) {
            systemUnderTest = new SsSystem(driver);
        }
        modelWebsite = WebsiteStates.LOGGED_OUT_USER;
        loggedOut = true;
        loggedIn = false;
        regLogin = false;
        prodDetails = false;
        resultList = false;
        shopCart = false;
        checkOut = false;
    }
}
