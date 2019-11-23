import enums.WebsiteStates;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import pageObjects.ssPageObject;

public class WebsiteModelTest implements FsmModel {
    // Linking with the SUT
    private ssPageObject systemUnderTest = new ssPageObject();

    // State Variables
    private WebsiteStates modelWebsite = WebsiteStates.LOGGED_OUT_USER;
    private boolean loggedIn = false, regLogin = false, prodDetails = false, resultList = false, shopCart = false, checkOut = false;

    // Method Implementations
    public WebsiteStates getState() { return modelWebsite; }

    public void reset(boolean b) {
        if (b){
            systemUnderTest = new ssPageObject();
        }
        modelWebsite = WebsiteStates.LOGGED_OUT_USER;
        loggedIn = false;
        regLogin = false;
        prodDetails = false;
        resultList = false;
        shopCart = false;
        checkOut = false;
    }

    // Transitions including guards
    public boolean userLoggedOff() { return getState().equals(WebsiteStates.LOGGED_OUT_USER); }
    public @Action void loginUser() {
        // Update SUT
        //systemUnderTest.loginUser();
    }
}
