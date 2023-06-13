package testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/appFeatures"},
		glue = {"stepsdefinitions", "AppHooks"},
		plugin = {"pretty"},
		monochrome = true // If the monochrome option is set to false, then the console output is not as readable as it should be.
		
		)

public class MyTestRunner {
	
	
}
