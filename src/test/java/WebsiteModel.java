import enums.WebsiteStates;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import org.openqa.selenium.WebDriver;
import pageObjects.SsSystem;

import static java.lang.Thread.sleep;
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
        if (b) {
            systemUnderTest = new SsSystem(driver);
        }
        modelState = WebsiteStates.LOGGED_OUT_USER;
        loggedOut = true;
        loggedIn = false;
        regLogin = false;
        prodDetails = false;
        resultList = false;
        shopCart = false;
        checkOut = false;
    }

    // Transitions including guards
    public boolean isLoggedIn() {
        return getState().equals(WebsiteStates.LOGGED_OUT_USER);
    }
    public @Action void loginUser() throws Exception {
        // Update the SUT
        systemUnderTest.loggingIn();
        sleep(2);
        // Updating Model
        modelState = WebsiteStates.LOGGED_IN_USER;
        loggedOut = false;
        loggedIn = true;

        // Checking correspondence between the model and SUT
        assertEquals("The models's user login state does not match the SUT's user login state", loggedIn, systemUnderTest.isLoggedIn());
    }
}
