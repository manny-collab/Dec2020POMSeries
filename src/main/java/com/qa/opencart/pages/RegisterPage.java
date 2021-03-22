package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPwd = By.id("input-confirm");
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[1]/input");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[2]/input");
	private By agreeCheckbox = By.name("agree");
	private By continueButton = By.xpath("//input[@value='Continue']");
	private By successMesg = By.xpath("//div[@id='content']/h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public boolean accountRegistration(String firstName, String lastName, String email, 
			String telephone, String password, String subscribe) {
		
		elementUtil.doSendKeys(this.firstName, firstName);
		elementUtil.doSendKeys(this.lastName, lastName);
		elementUtil.doSendKeys(this.email, email);
		elementUtil.doSendKeys(this.telephone, telephone);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.confirmPwd, password);
		
		if(subscribe.equals("yes")) {
			elementUtil.doClick(this.subscribeYes);
		} 
		else {
			elementUtil.doClick(this.subscribeNo);
		}
		
		elementUtil.doClick(this.agreeCheckbox);
		elementUtil.doClick(this.continueButton);
		elementUtil.waitForPresenceOfElement(this.successMesg, 5000);
		
		String message = elementUtil.doGetText(this.successMesg);
		if(message.contains(Constants.ACC_CREATION_SUCCESS_MESG)) {
			
			elementUtil.doClick(logoutLink);
			elementUtil.doClick(registerLink);
			
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	

}
