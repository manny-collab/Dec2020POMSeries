package com.qa.opencart.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void setupRegister() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		Object[][] data = ExcelUtil.getTestData(Constants.REGISTER_SHEET);
		return data;
	}
	
	@Test(dataProvider = "getRegisterData")
	public void userRegisterTest(String firstName, String lastName, String email, 
			String telephone, String password, String subscribe) {
		registerPage.accountRegistration(firstName, lastName, email, telephone, password, subscribe);
	}

}
