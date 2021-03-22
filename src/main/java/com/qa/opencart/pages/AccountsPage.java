package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private ElementUtil elementUtil;
	private WebDriver driver;
	
	// Page Objects - By locators 
	
	private By logo = By.xpath("//div[@id='logo']");
	private By accHeaders = By.xpath("//div[@id='content']/h2");
	private By searchField = By.xpath("//input[@name='search']");
	private By searchButton = By.xpath("//span[@class='input-group-btn']");
	
	// Constructor
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	// page actions 
	
	public String getAccPageTitle() {
		return elementUtil.waitForTitleExact(5, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	public boolean isLogoExist() {
		return elementUtil.doIsDisplayed(logo);
	}
	
	public int getAccountPageHeadersCount() {
		return elementUtil.createWebElements(accHeaders).size();
	}
	
	public List<String> getAccountPageHeadersList() {
		List<WebElement> accList = elementUtil.createWebElements(accHeaders);
		List<String> accSectionList = new ArrayList<String>();
		for(WebElement e : accList) {
			accSectionList.add(e.getText());
		}
		return accSectionList;
	}
	
	public SearchResultPage doSearch(String productName) {
		elementUtil.doSendKeys(searchField, productName);
		elementUtil.doClick(searchButton);
		return new SearchResultPage(driver);
	}
	

}
