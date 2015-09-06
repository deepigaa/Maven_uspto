package com.uspto.tsdr.pageObjects;

import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class TSDR_HomePage {
	

private WebDriver driver;
Properties OR = null;
private static final String EXPECTED_TITLE = "Trademark Status & Document Retrieval";
private static final String EXPECTED_PAGETEXT = "United States Patent and Trademark Office";

private By headerPageText = By.xpath(".//*[@id='header']/div/div[1]/h1/a/span");
private By searchNum = By.id("searchNumber");
private By statusBtn = By.id("statusSearch");
private By errorMsgTxt = By.id("errorMsg");
private By selectType = By.id("searchSelect");
	
	public TSDR_HomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public String getHomePageTitle() {
		String pageTitle = driver.getTitle();
		System.out.println("page title is: "+pageTitle);
		return pageTitle;
	}
	
	public boolean verifyHomePageTitle() {
		String pageTitle = getHomePageTitle();
		Boolean result=pageTitle.contains(EXPECTED_TITLE);
		//System.out.println("result is:"+result);
		return result;
		
	}
	
	public boolean verifyHeaderText() {
		WebElement element = driver.findElement(headerPageText);
		String pageText = element.getText();
		return pageText.contains(EXPECTED_PAGETEXT);
	}
	public void selectDropDown(String value){
		Select dropdown = new Select(driver.findElement(selectType));
		dropdown.selectByValue(value);
	}
	
	public void setSearhNumber(String number){
		WebElement searchNumber = driver.findElement(searchNum);
		searchNumber.sendKeys(number);
	}
	
	public void type(String Xpath,String text){
		driver.findElement(By.xpath(Xpath)).sendKeys(text);
	}
	
	
	public TSDR_StatusPage clickOnStatusButton() {
		WebElement statusButtn = driver.findElement(statusBtn);
		if(statusButtn.isDisplayed()&&statusButtn.isEnabled())
			statusButtn.click();
		return new TSDR_StatusPage(driver);
		}
		
	public String getErrorMessage() {
		String strErrorMsg = null;
		WebElement errorMsg = driver.findElement(errorMsgTxt);
		if(errorMsg.isDisplayed()&&errorMsg.isEnabled())
			strErrorMsg = errorMsg.getText();
		return strErrorMsg;
	}
	
	public void takeScreenshot(String filepath){
		try{
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(filepath));
		}catch(IOException e){
			e.printStackTrace();
		}

	}
	
	public boolean isElementPresent(String Xpath) {
		try{
		Thread.sleep(3000);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		int count = driver.findElements(By.xpath(Xpath)).size();
		if(count==0){
			return false;
		}else{
			return true;
		}
	}
		
	
	/**
	 * Selects a checkbox with the given label and sets its value according to
	 * the select param.
	 *
	 * @param label the label of the checkbox to select
	 * @param select true if the checkbox should be checked, false otherwise
	 */
	public void selectCheckbox(String label, boolean select) {
		WebElement checkbox = driver.findElement(By.xpath("//label[text()='" + label + "']/preceding-sibling::input"));
		if (select) {
			if (!checkbox.isSelected()) {
				checkbox.click();
			}
		}
		else {
			if (checkbox.isSelected()) {
				checkbox.click();
			}
		}
	}
	
	/**
	 * Returns the URL of a hyperlink with the specified text on the page
	 *
	 * @param linkText text of the link whose URL you wish to get.
	 * @return returns the url of the link
	 */
	public String checkHyperLinkUrl(String linkText) {
		return driver.findElement(By.linkText(linkText)).getAttribute("href").toLowerCase();
	}

	/**
	 * Checks to see if a link with the specified text is present on the page
	 *
	 * @param linkText text of the link to be checked
	 * @return true if a link with the specified text is found on the page
	 */
	public Boolean checkIfLinkExists(String linkText) {
		int count =  driver.findElements(By.linkText(linkText)).size();
		if(count!=0){
			return true;
		}else{
			return false;
		}
			
		
	}

	
}

