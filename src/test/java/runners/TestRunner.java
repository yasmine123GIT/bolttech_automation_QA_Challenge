package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import utils.WebDriverManager;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"bolttech_automation_QA_Challenge.stepDefinitions"},
    plugin = {"pretty", "html:target/cucumber-reports"}
)
public class TestRunner {
    @BeforeClass
    public static void setup()throws InterruptedException {
        WebDriverManager.initDriver();
    }
    
    @AfterClass
    public static void tearDown() {
        WebDriverManager.quitDriver();
    }
}