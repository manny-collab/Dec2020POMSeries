package com.qa.opencart.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest{
	
	private SoftAssert softAssert = new SoftAssert();
	
	@BeforeClass
	public void accSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void productInfoDataTest() {
		searchResultPage = accPage.doSearch("macbook");
		productInfoPage = searchResultPage.selectProductFromResults("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInformation();
		softAssert.assertTrue(actProductInfoMap.get("name").equals("MacBook Pro"));
		softAssert.assertTrue(actProductInfoMap.get("Brand").equals("Apple"));
		softAssert.assertTrue(actProductInfoMap.get("price").equals("$2,000.00"));
		softAssert.assertAll();
	}
	
	@Test(priority = 2)
	public void addToCartTest() {
		searchResultPage = accPage.doSearch("macbook");
		productInfoPage = searchResultPage.selectProductFromResults("MacBook Pro");
		// productInfoPage.selectQuantity("2");
		productInfoPage.addToCart();
		Assert.assertTrue(productInfoPage.getSuccessMessage().contains("MacBook"));
	}

}
