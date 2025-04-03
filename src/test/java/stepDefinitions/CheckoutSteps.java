package stepDefinitions;

import pages.CheckoutPage;
import utils.WebDriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class CheckoutSteps {
    private WebDriver driver = WebDriverManager.getDriver();
    private CheckoutPage checkoutPage = new CheckoutPage(driver);
    private InsurancePlanSteps insurancePlanSteps;
    private String originalPolicyStartDate;
    
    public CheckoutSteps(InsurancePlanSteps insurancePlanSteps) throws InterruptedException {
        this.insurancePlanSteps = insurancePlanSteps;
    }
    
    @Then("I verify I am on the checkout payment page")
    public void iVerifyIAmOnTheCheckoutPaymentPage() {
        checkoutPage.waitForCheckoutPageToLoad();
    }
    
    @Then("I verify checkout summary information")
    public void iVerifyCheckoutSummaryInformation() {
        String selectedPlanInfo = insurancePlanSteps.getSelectedPlanInfo();
        String[] parts = selectedPlanInfo.split(":");
        String productName = parts[0];
        String price = parts[1];
        
        checkoutPage.verifyCheckoutSummary(productName, price);
        originalPolicyStartDate = checkoutPage.getCurrentPolicyStartDate();
    }
    
    @When("I change policy start date to maximum allowed")
    public void iChangePolicyStartDateToMaximumAllowed() {
        checkoutPage.changePolicyStartDateToMaxAllowed();
    }
    
    @Then("I verify policy start date changed")
    public void iVerifyPolicyStartDateChanged() {
        checkoutPage.verifyPolicyStartDateChanged(originalPolicyStartDate);
        originalPolicyStartDate = checkoutPage.getCurrentPolicyStartDate();
    }
    
    @When("I change policy start date to minimum allowed")
    public void iChangePolicyStartDateToMinimumAllowed() {
        checkoutPage.changePolicyStartDateToMinAllowed();
    }
}