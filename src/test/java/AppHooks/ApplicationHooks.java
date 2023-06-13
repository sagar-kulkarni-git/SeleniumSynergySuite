package AppHooks;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/*We need certain preconditions over there where who will enter the URL, who will launch the browser.
 * Before and After annotations we have to use here
 * This is just like base test class that we create in TestNG where pre-annotations and post annotations we can write.
 * Before every scenario, this application hook @before and will be executed before scenario and @after will be executed after scenario
 * This is the feature of hooks; We have to launch the browser and enter the URL.
 * Its hooks responsibility to launch the browser.
 * Which class is responsible to launch the browser -> DriverFactory class which has init_driver method, where we need particular driver factory class object, so that we can call this init_driver method
*/

public class ApplicationHooks {

	private DriverFactory driverFactory;

	/*
	 * WebDriver and driver are specific to hooks only for this particular class and
	 * we will create some public wrappers. We will give the access via public
	 * wrappers.
	 */
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;

	/*
	 * First annotation is to launch the browser, who will launch the browser?
	 * browser is available in the config.properties; now we have to read this
	 * particular property for that we are creating below application hook
	 */

	@Before(order = 0) // import before from io.cucumber.java

	public void getProperty() {
		configReader = new ConfigReader();

		/*
		 * This will give one properties class reference; thats why we declared
		 * Properties prop above. prop will behave holding parameter for us.
		 */
		prop = configReader.init_prop();
	}

	@Before(order = 1)

	public void LaunchBrowser() {
		String browserName = prop.getProperty("browser"); // key is the browser which is mentioned in the
															// config.properties
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(browserName);

	}

	/*
	 * with before order 0 will run 1st and order 1 will run 2nd with after order 1
	 * will run 1st and order 0 will run 2nd
	 */

	@After(order = 0)
	public void quitBrowser() {
		driver.quit();

	}

	/*
	 * Below method is to take the screenshot if any scenario is missing here we are
	 * going to use concept of scenario object
	 */

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			// Take Screenshot

			String screenshotName = scenario.getName().replaceAll(" ", "_");

			/*
			 * logic: we have to convert the driver into typecast into take screenshot
			 * interface //(TakeScreenshot) and convert driver into this //import OutputType
			 * from selenium; it will take the screenshot and save it under sourcePath. Take
			 * Output type as Bytes not file as when we run it on jenkins or ci/cd, it won't
			 * take output file as Files
			 */

			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
		}

	}

}
