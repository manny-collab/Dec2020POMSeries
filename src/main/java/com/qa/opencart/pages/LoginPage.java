package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private ElementUtil elementUtil;
	private WebDriver driver;
	
	// page objects -> By locators (object repository)
	
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By forgotPwd = By.xpath("//div[@class='form-group'][2]/a[text()='Forgotten Password']");
	private By registerLink = By.linkText("Register");
	
	// Constructor
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	// page actions 
	
	@Step("Getting login page title")
	public String getLoginPageTitle() {
		return elementUtil.waitForTitleExact(5, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Step("Checking forgot password link")
	public boolean isForgotPwdLinkDisplayed() {
		return elementUtil.doIsDisplayed(forgotPwd);
	}
	
	@Step("Login with username: {0} and password: {1}")
	public AccountsPage doLogin(String uname, String pwd) {
		System.out.println("Login with: " + uname + " " + pwd);
		elementUtil.doSendKeys(username, "m.deep2727@gmail.com");
		elementUtil.doSendKeys(password, "7890Plpl");
		elementUtil.doClick(loginButton);
		return new AccountsPage(driver); // page chaining as this method will land to a diff page
	}
	
	@Step("Navigating to register page")
	public RegisterPage navigateToRegisterPage() {
		elementUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
}
