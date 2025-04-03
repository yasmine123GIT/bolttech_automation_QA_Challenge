package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShadowDOMHelper {
    private final WebDriver driver;
    private final JavascriptExecutor jsExecutor;

    public ShadowDOMHelper(WebDriver driver) {
        this.driver = driver;
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    public WebElement getShadowRoot(WebElement element) {
        return (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot", element);
    }

    public WebElement findElementInShadowDom(WebElement shadowHost, String cssSelector) {
        WebElement shadowRoot = getShadowRoot(shadowHost);
        return (WebElement) jsExecutor.executeScript(
                "return arguments[0].querySelector('" + cssSelector + "')", shadowRoot);
    }

    public WebElement findNestedShadowElement(WebElement shadowHost, String... cssSelectors) {
        WebElement currentElement = shadowHost;
        WebElement shadowRoot = getShadowRoot(currentElement);
        
        for (int i = 0; i < cssSelectors.length - 1; i++) {
            currentElement = (WebElement) jsExecutor.executeScript(
                    "return arguments[0].querySelector('" + cssSelectors[i] + "')", shadowRoot);
            shadowRoot = getShadowRoot(currentElement);
        }
        
        return (WebElement) jsExecutor.executeScript(
                "return arguments[0].querySelector('" + cssSelectors[cssSelectors.length - 1] + "')", shadowRoot);
    }
}
