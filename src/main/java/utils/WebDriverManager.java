package utils;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverManager {

	private static WebDriver driver;
		
		    
		    public static WebDriver getDriver()throws InterruptedException {
		        if (driver == null) {
		            initDriver();
		        }
		        return driver;
		    }
		    
		    public static void initDriver() throws InterruptedException{
		    	WebDriver driver;
				driver = new ChromeDriver();
				String url = "https://www.bolttech.co.th/en/fwd/car-insurance/ctpl-insurance?utm_source=fwd&utm_medium=genesis";
				System.setProperty("WebDriver.chrome.driver", "C:\\Users\\Yasmine.Mahfoudi\\eclipse-workspace\\bolttech_automation_QA_Challenge\\src\\test\\resources\\chromedriver");
				ChromeOptions option = new ChromeOptions();
				option.addArguments("headless");		
				driver.get(url);
				Thread.sleep(5000);
				driver.manage().window().fullscreen();
				driver.manage().window().maximize();
				Thread.sleep(8000);
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				WebElement acceptButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[normalize-space()='Accept All Cookies'])[1]")));

		        // Click on the "Accept" button
		        acceptButton.click();
		        Thread.sleep(8000);
		        
		    }
		    
		    public static void quitDriver() {
		        if (driver != null) {
		            driver.quit();
		            driver = null;
		        }		    
		

	}

}
