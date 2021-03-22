package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	
	private ElementUtil elementUtil;
	private WebDriver driver;

	// Page objects - By locators
	
	By searchItemResult = By.xpath("//div[@class='product-thumb']");
	By resultsItems = By.xpath("//div[@class='product-thumb']//h4/a");
	
	// Constructor
	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	// Page actions 
	
	public int getProductResultsCount() {
		return elementUtil.createWebElements(searchItemResult).size();
	}
	
	public ProductInfoPage selectProductFromResults(String productName) {
		List<WebElement> resultItemsList= elementUtil.createWebElements(resultsItems);
		for(WebElement e : resultItemsList) {
			if(e.getText().equals(productName)) {
				e.click();
				break;
			}
		}
		
		return new ProductInfoPage(driver);
	}

}
