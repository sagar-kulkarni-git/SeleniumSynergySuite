Feature: Login Feature

Scenario: Login page title
Given user is on the login page
When user gets the title of the page
Then page title should be "The Internet"

Scenario: Login with valid credentials
Given user is on the login page
When I enter the user name "tomsmith"
And I enter the password "SuperSecretPassword!"
And I click on the login button