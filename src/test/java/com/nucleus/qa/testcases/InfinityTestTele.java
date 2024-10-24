package com.nucleus.qa.testcases;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.infinity.qa.pages.InfinityHomePage;
import com.infinity.qa.pages.InfinityLoginPage;
import com.infinity.qa.pages.InfinityNewProposalPage;
import com.nucleus.qa.base.TestBase;
import com.nucleus.qa.pages.DirectorInformationPage;
import com.nucleus.qa.pages.DocumentsPage;
import com.nucleus.qa.pages.LoanInformationPage;
import com.nucleus.qa.pages.NewProposalPage;
import com.nucleus.qa.pages.NucleusSaleforcePage;
import com.nucleus.qa.pages.OfficePage;
import com.nucleus.qa.pages.ShareholderDetailsPage;
import com.nucleus.qa.pages.myPulsePage;
import com.nucleus.qa.util.TestUtil;

public class InfinityTestTele extends TestBase{
	
	InfinityLoginPage InfinityLogin;
	InfinityHomePage  InfinityHome;
	InfinityNewProposalPage InfinityNewProposal;
	
	ExtentReports extent;
	String Datepath;
	String FilePath;
	ExtentTest test1,test2;	
	NewProposalPage newproposalpage;
	LoanInformationPage LoanInfo;
	
	DirectorInformationPage DirectorInformation;
	DocumentsPage Documents;
	NucleusSaleforcePage NucleusSaleforce;
	ShareholderDetailsPage ShareholderDetails;
	OfficePage Office;
	myPulsePage myPulse;
	TestUtil testutil;
	TestBase TestBaseMethod;
	
	
	
	
	
	
	static ExtentTest WriteExtentReport;
	datadriven d;
	String path = "C:\\Users\\ShahrukhAata_l4\\Project\\Test\\TestData\\TestDataInfinity.xls";
	
	@BeforeSuite
	public void start() { 
	
		String  path2 = System.getProperty("user.dir")+ "\\reports\\Infinity.html";
	//	ExtentSparkReporter esp=new ExtentSparkReporter(System.getProperty("user.dir")+"/ExtentReport/ExtentReports_"+destDir+"/SwarupExtentReport.html");
		Calendar cal = Calendar.getInstance();
		File Dir = new File(path2);
		Dir.mkdir();
		int year = cal.get(Calendar.YEAR);
		Dir = new File(path2+"/"+year);
		Dir.mkdir();
		int month = cal.get(Calendar.MONTH);
		Dir = new File(path2+"/"+year+"/"+(month+1));
		Dir.mkdir();
		int day = cal.get(Calendar.DATE);
		Dir = new File(path2+"/"+year+"/"+(month+1)+"/"+day);
		Dir.mkdir();
		Dir = new File(path2+"/"+year+"/"+(month+1)+"/"+day);
		Dir.mkdir();
		Datepath= Dir.getAbsolutePath();
		
		Date sDate = new Date();
		String sScreenshotFilename = sDate.getHours()+"_"+sDate.getMinutes()+"_"+sDate.getSeconds();
		FilePath = Datepath + "/" + sScreenshotFilename;
		// String path = System.getProperty("user.dir")+ "\\reports\\"+Datepath+"\\index.html";
		
		
         // File DestFile=new File(FilePath);
		
		 // Files.copy(path.toPath(), DestFile.toPath());
         ExtentSparkReporter reporter=new ExtentSparkReporter(FilePath);
         reporter.config().setReportName("Web Automation Results");
		 reporter.config().setDocumentTitle("TestResult");
		 extent = new ExtentReports();
	     extent.attachReporter(reporter);
	     
	     
	}
	
	@BeforeMethod
	public void setUp() {
		
		
		
		
		initializationInfinity();
		InfinityLogin=new InfinityLoginPage();
		InfinityHome = new InfinityHomePage();
		InfinityNewProposal=new InfinityNewProposalPage();
		newproposalpage =new NewProposalPage();
		LoanInfo=new LoanInformationPage();
		DirectorInformation =new DirectorInformationPage();
		Documents = new DocumentsPage();
		myPulse = new myPulsePage();
		TestBaseMethod =new TestBase();
		Office= new OfficePage();
		NucleusSaleforce= new NucleusSaleforcePage();
		InfinityLogin=new InfinityLoginPage();
		InfinityHome = new InfinityHomePage();
		InfinityNewProposal=new InfinityNewProposalPage();
		 d = new datadriven();
		
	  }
	
	/*@Test
	public void InfinityTest() throws IOException
	{
		
		//FileInputStream fis = new FileInputStream("C:/Users/ShahrukhAatar/Documents/TestData.xlsx");
		//XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		//ExtentTest test= extent.createTest("TestCase");
		
		/*InfinityLogin.login();
		InfinityHome.NewProposal();
		InfinityNewProposal.EnterInfinityFundingProposal();
		extent.flush();
		Sleep(1000);
		InfinityNewProposal.ClickOnInfinityFundingProposal();
		InfinityNewProposal.LimitedCompany();
		InfinityNewProposal.ClickOnCompanyName();
		InfinityNewProposal.PrimaryDirector();
		InfinityNewProposal.Email();
		InfinityNewProposal.EnterPhoneNumberField();
		InfinityNewProposal.EnterBirthDay();
		InfinityNewProposal.EnterPostCode();
		InfinityNewProposal.ClickOnFindAddress();
		InfinityNewProposal.SelectAddress();
		InfinityNewProposal.SelectResidentialPropertyYes();
		InfinityNewProposal.BusinessAddressSelectYes();
		InfinityNewProposal.NextButton();
		
		//test.fail("Result not match");
		//extent.flush();
		
		
	    }*/
	
	
	
	@Test(enabled=true)
	public void TestCase1() throws Exception  {
		
		 test1 = extent.createTest("Test Case 1", "InfinityTestCase ");
	
		try {
				
		ArrayList data=d.getData("TestCase1", path);
		
		System.out.println(path);
		
		String url = (String) data.get(2);
		driver.get(url);
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		String CompanyName=(String) data.get(5);
		String Director = (String) data.get(6);
		String Email = (String) data.get(7);
		String PhoneNumber = (String) data.get(8);
		String BirthDay = (String) data.get(9);
		String HouseNumber = (String) data.get(10);
		String HouseName = (String) data.get(11);
		String City = (String) data.get(12);
		String Street = (String) data.get(13);
		String Country = (String) data.get(14);
		String PostCode = (String) data.get(15);
		String Fund = (String) data.get(16);
		String Months = (String) data.get(17);
		String Percent = (String) data.get(18);
		
		
		String BirthDay1= (String) data.get(19);
		String Email1 = (String) data.get(20);
		String MobileNumber = (String) data.get(21);
		String url1 = (String) data.get(22);
		String Bank = (String) data.get(23);
		String BankType = (String) data.get(24);
		String Email2 = (String) data.get(25);
		String MobileNumber1 = (String) data.get(26);
		String Email3 = (String) data.get(27);
		String MobileNumber2 = (String) data.get(28);
		String Email4 = (String) data.get(29);
		String MobileNumber3 = (String) data.get(30);
		String CRN = (String) data.get(31);
		//System.out.println(Percent);
		/*String BirthDay1= (String) data.get(18);
		String Email1 = (String) data.get(19);
		String MobileNumber = (String) data.get(20);
		String PhoneNO = (String) data.get(21);
		String url1 = (String) data.get(22);
		String Bank = (String) data.get(23);
		String BankType = (String) data.get(24);
		String Name = (String) data.get(29);*/
		String Saleforceurl = (String) data.get(32);
		String SaleforceUsername = (String) data.get(33);
		String SaleforcePassword = (String) data.get(34);
		String DirectorFirstName = (String) data.get(35);
		String DirectorLastName = (String) data.get(36);
		
		
		driver.get(Saleforceurl);
		
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Saleforce Login Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
      
		NucleusSaleforce.EnterUsername(SaleforceUsername);
		NucleusSaleforce.EnterPassword(SaleforcePassword);
		NucleusSaleforce.ClickOnLogin();
		
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Saleforce After Login Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
      
		
		try {
		Sleep(5000);
		
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		// new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='User']"))).isDisplayed();
			
		 
		WebElement element = driver.findElement(By.xpath("//img[@alt='User']"));
		 
		 js.executeScript("arguments[0].click();", element);
		
			//driver.findElement(By.xpath("//img[@alt='User']")).click();
		
		
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Switch to Salesforce Classic')]"))).isDisplayed();
		driver.findElement(By.xpath("//a[contains(text(),'Switch to Salesforce Classic')]")).click();
		
		//NucleusSaleforce.ClickOnProfile();
		//NucleusSaleforce.SwitchToClassic();
		}
		catch(Exception e)
		{
			
		}
		NucleusSaleforce.ClickOnLeadtab();
		NucleusSaleforce.ClickOnNewbutton();
		NucleusSaleforce.SelectOnLeadRecordType();
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Create Lead Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
      
		NucleusSaleforce.ClickOnContinue();
		NucleusSaleforce.EnterFirstName(DirectorFirstName);
		NucleusSaleforce.EnterLastName(DirectorLastName);
		NucleusSaleforce.SelectLeadSource();
		NucleusSaleforce.SelectLeadSourceInformation();
		NucleusSaleforce.EnterCompanyName(CompanyName);
		NucleusSaleforce.ClickOnSavebutton();
		
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Lead Sucssesfully Create Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
      
	
		String URL="https://myfunding.ncf-sandbox.com/deleteCompanyData/"+CRN;
		
		
		
		driver.get(URL);
		Sleep(3000);
		driver.get(url);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Infinty Login Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		InfinityLogin.login(Username,Password);
		
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Infinty After Login Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
      
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' NEW PROPOSAL')]"))).isDisplayed();
		driver.findElement(By.xpath("//a[contains(text(),' NEW PROPOSAL')]")).click();
			
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to NEW PROPOSAL page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
      
		
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='Infinity_lead']"))).isDisplayed();
		
		driver.findElement(By.xpath("//input[@id='Infinity_lead']")).sendKeys(CompanyName);
		
		
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'"+CompanyName+"')]"))).isDisplayed();
		
		driver.findElement(By.xpath("//h3[contains(text(),'"+CompanyName+"')]")).click();
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Limited Company')]"))).isDisplayed();
		
		driver.findElement(By.xpath("//div[contains(text(),'Limited Company')]")).click();
		
		
		//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("comp_name"))).isDisplayed();
			
			//driver.findElement(By.id("comp_name")).sendKeys(CompanyName);
		Sleep(5000);
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'"+CompanyName+"')]"))).isDisplayed();
			driver.findElement(By.xpath("//h3[contains(text(),'"+CompanyName+"')]")).click();
			Sleep(5000);
			
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Please Select Primary Director')]//following-sibling::div"))).isDisplayed();
			driver.findElement(By.xpath("//div[contains(text(),'Please Select Primary Director')]//following-sibling::div")).click();
			
			
			//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(),'"+Director+"')]"))).isDisplayed();
			//driver.findElement(By.xpath("//h3[contains(text(),'"+Director+"')]")).click();
			
	
		//Sleep(800000);
			
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']"))).isDisplayed();
			driver.findElement(By.xpath("//input[@id='email']")).clear();
			
			
		
		newproposalpage.EnterEmail(Email);
		System.out.println("email enter 1");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='phone']"))).isDisplayed();
		driver.findElement(By.xpath("//input[@id='phone']")).clear();
		
		newproposalpage.EnterPhoneNumberField(PhoneNumber);
		newproposalpage.EnterBirthDay(BirthDay);
		newproposalpage.EnterAddressManually();
		newproposalpage.EnterHouseNumber(HouseNumber);
		newproposalpage.EnterHouseName(HouseName);
		newproposalpage.EnterCity(City);
		newproposalpage.EnterStreet(Street);
		newproposalpage.EnterCountry(Country);
		newproposalpage.SendPostCode(PostCode);
		newproposalpage.SelectResidentialPropertyYes();
		newproposalpage.BusinessAddressSelectYes();
		
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Enter Company Detail Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
      
		newproposalpage.NextButton();
		
		
		
	
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Nucleus Business Loan')]"))).isDisplayed();
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Loan Information Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		try {
			
		driver.findElement(By.xpath("//div[contains(text(),'Nucleus Business Loan')]")).click();
		
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to NBL Confirmation POP Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
      
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Confirm')])[1]"))).isDisplayed();
		driver.findElement(By.xpath("(//a[contains(text(),'Confirm')])[1]")).click();
		
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		LoanInfo.BrokerPercent(Percent);
		}
		catch(Exception e) {
			
			try {
				
				new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@id='popmsgother']//following-sibling::a"))).isDisplayed();   
				driver.findElement(By.xpath("//p[@id='popmsgother']//following-sibling::a")).click();
				
				
				
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Revenue Based Loan')]"))).isDisplayed();
	        Sleep(2000);
			driver.findElement(By.xpath("//div[contains(text(),'Revenue Based Loan')]")).click();
			Sleep(3000);
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate to RBL Confirmation POP Page 1");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
	      
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Confirm')])[1]"))).isDisplayed();
			driver.findElement(By.xpath("(//a[contains(text(),'Confirm')])[1]")).click();	
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("card_terminals"))).isDisplayed();
			driver.findElement(By.id("card_terminals")).sendKeys("5");
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("average_monthly_card_volume"))).isDisplayed();
			driver.findElement(By.id("average_monthly_card_volume")).sendKeys("2000");
				
				LoanInfo.FundingNeeded(Fund);
				LoanInfo.LoanMonths(Months);
				LoanInfo.SelectPurposeFunding();
				LoanInfo.BrokerPercent(Percent);
			   
		}
			catch(Exception e1) {
				try {
					
					new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@id='popmsgother']//following-sibling::a"))).isDisplayed();   
					driver.findElement(By.xpath("//p[@id='popmsgother']//following-sibling::a")).click();
					
				 new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Growth Loans')]"))).isDisplayed();
				 Sleep(2000);
				driver.findElement(By.xpath("//div[contains(text(),'Growth Loans')]")).click();
				Sleep(3000);
				Screenshot();
				WriteExtentReport =test1.createNode("Navigate to Growth Loans Confirmation POP Page");
				WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		      
				 new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Confirm')])[1]"))).isDisplayed();
					
				driver.findElement(By.xpath("(//a[contains(text(),'Confirm')])[1]")).click();
				
                new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("business_turnover"))).isDisplayed();
			    
				driver.findElement(By.id("business_turnover")).sendKeys("200000");
				LoanInfo.FundingNeeded(Fund);
				LoanInfo.LoanMonths(Months);
				LoanInfo.SelectPurposeFunding();
			
			
			}
			catch(Exception e2) {
				
				try {
					
					 new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Merchant cash advance')]"))).isDisplayed();
				        Sleep(2000);
						driver.findElement(By.xpath("//div[contains(text(),'Merchant cash advance')]")).click();
						Sleep(3000);
						Screenshot();
						WriteExtentReport =test1.createNode("Navigate to Merchant Confirmation POP Page");
						WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
				      
						
						
						new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("number_of_trading_location"))).isDisplayed();
				        Sleep(2000);
						driver.findElement(By.id("number_of_trading_location")).sendKeys("10");
						
						new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("cardpay-show"))).isDisplayed();
				        Sleep(2000);
						driver.findElement(By.id("cardpay-show")).click();	
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("cardtakings_per_month"))).isDisplayed();
					    
						driver.findElement(By.id("cardtakings_per_month")).sendKeys("2000");
						
                       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//ul[@id='cardtransaction10andmore']//child::li//child::label)[1]"))).isDisplayed();
					    
						driver.findElement(By.xpath("(//ul[@id='cardtransaction10andmore']//child::li//child::label)[1]")).click();
						 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("business_turnover"))).isDisplayed();
						    
							driver.findElement(By.id("business_turnover")).sendKeys("200000");
							
							LoanInfo.FundingNeeded(Fund);
							LoanInfo.LoanMonths(Months);
							LoanInfo.SelectPurposeFunding();
							
						
							
							
					
				}
				catch(Exception e3) {
					
					new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Property Finance')]"))).isDisplayed();
			        Sleep(2000);
					driver.findElement(By.xpath("//div[contains(text(),'Property Finance')]")).click();
					Sleep(3000);
					Screenshot();
					WriteExtentReport =test1.createNode("Navigate to Property Finance Confirmation POP Page");
					WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			      
					
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Interest only')]"))).isDisplayed();
					    
						driver.findElement(By.xpath("//div[contains(text(),'Interest only')]")).click();
						
						new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("mortgage_amount"))).isDisplayed();
					    
						driver.findElement(By.id("mortgage_amount")).sendKeys("20000");
						 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("business_turnover"))).isDisplayed();
						    
							driver.findElement(By.id("business_turnover")).sendKeys("200000");
							
							LoanInfo.FundingNeeded(Fund);
							LoanInfo.LoanMonths(Months);
							LoanInfo.SelectPurposeFunding();
				}	
			}
				
			}
		}
		
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Loan Detail Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
      
		
		LoanInfo.NextStep();
		
		
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Director Details page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
      

		try {
			DirectorInformation.EditDirectorDetails();
			DirectorInformation.DateofBirth(BirthDay1);
			Sleep(1000);
			DirectorInformation.Email(Email1);
			System.out.println("email enter 2");
			Sleep(1000);
			DirectorInformation.DirMobile(MobileNumber);
			DirectorInformation.PersonalGuaranteeYes();
			DirectorInformation.EnterPostCode(PostCode);
			DirectorInformation.ClickonFindAddress();
			Sleep(3000);
			DirectorInformation.SelectAddress();
			DirectorInformation.SelectResidentialPropertyYes();
			Sleep(3000);
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate to Director Details page 1");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
	      
			DirectorInformation.ClickOnSubmit();
		}
		catch(Exception e) {
			
		}
		
		try{
			//	WebDriverWait wait=new WebDriverWait(driver, 20);
				
				
				new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("edit_3"))).isDisplayed();
				Sleep(5000);
				driver.findElement(By.id("edit_3")).click();
				
				//DirectorInformation.EditShareholderDetails1();
				Sleep(5000);
				System.out.println("555555555555555555555555555555555555555555");
				//DirectorInformation.DateofBirth(BirthDay1);
				DirectorInformation.Email(Email2);
				Sleep(1000);
				DirectorInformation.DirMobile(MobileNumber1);
				DirectorInformation.PersonalGuaranteeYes();
				DirectorInformation.EnterPostCode(PostCode);
				DirectorInformation.ClickonFindAddress();
				Sleep(3000);
				DirectorInformation.SelectAddress();
				DirectorInformation.SelectResidentialPropertyYes();
				Sleep(3000);
				Screenshot();
				WriteExtentReport =test1.createNode("Navigate Director Details page 2");
				WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		      
				DirectorInformation.ClickOnSubmit();
				Sleep(3000);
			} catch(Exception e)  
	        {  
	            System.out.println(e);  
	        }
		
		
		try {
			DirectorInformation.EditShareholderDetails();
			//DirectorInformation.DateofBirth(BirthDay1);
			DirectorInformation.Email(Email3);
			Sleep(1000);
			DirectorInformation.DirMobile(MobileNumber2);
			DirectorInformation.PersonalGuaranteeYes();
			DirectorInformation.EnterPostCode(PostCode);
			DirectorInformation.ClickonFindAddress();
			Sleep(3000);
			DirectorInformation.SelectAddress();
			DirectorInformation.SelectResidentialPropertyYes();
			Sleep(3000);
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate to Shareholder Details page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
	      
			DirectorInformation.ClickOnSubmit();
			} catch(Exception e)  
	        {  
	            System.out.println(e);  
	        }
		
				
				try {
				DirectorInformation.ClickOnShareholderDetails2();
				//DirectorInformation.DateofBirth(BirthDay1);
				DirectorInformation.Email(Email4);
				Sleep(1000);
				DirectorInformation.DirMobile(MobileNumber3);
				DirectorInformation.PersonalGuaranteeYes();
				DirectorInformation.EnterPostCode(PostCode);
				DirectorInformation.ClickonFindAddress();
				Sleep(3000);
				DirectorInformation.SelectAddress();
				DirectorInformation.SelectResidentialPropertyYes();
				Sleep(3000);
				Screenshot();
				WriteExtentReport =test1.createNode("Navigate to Shareholder Details page1");
				WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		      
				DirectorInformation.ClickOnSubmit();
				} catch(Exception e)  
		        {  
		            System.out.println(e);  
		        }
			
		
				Sleep(3000);
				Screenshot();
				WriteExtentReport =test1.createNode("Navigate to Shareholder Details page 2");
				WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		      
		
		DirectorInformation.ClickOnNext();
		
		
		Documents.SelectAccountingPackage();
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Documents Detail Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
      
		System.out.println("11111111111111111111");
		Sleep(3000);
		Documents.ClickOnDirector();
		System.out.println("11111111111111111111");

		Documents.ClickOnOk();
		Documents.SelectBank(Bank);
		Documents.SelectBankAccountType(BankType);
		Sleep(3000);
		Documents.ClickOnDirector1();
		System.out.println("11111111111111111111");
		Sleep(3000);
		Documents.ClickOnOk();
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Documents Detail Page1");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
      
		System.out.println("11111111111111111111");
		Documents.SubmitButton();
		System.out.println("11111111111111111111");
		driver.get(url1);
		
		System.out.println("11111111111111111111");
		Sleep(15000);
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Email Page1");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
      
		Set<String> handles3 = driver.getWindowHandles();
		List<String> hList3 = new ArrayList<String>(handles3);
		if(switchToRightWindow("Mail - Shahrukh Aatar - Outlook",hList3)){
			System.out.println("11111111111111111111");
		      }
		driver.findElement(By.xpath("//span[contains(text(),'Infinty')]")).click();
		Sleep(5000);
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Email Page2");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
      
	//driver.findElement(By.xpath("//span[@id='id__292']")).click();
		System.out.println("11111111111111111111");
		Office.SelectFirstEmail();
		System.out.println("11111111111111111111");
			Sleep(6000);
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Email Page3 ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='https://myfunding.ncf-sandbox.com/emailers/images/registeration.jfif']"))).isDisplayed();
			Sleep(5000);
			driver.findElement(By.xpath("//img[@src='https://myfunding.ncf-sandbox.com/emailers/images/registeration.jfif']")).click();
			Sleep(6000);
			Set<String> handles = driver.getWindowHandles();
			List<String> hList = new ArrayList<String>(handles);
			if(switchToRightWindow("MyNucleus",hList)){
			      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
			      }
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Open Banking Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			//Office.ClickonCompleteyourOpenBanking();
			
			Sleep(6000);
			Set<String> handles1 = driver.getWindowHandles();
			List<String> hList1 = new ArrayList<String>(handles1);
			if(switchToRightWindow("myPulse - Plaid Open Banking",hList1)){
			      }
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate myPulse Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			driver.switchTo().frame(0);
		    myPulse.ClickOnContinue();
		    myPulse.ClickOnBank();
		    myPulse.ClickOnBankWebsite();
		    Sleep(6000);
		    Set<String> handles2 = driver.getWindowHandles();
			List<String> hList2 = new ArrayList<String>(handles2);
			if(switchToRightWindow("First Platypus Bank - OAuth Login Page",hList2)){
			      }
			 myPulse.ClickOnSignIn();
			myPulse.ClickOnAccount();
			myPulse.ClickOnConnectAccountInformation();
			if(switchToRightWindow("myPulse - Plaid Open Banking",hList1)){
			      }
			Sleep(4000);
			myPulse.Congratulations();
			Sleep(2000);
			Screenshot();
			Sleep(2000);
			WriteExtentReport =test1.createNode("Navigate Congratulations Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		
	}
		
		
		
		
	
	catch(Exception e) {
		
		String Error = e.toString();
		System.out.println(Error);
		Screenshot();
		WriteExtentReport =test1.createNode("Failed Page ");
		WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+Error);
        }
	}
	
	

	
	
	
	

	@AfterMethod
	public void tearDown() throws Exception {
		
		driver.quit();
		
	   }
	
	@AfterSuite
	public void Exit() {
	
	extent.flush();

	}
  }
