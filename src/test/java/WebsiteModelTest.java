import nz.ac.waikato.modeljunit.*;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.SsSystem;

import java.io.FileNotFoundException;
import java.util.Random;

public class WebsiteModelTest {

    WebDriver driver;


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
//        final GreedyTester tester = new GreedyTester(new WebsiteModel(driver));
        final Tester tester = new LookaheadTester(new WebsiteModel(driver));
//        final Tester tester = new RandomTester(new WebsiteModel(driver));
        tester.setRandom(new Random());
//        tester.buildGraph(); - keep turned off for LookaheadTester
        tester.addListener(new StopOnFailureListener());
        tester.addListener("verbose");
        tester.addCoverageMetric(new TransitionPairCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());
        tester.generate(1000);
        tester.printCoverage();
    }
}