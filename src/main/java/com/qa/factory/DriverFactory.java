package com.qa.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	/*
	 * Below WebDriver will be public so that it can be accessed by DriverFactory
	 */

	public WebDriver driver;

	/*
	 * We are going to use the concept of Thread Local for parallel run; This is
	 * Java 8 feature. This is ThreadLocal instance and type of generic is WebDriver
	 * mentioned in <WebDriver>, because we have to generate WebDriver with thread
	 * local. tlDriver is reference name
	 */

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/*
	 * Create a method to initiaize the driver on the basis of passed browser
	 * In below function we need to return WebDriver hence removed void and placed WebDriver
	 */

	public WebDriver init_driver(String browser) {

		System.out.println("browser value is : " + browser);
		
		/*
		 * ThreadLocal will give 2 methods set() and get(); here object of the new
		 * ChromeDriver will be created and it will be set with tlDriver. This tlDriver
		 * will create local copy for the specific thread over there. tlDriver is having
		 * instance of <WebDriver>
		 */

		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());
		} 
		else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
		} 
		else if (browser.equals("safari")) {
			tlDriver.set(new SafariDriver());
		} 
		else {
			System.out.println("Enter the valid browser");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		
		
		return getDriver();
	}
	
	/*
	 * Method to get the driver; this method will return the WebDriver reference
	 * When someone will call the below method it will return the thread local
	 * instance where tlDriver we have already initialised based on the requested
	 * browser. Why did we add a synchronized keyword? When we are running in parallel
	 * execution with 5 threads and running together and they all will be calling
	 * this getDriver() function, so all the threads should be in sync
	 */
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
		
	}
}
