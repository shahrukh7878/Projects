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




public class Login1 extends TestBase {
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
	
	String path= "C:\\Users\\ShahrukhAata_l4\\Project\\Test\\TestData\\student1 - Copy.xls";
	public Login1() {
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
	
	
	@Test(enabled=false)
	public void TestCase1() throws Exception  {
		 test1 = extent.createTest("Test Case1", "NucleusTestCase ");
		try {
			System.out.println("TestCase started 1");
			
		ArrayList data=d.getData("TestCase1",path);
		
		String url = (String) data.get(2);
		String CompanyName=(String) data.get(5);
		String CRN = (String) data.get(6);
		String IncorporationDate = (String) data.get(7);
		String ICORegistrationNumber = (String) data.get(8);
		String Street = (String) data.get(9);
		String City = (String) data.get(10);
		String State = (String) data.get(11);
		String Country = (String) data.get(12);
		String PostCode = (String) data.get(13);
		String FirstName = (String) data.get(14);
		String LastName = (String) data.get(15);
		String AppointedOn = (String) data.get(16);
		String DOB = (String) data.get(17);
		String MobileNumber = (String) data.get(18);
		String EmaiID = (String) data.get(19);
		String ResidentialPostcode = (String) data.get(20);
		String BrokerFirstName = (String) data.get(21);
		String BrokerLastName = (String) data.get(22);
		String BrokerDOB = (String) data.get(23);
		String BrokerMobileNumber = (String) data.get(24);
		String BrokerEmaiID = (String) data.get(25);
	  
		 driver.get(url);
		
         new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Sign Up')]"))).isDisplayed();//
	     driver.findElement(By.xpath("//a[contains(text(),'Sign Up')]")).click();
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("limited_company"))).isDisplayed();
	     driver.findElement(By.id("limited_company")).click();
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("comp_name"))).isDisplayed();
	     driver.findElement(By.id("comp_name")).sendKeys(CompanyName);
	     
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("myUL"))).isDisplayed();
	     driver.findElement(By.id("myUL")).click();
	     
	     
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("ico"))).isDisplayed();
	      driver.findElement(By.id("ico")).sendKeys(ICORegistrationNumber);
	     
	     /*new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='crn']"))).isDisplayed();
	     driver.findElement(By.xpath("//input[@id='crn']")).sendKeys(CRN);
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("doi"))).isDisplayed();
	     driver.findElement(By.id("doi")).sendKeys(IncorporationDate);
	       
	     
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_company_street_1"))).isDisplayed();
	      driver.findElement(By.id("d_company_street_1")).sendKeys(Street);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_company_city_1"))).isDisplayed();
	      driver.findElement(By.id("d_company_city_1")).sendKeys(City);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_company_state_1"))).isDisplayed();
	      driver.findElement(By.id("d_company_state_1")).sendKeys(State);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_company_country_1"))).isDisplayed();
	      driver.findElement(By.id("d_company_country_1")).sendKeys(Country);
	      
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_company_zip_1"))).isDisplayed();
	      driver.findElement(By.id("d_company_zip_1")).sendKeys(PostCode);*/
	      
	      
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@id='btnAddDir']//following-sibling::div)[1]"))).isDisplayed();//
	      driver.findElement(By.xpath("(//a[@id='btnAddDir']//following-sibling::div)[1]")).click();
	    
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='com_dir_1']"))).isDisplayed();//
	      driver.findElement(By.xpath("//div[@id='com_dir_1']")).click();
	    
	     /* new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_first_name_1"))).isDisplayed();
	      driver.findElement(By.id("d_first_name_1")).sendKeys(FirstName);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_last_name_1"))).isDisplayed();
	      driver.findElement(By.id("d_last_name_1")).sendKeys(LastName);
		
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_appointed_1"))).isDisplayed();
	      driver.findElement(By.id("d_appointed_1")).sendKeys(AppointedOn);*/
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_dob_1"))).isDisplayed();
	      driver.findElement(By.id("d_dob_1")).sendKeys(DOB);
	      
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_mobile_1"))).isDisplayed();
	      driver.findElement(By.id("d_mobile_1")).sendKeys(MobileNumber);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_email_1"))).isDisplayed();
	      driver.findElement(By.id("d_email_1")).sendKeys(EmaiID);
	     
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_residential_address_1"))).isDisplayed();
	      driver.findElement(By.id("d_residential_address_1")).sendKeys(ResidentialPostcode);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(text(),'Find Address')])[2]"))).isDisplayed();//
	      driver.findElement(By.xpath("(//button[contains(text(),'Find Address')])[2]")).click();
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'1 Roundhead Street, Nantwich,Cheshire')]"))).isDisplayed();//
	      driver.findElement(By.xpath("//li[contains(text(),'1 Roundhead Street, Nantwich,Cheshire')]")).click();
	      
	    //  new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(text(),'Find Address')])[2]"))).isDisplayed();//
	     // driver.findElement(By.xpath("(//button[contains(text(),'Find Address')])[2]")).click();
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@data-toggle='collapse'])[3]"))).isDisplayed();
	      driver.findElement(By.xpath("(//div[@data-toggle='collapse'])[3]")).click();
	    
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("b_first_name"))).isDisplayed();
	      driver.findElement(By.id("b_first_name")).sendKeys(BrokerFirstName);
	     
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("b_first_name"))).isDisplayed();
	      driver.findElement(By.id("b_first_name")).sendKeys(BrokerFirstName);
	     
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("b_last_name"))).isDisplayed();
	      driver.findElement(By.id("b_last_name")).sendKeys(BrokerLastName);
	     
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("b_dob"))).isDisplayed();
	      driver.findElement(By.id("b_dob")).sendKeys(BrokerDOB);
	      
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='b_mobile']"))).isDisplayed();
	      driver.findElement(By.xpath("//input[@id='b_mobile']")).sendKeys(BrokerMobileNumber);
	      
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='b_email']"))).isDisplayed();
	      driver.findElement(By.xpath("//input[@id='b_email']")).sendKeys(BrokerEmaiID);
	      
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("intro_assess"))).isDisplayed();
	      driver.findElement(By.id("intro_assess")).click();
	      
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
	
	
	@Test(enabled=false)
	public void TestCase2() throws Exception  {
		 test1 = extent.createTest("Test Case2", "NucleusTestCase ");
		try {
			System.out.println("TestCase started 2");
			
		ArrayList data=d.getData("TestCase2",path);
		
		String url = (String) data.get(2);
		String CompanyName=(String) data.get(5);
		String CRN = (String) data.get(6);
		String IncorporationDate = (String) data.get(7);
		String ICORegistrationNumber = (String) data.get(8);
		String Street = (String) data.get(9);
		String City = (String) data.get(10);
		String State = (String) data.get(11);
		String Country = (String) data.get(12);
		String PostCode = (String) data.get(13);
		String FirstName = (String) data.get(14);
		String LastName = (String) data.get(15);
		String AppointedOn = (String) data.get(16);
		String DOB = (String) data.get(17);
		String MobileNumber = (String) data.get(18);
		String EmaiID = (String) data.get(19);
		String ResidentialPostcode = (String) data.get(20);
		String BrokerFirstName = (String) data.get(21);
		String BrokerLastName = (String) data.get(22);
		String BrokerDOB = (String) data.get(23);
		String BrokerMobileNumber = (String) data.get(24);
		String BrokerEmaiID = (String) data.get(25);
	  
		 driver.get(url);
		
         new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Sign Up')]"))).isDisplayed();//
	     driver.findElement(By.xpath("//a[contains(text(),'Sign Up')]")).click();
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("limited_company"))).isDisplayed();
	     driver.findElement(By.id("limited_company")).click();
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("comp_name"))).isDisplayed();
	     driver.findElement(By.id("comp_name")).sendKeys(CompanyName);
	     
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("myUL"))).isDisplayed();
	     driver.findElement(By.id("myUL")).click();
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("ico"))).isDisplayed();
	      driver.findElement(By.id("ico")).sendKeys(ICORegistrationNumber);
	     
	     /*new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='crn']"))).isDisplayed();
	     driver.findElement(By.xpath("//input[@id='crn']")).sendKeys(CRN);
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("doi"))).isDisplayed();
	     driver.findElement(By.id("doi")).sendKeys(IncorporationDate);
	       
	     
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_company_street_1"))).isDisplayed();
	      driver.findElement(By.id("d_company_street_1")).sendKeys(Street);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_company_city_1"))).isDisplayed();
	      driver.findElement(By.id("d_company_city_1")).sendKeys(City);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_company_state_1"))).isDisplayed();
	      driver.findElement(By.id("d_company_state_1")).sendKeys(State);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_company_country_1"))).isDisplayed();
	      driver.findElement(By.id("d_company_country_1")).sendKeys(Country);
	      
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_company_zip_1"))).isDisplayed();
	      driver.findElement(By.id("d_company_zip_1")).sendKeys(PostCode);*/
	     
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@id='btnAddDir']//following-sibling::div)[1]"))).isDisplayed();//
	      driver.findElement(By.xpath("(//a[@id='btnAddDir']//following-sibling::div)[1]")).click();
	    
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='com_dir_1']"))).isDisplayed();//
	      driver.findElement(By.xpath("//div[@id='com_dir_1']")).click();
	    
	     /* new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_first_name_1"))).isDisplayed();
	      driver.findElement(By.id("d_first_name_1")).sendKeys(FirstName);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_last_name_1"))).isDisplayed();
	      driver.findElement(By.id("d_last_name_1")).sendKeys(LastName);
		
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_appointed_1"))).isDisplayed();
	      driver.findElement(By.id("d_appointed_1")).sendKeys(AppointedOn);*/
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_dob_1"))).isDisplayed();
	      driver.findElement(By.id("d_dob_1")).sendKeys(DOB);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_mobile_1"))).isDisplayed();
	      driver.findElement(By.id("d_mobile_1")).sendKeys(MobileNumber);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_email_1"))).isDisplayed();
	      driver.findElement(By.id("d_email_1")).sendKeys(EmaiID);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='d_is_broker_1']"))).isDisplayed();//
	      driver.findElement(By.xpath("//label[@for='d_is_broker_1']")).click();
	     
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_residential_address_1"))).isDisplayed();
	      driver.findElement(By.id("d_residential_address_1")).sendKeys(ResidentialPostcode);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(text(),'Find Address')])[2]"))).isDisplayed();//
	      driver.findElement(By.xpath("(//button[contains(text(),'Find Address')])[2]")).click();
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'1 Roundhead Street, Nantwich,Cheshire')]"))).isDisplayed();//
	      driver.findElement(By.xpath("//li[contains(text(),'1 Roundhead Street, Nantwich,Cheshire')]")).click();
	      
	      //new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(text(),'Find Address')])[2]"))).isDisplayed();//
	     //driver.findElement(By.xpath("(//button[contains(text(),'Find Address')])[2]")).click();
	      
	      /*new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@data-toggle='collapse'])[3]"))).isDisplayed();
	      driver.findElement(By.xpath("(//div[@data-toggle='collapse'])[3]")).click();
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("b_first_name"))).isDisplayed();
	      driver.findElement(By.id("b_first_name")).sendKeys(BrokerFirstName);
	     
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("b_first_name"))).isDisplayed();
	      driver.findElement(By.id("b_first_name")).sendKeys(BrokerFirstName);
	     
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("b_last_name"))).isDisplayed();
	      driver.findElement(By.id("b_last_name")).sendKeys(BrokerLastName);
	     
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("b_dob"))).isDisplayed();
	      driver.findElement(By.id("b_dob")).sendKeys(BrokerDOB);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='b_mobile']"))).isDisplayed();
	      driver.findElement(By.xpath("//input[@id='b_mobile']")).sendKeys(BrokerMobileNumber);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='b_email']"))).isDisplayed();
	      driver.findElement(By.xpath("//input[@id='b_email']")).sendKeys(BrokerEmaiID);*/
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("intro_assess"))).isDisplayed();
	      driver.findElement(By.id("intro_assess")).click();
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
			System.out.println("TestCase started 3");
			
		ArrayList data=d.getData("TestCase3",path);
		
		String url = (String) data.get(2);
		String CompanyName=(String) data.get(5);
		String CRN = (String) data.get(6);
		String IncorporationDate = (String) data.get(7);
		String ICORegistrationNumber = (String) data.get(8);
		String Street = (String) data.get(9);
		String City = (String) data.get(10);
		String State = (String) data.get(11);
		String Country = (String) data.get(12);
		String PostCode = (String) data.get(13);
		String FirstName = (String) data.get(14);
		String LastName = (String) data.get(15);
		String AppointedOn = (String) data.get(16);
		String DOB = (String) data.get(17);
		String MobileNumber = (String) data.get(18);
		String EmaiID = (String) data.get(19);
		String ResidentialPostcode = (String) data.get(20);
		String BrokerFirstName = (String) data.get(21);
		String BrokerLastName = (String) data.get(22);
		String BrokerDOB = (String) data.get(23);
		String BrokerMobileNumber = (String) data.get(24);
		String BrokerEmaiID = (String) data.get(25);
	  
		 driver.get(url);
		
         new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Sign Up')]"))).isDisplayed();//
	     driver.findElement(By.xpath("//a[contains(text(),'Sign Up')]")).click();
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("limited_company"))).isDisplayed();
	     driver.findElement(By.id("limited_company")).click();
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("comp_name"))).isDisplayed();
	     driver.findElement(By.id("comp_name")).sendKeys(CompanyName);
	     
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("myUL"))).isDisplayed();
	     driver.findElement(By.id("myUL")).click();
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("ico"))).isDisplayed();
	      driver.findElement(By.id("ico")).sendKeys(ICORegistrationNumber);
	     
	     /*new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='crn']"))).isDisplayed();
	     driver.findElement(By.xpath("//input[@id='crn']")).sendKeys(CRN);
	     new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("doi"))).isDisplayed();
	     driver.findElement(By.id("doi")).sendKeys(IncorporationDate);
	       
	     
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_company_street_1"))).isDisplayed();
	      driver.findElement(By.id("d_company_street_1")).sendKeys(Street);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_company_city_1"))).isDisplayed();
	      driver.findElement(By.id("d_company_city_1")).sendKeys(City);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_company_state_1"))).isDisplayed();
	      driver.findElement(By.id("d_company_state_1")).sendKeys(State);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_company_country_1"))).isDisplayed();
	      driver.findElement(By.id("d_company_country_1")).sendKeys(Country);
	      
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_company_zip_1"))).isDisplayed();
	      driver.findElement(By.id("d_company_zip_1")).sendKeys(PostCode);*/
	     
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@id='btnAddDir']//following-sibling::div)[1]"))).isDisplayed();//
	      driver.findElement(By.xpath("(//a[@id='btnAddDir']//following-sibling::div)[1]")).click();
	    
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='com_dir_1']"))).isDisplayed();//
	      driver.findElement(By.xpath("//div[@id='com_dir_1']")).click();
	    
	     /* new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_first_name_1"))).isDisplayed();
	      driver.findElement(By.id("d_first_name_1")).sendKeys(FirstName);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_last_name_1"))).isDisplayed();
	      driver.findElement(By.id("d_last_name_1")).sendKeys(LastName);
		
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_appointed_1"))).isDisplayed();
	      driver.findElement(By.id("d_appointed_1")).sendKeys(AppointedOn);*/
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_dob_1"))).isDisplayed();
	      driver.findElement(By.id("d_dob_1")).sendKeys(DOB);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_mobile_1"))).isDisplayed();
	      driver.findElement(By.id("d_mobile_1")).sendKeys(MobileNumber);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_email_1"))).isDisplayed();
	      driver.findElement(By.id("d_email_1")).sendKeys(EmaiID);
	      
	     // new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='d_is_broker_1']"))).isDisplayed();//
	      //driver.findElement(By.xpath("//label[@for='d_is_broker_1']")).click();
	     
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("d_residential_address_1"))).isDisplayed();
	      driver.findElement(By.id("d_residential_address_1")).sendKeys(ResidentialPostcode);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(text(),'Find Address')])[2]"))).isDisplayed();//
	      driver.findElement(By.xpath("(//button[contains(text(),'Find Address')])[2]")).click();
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'1 Roundhead Street, Nantwich,Cheshire')]"))).isDisplayed();//
	      driver.findElement(By.xpath("//li[contains(text(),'1 Roundhead Street, Nantwich,Cheshire')]")).click();
	      
	      //new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(text(),'Find Address')])[2]"))).isDisplayed();//
	     //driver.findElement(By.xpath("(//button[contains(text(),'Find Address')])[2]")).click();
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@data-toggle='collapse'])[3]"))).isDisplayed();
	      driver.findElement(By.xpath("(//div[@data-toggle='collapse'])[3]")).click();
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("b_first_name"))).isDisplayed();
	      driver.findElement(By.id("b_first_name")).sendKeys(BrokerFirstName);
	     
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("b_first_name"))).isDisplayed();
	      driver.findElement(By.id("b_first_name")).sendKeys(BrokerFirstName);
	     
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("b_last_name"))).isDisplayed();
	      driver.findElement(By.id("b_last_name")).sendKeys(BrokerLastName);
	     
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("b_dob"))).isDisplayed();
	      driver.findElement(By.id("b_dob")).sendKeys(BrokerDOB);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='b_mobile']"))).isDisplayed();
	      driver.findElement(By.xpath("//input[@id='b_mobile']")).sendKeys(BrokerMobileNumber);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='b_email']"))).isDisplayed();
	      driver.findElement(By.xpath("//input[@id='b_email']")).sendKeys(BrokerEmaiID);
	      
	      new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.id("intro_assess"))).isDisplayed();
	      driver.findElement(By.id("intro_assess")).click();
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
