package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features",
                glue={"org.CircleHealth.stepdefs"},
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"},
        monochrome = true,
        publish = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
