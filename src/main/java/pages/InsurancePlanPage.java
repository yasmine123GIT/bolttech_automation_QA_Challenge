package pages;

import utils.ShadowDOMHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Assert;

import java.util.*;
import java.util.stream.Collectors;

public class InsurancePlanPage extends BasePage {
    private final By planCardsContainer = By.cssSelector("div.insurance-cards");
    private final By planCards = By.cssSelector("div.card");
    private final By planCardPrice = By.cssSelector("span.green-price");
    private final By planCardTitle = By.cssSelector("div.card-title");
    private final By planTable = By.cssSelector("table.price-table");
    private final By tableRows = By.cssSelector("tr");
    private final By planSelectButton = By.cssSelector("button.select-plan");
    
    public InsurancePlanPage(WebDriver driver) {
        super(driver);
    }
    
    public void navigateToInsurancePlanPage() {
        driver.get("https://bolttech.co.th/car-insurance/ctpl");
        waitForPageToLoad();
    }
    
    public Map<String, String> getAllCardPrices() {
        Map<String, String> cardPrices = new HashMap<>();
        List<WebElement> cards = driver.findElements(planCards);
        
        for (WebElement card : cards) {
            String title = card.findElement(planCardTitle).getText();
            String price = card.findElement(planCardPrice).getText().trim();
            cardPrices.put(title, price);
        }
        
        return cardPrices;
    }
    
    public Map<String, String> getAllTablePrices() {
        Map<String, String> tablePrices = new HashMap<>();
        WebElement table = driver.findElement(planTable);
        List<WebElement> rows = table.findElements(tableRows);
        
        // Skip header row
        for (int i = 1; i < rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            String title = cells.get(0).getText().trim();
            String price = cells.get(cells.size() - 1).getText().trim();
            tablePrices.put(title, price);
        }
        
        return tablePrices;
    }
    
    public void verifyCardAndTablePricesMatch() {
        Map<String, String> cardPrices = getAllCardPrices();
        Map<String, String> tablePrices = getAllTablePrices();
        
        for (Map.Entry<String, String> entry : cardPrices.entrySet()) {
            String cardTitle = entry.getKey();
            String cardPrice = entry.getValue();
            String tablePrice = tablePrices.get(cardTitle);
            
            Assert.assertNotNull("No matching price found in table for card: " + cardTitle, tablePrice);
            Assert.assertEquals("Price mismatch for plan: " + cardTitle, cardPrice, tablePrice);
        }
    }
    
    public String selectRandomPlan() {
        List<WebElement> cards = driver.findElements(planCards);
        int randomIndex = new Random().nextInt(cards.size());
        WebElement selectedCard = cards.get(randomIndex);
        
        String planTitle = selectedCard.findElement(planCardTitle).getText();
        String planPrice = selectedCard.findElement(planCardPrice).getText();
        
        WebElement selectButton = selectedCard.findElement(planSelectButton);
        scrollToElement(selectButton);
        selectButton.click();
        
        waitForPageToLoad();
        return planTitle + ":" + planPrice;
    }
    
    public String selectSpecificPlan(String planName) {
        List<WebElement> cards = driver.findElements(planCards);
        WebElement selectedCard = null;
        
        for (WebElement card : cards) {
            String title = card.findElement(planCardTitle).getText();
            if (title.contains(planName)) {
                selectedCard = card;
                break;
            }
        }
        
        if (selectedCard == null) {
            throw new NoSuchElementException("Plan with name " + planName + " not found");
        }
        
        String planTitle = selectedCard.findElement(planCardTitle).getText();
        String planPrice = selectedCard.findElement(planCardPrice).getText();
        
        WebElement selectButton = selectedCard.findElement(planSelectButton);
        scrollToElement(selectButton);
        selectButton.click();
        
        waitForPageToLoad();
        return planTitle + ":" + planPrice;
    }
}
