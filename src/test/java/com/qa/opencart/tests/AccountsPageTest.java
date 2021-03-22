package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Error;

public class AccountsPageTest extends BaseTest {
	
	SoftAssert softAssert = new SoftAssert();
	
	@BeforeClass
	public void accSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void accPageTitleTest() {
		String title = accPage.getAccPageTitle();
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void accPageLogoTest() {
		Assert.assertTrue(accPage.isLogoExist());
	}
	
	@Test(priority = 3)
	public void accPageSectionsCountTest() {
		Assert.assertEquals(accPage.getAccountPageHeadersCount(), 
				Constants.ACC_PAGE_SECTIONS_COUNT, 
				Error.ACC_PAGE_SECTION_ERROR);
	}
	
	@Test(priority = 4)
	public void accPageSectionsTest() {
		List<String> actualAccSecList = accPage.getAccountPageHeadersList();
		System.out.println(actualAccSecList);
		Assert.assertEquals(actualAccSecList, Constants.expectedAccSecList());
	}
	
	@Test(priority = 5)
	public void searchTest() {
		searchResultPage = accPage.doSearch("macbook");
		Assert.assertTrue(searchResultPage.getProductResultsCount() > 0, Error.SEARCH_NOT_SUCCESSFUL);
	}
	
	@Test(priority = 6)
	public void selectProductTest() {
		searchResultPage = accPage.doSearch("macbook");
		productInfoPage = searchResultPage.selectProductFromResults("MacBook Pro");
		String actualHeader = productInfoPage.getProductHeaderText();
		softAssert.assertEquals(actualHeader, "MacBook Pro");
		softAssert.assertEquals(productInfoPage.getproductImagesCount(), Constants.ACC_PAGE_SECTIONS_COUNT);
		softAssert.assertAll();
	}

}
