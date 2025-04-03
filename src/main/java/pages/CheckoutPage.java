package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CheckoutPage extends BasePage {
    // Shadow DOM elements
    private final By shadowHost = By.tagName("checkout-page");
    private final By summarySection = By.cssSelector("div.summary-section");
    private final By productName = By.cssSelector("div.product-name");
    private final By currentPrice = By.cssSelector("div.current-price");
    private final By provider = By.cssSelector("div.provider-name");
    private final By policyStartDate = By.cssSelector("div.policy-start-date");
    private final By coveragePeriod = By.cssSelector("div.coverage-period");
    private final By datePickerInput = By.cssSelector("input.datepicker-input");
    private final By datePickerMinDate = By.cssSelector("button.min-date");
    private final By datePickerMaxDate = By.cssSelector("button.max-date");
    
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    
    public void waitForCheckoutPageToLoad() {
        waitForPageToLoad();
        Assert.assertTrue("Not on checkout page", getCurrentUrl().contains("/checkout/payment"));
    }
    
    public void verifyCheckoutSummary(String expectedProductName, String expectedPrice) {
        WebElement host = driver.findElement(shadowHost);
        
        // Get product name from summary
        WebElement productNameElement = shadowDOMHelper.findElementInShadowDom(host, "div.product-name");
        String actualProductName = productNameElement.getText().trim();
        
        // Get price from summary
        WebElement priceElement = shadowDOMHelper.findElementInShadowDom(host, "div.current-price");
        String actualPrice = priceElement.getText().trim();
        
        // Get provider
        WebElement providerElement = shadowDOMHelper.findElementInShadowDom(host, "div.provider-name");
        String actualProvider = providerElement.getText().trim();
        
        // Get policy start date
        WebElement startDateElement = shadowDOMHelper.findElementInShadowDom(host, "div.policy-start-date");
        String actualStartDate = startDateElement.getText().trim();
        
        // Get coverage period
        WebElement coveragePeriodElement = shadowDOMHelper.findElementInShadowDom(host, "div.coverage-period");
        String actualCoveragePeriod = coveragePeriodElement.getText().trim();
        
        // Verify product name
        Assert.assertEquals("Product name mismatch", expectedProductName, actualProductName);
        
        // Verify price
        Assert.assertTrue("Price mismatch", actualPrice.contains(expectedPrice));
        
        // Verify provider is ERGO
        Assert.assertTrue("Provider mismatch", 
                actualProvider.equals("ERGO") || actualProvider.equals("เออรโกประกันภัย"));
        
        // Verify policy start date is today's date by default
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String expectedDate = today.format(formatter);
        Assert.assertTrue("Policy start date is not today's date", actualStartDate.contains(expectedDate));
        
        // Verify coverage period is 12 months
        Assert.assertTrue("Coverage period is not 12 months", actualCoveragePeriod.contains("12 months"));
    }
    
    public void changePolicyStartDateToMaxAllowed() {
        WebElement host = driver.findElement(shadowHost);
        
        // Click on date input to open date picker
        WebElement dateInput = shadowDOMHelper.findElementInShadowDom(host, "input.datepicker-input");
        dateInput.click();
        
        // Select max date
        WebElement maxDateButton = shadowDOMHelper.findElementInShadowDom(host, "button.max-date");
        maxDateButton.click();
        
        // Wait for summary to update
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void changePolicyStartDateToMinAllowed() {
        WebElement host = driver.findElement(shadowHost);
        
        // Click on date input to open date picker
        WebElement dateInput = shadowDOMHelper.findElementInShadowDom(host, "input.datepicker-input");
        dateInput.click();
        
        // Select min date
        WebElement minDateButton = shadowDOMHelper.findElementInShadowDom(host, "button.min-date");
        minDateButton.click();
        
        // Wait for summary to update
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public String getCurrentPolicyStartDate() {
        WebElement host = driver.findElement(shadowHost);
        WebElement startDateElement = shadowDOMHelper.findElementInShadowDom(host, "div.policy-start-date");
        return startDateElement.getText().trim();
    }
    
    public void verifyPolicyStartDateChanged(String previousDate) {
        String currentDate = getCurrentPolicyStartDate();
        Assert.assertNotEquals("Policy start date did not change", previousDate, currentDate);
    }
}