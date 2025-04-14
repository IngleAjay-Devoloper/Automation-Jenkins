
#Author:Ajay Ingle
#@Regression
Feature: Verify Functionality of Login Page

	@Login
	Scenario Outline: Login to Amazon
		Given Open Amazon
		When Click on SignIn Button
		Then Enter Email and Click on Continue Button<Username>
		And Enter Password and Click on Sign Button<Password>
	

		Examples:
		| Username            | Password   |
		| abingle27@gmail.com | Ingle@1997 | 