package stepsdefinitions;

import org.junit.Assert;

import com.pages.LoginPage;
import com.qa.factory.DriverFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	/*
	 * When we create object of LoginPage class. But the LoginPage is expecting the
	 * constructor and if we go to the LoginPage, the constructor says give me the
	 * driver
	 */

	private static String title;
	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

	@Given("user is on the login page")
	public void user_is_on_the_login_page() {
		DriverFactory.getDriver()
				.get("http://the-internet.herokuapp.com/login");
	}

	@When("user gets the title of the page")
	public void user_gets_the_title_of_the_page() {
		title = loginPage.getLoginPageTitle();
		System.out.println("login page title is : " + title);
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String expectedTitle) {
		Assert.assertTrue(title.contains(expectedTitle));
	}

	@When("I enter the user name {string}")
	public void i_enter_the_user_name(String userName) {
		loginPage.enterUserName(userName);

	}

	@When("I enter the password {string}")
	public void i_enter_the_password(String password) {
		loginPage.enterPassword(password);

	}

	@When("I click on the login button")
	public void i_click_on_the_login_button() {
		loginPage.clickOnLogin();
	}

}
