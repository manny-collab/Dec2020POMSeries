package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By productHeader = By.xpath("//div[@id='content']//h1");
	private By productImages = By.xpath("//ul[@class='thumbnails']//li");
	private By productMetaData = By.xpath("(//div[@class='col-sm-4']//ul)[1]/li");
	private By productPriceData = By.xpath("(//div[@class='col-sm-4']//ul)[2]/li");
	private By quantity = By.xpath("//input[@id='input-quantity']");
	private By addToCartBtn = By.xpath("//button[@id='button-cart']");
	private By successMessage = By.xpath("(//div[@class='alert alert-success alert-dismissible']/a)[1]");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getProductHeaderText() {
		return elementUtil.doGetText(productHeader);
	}
	
	public int getproductImagesCount() {
		return elementUtil.createWebElements(productImages).size();
	}
	
	public Map<String, String> getProductInformation() {
		Map<String, String> productInfoMap = new HashMap<String, String>();
		productInfoMap.put("name", getProductHeaderText());
		
		List<WebElement> productMetaDataList = elementUtil.createWebElements(productMetaData);
		
		for(WebElement e : productMetaDataList) {
			String[] meta = e.getText().split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
			
		}
		
		List<WebElement> productPriceList = elementUtil.createWebElements(productPriceData);
		productInfoMap.put("price", productPriceList.get(0).getText().trim());
		productInfoMap.put(productPriceList.get(1).getText().split(":")[0].trim(), 
				productPriceList.get(1).getText().split(":")[1].trim());
		
		return productInfoMap;
		
	}
	
	public void selectQuantity(String qty) {
		elementUtil.doSendKeys(quantity, qty);
	}
	
	public void addToCart() {
		elementUtil.doClick(addToCartBtn);
	}
	
	public String getSuccessMessage() {
		return elementUtil.doGetText(successMessage);
	}
	

}
