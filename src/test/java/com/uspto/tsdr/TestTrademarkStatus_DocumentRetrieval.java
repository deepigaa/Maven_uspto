package com.uspto.tsdr;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;




import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.uspto.tsdr.pageObjects.BrowserFactory;
import com.uspto.tsdr.pageObjects.TSDR_HomePage;
import com.uspto.tsdr.pageObjects.TSDR_StatusPage;


public class TestTrademarkStatus_DocumentRetrieval {

	Properties CONFIG = null;
	WebDriver driver = null;
	TSDR_HomePage homePage;
	TSDR_StatusPage statusPage;
	
	
	@BeforeSuite
	public void init(){
		
		try{
			//read the CONFIG.properties file
			CONFIG = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\com\\uspto\\tsdr\\config\\prod_config.properties");
			CONFIG.load(fis);
			System.out.println(CONFIG.getProperty("testsiteBaseUrl"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
	@BeforeTest
	public void setUp() {
		
		driver = BrowserFactory.getBrowser(CONFIG.getProperty("browser"));
		driver.get(CONFIG.getProperty("testsiteBaseUrl"));
		//driver = BrowserFactory.getBrowser("Chrome");
		//driver.get("http://tsdr.uspto.gov/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS );
		
	}
	
	
	
	@Test(dataProviderClass = Data_Provider.class,dataProvider = "statusTestData")
	public void testTrademarkStatus(String selectType,String searchNum,String title,String statusMsg){
					
		try{
			
			System.out.println("********************");
			homePage = new TSDR_HomePage(driver);
			Assert.assertTrue(homePage.verifyHomePageTitle(),"page title did not match");
				
			homePage.selectDropDown(selectType);
			homePage.setSearhNumber(searchNum);
			statusPage=homePage.clickOnStatusButton();
			
			Assert.assertTrue(statusPage.verifyStatusPageTitle(searchNum),"page title did not match");
			Assert.assertTrue(statusPage.verifyMarkTitle(title),"mark title did not match");
	        Assert.assertTrue(statusPage.verifyStatusMsg(statusMsg),"status msg did not match");
			
	        driver.navigate().back();
			driver.navigate().refresh();
			System.out.println("******************");
			
		}catch(Throwable t){
			System.out.println(t.getMessage());
		}
	  }
	
	@AfterTest
	public void tearDown(){
		driver.quit();
		
	}
	@AfterSuite
	public void exit()
	{
		BrowserFactory.closeAllDriver();
	}
	
	
}
