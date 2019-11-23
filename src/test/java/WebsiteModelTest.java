import cucumber.api.java.After;
import cucumber.api.java.Before;
import enums.WebsiteStates;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.ssPageObject;

import java.util.Random;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class WebsiteModelTest implements FsmModel {
    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/dev/trees/CPS3230_Assignment_2/additionalFiles/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    // Test runner
    @Test
    public void WebsiteModelTestRunner() {
        final GreedyTester tester = new GreedyTester(new WebsiteModelTest(driver)); //Creates a test generator that can generate random walks. A greedy random walk gives preference to transitions that have never been taken before. Once all transitions out of a state have been taken, it behaves the same as a random walk.
        tester.setRandom(new Random()); //Allows for a random path each time the model is run.
        tester.buildGraph(); //Builds a model of our FSM to ensure that the coverage metrics are correct.
        tester.addListener(new StopOnFailureListener()); //This listener forces the test class to stop running as soon as a failure is encountered in the model.
        tester.addListener("verbose"); //This gives you printed statements of the transitions being performed along with the source and destination states.
        tester.addCoverageMetric(new TransitionPairCoverage()); //Records the transition pair coverage i.e. the number of paired transitions traversed during the execution of the test.
        tester.addCoverageMetric(new StateCoverage()); //Records the state coverage i.e. the number of states which have been visited during the execution of the test.
        tester.addCoverageMetric(new ActionCoverage()); //Records the number of @Action methods which have ben executed during the execution of the test.
        tester.generate(500); //Generates 500 transitions
        tester.printCoverage(); //Prints the coverage metrics specified above.
    }
    // Linking with the SUT
    private ssPageObject systemUnderTest = new ssPageObject();

    // State Variables
    private WebsiteStates modelWebsite = WebsiteStates.LOGGED_OUT_USER;
    private boolean loggedOut = true, loggedIn = false, regLogin = false, prodDetails = false, resultList = false, shopCart = false, checkOut = false;


    // Method Implementations
    public WebsiteStates getState() { return modelWebsite; }

    public void reset(boolean b) {
        if (b){
            systemUnderTest = new ssPageObject();
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

    // Transitions including guards
    public boolean userLoggedOff() { return getState().equals(WebsiteStates.LOGGED_OUT_USER); }
    public @Action void loginUser() throws Exception {
        // Update SUT
        systemUnderTest.get();
        systemUnderTest.loginUser("testingcps3230", "what is the time69");

        sleep(2);

        // Update Model
        modelWebsite = WebsiteStates.LOGGED_IN_USER;
        loggedIn = true;
        loggedOut = false;

        // Check correspondence between model and SUT
        assertEquals("The SUT's user does not match the model's user after logging in", loggedIn,  systemUnderTest.validateUser_valid());
    }
}
