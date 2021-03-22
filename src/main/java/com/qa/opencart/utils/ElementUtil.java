package com.qa.opencart.utils;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	
	private WebDriver driver;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public WebElement createWebElement(By locator) {
		return driver.findElement(locator);
	}
	
	public List<WebElement> createWebElements(By locator) {
		return driver.findElements(locator);
	}
	 
	public void doSendKeys(By locator, String text) {
		WebElement ele = createWebElement(locator);
		ele.clear();
		ele.sendKeys(text);
	}
	
	public void doClick(By locator) {
		createWebElement(locator).click();
	} 
	
	public String doGetText(By locator) {
		return createWebElement(locator).getText();
	}
	
	public boolean doIsDisplayed(By locator) {
		return createWebElement(locator).isDisplayed();
	}
	
	public void doLinkClick(By locator, String value) {
		List<WebElement> links = createWebElements(locator);
		
		for(WebElement e :links) {
			if (e.getText().equals(value)) {
				e.click();
				break;
			}
		}
	}
	
	// ********************* Wait Utils *************************
	
	public WebElement waitForPresenceOfElement(By locator, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public WebElement waitForVisibilityOfElement(By locator, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		return wait.until(ExpectedConditions.visibilityOf(createWebElement(locator)));
	}
	
	public List<WebElement> waitForVisibilityOfElements(By locator, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public WebElement waitForElementToBeClickable(By locator, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public boolean waitForPresenceOfElementText(By locator, int timeInSeconds, String text) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		return wait.until(ExpectedConditions.textToBe(locator, text));
	}
	
	public boolean waitForPartialUrlTextMatch(int timeInSeconds, String urlText) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		return wait.until(ExpectedConditions.urlContains(urlText));
	}
	
	public boolean waitForExactUrlTextMatch(int timeInSeconds, String urlText) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		return wait.until(ExpectedConditions.urlToBe(urlText));
	}
	
	public void waitForFrameAndSwitch(By locator, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}
	
	public void clickWhenReady(By locator, int timeInSeconds) {
		waitForElementToBeClickable(locator, timeInSeconds).click();
	}
	
	public String getElementAttribute(By locator, int timeInSeconds, String name) {
		return waitForElementToBeClickable(locator, timeInSeconds).getAttribute(name);
	}
	
	public void printElementsText(By locator, int timeInSeconds) {
		waitForVisibilityOfElements(locator, timeInSeconds)
				.stream()
						.forEach(ele -> System.out.println(ele.getText()));
	}
	
	public String waitForTitleContains(int timeInSeconds, String title) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}
	
	public String waitForTitleExact(int timeInSeconds, String title) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}
	
	public Boolean waitForTitle(int timeInSeconds, String title) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.titleIs(title));
	}
	
	// Fluent Wait 
	public WebElement waitForElementWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
								.withTimeout(Duration.ofSeconds(timeOut))
								.pollingEvery(Duration.ofSeconds(pollingTime))
								.ignoring(StaleElementReferenceException.class) // will not throw till timeout
								.ignoring(NoSuchElementException.class); // will not throw till timeout 
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	
	// ********************** Alert Utils ************************
	
	public Alert waitForAlertPresent(int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public String getAlertText(int timeInSeconds) {
		Alert alert = waitForAlertPresent(timeInSeconds);
		return alert.getText();
	}
	
	public void alertAccept() {
		Alert alert = waitForAlertPresent(10);
		alert.accept();
	}
	
	public void alertDismiss() {
		Alert alert = waitForAlertPresent(10);
		alert.dismiss();
	}

	

}
