package stepDefinitions;

	import pages.InsurancePlanPage;
	import utils.WebDriverManager;
	import io.cucumber.java.en.Given;
	import io.cucumber.java.en.Then;
	import io.cucumber.java.en.When;
	import org.openqa.selenium.WebDriver;

	public class InsurancePlanSteps {

	    private WebDriver driver;
	    private InsurancePlanPage insurancePlanPage;
	    private String selectedPlanInfo;

	    public InsurancePlanSteps() {
	        try {
	            driver = WebDriverManager.getDriver();
	            insurancePlanPage = new InsurancePlanPage(driver);
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt(); 
	            throw new RuntimeException("Failed to initialize WebDriver", e);
	        }
	    }

	    @Given("I access the car CTPL insurance website")
	    public void iAccessTheCarCTPLInsuranceWebsite() {
	        insurancePlanPage.navigateToInsurancePlanPage();
	    }

	    @Then("I verify that the card prices match the table prices")
	    public void verifyThatTheCardPricesMatchTheTablePrices() {
	        insurancePlanPage.verifyCardAndTablePricesMatch();
	    }

	    @When("I select a random insurance plan")
	    public void iSelectARandomInsurancePlan() {
	        selectedPlanInfo = insurancePlanPage.selectRandomPlan();
	    }

	    @When("I select the insurance plan {string}")
	    public void iSelectTheInsurancePlan(String planName) {
	        selectedPlanInfo = insurancePlanPage.selectSpecificPlan(planName);
	    }

	    public String getSelectedPlanInfo() {
	        return selectedPlanInfo;
	    }
	}