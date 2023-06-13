package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;

	/*
	 * 1. By Locators Here we are going to use the concept of encapsulation, as our
	 * locators will be private and page actions or page methods will be public in
	 * nature Every Page class will be having there own private WebDriver and we
	 * will be creating reference of this WebDriver, so that we can hold the driver
	 * in this particular variable Page class should not have any assertion.
	 * 
	 */

	private By emailId = By.id("username");
	private By passwordField = By.id("password");
	private By signInButton = By.xpath("//button[@type='submit']//i");

	// 2. Constructor of the page class

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * 3. page actions: features (behaviour) of the page the form of methods: Page
	 * class should not have any assertion. Assertion should be written in the test
	 * class or the step definition class
	 * 
	 */
	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	public void enterUserName(String userName) {
		driver.findElement(emailId).sendKeys(userName);
	}
	
	public void enterPassword(String password) {
		driver.findElement(passwordField).sendKeys(password);
	}
	
	public void clickOnLogin() {
		driver.findElement(signInButton).click();
	}

}
