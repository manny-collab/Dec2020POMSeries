package com.qa.opencart.tests;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Story("OC-10: Design the login page for demo opencart application with login, title and forgot pwd link")
@Epic("Epic 1: Design login page")
public class LoginPageTest extends BaseTest {
	
	@Description("login page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login page title: " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("Forgot password link test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkDisplayed());
	}
	
	@Description("Login test with correct credentials")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccPageTitle(), Constants.ACCOUNT_PAGE_TITLE);
	}

}
