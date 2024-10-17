package com.nucleus.qa.testcases;

import static io.restassured.RestAssured.given;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nucleus.qa.base.Api;
import com.nucleus.qa.base.TestBase;
import com.nucleus.qa.pages.DirectorInformationPage;
import com.nucleus.qa.pages.DocumentsPage;
import com.nucleus.qa.pages.HomePage;
import com.nucleus.qa.pages.LoanInformationPage;
import com.nucleus.qa.pages.LoginPage;
import com.nucleus.qa.pages.NewProposalPage;
import com.nucleus.qa.pages.NucleusSaleforcePage;
import com.nucleus.qa.pages.OfficePage;
import com.nucleus.qa.pages.ShareholderDetailsPage;
import com.nucleus.qa.pages.myPulsePage;
import com.nucleus.qa.util.TestUtil;

import io.restassured.response.Response;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.infinity.qa.pages.InfinityHomePage;
import com.infinity.qa.pages.InfinityLoginPage;
import com.infinity.qa.pages.InfinityNewProposalPage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;




public class LoginPageTest2 extends TestBase {
    XSSFWorkbook workbook;
    Sheet sheet;
    Cell cell;
    public static ExtentTest test;
    static ExtentTest WriteExtentReport;
    public static boolean sStatus;
    public static String sErrorLog;
    public static ExtentReports extent;
	ExtentTest test1,test2;		  
	LoginPage loginPage;
	HomePage  homePage;
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
	String Datepath;
	String FilePath;
	InfinityLoginPage InfinityLogin;
	InfinityHomePage  InfinityHome;
	InfinityNewProposalPage InfinityNewProposal;	
	datadriven d;
	
	
	
	
	
	DataFormatter formatter=new DataFormatter();
	String sheetName = "Credentilas";
	ExtentTest logger;
	
	String path= "C:\\Users\\ShahrukhAata_l4\\Project\\Test\\TestData\\student1.xls";
	public LoginPageTest2() {
		super();
	}
	
	@BeforeSuite
	public void start() {
		 
		
		String  path2 = System.getProperty("user.dir")+ "\\reports\\index.html";
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
	public void setUp() throws IOException {
		
		/*String sRequest = "https://myfunding.ncf-sandbox.com/deleteCompanyData/09448371";
		
		Response response = given()
				.contentType("application/json")
				.accept("application/json")
				//.header("Authorization","a45e7250f2b06ad85f35bb4c24292f12e009088d57efa05aa9a036faeab71ea4")
				//.header("Authorization",sTokenkey)
				//.body(userDetails)
				.when()
				.get(sRequest)
				.then()
				//.statusCode(200)
				//.contentType("application/json")
				.extract()
				.response();

		int iResponseCode = response.getStatusCode();
		System.out.println(response.getStatusCode());
		System.out.println("-------------"+response.asString());
		System.out.println("-------------"+response.getBody().asString());
		//int iResponseCode = response.getStatusCode();
		System.out.println("-------------"+iResponseCode);
		System.out.println("-------------"+response.getStatusLine());
		System.out.println("-------------"+response.getHeader("content-type"));
		System.out.println("-------------"+response.getTime()+" Seconds");*/
		
		
		initialization();
	    loginPage = new LoginPage();
		testutil = new TestUtil();
		homePage = new HomePage();
		Office= new OfficePage();
		newproposalpage =new NewProposalPage();
		LoanInfo=new LoanInformationPage();
		DirectorInformation =new DirectorInformationPage();
		Documents = new DocumentsPage();
		myPulse = new myPulsePage();
		TestBaseMethod =new TestBase();
		NucleusSaleforce= new NucleusSaleforcePage();
		InfinityLogin=new InfinityLoginPage();
		InfinityHome = new InfinityHomePage();
		InfinityNewProposal=new InfinityNewProposalPage();
		 d = new datadriven();	
		 
	}

	
	/*@Test
	public void SaleForce() throws Exception {
		
		//System.out.println(name+user+id);
		// Framework.test=Framework.extent.createTest("Test case ID:"+"<br>"+"Test case Description:Application landing page"+"<br>"+"Expected Result:Application launch successfully"+"</br>");
		//Framework.WriteExtentReport=Framework.test.createNode("Navigate to Application landing page <br>");
		 test1 = extent.createTest("Test1", "Saleforce ");
		 test1.log(Status.INFO, "Starting test case");
		//ExtentTest test=extent.createTest("TestCase1");
		ArrayList data=d.getData("Test");
		String url = (String) data.get(1);
		driver.get(url);
	   String Username = (String) data.get(2);
	   String Password = (String) data.get(3);
	   String FirstName =(String) data.get(4);
	   String LastName = (String) data.get(5);
	   String CompanyName=(String)data.get(6);
	 
	   System.out.println(Username);
	   System.out.println(Password);
	   System.out.println(FirstName);
	   System.out.println(LastName);
	   System.out.println(CompanyName);
	 
	   try {
		NucleusSaleforce.EnterUsername(Username);
		NucleusSaleforce.EnterPassword(Password);
		NucleusSaleforce.ClickOnLogin();
		
		//test1.log(Status.PASS, "Chrome browser has opened",MediaEntityBuilder.createScreenCaptureFromPath(capture(driver)).build());
		Sleep(5000);
		NucleusSaleforce.ClickOnProfile();
		NucleusSaleforce.SwitchToClassic();
		NucleusSaleforce.ClickOnLeadtab();
		NucleusSaleforce.ClickOnNewbutton();
		NucleusSaleforce.SelectOnLeadRecordType();
		NucleusSaleforce.ClickOnContinue();
		NucleusSaleforce.EnterFirstName(FirstName);
		NucleusSaleforce.EnterLastName(LastName);
		NucleusSaleforce.SelectLeadSource();
		NucleusSaleforce.SelectLeadSourceInformation();
		NucleusSaleforce.EnterCompanyName(CompanyName);
		NucleusSaleforce.ClickOnSavebutton();
		//test.fail("do not match");
		
	/*	sStatus=true;
    	
		}
		catch(Exception e) {
			sStatus=true;
			
			sErrorLog = e.toString();
		}
		
	   
	   if(sStatus==true)
		{
			Framework.CreateResults(true, "Step Pass");
		}
		else
		{
			Framework.CreateResults(true, sErrorLog);
		}
		//driver.get("https://myfunding.ncf-sandbox.com/");
	   }
	catch(Exception e) {
		//File DestFile=new File(sScreenshotFilePath);
		String Error = e.toString();
		test1.fail("Error Message" +Error );
		Screenshot();
		//String screenshotPath = TestBase.Screenshot();
        test1.fail("Test Case failed check screenshot below"+test1.addScreenCaptureFromPath(sScreenshotFilePath));
	}
	}*/
	
	
	@Test(enabled=true)
	public void TestCase1() throws Exception  {
		 test1 = extent.createTest("Test Case1", "NucleusTestCase ");
		try {
			System.out.println("TestCase started 1");
		ArrayList data=d.getData("TestCase1",path);
		String url = (String) data.get(2);
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		String CompanyName=(String) data.get(5);
		String CRN = (String) data.get(6);
		String Email = (String) data.get(7);
		String PhoneNumber = (String) data.get(8);
		String BirthDay = (String) data.get(9);
		String PostCode = (String) data.get(10);
	    String Fund = (String) data.get(11);
		String Months = (String) data.get(12);
		String Percent = (String) data.get(13);
		String BirthDay1= (String) data.get(14);
		String Email1 = (String) data.get(15);
		String MobileNumber = (String) data.get(16);
		String url1 = (String) data.get(17);
		String Bank = (String) data.get(18);
		String BankType = (String) data.get(19);
		String Email2 = (String) data.get(20);
		String MobileNumber1 = (String) data.get(21);
		String Email3 = (String) data.get(22);
		String MobileNumber2 = (String) data.get(23);
		String Email4 = (String) data.get(24);
		String MobileNumber3 = (String) data.get(25);
		String HouseNumber = (String) data.get(26);
		String HouseName = (String) data.get(27);
		String City = (String) data.get(28);
		String Street = (String) data.get(29);
		String Country = (String) data.get(30);
		
		
		String URL="https://myfunding.ncf-sandbox.com/deleteCompanyData/"+CRN;
		
		driver.get(URL);
		System.out.println(CRN);
		
		Sleep(5000);
		
		driver.get(URL);
		
		//https://myfunding.ncf-sandbox.com/deleteCompanyData/14514499
      
		driver.get(url);
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Application landing page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		loginPage.login(Username,Password);	
		Screenshot();
		WriteExtentReport = test1.createNode("Navigate to Home Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		homePage.NewProposal();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate NewProposal Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		newproposalpage.LimitedCompany();
		newproposalpage.SearchCompanyName(CompanyName);
		newproposalpage.CompanyName(CompanyName);
		newproposalpage.PrimaryDirector();
		newproposalpage.EnterEmail(Email);
		System.out.println("email enter 1");
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
		newproposalpage.NextButton();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate Loan Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		try {
			LoanInfo.NucleusBusinessLoan();
			LoanInfo.ConfirmAlert();
			}
			catch(Exception e) {
				 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//p[@id='popupmessage']//following-sibling::a)[1]"))).isDisplayed();
				    
				driver.findElement(By.xpath("(//p[@id='popupmessage']//following-sibling::a)[1]")).click();
			       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Revenue Based Loan')]"))).isDisplayed();
			       driver.findElement(By.xpath("//div[contains(text(),'Revenue Based Loan')]")).click();
			       LoanInfo.ConfirmAlert();
			       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("card_terminals"))).isDisplayed();
				    
					driver.findElement(By.id("card_terminals")).sendKeys("5");
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("average_monthly_card_volume"))).isDisplayed();
					    
						driver.findElement(By.id("average_monthly_card_volume")).sendKeys("2000");
					   
			}
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		LoanInfo.BrokerPercent(Percent);
		LoanInfo.NextStep();
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		
		
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
				DirectorInformation.ClickOnSubmit();
				Sleep(3000);
			} catch(Exception e)  
	        {  
	            System.out.println(e);  
	        }
				
				try {
				DirectorInformation.ClickOnShareholderDetails2();
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
				DirectorInformation.ClickOnSubmit();
				} catch(Exception e)  
		        {  
		            System.out.println(e);  
		        }
			
		
		
		
		DirectorInformation.ClickOnNext();
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Documents Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		Sleep(3000);
		System.out.println("11111111111111111111");
		Documents.SelectAccountingPackage();
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
		System.out.println("11111111111111111111");
		Documents.SubmitButton();
		System.out.println("11111111111111111111");
		driver.get(url1);
		System.out.println("11111111111111111111");
		Sleep(15000);
		Set<String> handles3 = driver.getWindowHandles();
		List<String> hList3 = new ArrayList<String>(handles3);
		if(switchToRightWindow("Mail - Shahrukh Aatar - Outlook",hList3)){
			System.out.println("11111111111111111111");
		      }
		Office.ClickOnPulse();
		
		
		//driver.findElement(By.xpath("//span[@id='id__292']")).click();
		System.out.println("11111111111111111111");
		Office.SelectFirstEmail();
		System.out.println("11111111111111111111");
			Sleep(6000);
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Email Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickOnCompleteApplication();
			Sleep(6000);
			Set<String> handles = driver.getWindowHandles();
			List<String> hList = new ArrayList<String>(handles);
			if(switchToRightWindow("MyNucleus",hList)){
			      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
			      }
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Open Banking Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickonCompleteyourOpenBanking();
			
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
	
	
	@Test(enabled=true)
	public void TestCase2() throws Exception  {
		 test1 = extent.createTest("Test Case2", "NucleusTestCase ");
		try {
			System.out.println("TestCase2 started 2");
		ArrayList data=d.getData("TestCase2",path);
		String url = (String) data.get(2);
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		String CompanyName=(String) data.get(5);
		String CRN = (String) data.get(6);
		String Email = (String) data.get(7);
		String PhoneNumber = (String) data.get(8);
		String BirthDay = (String) data.get(9);
		String PostCode = (String) data.get(10);
	    String Fund = (String) data.get(11);
		String Months = (String) data.get(12);
		String Percent = (String) data.get(13);
		String BirthDay1= (String) data.get(14);
		String Email1 = (String) data.get(15);
		String MobileNumber = (String) data.get(16);
		String url1 = (String) data.get(17);
		String Bank = (String) data.get(18);
		String BankType = (String) data.get(19);
		String Email2 = (String) data.get(20);
		String MobileNumber1 = (String) data.get(21);
		String Email3 = (String) data.get(22);
		String MobileNumber2 = (String) data.get(23);
		String Email4 = (String) data.get(24);
		String MobileNumber3 = (String) data.get(25);
		String HouseNumber = (String) data.get(26);
		String HouseName = (String) data.get(27);
		String City = (String) data.get(28);
		String Street = (String) data.get(29);
		String Country = (String) data.get(30);
		
		
		String URL="https://myfunding.ncf-sandbox.com/deleteCompanyData/"+CRN;
		
		driver.get(URL);
		System.out.println(CRN);
		
		Sleep(5000);
      
		driver.get(url);
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Application landing page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		loginPage.login(Username,Password);	
		Screenshot();
		WriteExtentReport = test1.createNode("Navigate to Home Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		homePage.NewProposal();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate NewProposal Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		newproposalpage.LimitedCompany();
		newproposalpage.SearchCompanyName(CompanyName);
		newproposalpage.CompanyName(CompanyName);
		newproposalpage.PrimaryDirector();
		newproposalpage.EnterEmail(Email);
		System.out.println("email enter 1");
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
		newproposalpage.NextButton();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate Loan Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		try {
			LoanInfo.NucleusBusinessLoan();
			LoanInfo.ConfirmAlert();
			}
			catch(Exception e) {
				 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//p[@id='popupmessage']//following-sibling::a)[1]"))).isDisplayed();
				    
				driver.findElement(By.xpath("(//p[@id='popupmessage']//following-sibling::a)[1]")).click();
			       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Revenue Based Loan')]"))).isDisplayed();
			       driver.findElement(By.xpath("//div[contains(text(),'Revenue Based Loan')]")).click();
			       LoanInfo.ConfirmAlert();
			       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("card_terminals"))).isDisplayed();
				    
					driver.findElement(By.id("card_terminals")).sendKeys("5");
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("average_monthly_card_volume"))).isDisplayed();
					    
						driver.findElement(By.id("average_monthly_card_volume")).sendKeys("2000");
					   
			}
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		LoanInfo.BrokerPercent(Percent);
		LoanInfo.NextStep();
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		
		
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
				DirectorInformation.ClickOnSubmit();
				Sleep(3000);
			} catch(Exception e)  
	        {  
	            System.out.println(e);  
	        }
				
				try {
				DirectorInformation.ClickOnShareholderDetails2();
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
				DirectorInformation.ClickOnSubmit();
				} catch(Exception e)  
		        {  
		            System.out.println(e);  
		        }
			
		
		
		DirectorInformation.ClickOnNext();
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Documents Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		Sleep(3000);
		System.out.println("11111111111111111111");
		Documents.SelectAccountingPackage();
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
		System.out.println("11111111111111111111");
		Documents.SubmitButton();
		System.out.println("11111111111111111111");
		driver.get(url1);
		System.out.println("11111111111111111111");
		Sleep(15000);
		Set<String> handles3 = driver.getWindowHandles();
		List<String> hList3 = new ArrayList<String>(handles3);
		if(switchToRightWindow("Mail - Shahrukh Aatar - Outlook",hList3)){
			System.out.println("11111111111111111111");
		      }
		Office.ClickOnPulse();
		
		
		//driver.findElement(By.xpath("//span[@id='id__292']")).click();
		System.out.println("11111111111111111111");
		Office.SelectFirstEmail();
		System.out.println("11111111111111111111");
			Sleep(6000);
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Email Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickOnCompleteApplication();
			Sleep(6000);
			Set<String> handles = driver.getWindowHandles();
			List<String> hList = new ArrayList<String>(handles);
			if(switchToRightWindow("MyNucleus",hList)){
			      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
			      }
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Open Banking Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickonCompleteyourOpenBanking();
			
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
	
	
	@Test(enabled=true)
	public void TestCase3() throws Exception  {
		 test1 = extent.createTest("Test Case3", "NucleusTestCase ");
		try {
			System.out.println("TestCase3 started 3");
		ArrayList data=d.getData("TestCase3",path);
		String url = (String) data.get(2);
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		String CompanyName=(String) data.get(5);
		String CRN = (String) data.get(6);
		String Email = (String) data.get(7);
		String PhoneNumber = (String) data.get(8);
		String BirthDay = (String) data.get(9);
		String PostCode = (String) data.get(10);
	    String Fund = (String) data.get(11);
		String Months = (String) data.get(12);
		String Percent = (String) data.get(13);
		String BirthDay1= (String) data.get(14);
		String Email1 = (String) data.get(15);
		String MobileNumber = (String) data.get(16);
		String url1 = (String) data.get(17);
		String Bank = (String) data.get(18);
		String BankType = (String) data.get(19);
		String Email2 = (String) data.get(20);
		String MobileNumber1 = (String) data.get(21);
		String Email3 = (String) data.get(22);
		String MobileNumber2 = (String) data.get(23);
		String Email4 = (String) data.get(24);
		String MobileNumber3 = (String) data.get(25);
		String HouseNumber = (String) data.get(26);
		String HouseName = (String) data.get(27);
		String City = (String) data.get(28);
		String Street = (String) data.get(29);
		String Country = (String) data.get(30);
		
		String URL="https://myfunding.ncf-sandbox.com/deleteCompanyData/"+CRN;
		
		driver.get(URL);
		Sleep(5000);
      
		driver.get(url);
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Application landing page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		loginPage.login(Username,Password);	
		Screenshot();
		WriteExtentReport = test1.createNode("Navigate to Home Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		homePage.NewProposal();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate NewProposal Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		newproposalpage.LimitedCompany();
		newproposalpage.SearchCompanyName(CompanyName);
		newproposalpage.CompanyName(CompanyName);
		newproposalpage.PrimaryDirector();
		newproposalpage.EnterEmail(Email);
		System.out.println("email enter 1");
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
		newproposalpage.NextButton();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate Loan Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		try {
			LoanInfo.NucleusBusinessLoan();
			LoanInfo.ConfirmAlert();
			}
			catch(Exception e) {
				 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//p[@id='popupmessage']//following-sibling::a)[1]"))).isDisplayed();
				    
				driver.findElement(By.xpath("(//p[@id='popupmessage']//following-sibling::a)[1]")).click();
			       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Revenue Based Loan')]"))).isDisplayed();
			       driver.findElement(By.xpath("//div[contains(text(),'Revenue Based Loan')]")).click();
			       LoanInfo.ConfirmAlert();
			       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("card_terminals"))).isDisplayed();
				    
					driver.findElement(By.id("card_terminals")).sendKeys("5");
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("average_monthly_card_volume"))).isDisplayed();
					    
						driver.findElement(By.id("average_monthly_card_volume")).sendKeys("2000");
					   
				 
			}
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		LoanInfo.BrokerPercent(Percent);
		LoanInfo.NextStep();
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		
		
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
				DirectorInformation.ClickOnSubmit();
				Sleep(3000);
			} catch(Exception e)  
	        {  
	            System.out.println(e);  
	        }
				
				try {
				DirectorInformation.ClickOnShareholderDetails2();
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
				DirectorInformation.ClickOnSubmit();
				} catch(Exception e)  
		        {  
		            System.out.println(e);  
		        }
			
		
		
		
		
		DirectorInformation.ClickOnNext();
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Documents Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		Sleep(3000);
		System.out.println("11111111111111111111");
		Documents.SelectAccountingPackage();
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
		System.out.println("11111111111111111111");
		Documents.SubmitButton();
		System.out.println("11111111111111111111");
		driver.get(url1);
		System.out.println("11111111111111111111");
		Sleep(15000);
		Set<String> handles3 = driver.getWindowHandles();
		List<String> hList3 = new ArrayList<String>(handles3);
		if(switchToRightWindow("Mail - Shahrukh Aatar - Outlook",hList3)){
			System.out.println("11111111111111111111");
		      }
		Office.ClickOnPulse();
		
		
		//driver.findElement(By.xpath("//span[@id='id__292']")).click();
		System.out.println("11111111111111111111");
		Office.SelectFirstEmail();
		System.out.println("11111111111111111111");
			Sleep(6000);
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Email Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickOnCompleteApplication();
			Sleep(6000);
			Set<String> handles = driver.getWindowHandles();
			List<String> hList = new ArrayList<String>(handles);
			if(switchToRightWindow("MyNucleus",hList)){
			      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
			      }
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Open Banking Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickonCompleteyourOpenBanking();
			
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
	
	
	
	
	@Test(enabled=true)
	public void TestCase4() throws Exception  {
		 test1 = extent.createTest("Test Case4", "NucleusTestCase ");
		try {
			System.out.println("TestCase started 4");
		ArrayList data=d.getData("TestCase4",path);
		String url = (String) data.get(2);
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		String CompanyName=(String) data.get(5);
		String CRN = (String) data.get(6);
		String Email = (String) data.get(7);
		String PhoneNumber = (String) data.get(8);
		String BirthDay = (String) data.get(9);
		String PostCode = (String) data.get(10);
	    String Fund = (String) data.get(11);
		String Months = (String) data.get(12);
		String Percent = (String) data.get(13);
		String BirthDay1= (String) data.get(14);
		String Email1 = (String) data.get(15);
		String MobileNumber = (String) data.get(16);
		String url1 = (String) data.get(17);
		String Bank = (String) data.get(18);
		String BankType = (String) data.get(19);
		String Email2 = (String) data.get(20);
		String MobileNumber1 = (String) data.get(21);
		String Email3 = (String) data.get(22);
		String MobileNumber2 = (String) data.get(23);
		String Email4 = (String) data.get(24);
		String MobileNumber3 = (String) data.get(25);
		String HouseNumber = (String) data.get(26);
		String HouseName = (String) data.get(27);
		String City = (String) data.get(28);
		String Street = (String) data.get(29);
		String Country = (String) data.get(30);
		
		
		String URL="https://myfunding.ncf-sandbox.com/deleteCompanyData/"+CRN;
		
		driver.get(URL);
		Sleep(5000);
      
		driver.get(url);
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Application landing page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		loginPage.login(Username,Password);	
		Screenshot();
		WriteExtentReport = test1.createNode("Navigate to Home Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		homePage.NewProposal();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate NewProposal Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		newproposalpage.LimitedCompany();
		newproposalpage.SearchCompanyName(CompanyName);
		newproposalpage.CompanyName(CompanyName);
		newproposalpage.PrimaryDirector();
		newproposalpage.EnterEmail(Email);
		System.out.println("email enter 1");
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
		newproposalpage.NextButton();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate Loan Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		try {
			LoanInfo.NucleusBusinessLoan();
			LoanInfo.ConfirmAlert();
			}
			catch(Exception e) {
				 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//p[@id='popupmessage']//following-sibling::a)[1]"))).isDisplayed();
				    
				   driver.findElement(By.xpath("(//p[@id='popupmessage']//following-sibling::a)[1]")).click();
			       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Revenue Based Loan')]"))).isDisplayed();
			       driver.findElement(By.xpath("//div[contains(text(),'Revenue Based Loan')]")).click();
			       LoanInfo.ConfirmAlert();
			       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("card_terminals"))).isDisplayed();
				    
					driver.findElement(By.id("card_terminals")).sendKeys("5");
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("average_monthly_card_volume"))).isDisplayed();
					    
						driver.findElement(By.id("average_monthly_card_volume")).sendKeys("2000");
					   
				 
			}
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		LoanInfo.BrokerPercent(Percent);
		LoanInfo.NextStep();
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		
		
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
			DirectorInformation.ClickOnSubmit();
			Sleep(3000);
		} catch(Exception e)  
        {  
            System.out.println(e);  
        }
			
			try {
			DirectorInformation.ClickOnShareholderDetails2();
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
			DirectorInformation.ClickOnSubmit();
			} catch(Exception e)  
	        {  
	            System.out.println(e);  
	        }
		
		
		
			
		DirectorInformation.ClickOnNext();
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Documents Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		Sleep(3000);
		System.out.println("11111111111111111111");
		Documents.SelectAccountingPackage();
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
		System.out.println("11111111111111111111");
		Documents.SubmitButton();
		System.out.println("11111111111111111111");
		driver.get(url1);
		System.out.println("11111111111111111111");
		Sleep(15000);
		Set<String> handles3 = driver.getWindowHandles();
		List<String> hList3 = new ArrayList<String>(handles3);
		if(switchToRightWindow("Mail - Shahrukh Aatar - Outlook",hList3)){
			System.out.println("11111111111111111111");
		      }
		Office.ClickOnPulse();
		
		
		//driver.findElement(By.xpath("//span[@id='id__292']")).click();
		System.out.println("11111111111111111111");
		Office.SelectFirstEmail();
		System.out.println("11111111111111111111");
			Sleep(6000);
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Email Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickOnCompleteApplication();
			Sleep(6000);
			Set<String> handles = driver.getWindowHandles();
			List<String> hList = new ArrayList<String>(handles);
			if(switchToRightWindow("MyNucleus",hList)){
			      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
			      }
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Open Banking Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickonCompleteyourOpenBanking();
			
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
	
	
	
	@Test(enabled=true)
	public void TestCase5() throws Exception  {
		 test1 = extent.createTest("Test Case5", "NucleusTestCase ");
		try {
			System.out.println("TestCase started 5");
		ArrayList data=d.getData("TestCase5",path);
		System.out.println(path);
		String url = (String) data.get(2);
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		String CompanyName=(String) data.get(5);
		String CRN = (String) data.get(6);
		String Email = (String) data.get(7);
		String PhoneNumber = (String) data.get(8);
		String BirthDay = (String) data.get(9);
		String PostCode = (String) data.get(10);
	    String Fund = (String) data.get(11);
		String Months = (String) data.get(12);
		String Percent = (String) data.get(13);
		String BirthDay1= (String) data.get(14);
		String Email1 = (String) data.get(15);
		String MobileNumber = (String) data.get(16);
		String url1 = (String) data.get(17);
		String Bank = (String) data.get(18);
		String BankType = (String) data.get(19);
		String Email2 = (String) data.get(20);
		String MobileNumber1 = (String) data.get(21);
		String Email3 = (String) data.get(22);
		String MobileNumber2 = (String) data.get(23);
		String Email4 = (String) data.get(24);
		String MobileNumber3 = (String) data.get(25);
		String HouseNumber = (String) data.get(26);
		String HouseName = (String) data.get(27);
		String City = (String) data.get(28);
		String Street = (String) data.get(29);
		String Country = (String) data.get(30);
		
		
		String URL="https://myfunding.ncf-sandbox.com/deleteCompanyData/"+CRN;
		
		driver.get(URL);
		Sleep(5000);
      
		driver.get(url);
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Application landing page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		loginPage.login(Username,Password);	
		Screenshot();
		WriteExtentReport = test1.createNode("Navigate to Home Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		homePage.NewProposal();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate NewProposal Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		newproposalpage.LimitedCompany();
		newproposalpage.SearchCompanyName(CompanyName);
		newproposalpage.CompanyName(CompanyName);
		newproposalpage.PrimaryDirector();
		newproposalpage.EnterEmail(Email);
		System.out.println("email enter 1");
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
		newproposalpage.NextButton();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate Loan Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		try {
			LoanInfo.NucleusBusinessLoan();
			LoanInfo.ConfirmAlert();
			}
			catch(Exception e) {
				 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//p[@id='popupmessage']//following-sibling::a)[1]"))).isDisplayed();
				    
				driver.findElement(By.xpath("(//p[@id='popupmessage']//following-sibling::a)[1]")).click();
			       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Revenue Based Loan')]"))).isDisplayed();
			       driver.findElement(By.xpath("//div[contains(text(),'Revenue Based Loan')]")).click();
			       LoanInfo.ConfirmAlert();
			       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("card_terminals"))).isDisplayed();
				    
					driver.findElement(By.id("card_terminals")).sendKeys("5");
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("average_monthly_card_volume"))).isDisplayed();
					    
						driver.findElement(By.id("average_monthly_card_volume")).sendKeys("2000");
					   
				 
			}
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		LoanInfo.BrokerPercent(Percent);
		LoanInfo.NextStep();
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		
		
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
				DirectorInformation.ClickOnSubmit();
				Sleep(3000);
			} catch(Exception e)  
	        {  
	            System.out.println(e);  
	        }
				
				try {
				DirectorInformation.ClickOnShareholderDetails2();
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
				DirectorInformation.ClickOnSubmit();
				} catch(Exception e)  
		        {  
		            System.out.println(e);  
		        }
			
		
		DirectorInformation.ClickOnNext();
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Documents Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		Sleep(3000);
		System.out.println("11111111111111111111");
		Documents.SelectAccountingPackage();
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
		System.out.println("11111111111111111111");
		Documents.SubmitButton();
		System.out.println("11111111111111111111");
		driver.get(url1);
		System.out.println("11111111111111111111");
		Sleep(15000);
		Set<String> handles3 = driver.getWindowHandles();
		List<String> hList3 = new ArrayList<String>(handles3);
		if(switchToRightWindow("Mail - Shahrukh Aatar - Outlook",hList3)){
			System.out.println("11111111111111111111");
		      }
		Office.ClickOnPulse();
		
		
		//driver.findElement(By.xpath("//span[@id='id__292']")).click();
		System.out.println("11111111111111111111");
		Office.SelectFirstEmail();
		System.out.println("11111111111111111111");
			Sleep(6000);
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Email Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickOnCompleteApplication();
			Sleep(6000);
			Set<String> handles = driver.getWindowHandles();
			List<String> hList = new ArrayList<String>(handles);
			if(switchToRightWindow("MyNucleus",hList)){
			      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
			      }
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Open Banking Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickonCompleteyourOpenBanking();
			
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
	
	
	
	@Test(enabled=true)
	public void TestCase6() throws Exception  {
		 test1 = extent.createTest("Test Case6", "NucleusTestCase ");
		try {
			System.out.println("TestCase started 6");
		ArrayList data=d.getData("TestCase6",path);
		System.out.println(path);
		String url = (String) data.get(2);
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		String CompanyName=(String) data.get(5);
		String CRN = (String) data.get(6);
		String Email = (String) data.get(7);
		String PhoneNumber = (String) data.get(8);
		String BirthDay = (String) data.get(9);
		String PostCode = (String) data.get(10);
	    String Fund = (String) data.get(11);
		String Months = (String) data.get(12);
		String Percent = (String) data.get(13);
		String BirthDay1= (String) data.get(14);
		String Email1 = (String) data.get(15);
		String MobileNumber = (String) data.get(16);
		String url1 = (String) data.get(17);
		String Bank = (String) data.get(18);
		String BankType = (String) data.get(19);
		String Email2 = (String) data.get(20);
		String MobileNumber1 = (String) data.get(21);
		String Email3 = (String) data.get(22);
		String MobileNumber2 = (String) data.get(23);
		String Email4 = (String) data.get(24);
		String MobileNumber3 = (String) data.get(25);
		String HouseNumber = (String) data.get(26);
		String HouseName = (String) data.get(27);
		String City = (String) data.get(28);
		String Street = (String) data.get(29);
		String Country = (String) data.get(30);
		
		
		String URL="https://myfunding.ncf-sandbox.com/deleteCompanyData/"+CRN;
		
		driver.get(URL);
		Sleep(5000);
		driver.get(url);
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Application landing page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		loginPage.login(Username,Password);	
		Screenshot();
		WriteExtentReport = test1.createNode("Navigate to Home Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		homePage.NewProposal();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate NewProposal Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		newproposalpage.LimitedCompany();
		newproposalpage.SearchCompanyName(CompanyName);
		newproposalpage.CompanyName(CompanyName);
		newproposalpage.PrimaryDirector();
		newproposalpage.EnterEmail(Email);
		System.out.println("email enter 1");
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
		newproposalpage.NextButton();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate Loan Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		try {
			LoanInfo.NucleusBusinessLoan();
			LoanInfo.ConfirmAlert();
			}
			catch(Exception e) {
				 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//p[@id='popupmessage']//following-sibling::a)[1]"))).isDisplayed();
				    
				driver.findElement(By.xpath("(//p[@id='popupmessage']//following-sibling::a)[1]")).click();
			       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Revenue Based Loan')]"))).isDisplayed();
			       driver.findElement(By.xpath("//div[contains(text(),'Revenue Based Loan')]")).click();
			       LoanInfo.ConfirmAlert();
			       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("card_terminals"))).isDisplayed();
				    
					driver.findElement(By.id("card_terminals")).sendKeys("5");
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("average_monthly_card_volume"))).isDisplayed();
					    
						driver.findElement(By.id("average_monthly_card_volume")).sendKeys("2000");
					   
				 
			}
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		LoanInfo.BrokerPercent(Percent);
		LoanInfo.NextStep();
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000); 
		
		
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
			DirectorInformation.ClickOnSubmit();
		}
		catch(Exception e) {
			
		}
		
		
		try{
       // new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@alt='Edit Details'])[4]"))).isDisplayed();
		
			Sleep(3000);
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("edit_2"))).isDisplayed();
			Sleep(5000);
			driver.findElement(By.id("edit_2")).click();
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
		DirectorInformation.ClickOnSubmit();
		Sleep(3000);
		
		} catch(Exception e)  
        {  
            System.out.println(e);  
        }
			
			try {
			//	WebDriverWait wait=new WebDriverWait(driver, 20);
				
				
				new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("edit_3"))).isDisplayed();
				Sleep(5000);
				driver.findElement(By.id("edit_3")).click();
				
				//DirectorInformation.EditShareholderDetails1();
				Sleep(5000);
				System.out.println("555555555555555555555555555555555555555555");
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
				DirectorInformation.ClickOnSubmit();
				Sleep(3000);
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
				DirectorInformation.ClickOnSubmit();
				} catch(Exception e)  
		        {  
		            System.out.println(e);  
		        }
			
		
		DirectorInformation.ClickOnNext();
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Documents Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		Sleep(3000);
		System.out.println("11111111111111111111");
		Documents.SelectAccountingPackage();
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
		System.out.println("11111111111111111111");
		Documents.SubmitButton();
		System.out.println("11111111111111111111");
		driver.get(url1);
		System.out.println("11111111111111111111");
		Sleep(15000);
		Set<String> handles3 = driver.getWindowHandles();
		List<String> hList3 = new ArrayList<String>(handles3);
		if(switchToRightWindow("Mail - Shahrukh Aatar - Outlook",hList3)){
			System.out.println("11111111111111111111");
		      }
		Office.ClickOnPulse();
		
		
		//driver.findElement(By.xpath("//span[@id='id__292']")).click();
		System.out.println("11111111111111111111");
		Office.SelectFirstEmail();
		System.out.println("11111111111111111111");
			Sleep(6000);
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Email Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickOnCompleteApplication();
			Sleep(6000);
			Set<String> handles = driver.getWindowHandles();
			List<String> hList = new ArrayList<String>(handles);
			if(switchToRightWindow("MyNucleus",hList)){
			      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
			      }
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Open Banking Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickonCompleteyourOpenBanking();
			
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
	
	
	
	
	
	
	@Test(enabled=true)
	public void TestCase7() throws Exception  {
		 test1 = extent.createTest("Test Case7", "NucleusTestCase ");
		try {
			System.out.println("TestCase started 7");
		ArrayList data=d.getData("TestCase7",path);
		System.out.println(path);
		String url = (String) data.get(2);
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		String CompanyName=(String) data.get(5);
		String CRN = (String) data.get(6);
		String Email = (String) data.get(7);
		String PhoneNumber = (String) data.get(8);
		String BirthDay = (String) data.get(9);
		String PostCode = (String) data.get(10);
	    String Fund = (String) data.get(11);
		String Months = (String) data.get(12);
		String Percent = (String) data.get(13);
		String BirthDay1= (String) data.get(14);
		String Email1 = (String) data.get(15);
		String MobileNumber = (String) data.get(16);
		String url1 = (String) data.get(17);
		String Bank = (String) data.get(18);
		String BankType = (String) data.get(19);
		String Email2 = (String) data.get(20);
		String MobileNumber1 = (String) data.get(21);
		String Email3 = (String) data.get(22);
		String MobileNumber2 = (String) data.get(23);
		String Email4 = (String) data.get(24);
		String MobileNumber3 = (String) data.get(25);
		String HouseNumber = (String) data.get(26);
		String HouseName = (String) data.get(27);
		String City = (String) data.get(28);
		String Street = (String) data.get(29);
		String Country = (String) data.get(30);
		
		
		String URL="https://myfunding.ncf-sandbox.com/deleteCompanyData/"+CRN;
		
		driver.get(URL);
		Sleep(5000);
      
		driver.get(url);
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Application landing page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		loginPage.login(Username,Password);	
		Screenshot();
		WriteExtentReport = test1.createNode("Navigate to Home Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		homePage.NewProposal();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate NewProposal Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		newproposalpage.LimitedCompany();
		newproposalpage.SearchCompanyName(CompanyName);
		newproposalpage.CompanyName(CompanyName);
		newproposalpage.PrimaryDirector();
		newproposalpage.EnterEmail(Email);
		System.out.println("email enter 1");
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
		newproposalpage.NextButton();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate Loan Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		try {
			LoanInfo.NucleusBusinessLoan();
			LoanInfo.ConfirmAlert();
			}
			catch(Exception e) {
				 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//p[@id='popupmessage']//following-sibling::a)[1]"))).isDisplayed();
				    
				driver.findElement(By.xpath("(//p[@id='popupmessage']//following-sibling::a)[1]")).click();
			       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Revenue Based Loan')]"))).isDisplayed();
			       driver.findElement(By.xpath("//div[contains(text(),'Revenue Based Loan')]")).click();
			       LoanInfo.ConfirmAlert();
			       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("card_terminals"))).isDisplayed();
				    
					driver.findElement(By.id("card_terminals")).sendKeys("5");
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("average_monthly_card_volume"))).isDisplayed();
					    
						driver.findElement(By.id("average_monthly_card_volume")).sendKeys("2000");
					   
				 
			}
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		LoanInfo.BrokerPercent(Percent);
		LoanInfo.NextStep();
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		
		
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
				DirectorInformation.ClickOnSubmit();
				Sleep(3000);
			} catch(Exception e)  
	        {  
	            System.out.println(e);  
	        }
				
				try {
				DirectorInformation.ClickOnShareholderDetails2();
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
				DirectorInformation.ClickOnSubmit();
				} catch(Exception e)  
		        {  
		            System.out.println(e);  
		        }
			
		
		
		DirectorInformation.ClickOnNext();
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Documents Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		Sleep(3000);
		System.out.println("11111111111111111111");
		Documents.SelectAccountingPackage();
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
		System.out.println("11111111111111111111");
		Documents.SubmitButton();
		System.out.println("11111111111111111111");
		driver.get(url1);
		System.out.println("11111111111111111111");
		Sleep(15000);
		Set<String> handles3 = driver.getWindowHandles();
		List<String> hList3 = new ArrayList<String>(handles3);
		if(switchToRightWindow("Mail - Shahrukh Aatar - Outlook",hList3)){
			System.out.println("11111111111111111111");
		      }
		Office.ClickOnPulse();
		
		
		//driver.findElement(By.xpath("//span[@id='id__292']")).click();
		System.out.println("11111111111111111111");
		Office.SelectFirstEmail();
		System.out.println("11111111111111111111");
			Sleep(6000);
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Email Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickOnCompleteApplication();
			Sleep(6000);
			Set<String> handles = driver.getWindowHandles();
			List<String> hList = new ArrayList<String>(handles);
			if(switchToRightWindow("MyNucleus",hList)){
			      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
			      }
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Open Banking Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickonCompleteyourOpenBanking();
			
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
	
	
	
	
	@Test(enabled=true)
	public void TestCase8() throws Exception  {
		 test1 = extent.createTest("Test Case8", "NucleusTestCase ");
		try {
			System.out.println("TestCase started 8");
		ArrayList data=d.getData("TestCase8",path);
		System.out.println(path);
		String url = (String) data.get(2);
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		String CompanyName=(String) data.get(5);
		String CRN = (String) data.get(6);
		String Email = (String) data.get(7);
		String PhoneNumber = (String) data.get(8);
		String BirthDay = (String) data.get(9);
		String PostCode = (String) data.get(10);
	    String Fund = (String) data.get(11);
		String Months = (String) data.get(12);
		String Percent = (String) data.get(13);
		String BirthDay1= (String) data.get(14);
		String Email1 = (String) data.get(15);
		String MobileNumber = (String) data.get(16);
		String url1 = (String) data.get(17);
		String Bank = (String) data.get(18);
		String BankType = (String) data.get(19);
		String Email2 = (String) data.get(20);
		String MobileNumber1 = (String) data.get(21);
		String Email3 = (String) data.get(22);
		String MobileNumber2 = (String) data.get(23);
		String Email4 = (String) data.get(24);
		String MobileNumber3 = (String) data.get(25);
		String HouseNumber = (String) data.get(26);
		String HouseName = (String) data.get(27);
		String City = (String) data.get(28);
		String Street = (String) data.get(29);
		String Country = (String) data.get(30);
		
		String URL="https://myfunding.ncf-sandbox.com/deleteCompanyData/"+CRN;
		
		driver.get(URL);
		Sleep(5000);
		driver.get(url);
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Application landing page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		loginPage.login(Username,Password);	
		Screenshot();
		WriteExtentReport = test1.createNode("Navigate to Home Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		homePage.NewProposal();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate NewProposal Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		newproposalpage.LimitedCompany();
		newproposalpage.SearchCompanyName(CompanyName);
		newproposalpage.CompanyName(CompanyName);
		newproposalpage.PrimaryDirector();
		newproposalpage.EnterEmail(Email);
		System.out.println("email enter 1");
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
		newproposalpage.NextButton();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate Loan Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		try {
			LoanInfo.NucleusBusinessLoan();
			LoanInfo.ConfirmAlert();
			}
			catch(Exception e) {
				 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//p[@id='popupmessage']//following-sibling::a)[1]"))).isDisplayed();
				    
				driver.findElement(By.xpath("(//p[@id='popupmessage']//following-sibling::a)[1]")).click();
			       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Revenue Based Loan')]"))).isDisplayed();
			       driver.findElement(By.xpath("//div[contains(text(),'Revenue Based Loan')]")).click();
			       LoanInfo.ConfirmAlert();
			       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("card_terminals"))).isDisplayed();
				    
					driver.findElement(By.id("card_terminals")).sendKeys("5");
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("average_monthly_card_volume"))).isDisplayed();
					    
						driver.findElement(By.id("average_monthly_card_volume")).sendKeys("2000");
					   
				 
			}	
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		LoanInfo.BrokerPercent(Percent);
		LoanInfo.NextStep();
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		
		
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
				DirectorInformation.ClickOnSubmit();
				Sleep(3000);
			} catch(Exception e)  
	        {  
	            System.out.println(e);  
	        }
				
				try {
				DirectorInformation.ClickOnShareholderDetails2();
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
				DirectorInformation.ClickOnSubmit();
				} catch(Exception e)  
		        {  
		            System.out.println(e);  
		        }
			
		
		DirectorInformation.ClickOnNext();
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Documents Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		Sleep(3000);
		System.out.println("11111111111111111111");
		Documents.SelectAccountingPackage();
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
		System.out.println("11111111111111111111");
		Documents.SubmitButton();
		System.out.println("11111111111111111111");
		driver.get(url1);
		System.out.println("11111111111111111111");
		Sleep(15000);
		Set<String> handles3 = driver.getWindowHandles();
		List<String> hList3 = new ArrayList<String>(handles3);
		if(switchToRightWindow("Mail - Shahrukh Aatar - Outlook",hList3)){
			System.out.println("11111111111111111111");
		      }
		Office.ClickOnPulse();
		
		
		//driver.findElement(By.xpath("//span[@id='id__292']")).click();
		System.out.println("11111111111111111111");
		Office.SelectFirstEmail();
		System.out.println("11111111111111111111");
			Sleep(6000);
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Email Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickOnCompleteApplication();
			Sleep(6000);
			Set<String> handles = driver.getWindowHandles();
			List<String> hList = new ArrayList<String>(handles);
			if(switchToRightWindow("MyNucleus",hList)){
			      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
			      }
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Open Banking Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickonCompleteyourOpenBanking();
			
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

	
	@Test(enabled=true)
	public void TestCase9() throws Exception  {
		 test1 = extent.createTest("Test Case9", "NucleusTestCase ");
		try {
			System.out.println("TestCase started 9");
		ArrayList data=d.getData("TestCase9",path);
		System.out.println(path);
		String url = (String) data.get(2);
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		String CompanyName=(String) data.get(5);
		String CRN = (String) data.get(6);
		String Email = (String) data.get(7);
		String PhoneNumber = (String) data.get(8);
		String BirthDay = (String) data.get(9);
		String PostCode = (String) data.get(10);
	    String Fund = (String) data.get(11);
		String Months = (String) data.get(12);
		String Percent = (String) data.get(13);
		String BirthDay1= (String) data.get(14);
		String Email1 = (String) data.get(15);
		String MobileNumber = (String) data.get(16);
		String url1 = (String) data.get(17);
		String Bank = (String) data.get(18);
		String BankType = (String) data.get(19);
		String Email2 = (String) data.get(20);
		String MobileNumber1 = (String) data.get(21);
		String Email3 = (String) data.get(22);
		String MobileNumber2 = (String) data.get(23);
		String Email4 = (String) data.get(24);
		String MobileNumber3 = (String) data.get(25);
		String HouseNumber = (String) data.get(26);
		String HouseName = (String) data.get(27);
		String City = (String) data.get(28);
		String Street = (String) data.get(29);
		String Country = (String) data.get(30);
		
		String URL="https://myfunding.ncf-sandbox.com/deleteCompanyData/"+CRN;
		
		driver.get(URL);
		Sleep(5000);
      
		driver.get(url);
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Application landing page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		loginPage.login(Username,Password);	
		Screenshot();
		WriteExtentReport = test1.createNode("Navigate to Home Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		homePage.NewProposal();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate NewProposal Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		newproposalpage.LimitedCompany();
		newproposalpage.SearchCompanyName(CompanyName);
		newproposalpage.CompanyName(CompanyName);
		newproposalpage.PrimaryDirector();
		newproposalpage.EnterEmail(Email);
		System.out.println("email enter 1");
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
		newproposalpage.NextButton();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate Loan Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		try {
		LoanInfo.NucleusBusinessLoan();
		LoanInfo.ConfirmAlert();
		}
		catch(Exception e) {
			 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//p[@id='popupmessage']//following-sibling::a)[1]"))).isDisplayed();
			    
			driver.findElement(By.xpath("(//p[@id='popupmessage']//following-sibling::a)[1]")).click();
		       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Revenue Based Loan')]"))).isDisplayed();
		       driver.findElement(By.xpath("//div[contains(text(),'Revenue Based Loan')]")).click();
		       LoanInfo.ConfirmAlert();
		       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("card_terminals"))).isDisplayed();
			    
				driver.findElement(By.id("card_terminals")).sendKeys("5");
				 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("average_monthly_card_volume"))).isDisplayed();
				    
					driver.findElement(By.id("average_monthly_card_volume")).sendKeys("2000");
				   
			 
		}
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		LoanInfo.BrokerPercent(Percent);
		LoanInfo.NextStep();
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		
		
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
				DirectorInformation.ClickOnSubmit();
				Sleep(3000);
			} catch(Exception e)  
	        {  
	            System.out.println(e);  
	        }
				
				try {
				DirectorInformation.ClickOnShareholderDetails2();
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
				DirectorInformation.ClickOnSubmit();
				} catch(Exception e)  
		        {  
		            System.out.println(e);  
		        }
			
		
		
		DirectorInformation.ClickOnNext();
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Documents Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		Sleep(3000);
		System.out.println("11111111111111111111");
		Documents.SelectAccountingPackage();
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
		System.out.println("11111111111111111111");
		Documents.SubmitButton();
		System.out.println("11111111111111111111");
		driver.get(url1);
		System.out.println("11111111111111111111");
		Sleep(15000);
		Set<String> handles3 = driver.getWindowHandles();
		List<String> hList3 = new ArrayList<String>(handles3);
		if(switchToRightWindow("Mail - Shahrukh Aatar - Outlook",hList3)){
			System.out.println("11111111111111111111");
		      }
		Office.ClickOnPulse();
		
		
		//driver.findElement(By.xpath("//span[@id='id__292']")).click();
		System.out.println("11111111111111111111");
		Office.SelectFirstEmail();
		System.out.println("11111111111111111111");
			Sleep(6000);
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Email Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickOnCompleteApplication();
			Sleep(6000);
			Set<String> handles = driver.getWindowHandles();
			List<String> hList = new ArrayList<String>(handles);
			if(switchToRightWindow("MyNucleus",hList)){
			      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
			      }
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Open Banking Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickonCompleteyourOpenBanking();
			
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

	
	@Test(enabled=true)
	public void TestCase10() throws Exception  {
		 test1 = extent.createTest("Test Case10", "NucleusTestCase ");
		try {
			System.out.println("TestCase started 10");
		ArrayList data=d.getData("TestCase10",path);
		String url = (String) data.get(2);
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		String CompanyName=(String) data.get(5);
		String CRN = (String) data.get(6);
		String Email = (String) data.get(7);
		String PhoneNumber = (String) data.get(8);
		String BirthDay = (String) data.get(9);
		String PostCode = (String) data.get(10);
	    String Fund = (String) data.get(11);
		String Months = (String) data.get(12);
		String Percent = (String) data.get(13);
		String BirthDay1= (String) data.get(14);
		String Email1 = (String) data.get(15);
		String MobileNumber = (String) data.get(16);
		String url1 = (String) data.get(17);
		String Bank = (String) data.get(18);
		String BankType = (String) data.get(19);
		String Email2 = (String) data.get(20);
		String MobileNumber1 = (String) data.get(21);
		String Email3 = (String) data.get(22);
		String MobileNumber2 = (String) data.get(23);
		String Email4 = (String) data.get(24);
		String MobileNumber3 = (String) data.get(25);
		String HouseNumber = (String) data.get(26);
		String HouseName = (String) data.get(27);
		String City = (String) data.get(28);
		String Street = (String) data.get(29);
		String Country = (String) data.get(30);
		
		
		String URL="https://myfunding.ncf-sandbox.com/deleteCompanyData/"+CRN;
		
		driver.get(URL);
	
		Sleep(5000);
      
		driver.get(url);
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Application landing page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		loginPage.login(Username,Password);	
		Screenshot();
		WriteExtentReport = test1.createNode("Navigate to Home Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		homePage.NewProposal();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate NewProposal Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		newproposalpage.LimitedCompany();
		newproposalpage.SearchCompanyName(CompanyName);
		newproposalpage.CompanyName(CompanyName);
		newproposalpage.PrimaryDirector();
		newproposalpage.EnterEmail(Email);
		System.out.println("email enter 1");
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
		newproposalpage.NextButton();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate Loan Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		try {
			LoanInfo.NucleusBusinessLoan();
			LoanInfo.ConfirmAlert();
			}
			catch(Exception e) {
				 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//p[@id='popupmessage']//following-sibling::a)[1]"))).isDisplayed();
				    
				driver.findElement(By.xpath("(//p[@id='popupmessage']//following-sibling::a)[1]")).click();
			       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Revenue Based Loan')]"))).isDisplayed();
			       driver.findElement(By.xpath("//div[contains(text(),'Revenue Based Loan')]")).click();
			       LoanInfo.ConfirmAlert();
			       new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("card_terminals"))).isDisplayed();
				    
					driver.findElement(By.id("card_terminals")).sendKeys("5");
					 new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("average_monthly_card_volume"))).isDisplayed();
					    
						driver.findElement(By.id("average_monthly_card_volume")).sendKeys("2000");
			 
			}
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		LoanInfo.BrokerPercent(Percent);
		LoanInfo.NextStep();
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		
		
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
				DirectorInformation.ClickOnSubmit();
				Sleep(3000);
			} catch(Exception e)  
	        {  
	            System.out.println(e);  
	        }
				
				try {
				DirectorInformation.ClickOnShareholderDetails2();
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
				DirectorInformation.ClickOnSubmit();
				} catch(Exception e)  
		        {  
		            System.out.println(e);  
		        }
			
		
		DirectorInformation.ClickOnNext();
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Documents Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		Sleep(3000);
		System.out.println("11111111111111111111");
		Documents.SelectAccountingPackage();
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
		System.out.println("11111111111111111111");
		Documents.SubmitButton();
		System.out.println("11111111111111111111");
		driver.get(url1);
		System.out.println("11111111111111111111");
		Sleep(15000);
		Set<String> handles3 = driver.getWindowHandles();
		List<String> hList3 = new ArrayList<String>(handles3);
		if(switchToRightWindow("Mail - Shahrukh Aatar - Outlook",hList3)){
			System.out.println("11111111111111111111");
		      }
		Office.ClickOnPulse();
		
		
		//driver.findElement(By.xpath("//span[@id='id__292']")).click();
		System.out.println("11111111111111111111");
		Office.SelectFirstEmail();
		System.out.println("11111111111111111111");
			Sleep(6000);
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Email Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickOnCompleteApplication();
			Sleep(6000);
			Set<String> handles = driver.getWindowHandles();
			List<String> hList = new ArrayList<String>(handles);
			if(switchToRightWindow("MyNucleus",hList)){
			      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
			      }
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Open Banking Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickonCompleteyourOpenBanking();
			
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
	
	/*public void CaptureScreenshot() {
		 try {
             TakesScreenshot ts=(TakesScreenshot)driver;
             File source=ts.getScreenshotAs(OutputType.FILE);
             FileUtils.copyFile(source, new File("C:\\Users\\ShahrukhAatar\\OneDrive - Nucleus Services Ltd\\Documents\\Automation\\MyNucleusTest\\test-output"));
             System.out.println("Screenshot taken");

 } catch (Exception e) {
             System.out.println("Exception "+e.getMessage());
 }       
		
	}*/ 

}
