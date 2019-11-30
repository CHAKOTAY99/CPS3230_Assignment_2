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

        while(driver.findElements(By.className("div_ShoppingCartSummary_NoItemsMsg")).size() != 1){
            driver.findElement(By.className("cell_ShoppingCartSummary_RemoveContainer")).click();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }

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
    public boolean loginUserGuard() {
        return systemUnderTest.isLoggedOut() && (getState().equals(WebsiteStates.LOGGED_OUT_USER) ||
                getState().equals(WebsiteStates.RESULT_LIST) || getState().equals(WebsiteStates.SHOPPING_CART));
    }
    @Action
    public void loginUser() {
        // Update the SUT
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        systemUnderTest.loggingIn();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);


        // Updating Model
        modelState = WebsiteStates.LOGGED_IN_USER;
        loggedIn = true;
        loggedOut = false;

        // Checking correspondence between the model and SUT
        assertTrue("The model's user login state does not match the SUT's user login state", systemUnderTest.isLoggedIn());
        assertFalse("The model's user logout state does not match te SUT's user logout state", systemUnderTest.isLoggedOut() && systemUnderTest.isInResultsList() && systemUnderTest.isInHeadingResultsList() && systemUnderTest.isInShoppingCart());
    }

    public boolean logoutUserGuard() {
        return systemUnderTest.isLoggedIn() && (getState().equals(WebsiteStates.LOGGED_IN_USER) || getState().equals(WebsiteStates.RESULT_LIST)
        || getState().equals(WebsiteStates.SHOPPING_CART));
    }
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

    public boolean searchListGuard() { return getState().equals(WebsiteStates.LOGGED_OUT_USER) || getState().equals(WebsiteStates.LOGGED_IN_USER) || getState().equals(WebsiteStates.SHOPPING_CART) || getState().equals(WebsiteStates.RESULT_LIST); }
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
        assertFalse("The model's state should not match the SUT's state", systemUnderTest.isInShoppingCart());
    }

    public boolean searchCategoryGuard() { return getState().equals(WebsiteStates.LOGGED_OUT_USER) || getState().equals(WebsiteStates.LOGGED_IN_USER) || getState().equals(WebsiteStates.SHOPPING_CART) || getState().equals(WebsiteStates.RESULT_LIST); }
    @Action
    public void searchCategory(){
        // Update the SUT
        systemUnderTest.searchCategory();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        // Update Model
        modelState = WebsiteStates.RESULT_LIST;
        resultList = true;

        // Checking correspondence between the model and the SUT
        assertTrue("The model's result list state does not match the SUT's result list state", systemUnderTest.isInResultsList());
        assertTrue("The model's user state does not match the SUT's user state", systemUnderTest.isLoggedOut() || systemUnderTest.isLoggedIn());
        assertFalse("The model's state should not match the SUT's state", systemUnderTest.isInShoppingCart() && systemUnderTest.isInHeadingResultsList());
    }

    public boolean searchProductHeading_SystemsGuard() { return getState().equals(WebsiteStates.LOGGED_OUT_USER) || getState().equals(WebsiteStates.LOGGED_IN_USER) || getState().equals(WebsiteStates.SHOPPING_CART) || getState().equals(WebsiteStates.RESULT_LIST); }
    @Action
    public void searchProductHeading_Systems() {
        // Update the SUT
        systemUnderTest.searchProductHeading(3);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        // Update Model
        modelState = WebsiteStates.RESULT_LIST;
        resultList = true;

        // Checking correspondence between the model and the SUT
        assertTrue("The model's result list state does not match the SUT's result list state", systemUnderTest.isInResultsList());
        assertTrue("The model's user state does not match the SUT's user state", systemUnderTest.isLoggedOut() || systemUnderTest.isLoggedIn());
        assertFalse("The model's state should not match the SUT's state", systemUnderTest.isInShoppingCart() && systemUnderTest.isInHeadingResultsList());
    }

    public boolean searchProductHeading_NotebookGuard() { return getState().equals(WebsiteStates.LOGGED_OUT_USER) || getState().equals(WebsiteStates.LOGGED_IN_USER) || getState().equals(WebsiteStates.SHOPPING_CART) || getState().equals(WebsiteStates.RESULT_LIST); }
    @Action
    public void searchProductHeading_Notebook(){
        // Update the SUT
        systemUnderTest.searchProductHeading(4);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        // Update Model
        modelState = WebsiteStates.RESULT_LIST;
        resultList = true;

        // Checking correspondence between the model and the SUT
        assertTrue("The model's result list state does not match the SUT's result list state", systemUnderTest.isInResultsList());
        assertTrue("The model's user state does not match the SUT's user state", systemUnderTest.isLoggedOut() || systemUnderTest.isLoggedIn());
        assertFalse("The model's state should not match the SUT's state", systemUnderTest.isInShoppingCart() && systemUnderTest.isInHeadingResultsList());
    }

    public boolean searchNewProductHeadingGuard() { return getState().equals(WebsiteStates.LOGGED_OUT_USER) || getState().equals(WebsiteStates.LOGGED_IN_USER) || getState().equals(WebsiteStates.SHOPPING_CART) || getState().equals(WebsiteStates.RESULT_LIST); }
    @Action
    public void searchNewProductHeading(){
        // Update the SUT
        systemUnderTest.searchProductHeading(1);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        // Update Model
        modelState = WebsiteStates.RESULT_LIST;
        resultList = true;

        // Checking correspondence between the model and the SUT
        assertTrue("The model's result list state does not match the SUT's result list state", systemUnderTest.isInHeadingResultsList());
        assertTrue("The model's user state does not match the SUT's user state", systemUnderTest.isLoggedOut() || systemUnderTest.isLoggedIn());
        assertFalse("The model's state should not match the SUT's state", systemUnderTest.isInResultsList() && systemUnderTest.isInShoppingCart());
    }

    public boolean buyProduct_FromResultsListGuard() { return getState().equals(WebsiteStates.RESULT_LIST); }
    @Action
    public void buyProduct_FromResultsList() {
        // Update the SUT
        systemUnderTest.buyFromList_ViewCart();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        // Update Model
        modelState = WebsiteStates.SHOPPING_CART;
        resultList = false;
        shopCart = true;

        // Check correspondence between the model and the SUT
        assertTrue("The model's cart state does not match the SUT's cart state", systemUnderTest.isInShoppingCart());
        assertFalse("The model's result list state does not match the SUT's result list state", systemUnderTest.isInResultsList());
    }

    public boolean buyProduct_FromResultsList_ModifyQtyGuard() { return getState().equals(WebsiteStates.RESULT_LIST); }
    @Action
    public void buyProduct_FromResultsList_ModifyQty() {
        // Update the SUT
        systemUnderTest.buyFromList_ViewCart_ModifyQty();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        // Update Model
        modelState = WebsiteStates.SHOPPING_CART;
        resultList = false;
        shopCart = true;

        // Check correspondence between the model and the SUT
        assertTrue("The model's cart state does not match the SUT's cart state", systemUnderTest.isInShoppingCart());
        assertFalse("The model's result list state does not match the SUT's result list state", systemUnderTest.isInResultsList());
    }
}