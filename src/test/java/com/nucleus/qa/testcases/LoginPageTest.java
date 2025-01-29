package com.nucleus.qa.testcases;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;
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
import com.nucleus.qa.pages.HomePage;
import com.nucleus.qa.pages.LoanInformationPage;
import com.nucleus.qa.pages.LoginPage;
import com.nucleus.qa.pages.NewProposalPage;
import com.nucleus.qa.pages.NucleusSaleforcePage;
import com.nucleus.qa.pages.OfficePage;
import com.nucleus.qa.pages.ShareholderDetailsPage;
import com.nucleus.qa.pages.myPulsePage;
import com.nucleus.qa.util.TestUtil;




public class LoginPageTest extends TestBase {
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
	
	String path= "C://Users//ShahrukhAata_l4//Project//Test//TestData//student.xls";
	public LoginPageTest() {
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
			sStatus=false;
			
			sErrorLog = e.toString();
		}
		
	   
	   if(sStatus==true)
		{
			Framework.CreateResults(true, "Step Pass");
		}
		else
		{
			Framework.CreateResults(false, sErrorLog);
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
		System.out.println(path);
		String url = (String) data.get(2);
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		
		System.out.println(Password);
		
		String CompanyName=(String) data.get(5);
		String Email = (String) data.get(6);
		String PhoneNumber = (String) data.get(7);
		String BirthDay = (String) data.get(8);
		String PostCode = (String) data.get(9);
		String HouseNumber = (String) data.get(10);
		String HouseName = (String) data.get(11);
		String City = (String) data.get(12);
		String Street = (String) data.get(13);
		String Country = (String) data.get(14);
		String Fund = (String) data.get(15);
		String Months = (String) data.get(16);
		String Percent = (String) data.get(17);
		System.out.println(Percent);
		String BirthDay1= (String) data.get(18);
		String Email1 = (String) data.get(19);
		String MobileNumber = (String) data.get(20);
		String PhoneNO = (String) data.get(21);
		String url1 = (String) data.get(22);
		String Bank = (String) data.get(23);
		String BankType = (String) data.get(24);
		String Name = (String) data.get(29);
		
		
		/*driver.get("https://nucleus--qa.sandbox.my.salesforce.com/");
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("shahrukh.aatar@mypulse.io.qa.qa");
		
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Quality@123");
		
		driver.findElement(By.xpath("//input[@id='Login']")).click();
		
		/*try {
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


			}*/
		
		
		/*driver.findElement(By.xpath("//input[@id='phSearchInput']")).sendKeys(CompanyName);
				Sleep(5000);
		driver.findElement(By.xpath("//input[@id='phSearchButton']")).click();
		Sleep(5000);
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'"+CompanyName+"')]"))).isDisplayed();
		driver.findElement(By.xpath("//a[contains(text(),'"+CompanyName+"')]")).click();
		
		
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'Contacts')])[1]"))).isDisplayed();
		
		
		WebElement clickable = driver.findElement(By.xpath("(//span[contains(text(),'Contacts')])[1]"));
        new Actions(driver)
                .clickAndHold(clickable)
                .perform();
		
        Sleep(3000);
        driver.switchTo().frame("RLPanelFrame");
        
        
        
        System.out.println("111111111111111111111111111111");
		//driver.findElement(By.id("00QPt00000DRcEb_00N5800000DVdIG_link")).click();
		
		
      // new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Sinead Ro-anne Hunter- Caprice')])"))).isDisplayed();
		
		//driver.findElement(By.xpath("(//a[contains(text(),'Sinead Ro-anne Hunter- Caprice')])")).click();
		
		
		 new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@target='_parent'])[3]"))).isDisplayed();
			
			driver.findElement(By.xpath("(//a[@target='_parent'])[3]")).click();
			//Select drpCountry = new Select(driver.findElement(By.xpath("//select[@id='00N4I00000ERiSM']")));
		
			Sleep(5000);
			
			
			Object[] windowHandles=driver.getWindowHandles().toArray();
	        driver.switchTo().window((String) windowHandles[0]);
	        
			
			Sleep(5000);
			//driver.findElement(By.xpath("//h2[contains(text(),'Sinead Ro-anne Hunter- Caprice')]")).click();
		
			//driver.findElement(By.xpath("//label[contains(text(),'Email Risk level')]")).click();
			 
			WebElement element = driver.findElement(By.xpath("//select[@id='00N4I00000ERiSM']"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			
			// new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@tabindex='26']"))).isDisplayed();	
		     //driver.findElement(By.xpath("//select[@id='00N4I00000ERiSM']")).click();
		 
		     Sleep(3000);
		     driver.findElement(By.xpath("//option[contains(text(),'Low')]")).click();
			 
		
		//Select sc = new Select(driver.findElement(By.xpath("//select[@id='00N4I00000ERiSM']")));
		
		//sc.selectByVisibleText("Low");
		
			
		 new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value=' Save ']"))).isDisplayed();
			
			driver.findElement(By.xpath("//input[@value=' Save ']")).click();
		
			// new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'Financial Data')])[1]"))).isDisplayed();
				
				//driver.findElement(By.xpath("(//span[contains(text(),'Financial Data')])[1]")).click();
			
			
			///2nd customer finacial account name update
			
				WebElement clickable1 = driver.findElement(By.xpath("((//span[contains(text(),'Financial Data')])//parent::a)//child::span//child::span"));
		        new Actions(driver)
		                .clickAndHold(clickable1)
		                .perform();
		
		        
		        Sleep(3000);
		        driver.switchTo().frame("RLPanelFrame");
		
		        
		        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@target='_parent'])[3]"))).isDisplayed();
				
				driver.findElement(By.xpath("(//a[@target='_parent'])[3]")).click();
			
		        
				Sleep(5000);
				
				
				Object[] windowHandles1=driver.getWindowHandles().toArray();
		        driver.switchTo().window((String) windowHandles1[0]);
		        
		        
		        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='00N5800000Dk2vB']"))).isDisplayed();
				
				driver.findElement(By.xpath("//input[@id='00N5800000Dk2vB']")).clear();
			
				driver.findElement(By.xpath("//input[@id='00N5800000Dk2vB']")).sendKeys(CompanyName);				
				
		System.out.println("HIT & CO LIMITED");
		

		
				
				 new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value=' Save ']"))).isDisplayed();
				driver.findElement(By.xpath("//input[@value=' Save ']")).click();				
				
				
				///2nd customer finacial account name update
				WebElement clickable2 = driver.findElement(By.xpath("((//span[contains(text(),'Financial Data')])//parent::a)//child::span//child::span"));
		        new Actions(driver)
		                .clickAndHold(clickable2)
		                .perform();

		        
		        Sleep(3000);
		        driver.switchTo().frame("RLPanelFrame");

		        
		        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@target='_parent'])[6]"))).isDisplayed();
				
				driver.findElement(By.xpath("(//a[@target='_parent'])[6]")).click();
			
		        
				Sleep(5000);
				
				
				Object[] windowHandles4=driver.getWindowHandles().toArray();
		        driver.switchTo().window((String) windowHandles4[0]);
		        
		        
		        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='00N5800000Dk2vB']"))).isDisplayed();
				
				driver.findElement(By.xpath("//input[@id='00N5800000Dk2vB']")).clear();
			
				driver.findElement(By.xpath("//input[@id='00N5800000Dk2vB']")).sendKeys(CompanyName);				
				
				
				
				
				
						
						 new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value=' Save ']"))).isDisplayed();
						driver.findElement(By.xpath("//input[@value=' Save ']")).click();				
			
				
				
				
				
				
				WebElement clickable3 = driver.findElement(By.xpath("(//span[contains(text(),'Contacts')])[1]//child::span"));
		        new Actions(driver)
		                .clickAndHold(clickable3)
		                .perform();
		        
		        Sleep(3000);
		        driver.switchTo().frame("RLPanelFrame");
		        System.out.println("777777777777777");
				
				 new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@target='_parent'])[5]"))).isDisplayed();
					driver.findElement(By.xpath("(//a[@target='_parent'])[5]")).click();				
					System.out.println("777777777777777");
					Object[] windowHandles2=driver.getWindowHandles().toArray();
			        driver.switchTo().window((String) windowHandles2[0]);
			        System.out.println("777777777777777");
			        
			        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Click Here')]"))).isDisplayed();
					driver.findElement(By.xpath("//a[contains(text(),'Click Here')]")).click();				
					System.out.println("777777777777777");
					
					
					Object[] windowHandles3=driver.getWindowHandles().toArray();
			        driver.switchTo().window((String) windowHandles3[0]);
			        
			        
			        Set<String> handles4 = driver.getWindowHandles();
					List<String> hList4 = new ArrayList<String>(handles4);
					if(switchToRightWindow("MyNucleus",hList4)){
					      }
					
					//https://myfunding.ncf-sandbox.com/mynucleus/003Pt00000H45Jx/crt
			        
						
						
					String text = driver.getCurrentUrl();
					
					
					System.out.println(text);
					
                    System.out.println("inininin");
                    
                    
                    
                    Thread.sleep(6000);
				/*	new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Choose File')]"))).isDisplayed();
					driver.findElement(By.xpath("//label[contains(text(),'Choose File')]")).click();*/				
					/* System.out.println("out out");
					
					 WebElement element1 = driver.findElement(By.xpath("//label[contains(text(),'Choose File')]"));
						JavascriptExecutor executor1 = (JavascriptExecutor)driver;
						executor1.executeScript("arguments[0].click();", element1);
						
						
						
						String filepath = "D:\\Guru99Demo\\Files\\";
				        String inputFilePath = "D:\\Guru99Demo\\Files\\";
				        Screen s = new Screen();
				        //Pattern fileInputTextBox = new Pattern(filepath + "FileTextBox.PNG");
				        //Pattern openButton = new Pattern(filepath + "OpenButton.PNG", 0);
						
				      //  s.click(openButton);

					 //WebDriver driver = getWebDriver();
				    
					System.out.println("777777777777777");*/
	
       /* driver.get("https://myfunding.ncf-sandbox.com/deleteCompanyData/01554169");
	
		driver.get("https://nucleus--qa.sandbox.my.salesforce.com/");
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("shahrukh.aatar@mypulse.io.qa");
		
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Quality@123");
		
		System.out.println("777777777777777");
		
		driver.findElement(By.xpath("//input[@id='Login']")).click();
		Sleep(3000);
		try {
		System.out.println("777777777777777");
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='uiImage'])[1]"))).isDisplayed();
		System.out.println("777777777777777");
		driver.findElement(By.xpath("(//span[@class='uiImage'])[1]")).click();
		System.out.println("777777777777777");
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Switch to Salesforce Classic')]"))).isDisplayed();
		driver.findElement(By.xpath("//a[contains(text(),'Switch to Salesforce Classic')]")).click();
	     }
		catch(Exception e) {
			
		}
		//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@title='User']"))).isDisplayed();
		// new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(text(),'"+CWID+"')]"))).isDisplayed();
		//driver.findElement(By.xpath("//img[@title='User']")).click();
		//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Switch to Salesforce Classic')]"))).isDisplayed();
		//driver.findElement(By.xpath("//a[contains(text(),'Switch to Salesforce Classic')]")).click();
		
		//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Leads')]"))).isDisplayed();
		//driver.findElement(By.xpath("//a[contains(text(),'Leads')]")).click();
		
		//Sleep(5000);
		
		//System.out.println("click on serch111111111111111111");
		//Sleep(5000);
		//driver.findElement(By.xpath("//input[@id='phSearchInput']")).sendKeys(CompanyName);
		//System.out.println("click on serch222222222222222222222222222");
		//Sleep(5000);
		//driver.findElement(By.xpath("//input[@id='phSearchButton']")).click();
		
		//Sleep(5000);
		//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Leads')]"))).isDisplayed();
		//driver.findElement(By.xpath("//a[contains(text(),'Leads')]")).click();
		
		//Sleep(5000);
		//driver.findElement(By.xpath("//input[@id='phSearchInput']")).sendKeys(CompanyName);
		//System.out.println("click on serch222222222222222222222222222");
		//Sleep(5000);
		//driver.findElement(By.xpath("//input[@id='phSearchButton']")).click();		
		//System.out.println("click on serch23333333333333333333333333333333");
		try {

			for(int iCount=1;iCount<=100;iCount++) {
				Sleep(3000);
				driver.findElement(By.xpath("//input[@id='phSearchInput']")).sendKeys(CompanyName);
				System.out.println("sendkeys111111111111111111");
				//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'"+CompanyName+"')]"))).isDisplayed();
				new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='phSearchButton']"))).isDisplayed();
				driver.findElement(By.xpath("//input[@id='phSearchButton']")).click();
				System.out.println("click on serch111111111111111111");
				Sleep(3000);
				System.out.println("inside for loop ");
				driver.findElement(By.xpath("(//a[contains(text(),'"+CompanyName+"')])[1]")).click();
				Sleep(3000);
				driver.findElement(By.xpath("(//input[@value='Delete'])[1]")).click();
				Sleep(3000);
				driver.switchTo().alert().accept();
				//break;
			}
		
		}
		catch(Exception e) {
			
		}*/
		
		

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
		newproposalpage.CompanyName(Name);
		newproposalpage.PrimaryDirector();
		
		Sleep(2000);
	      ((JavascriptExecutor)driver).executeScript("scroll(0,500)");
	      Sleep(2000);
		
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
		Sleep(2000);
	      ((JavascriptExecutor)driver).executeScript("scroll(0,1000)");
	      Sleep(3000);
		newproposalpage.SelectResidentialPropertyYes();
		newproposalpage.BusinessAddressSelectYes();
		
		Sleep(2000);
	      ((JavascriptExecutor)driver).executeScript("scroll(0,1000)");
	      Sleep(3000);
		
		newproposalpage.NextButton();
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate Loan Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		LoanInfo.NucleusBusinessLoan();
		LoanInfo.ConfirmAlert();
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		Sleep(2000);
	      ((JavascriptExecutor)driver).executeScript("scroll(0,500)");
	      Sleep(3000);
		
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
			
			
						//System.out.println(text);

			
			
			
	   }
	catch(Exception e) {
		String Error = e.toString();
		System.out.println(Error);
		Screenshot();
		WriteExtentReport =test1.createNode("Failed Page ");
		WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+Error);
	  }
	}
	
	@Test (enabled=true)
	public void TestCase2() throws Exception {
		 test1 = extent.createTest("Test Case2", "Single Dir OB AS");
		try {
		ArrayList data=d.getData("TestCase2",path);
		String url = (String) data.get(2);
		System.out.println("TestCase2 stated");
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		String CompanyName=(String) data.get(5);
		String Email = (String) data.get(6);
		String PhoneNumber = (String) data.get(7);
		String BirthDay = (String) data.get(8);
		String PostCode = (String) data.get(9);
		String HouseNumber = (String) data.get(10);
		String HouseName = (String) data.get(11);
		String City = (String) data.get(12);
		String Street = (String) data.get(13);
		String Country = (String) data.get(14);
		String Fund = (String) data.get(15);
		String Months = (String) data.get(16);
		String Percent = (String) data.get(17);
		System.out.println(Percent);
		String BirthDay1= (String) data.get(18);
		String Email1 = (String) data.get(19);
		String MobileNumber = (String) data.get(20);
		String PhoneNO = (String) data.get(21);
		String url1 = (String) data.get(22);
		String Bank = (String) data.get(23);
		String BankType = (String) data.get(24);
		String Email2 = (String) data.get(25);
		String MobileNumber1 = (String) data.get(26);
		String Email3 = (String) data.get(27);
		String MobileNumber2 = (String) data.get(28);
		String Name = (String) data.get(29);
		

		/*driver.get("https://myfunding.ncf-sandbox.com/deleteCompanyData/12225716");
		 
		driver.get("https://nucleus--qa.sandbox.my.salesforce.com/");
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("shahrukh.aatar@mypulse.io.qa");
		
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Hasanw@123456");
		
		driver.findElement(By.xpath("//input[@id='Login']")).click();
		
		//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@title='User']"))).isDisplayed();
		// new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(text(),'"+CWID+"')]"))).isDisplayed();
		//driver.findElement(By.xpath("//img[@title='User']")).click();
		//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Switch to Salesforce Classic')]"))).isDisplayed();
		//driver.findElement(By.xpath("//a[contains(text(),'Switch to Salesforce Classic')]")).click();
		//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Leads')]"))).isDisplayed();
		//driver.findElement(By.xpath("//a[contains(text(),'Leads')]")).click();
		//Sleep(5000);
		//System.out.println("click on serch111111111111111111");
		//Sleep(5000);
		//driver.findElement(By.xpath("//input[@id='phSearchInput']")).sendKeys(CompanyName);
		//System.out.println("click on serch222222222222222222222222222");
		//Sleep(5000);
		//driver.findElement(By.xpath("//input[@id='phSearchButton']")).click();
		//Sleep(5000);
		//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Leads')]"))).isDisplayed();
		//driver.findElement(By.xpath("//a[contains(text(),'Leads')]")).click();
		
		//Sleep(5000);
		//driver.findElement(By.xpath("//input[@id='phSearchInput']")).sendKeys(CompanyName);
		//System.out.println("click on serch222222222222222222222222222");
		//Sleep(5000);
		//driver.findElement(By.xpath("//input[@id='phSearchButton']")).click();		
		//System.out.println("click on serch23333333333333333333333333333333");
		
		try {
			for(int iCount=1;iCount>=100;iCount++) {
				System.out.println("inside loop 11111111111111111");
				driver.findElement(By.xpath("//input[@id='phSearchInput']")).sendKeys(CompanyName);
				System.out.println("sendkeys111111111111111111");
				//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'"+CompanyName+"')]"))).isDisplayed();
				new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='phSearchButton']"))).isDisplayed();
				driver.findElement(By.xpath("//input[@id='phSearchButton']")).click();
				System.out.println("click on serch111111111111111111");
				Sleep(3000);
				System.out.println("inside for loop ");
				driver.findElement(By.xpath("(//a[contains(text(),'"+CompanyName+"')])[1]")).click();
				Sleep(3000);
				driver.findElement(By.xpath("(//input[@value='Delete'])[1]")).click();
				Sleep(3000);
				driver.switchTo().alert().accept();
			}
		
		}
		catch(Exception e) {
			
			System.out.println("inside catch");
			
		}*/
		
		
		
		driver.get("https://nucleus--qa.sandbox.my.salesforce.com/");
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("shahrukh.aatar@mypulse.io.qa.qa");
		
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Quality@123");
		
		driver.findElement(By.xpath("//input[@id='Login']")).click();
		
		
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
		
		
		driver.findElement(By.xpath("//input[@id='phSearchInput']")).sendKeys(CompanyName);
		System.out.println("click on serch222222222222222222222222222");
		Sleep(5000);
		driver.findElement(By.xpath("//input[@id='phSearchButton']")).click();
		Sleep(5000);
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'DIAMOND GIZA LTD')]"))).isDisplayed();
		driver.findElement(By.xpath("//a[contains(text(),'DIAMOND GIZA LTD')]")).click();
		
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("00QPt00000DRcEb_00N5800000DVdIG_link"))).isDisplayed();
		
		driver.findElement(By.id("00QPt00000DRcEb_00N5800000DVdIG_link")).click();
		
		
      // new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Sinead Ro-anne Hunter- Caprice')])"))).isDisplayed();
		
		//driver.findElement(By.xpath("(//a[contains(text(),'Sinead Ro-anne Hunter- Caprice')])")).click();
		
		
		 new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@target='_parent'])[3]"))).isDisplayed();
			
			driver.findElement(By.xpath("(//a[@target='_parent'])[3]")).click();
			
		
			WebElement Element = driver.findElement(By.xpath("//select[@id='00N4I00000ERiSM']"));
		
		Select sc = new Select(Element);
		
		sc.deselectByValue("Low");
		
		driver.get(url);
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate to Application landing page");
		
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		loginPage.login(Username,Password);	
		Screenshot();
		 WriteExtentReport = test1.createNode("Navigate to Home Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(3000);
		homePage.NewProposal();
		Screenshot(); 
		 WriteExtentReport =test1.createNode("Navigate NewProposal Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		newproposalpage.LimitedCompany();
		newproposalpage.SearchCompanyName(CompanyName);
		newproposalpage.CompanyName(Name);
		newproposalpage.PrimaryDirector();
		newproposalpage.EnterEmail(Email);
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
		Sleep(3000);
		driver.findElement(By.id("rbl")).click();
		Sleep(1000);
		LoanInfo.ConfirmAlert();
		driver.findElement(By.id("card_terminals")).sendKeys("5");
		Sleep(1000);
		driver.findElement(By.id("average_monthly_card_volume")).sendKeys("4000");
		//LoanInfo.NucleusBusinessLoan();
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		LoanInfo.BrokerPercent(Percent);
		LoanInfo.NextStep();
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(3000);
		/*try{
		DirectorInformation.EditDirectorDetails();
		DirectorInformation.DateofBirth(BirthDay1);
		DirectorInformation.Email(Email1);
		Sleep(1000);
		Sleep(1000); 
		DirectorInformation.DirMobile(MobileNumber);
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
       } */
		DirectorInformation.ClickOnNext();
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Documents Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Documents.SelectAccountingPackage();
		Sleep(5000);
		Documents.ClickOnDirector();
		Documents.ClickOnOk();
		Documents.SelectBank(Bank);
		Documents.SelectBankAccountType(BankType);
		Sleep(5000);
		Documents.ClickOnDirector1();
		Sleep(5000);
		Documents.ClickOnOk();
		Documents.SubmitButton();
		driver.get(url1);
		Sleep(3000);
		Set<String> handles3 = driver.getWindowHandles();
		List<String> hList3 = new ArrayList<String>(handles3);
		if(switchToRightWindow("Mail - Shahrukh Aatar - Outlook",hList3)){
		      }
		    Office.ClickOnPulse();
			Office.SelectFirstEmail();
			Sleep(3000);
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Email Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickOnCompleteApplication();
			Sleep(3000);
			Set<String> handles = driver.getWindowHandles();
			List<String> hList = new ArrayList<String>(handles);
			if(switchToRightWindow("MyNucleus",hList)){
			      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
			      }
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Open Banking Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickonCompleteyourOpenBanking();
			Sleep(3000);
			Set<String> handles1 = driver.getWindowHandles();
			List<String> hList1 = new ArrayList<String>(handles1);
			if(switchToRightWindow("myPulse - Plaid Open Banking",hList1)){
			      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
			      }
			Sleep(2000);
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate myPulse Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			myPulse.ClickOnGetStarted();
			Sleep(1000);
			
			List<WebElement> Company = driver.findElements(By.xpath("//input[@name='CompanyName']"));
			if (Company.size() > 0)
			{
				myPulse.CompanyNames();
				myPulse.EnterEmail();
				myPulse.mobileNumber();
				myPulse.ClickOnContinue1();
			}
			Screenshot();
			Sleep(2000);
			WriteExtentReport =test1.createNode("Navigate Bank Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Sleep(6000);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_TAB);
			Sleep(2000);
			robot.keyPress(KeyEvent.VK_TAB);
			Sleep(2000);
			myPulse.EnterBankName();
			myPulse.ClickOnYourBank();
			myPulse.ClickOnConsent();
			Set<String> handles2 = driver.getWindowHandles();
			List<String> hList2 = new ArrayList<String>(handles2);
			if(switchToRightWindow("AccountScore Test Bank",hList2)){
			      }
			Sleep(3000);
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Bank Login Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			 myPulse.EnterUsername();
			myPulse.ClickOnSubmit();
			if(switchToRightWindow("myPulse - Plaid Open Banking",hList1)){
			      }
			Sleep(2000);
			 myPulse.VerifyMessage();
			 Sleep(2000);
			    Screenshot();
				Sleep(2000);
				WriteExtentReport =test1.createNode("Navigate  Page ");
				WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			 myPulse.ClickOnFinish();	
			Sleep(2000); 
			
			
			
			
			//driver.get("https://myfunding.ncf-sandbox.com/deleteCompanyData/12225716");
			 
			/*driver.get("https://nucleus--qa.sandbox.my.salesforce.com/");
			
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("shahrukh.aatar@mypulse.io.qa.qa");
			
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Quality@123");
			
			driver.findElement(By.xpath("//input[@id='Login']")).click();
			
			
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
			
			
			driver.findElement(By.xpath("//input[@id='phSearchInput']")).sendKeys(CompanyName);
			System.out.println("click on serch222222222222222222222222222");
			Sleep(5000);
			driver.findElement(By.xpath("//input[@id='phSearchButton']")).click();
			Sleep(5000);
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'DIAMOND GIZA LTD')]"))).isDisplayed();
			driver.findElement(By.xpath("//a[contains(text(),'DIAMOND GIZA LTD')]")).click();
			
			
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("00QPt00000DRcEb_00N5800000DVdIG_link"))).isDisplayed();
			
			driver.findElement(By.id("00QPt00000DRcEb_00N5800000DVdIG_link")).click();
			
			
          // new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Sinead Ro-anne Hunter- Caprice')])"))).isDisplayed();
			
			//driver.findElement(By.xpath("(//a[contains(text(),'Sinead Ro-anne Hunter- Caprice')])")).click();
			
			
			 new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@target='_parent'])[3]"))).isDisplayed();
				
				driver.findElement(By.xpath("(//a[@target='_parent'])[3]")).click();
				
			
				WebElement Element = driver.findElement(By.xpath("//select[@id='00N4I00000ERiSM']"));
			
			Select sc = new Select(Element);
			
			sc.deselectByValue("Low");*/
			
			
			//span[contains(text(),'Contacts')]
			
			
			
			
			
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
	public void TestCase3() throws Exception {
		 test1 = extent.createTest("Test Case 3", "Single Dir/Shareholder OB Plaid");
		try {
			
		ArrayList data=d.getData("TestCase3",path);
		String url = (String) data.get(2);
		System.out.println("TestCase3 started");
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		String CompanyName=(String) data.get(5);
		String Email = (String) data.get(6);
		String PhoneNumber = (String) data.get(7);
		String BirthDay = (String) data.get(8);
		String PostCode = (String) data.get(9);
		String HouseNumber = (String) data.get(10);
		String HouseName = (String) data.get(11);
		String City = (String) data.get(12);
		String Street = (String) data.get(13);
		String Country = (String) data.get(14);
		String Fund = (String) data.get(15);
		String Months = (String) data.get(16);
		String Percent = (String) data.get(17);
		String BirthDay1= (String) data.get(18);
		String Email1 = (String) data.get(19);
		String MobileNumber = (String) data.get(20);
		String PhoneNO = (String) data.get(21);
		String url1 = (String) data.get(22);
		String Bank = (String) data.get(23);
		String BankType = (String) data.get(24);
		String Name = (String) data.get(29);
		
		  /*  driver.get("https://myfunding.ncf-sandbox.com/deleteCompanyData/13483988");
		    
			driver.get("https://nucleus--qa.sandbox.my.salesforce.com/");
		
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("shahrukh.aatar@mypulse.io.qa");
			
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Hasanw@123456");
			
			driver.findElement(By.xpath("//input[@id='Login']")).click();
			Sleep(3000);
			
			//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@title='User']"))).isDisplayed();
			// new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(text(),'"+CWID+"')]"))).isDisplayed();
			//driver.findElement(By.xpath("//img[@title='User']")).click();
			//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Switch to Salesforce Classic')]"))).isDisplayed();
			//driver.findElement(By.xpath("//a[contains(text(),'Switch to Salesforce Classic')]")).click();
			
			//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Leads')]"))).isDisplayed();
			//driver.findElement(By.xpath("//a[contains(text(),'Leads')]")).click();
			
			//Sleep(5000);
			
			//System.out.println("click on serch111111111111111111");
			//Sleep(5000);
			//driver.findElement(By.xpath("//input[@id='phSearchInput']")).sendKeys(CompanyName);
			//System.out.println("click on serch222222222222222222222222222");
			//Sleep(5000);
			//driver.findElement(By.xpath("//input[@id='phSearchButton']")).click();
			
			//Sleep(5000);
			//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Leads')]"))).isDisplayed();
			//driver.findElement(By.xpath("//a[contains(text(),'Leads')]")).click();
			
			//Sleep(5000);
			//driver.findElement(By.xpath("//input[@id='phSearchInput']")).sendKeys(CompanyName);
			//System.out.println("click on serch222222222222222222222222222");
			//Sleep(5000);
			//driver.findElement(By.xpath("//input[@id='phSearchButton']")).click();		
			//System.out.println("click on serch23333333333333333333333333333333");
			try {

				for(int iCount=1;iCount<=100;iCount++) {
					Sleep(3000);
					driver.findElement(By.xpath("//input[@id='phSearchInput']")).sendKeys(CompanyName);
					System.out.println("sendkeys111111111111111111");
					//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'"+CompanyName+"')]"))).isDisplayed();
				
					new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='phSearchButton']"))).isDisplayed();
					driver.findElement(By.xpath("//input[@id='phSearchButton']")).click();
					System.out.println("click on serch111111111111111111");
					Sleep(3000);
					System.out.println("inside for loop ");
					driver.findElement(By.xpath("(//a[contains(text(),'"+CompanyName+"')])[1]")).click();
					Sleep(3000);
					driver.findElement(By.xpath("(//input[@value='Delete'])[1]")).click();
					Sleep(3000);
					driver.switchTo().alert().accept();
					//break;
				}

			}
			catch(Exception e) {
			}*/
		
		
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
		newproposalpage.CompanyName(Name);
		newproposalpage.PrimaryDirector();
		newproposalpage.EnterEmail(Email);
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
		LoanInfo.NucleusBusinessLoan();
		LoanInfo.ConfirmAlert();
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		LoanInfo.BrokerPercent(Percent);
		LoanInfo.NextStep();
		Sleep(2000);
		Screenshot();
		Sleep(2000);
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(2000);
		/*DirectorInformation.EditDirectorDetails();
		System.out.println("111111111111111111111111111111111111111111");
		//ShareholderDetails.ClickOnShareholderDetails();
		ShareholderDetails.DateofBirth(BirthDay1);
		System.out.println("222222222222222222222222222222222222222");
		ShareholderDetails.Email(Email1);
		System.out.println("333333333333333333333333333111111");
		ShareholderDetails.DirMobile(MobileNumber);
		ShareholderDetails.PersonalGuaranteeYes();
		ShareholderDetails.EnterPostCode(PostCode);
		ShareholderDetails.ClickonFindAddress();
		ShareholderDetails.SelectAddress();
		ShareholderDetails.ClickOnSubmit();*/
		DirectorInformation.EditDirectorDetails();
		Sleep(5000);
		//DirectorInformation.DateofBirth(BirthDay1);
		DirectorInformation.Email(Email1);
		Sleep(1000);
		DirectorInformation.DirMobile(MobileNumber);
		DirectorInformation.PersonalGuaranteeYes();
		DirectorInformation.EnterPostCode(PostCode);
		DirectorInformation.ClickonFindAddress();
		Sleep(3000);
		DirectorInformation.SelectAddress();
		DirectorInformation.SelectResidentialPropertyYes();
		DirectorInformation.ClickOnSubmit(); 
		Sleep(3000);
		DirectorInformation.ClickOnNext();
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Documents Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Documents.SelectAccountingPackage();
		Sleep(3000);
		Documents.ClickOnDirector();
		Documents.ClickOnOk();
		Documents.SelectBank(Bank);
		Documents.SelectBankAccountType(BankType);
		Sleep(3000);
		Documents.ClickOnDirector1();
		Sleep(3000);
		Documents.ClickOnOk();
		Documents.SubmitButton();
		driver.get(url1);
		Sleep(6000);
		Set<String> handles3 = driver.getWindowHandles();
		List<String> hList3 = new ArrayList<String>(handles3);
		if(switchToRightWindow("Mail - Shahrukh Aatar - Outlook",hList3)){
		      }
		Office.ClickOnPulse();
		Office.SelectFirstEmail();
			Sleep(6000);
			Screenshot();
			WriteExtentReport =test1.createNode("Navigate Email Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
			Office.ClickOnCompleteApplication();
			Sleep(6000);
			Set<String> handles = driver.getWindowHandles();
			List<String> hList = new ArrayList<String>(handles);
			if(switchToRightWindow("MyNucleus",hList)){
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
		Screenshot();
		WriteExtentReport =test1.createNode("Failed Page ");
		WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+Error);	
	}	
	}

	
	@Test(enabled=true)
	public void TestCase4() throws Exception  {
		
		
		 test1 = extent.createTest("Test Case 4", "Multiple Dir/Shareholder");
		
		try {
		ArrayList data=d.getData("TestCase4",path);
		String url = (String) data.get(2);
		driver.get(url);
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		String CompanyName=(String) data.get(5);
		String Email = (String) data.get(6);
		String PhoneNumber = (String) data.get(7);
		String BirthDay = (String) data.get(8);
		String PostCode = (String) data.get(9);
		String HouseNumber = (String) data.get(10);
		String HouseName = (String) data.get(11);
		String City = (String) data.get(12);
		String Street = (String) data.get(13);
		String Country = (String) data.get(14);
		String Fund = (String) data.get(15);
		String Months = (String) data.get(16);
		String Percent = (String) data.get(17);
		System.out.println(Percent);
		String BirthDay1= (String) data.get(18);
		String Email1 = (String) data.get(19);
		String MobileNumber = (String) data.get(20);
		String PhoneNO = (String) data.get(21);
		String url1 = (String) data.get(22);
		String Bank = (String) data.get(23);
		String BankType = (String) data.get(24);
		String Email2 = (String) data.get(25);
		String MobileNumber1 = (String) data.get(26);
		String Email3 = (String) data.get(27);
		String MobileNumber2 = (String) data.get(28);
		String Name = (String) data.get(29);
		
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
		newproposalpage.CompanyName(Name);
		newproposalpage.PrimaryDirector();
		newproposalpage.EnterEmail(Email);
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
		LoanInfo.NucleusBusinessLoan();
		LoanInfo.ConfirmAlert();
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		LoanInfo.BrokerPercent(Percent);
		LoanInfo.NextStep();
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		try{
		DirectorInformation.EditDirectorDetails();
		DirectorInformation.DateofBirth(BirthDay1);
		DirectorInformation.Email(Email1);
		Sleep(1000);
		DirectorInformation.DirMobile(MobileNumber);
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
		Sleep(4000);
		try{
		System.out.println("11111111111111111111111111111111");
		
	//	WebDriverWait wait=new WebDriverWait(driver, 20);
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@alt='Edit Details'])[4]"))).isDisplayed();
		
		
		DirectorInformation.EditShareholderDetails1();
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
		Sleep(3000);
		DirectorInformation.ClickOnNext();
		System.out.println("555555555555555555555555555555555555555555");
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Documents Page");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		//Sleep(3000);
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.id("customer_accounting_package"))).isDisplayed();
		
		Documents.SelectAccountingPackage();
		System.out.println("6666666666666666666666666666666666666666");
	//	Sleep(3000);
		//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='DirectorOpenFinance1']"))).isDisplayed();
		
		Sleep(5000);
		Documents.ClickOnDirector();
		System.out.println("777777777777777777777777777777777777777777777777");
		Documents.ClickOnOk();
		System.out.println("888888888888888888888888888888888888888888");
		Documents.SelectBank(Bank);
		System.out.println("99999999999999999999999999999999999");
		Documents.SelectBankAccountType(BankType);
		Sleep(3000);
		//Documents.ClickOnDirector1();
		Sleep(3000);
		//6 tab enter
		Documents.StartDate();
		Documents.EndDate();
		
		Sleep(3000);
		Documents.uploadfile();
		
		Sleep(6000);
		
		
		//Documents.SubmitButton();
		Screenshot();
			WriteExtentReport =test1.createNode("Navigate Documents Page");
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
		
		
		 test1 = extent.createTest("Test Case 5", "Complete Step1");
		

		try {
				
		ArrayList data=d.getData("TestCase5",path);
		String url = (String) data.get(2);
		
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		String CompanyName=(String) data.get(5);
		String Email = (String) data.get(6);
		String PhoneNumber = (String) data.get(7);
		String BirthDay = (String) data.get(8);
		String PostCode = (String) data.get(9);
		String HouseNumber = (String) data.get(10);
		String HouseName = (String) data.get(11);
		String City = (String) data.get(12);
		String Street = (String) data.get(13);
		String Country = (String) data.get(14);
		String Fund = (String) data.get(15);
		String Months = (String) data.get(16);
		String Percent = (String) data.get(17);
		String BirthDay1= (String) data.get(18);
		String Email1 = (String) data.get(19);
		String MobileNumber = (String) data.get(20);
		String PhoneNO = (String) data.get(21);
		String url1 = (String) data.get(22);
		String Bank = (String) data.get(23);
		String BankType = (String) data.get(24);
		String Email2 = (String) data.get(25);
		String MobileNumber1 = (String) data.get(26);
		String Email3 = (String) data.get(27);
		String MobileNumber2 = (String) data.get(28);
		String Name = (String) data.get(29);
		
		
		
		    driver.get("https://myfunding.ncf-sandbox.com/deleteCompanyData/13483988");
			
			driver.get("https://nucleus--qa.sandbox.my.salesforce.com/");
			
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("shahrukh.aatar@mypulse.io.qa");
			
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Hasanw@123456");
			
			driver.findElement(By.xpath("//input[@id='Login']")).click();
			Sleep(3000);
			
			//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@title='User']"))).isDisplayed();
			// new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(text(),'"+CWID+"')]"))).isDisplayed();
			//driver.findElement(By.xpath("//img[@title='User']")).click();
			//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Switch to Salesforce Classic')]"))).isDisplayed();
			//driver.findElement(By.xpath("//a[contains(text(),'Switch to Salesforce Classic')]")).click();
			
			//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Leads')]"))).isDisplayed();
			//driver.findElement(By.xpath("//a[contains(text(),'Leads')]")).click();
			
			//Sleep(5000);
			
			//System.out.println("click on serch111111111111111111");
			//Sleep(5000);
			//driver.findElement(By.xpath("//input[@id='phSearchInput']")).sendKeys(CompanyName);
			//System.out.println("click on serch222222222222222222222222222");
			//Sleep(5000);
			//driver.findElement(By.xpath("//input[@id='phSearchButton']")).click();
			
			//Sleep(5000);
			//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Leads')]"))).isDisplayed();
			//driver.findElement(By.xpath("//a[contains(text(),'Leads')]")).click();
			
			//Sleep(5000);
			//driver.findElement(By.xpath("//input[@id='phSearchInput']")).sendKeys(CompanyName);
			//System.out.println("click on serch222222222222222222222222222");
			//Sleep(5000);
			//driver.findElement(By.xpath("//input[@id='phSearchButton']")).click();		
			//System.out.println("click on serch23333333333333333333333333333333");
			try {

				for(int iCount=1;iCount<=100;iCount++) {
					Sleep(3000);
					driver.findElement(By.xpath("//input[@id='phSearchInput']")).sendKeys(CompanyName);
					System.out.println("sendkeys111111111111111111");
					//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'"+CompanyName+"')]"))).isDisplayed();
					
					new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='phSearchButton']"))).isDisplayed();
					driver.findElement(By.xpath("//input[@id='phSearchButton']")).click();
					System.out.println("click on serch111111111111111111");
					
					Sleep(3000);
					System.out.println("inside for loop ");
					driver.findElement(By.xpath("(//a[contains(text(),'"+CompanyName+"')])[1]")).click();
					Sleep(3000);
					driver.findElement(By.xpath("(//input[@value='Delete'])[1]")).click();
					Sleep(3000);
					driver.switchTo().alert().accept();
					break;
				}
			
			}
			catch(Exception e) {
				
			}
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
		newproposalpage.CompanyName(Name);
		newproposalpage.PrimaryDirector();
		newproposalpage.EnterEmail(Email);
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
		
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Nucleus Business Loan')]"))).isDisplayed();
		
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate Loan Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' DASHBOARD')]"))).isDisplayed();
		
		homePage.Dashboard();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Incomplete Proposals')]"))).isDisplayed();
		
		homePage.IncompleteProposals(CompanyName);
		LoanInfo.NucleusBusinessLoan();
		LoanInfo.ConfirmAlert();
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		LoanInfo.BrokerPercent(Percent);
		LoanInfo.NextStep();
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(6000);
		DirectorInformation.EditDirectorDetails();
		//DirectorInformation.DateofBirth(BirthDay1);
		DirectorInformation.Email(Email1);
		Sleep(1000);
		DirectorInformation.DirMobile(MobileNumber);
		DirectorInformation.PersonalGuaranteeYes();
		DirectorInformation.EnterPostCode(PostCode);
		DirectorInformation.ClickonFindAddress();
		Sleep(3000);
		DirectorInformation.SelectAddress();
		DirectorInformation.SelectResidentialPropertyYes();
		DirectorInformation.ClickOnSubmit();
		Sleep(1000);
		DirectorInformation.ClickOnNext();
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(6000);
		Documents.SelectAccountingPackage();
		Sleep(3000);
		Documents.ClickOnDirector();
		Documents.ClickOnOk();
		Documents.SelectBank(Bank);
		Documents.SelectBankAccountType(BankType);
		System.out.println("9999999999999999999999999999999999999999");
		Sleep(3000);
		Documents.ClickOnDirector1();
		Sleep(3000);
		Documents.ClickOnOk();
		Documents.SubmitButton();
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Documents Page1 ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
	
	}
	
	catch(Exception e) {
	
		String Error = e.toString();
		Screenshot();
		WriteExtentReport =test1.createNode("Failed Page ");
		WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+Error);

		
	}
	}
	
	
	@Test(enabled=false)
	public void TestCase6() throws Exception  {
		
		
		 test1 = extent.createTest("Test Case 6", "Complete Step1,Step2");
		

		try {
				
		ArrayList data=d.getData("TestCase6",path);
		String url = (String) data.get(2);
		driver.get(url);
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		String CompanyName=(String) data.get(5);
		String Email = (String) data.get(6);
		String PhoneNumber = (String) data.get(7);
		String BirthDay = (String) data.get(8);
		String PostCode = (String) data.get(9);
		String HouseNumber = (String) data.get(10);
		String HouseName = (String) data.get(11);
		String City = (String) data.get(12);
		String Street = (String) data.get(13);
		String Country = (String) data.get(14);
		String Fund = (String) data.get(15);
		String Months = (String) data.get(16);
		String Percent = (String) data.get(17);
		String BirthDay1= (String) data.get(18);
		String Email1 = (String) data.get(19);
		String MobileNumber = (String) data.get(20);
		String PhoneNO = (String) data.get(21);
		String url1 = (String) data.get(22);
		String Bank = (String) data.get(23);
		String BankType = (String) data.get(24);
		String Email2 = (String) data.get(25);
		String MobileNumber1 = (String) data.get(26);
		String Email3 = (String) data.get(27);
		String MobileNumber2 = (String) data.get(28);
		String Name = (String) data.get(29);
		
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
		newproposalpage.CompanyName(Name);
		newproposalpage.PrimaryDirector();
		newproposalpage.EnterEmail(Email);
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
		
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Nucleus Business Loan')]"))).isDisplayed();
		
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate Loan Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		LoanInfo.NucleusBusinessLoan();
		LoanInfo.ConfirmAlert();
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		LoanInfo.BrokerPercent(Percent);
		LoanInfo.NextStep();
		Sleep(3000);
		
		Sleep(6000);
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' DASHBOARD')]"))).isDisplayed();	
	    homePage.Dashboard();
	    System.out.println("Dshaboardddddddddddddddddddddddddddddddddddddddd");
	    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Incomplete Proposals')]"))).isDisplayed();
	    Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate Dashboard Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		homePage.IncompleteProposals(CompanyName);
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		DirectorInformation.EditDirectorDetails();
		//DirectorInformation.DateofBirth(BirthDay1);
		DirectorInformation.Email(Email1);
		Sleep(1000);
		DirectorInformation.DirMobile(MobileNumber);
		DirectorInformation.PersonalGuaranteeYes();
		DirectorInformation.EnterPostCode(PostCode);
		DirectorInformation.ClickonFindAddress();
		Sleep(3000);
		DirectorInformation.SelectAddress();
		DirectorInformation.SelectResidentialPropertyYes();
		DirectorInformation.ClickOnSubmit();
		Sleep(1000);
		DirectorInformation.ClickOnNext();
		Sleep(6000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		Documents.SelectAccountingPackage();
		Sleep(3000);
		Documents.ClickOnDirector();
		Documents.ClickOnOk();
		Documents.SelectBank(Bank);
		Documents.SelectBankAccountType(BankType);
		System.out.println("9999999999999999999999999999999999999999");
		Sleep(3000);
		Documents.ClickOnDirector1();
		Sleep(3000);
		Documents.ClickOnOk();
		Documents.SubmitButton();
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Documents Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
	
	}
	
	catch(Exception e) {
	
		String Error = e.toString();
		Screenshot();
		WriteExtentReport =test1.createNode("Failed Page ");
		WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+Error);

		
	}
	}

	

	@Test(enabled=false)
	public void TestCase7() throws Exception  {
		
		
		 test1 = extent.createTest("Test Case 7", "Complete Step1,Step2,Step3");
		try {	
		ArrayList data=d.getData("TestCase7",path);
		String url = (String) data.get(2);
		driver.get(url);
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		String CompanyName=(String) data.get(5);
		String Email = (String) data.get(6);
		String PhoneNumber = (String) data.get(7);
		String BirthDay = (String) data.get(8);
		String PostCode = (String) data.get(9);
		String HouseNumber = (String) data.get(10);
		String HouseName = (String) data.get(11);
		String City = (String) data.get(12);
		String Street = (String) data.get(13);
		String Country = (String) data.get(14);
		String Fund = (String) data.get(15);
		String Months = (String) data.get(16);
		String Percent = (String) data.get(17);
		String BirthDay1= (String) data.get(18);
		String Email1 = (String) data.get(19);
		String MobileNumber = (String) data.get(20);
		String PhoneNO = (String) data.get(21);
		String url1 = (String) data.get(22);
		String Bank = (String) data.get(23);
		String BankType = (String) data.get(24);
		String Email2 = (String) data.get(25);
		String MobileNumber1 = (String) data.get(26);
		String Email3 = (String) data.get(27);
		String MobileNumber2 = (String) data.get(28);
		String Name = (String) data.get(29);
		
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
		newproposalpage.CompanyName(Name);
		newproposalpage.PrimaryDirector();
		newproposalpage.EnterEmail(Email);
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
		
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Nucleus Business Loan')]"))).isDisplayed();
		
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate Loan Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		LoanInfo.NucleusBusinessLoan();
		LoanInfo.ConfirmAlert();
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		LoanInfo.BrokerPercent(Percent);
		LoanInfo.NextStep();
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		DirectorInformation.EditDirectorDetails();
		//DirectorInformation.DateofBirth(BirthDay1);
		DirectorInformation.Email(Email1);
		Sleep(1000);
		DirectorInformation.DirMobile(MobileNumber);
		DirectorInformation.PersonalGuaranteeYes();
		DirectorInformation.EnterPostCode(PostCode);
		DirectorInformation.ClickonFindAddress();
		Sleep(3000);
		DirectorInformation.SelectAddress();
		DirectorInformation.SelectResidentialPropertyYes();
		DirectorInformation.ClickOnSubmit();
		Sleep(1000);
		DirectorInformation.ClickOnNext();
		
		Sleep(6000);
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' DASHBOARD')]"))).isDisplayed();	
	    homePage.Dashboard();
	    System.out.println("Dshaboardddddddddddddddddddddddddddddddddddddddd");
	    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Incomplete Proposals')]"))).isDisplayed();
	    Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate Dashboard Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		homePage.IncompleteProposals(CompanyName);
		Sleep(6000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Documents Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		Documents.SelectAccountingPackage();
		Sleep(3000);
		Documents.ClickOnDirector();
		Documents.ClickOnOk();
		Documents.SelectBank(Bank);
		Documents.SelectBankAccountType(BankType);
		System.out.println("9999999999999999999999999999999999999999");
		Sleep(3000);
		Documents.ClickOnDirector1();
		Sleep(3000);
		Documents.ClickOnOk();
		Documents.SubmitButton();
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Submission Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
	
	}
	
	catch(Exception e) {
	
		String Error = e.toString();
		Screenshot();
		WriteExtentReport =test1.createNode("Failed Page ");
		WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+Error);

		
	}
	}

	
	
	@Test(enabled=false)
	public void TestCase8() throws Exception  {
		
		
		 test1 = extent.createTest("Test Case 8", "Send OB Link");
		

		try {
				
		ArrayList data=d.getData("TestCase8",path);
		String url = (String) data.get(2);
		driver.get(url);
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		String CompanyName=(String) data.get(5);
		String Email = (String) data.get(6);
		String PhoneNumber = (String) data.get(7);
		String BirthDay = (String) data.get(8);
		String PostCode = (String) data.get(9);
		String HouseNumber = (String) data.get(10);
		String HouseName = (String) data.get(11);
		String City = (String) data.get(12);
		String Street = (String) data.get(13);
		String Country = (String) data.get(14);
		String Fund = (String) data.get(15);
		String Months = (String) data.get(16);
		String Percent = (String) data.get(17);
		String BirthDay1= (String) data.get(18);
		String Email1 = (String) data.get(19);
		String MobileNumber = (String) data.get(20);
		String PhoneNO = (String) data.get(21);
		String url1 = (String) data.get(22);
		String Bank = (String) data.get(23);
		String BankType = (String) data.get(24);
		String Email2 = (String) data.get(25);
		String MobileNumber1 = (String) data.get(26);
		String Email3 = (String) data.get(27);
		String MobileNumber2 = (String) data.get(28);
		String Name = (String) data.get(29);
		
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
		newproposalpage.CompanyName(Name);
		newproposalpage.PrimaryDirector();
		newproposalpage.EnterEmail(Email);
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
		
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Nucleus Business Loan')]"))).isDisplayed();
		
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate Loan Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		LoanInfo.NucleusBusinessLoan();
		LoanInfo.ConfirmAlert();
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		LoanInfo.BrokerPercent(Percent);
		LoanInfo.NextStep();
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		DirectorInformation.EditDirectorDetails();
		//DirectorInformation.DateofBirth(BirthDay1);
		DirectorInformation.Email(Email1);
		Sleep(1000);
		DirectorInformation.DirMobile(MobileNumber);
		DirectorInformation.PersonalGuaranteeYes();
		DirectorInformation.EnterPostCode(PostCode);
		DirectorInformation.ClickonFindAddress();
		Sleep(3000);
		DirectorInformation.SelectAddress();
		DirectorInformation.SelectResidentialPropertyYes();
		DirectorInformation.ClickOnSubmit();
		Sleep(1000);
		DirectorInformation.ClickOnNext();
		Sleep(6000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page1 ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		Documents.SelectAccountingPackage();
		Sleep(3000);
		Documents.ClickOnDirector();
		Documents.ClickOnOk();
		Documents.SelectBank(Bank);
		Documents.SelectBankAccountType(BankType);
		System.out.println("9999999999999999999999999999999999999999");
		Sleep(3000);
		Documents.ClickOnDirector1();
		Sleep(3000);
		Documents.ClickOnOk();
		Sleep(6000);
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),' DASHBOARD')]"))).isDisplayed();	
	    homePage.Dashboard();
	    System.out.println("Dshaboardddddddddddddddddddddddddddddddddddddddd");
	    new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Incomplete Proposals')]"))).isDisplayed();
	    Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate Dashboard Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		homePage.IncompleteProposals(CompanyName);
		
		Documents.SubmitButton();
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Submission Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
	
	}
	
	catch(Exception e) {
	
		String Error = e.toString();
		Screenshot();
		WriteExtentReport =test1.createNode("Failed Page ");
		WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+Error);

		
	}
	}

	
	
	

	@Test(enabled=false)
	public void TestCase10() throws Exception  {
		
		 test1 = extent.createTest("Test Case 10", "OutSide Risk Criteria");
		try {
		ArrayList data=d.getData("TestCase10",path);
		String url = (String) data.get(2);
		driver.get(url);
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		String CompanyName=(String) data.get(5);
		String Email = (String) data.get(6);
		String PhoneNumber = (String) data.get(7);
		String BirthDay = (String) data.get(8);
		String PostCode = (String) data.get(9);
		String HouseNumber = (String) data.get(10);
		String HouseName = (String) data.get(11);
		String City = (String) data.get(12);
		String Street = (String) data.get(13);
		String Country = (String) data.get(14);
		String Fund = (String) data.get(15);
		String Months = (String) data.get(16);
		String Percent = (String) data.get(17);
		String BirthDay1= (String) data.get(18);
		String Email1 = (String) data.get(19);
		String MobileNumber = (String) data.get(20);
		String PhoneNO = (String) data.get(21);
		String url1 = (String) data.get(22);
		String Bank = (String) data.get(23);
		String BankType = (String) data.get(24);
		String Email2 = (String) data.get(25);
		String MobileNumber1 = (String) data.get(26);
		String Email3 = (String) data.get(27);
		String MobileNumber2 = (String) data.get(28);
		String Name = (String) data.get(29);
		
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
		newproposalpage.CompanyName(Name);
		//newproposalpage.PrimaryDirector();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@id='popmsg']"))).isDisplayed();
		Sleep(3000);
		String Message = driver.findElement(By.xpath("//p[@id='popmsg']")).getText();
		String[] myArray = Message.split(",");
		String part1 = myArray[0];
		String part2 = myArray[1];
		System.out.println(part1);
		System.out.println(part2);
		String Array = part2;
		String[] myArray1 = Array.split("\\.");
		String Text = myArray1[0];
		String Text1 = myArray1[1];
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate OutSide Risk Criteria Pop-Up");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'OK')])[3]"))).isDisplayed();
		driver.findElement(By.xpath("(//a[contains(text(),'OK')])[3]")).click();
		System.out.println("999999999999999999"+Text);
	//	System.out.println(Text1);
		
		String str2 = Text;
	    String str1 ="we cannot support GROOVE ASSOCIATES LIMITED at this time as the Customer is Outside our Risk Criteria due to a previous application";
	/*	if(str2.equalsIgnoreCase(str1)) {
			Sleep(1000000);
			System.out.println("1010101666666666666666666665555555555555555");
		}
		else
		{
			System.out.println("1010101666666666666666666665555555555555555");
		}*/
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate  NewProposal Page");
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
	public void TestCase9() throws Exception  {
		
		
		 test1 = extent.createTest("Test Case 9", "Skip Step1 and complete step2");
		

		try {
				
		ArrayList data=d.getData("TestCase9",path);
		String url = (String) data.get(2);
		driver.get(url);
		String Username= (String) data.get(3);
		String Password= (String) data.get(4);
		String CompanyName=(String) data.get(5);
		String Email = (String) data.get(6);
		String PhoneNumber = (String) data.get(7);
		String BirthDay = (String) data.get(8);
		String PostCode = (String) data.get(9);
		String HouseNumber = (String) data.get(10);
		String HouseName = (String) data.get(11);
		String City = (String) data.get(12);
		String Street = (String) data.get(13);
		String Country = (String) data.get(14);
		String Fund = (String) data.get(15);
		String Months = (String) data.get(16);
		String Percent = (String) data.get(17);
		String BirthDay1= (String) data.get(18);
		String Email1 = (String) data.get(19);
		String MobileNumber = (String) data.get(20);
		String PhoneNO = (String) data.get(21);
		String url1 = (String) data.get(22);
		String Bank = (String) data.get(23);
		String BankType = (String) data.get(24);
		String Email2 = (String) data.get(25);
		String MobileNumber1 = (String) data.get(26);
		String Email3 = (String) data.get(27);
		String MobileNumber2 = (String) data.get(28);
		String Name = (String) data.get(29);
		
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
		newproposalpage.CompanyName(Name);
		newproposalpage.PrimaryDirector();
		newproposalpage.EnterEmail(Email);
		newproposalpage.EnterPhoneNumberField(PhoneNumber);
		newproposalpage.EnterBirthDay(BirthDay);
		Sleep(3000);
		newproposalpage.NextButton();
		
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate Loan Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Nucleus Business Loan')]"))).isDisplayed();
		Sleep(3000);
		LoanInfo.NucleusBusinessLoan();
		LoanInfo.ConfirmAlert();
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		LoanInfo.BrokerPercent(Percent);
		LoanInfo.NextStep();
		Sleep(3000);
		
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//P[contains(text(),'The previous section is incomplete, please Go Back and complete the section before moving forward.')]"))).isDisplayed();	
	    driver.findElement(By.id("triggerPreStep")).click();
	    
	    Sleep(3000);
		
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
		Sleep(3000);
		 LoanInfo.NextStep();
		
		
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		

			DirectorInformation.EditDirectorDetails();
			//DirectorInformation.DateofBirth(BirthDay1);
			DirectorInformation.Email(Email1);
			Sleep(1000);
			DirectorInformation.DirMobile(MobileNumber);
			DirectorInformation.PersonalGuaranteeYes();
			DirectorInformation.EnterPostCode(PostCode);
			DirectorInformation.ClickonFindAddress();
			Sleep(3000);
			DirectorInformation.SelectAddress();
			DirectorInformation.SelectResidentialPropertyYes();
			DirectorInformation.ClickOnSubmit();

			
		Sleep(3000);
		
		Sleep(1000);
		DirectorInformation.ClickOnNext();
		Sleep(6000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page1 ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		Documents.SelectAccountingPackage();
		Sleep(3000);
		Documents.ClickOnDirector();
		Documents.ClickOnOk();
		Documents.SelectBank(Bank);
		Documents.SelectBankAccountType(BankType);
		System.out.println("9999999999999999999999999999999999999999");
		Sleep(3000);
		Documents.ClickOnDirector1();
		Sleep(3000);
		Documents.ClickOnOk();
		Documents.SubmitButton();
		Sleep(3000);
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Submission Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
	
	}
	
	catch(Exception e) {
	
		String Error = e.toString();
		Screenshot();
		WriteExtentReport =test1.createNode("Failed Page ");
		WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+Error);

		
	}
	}

	

/*	@Test
	public void InfinityTest() throws Exception{
		
		 test1 = extent.createTest("Test", "Single Dir");
		//  test1.log(Status.INFO, "Starting test case");
		 // test1.log(Status.PASS, "Some test step");

		try {
			
		ArrayList data=d.getData("Test2");
		String url = (String) data.get(1);
		driver.get(url);
		String Username= (String) data.get(2);
		String Password= (String) data.get(3);
		String CompanyName=(String) data.get(4);
		String Email = (String) data.get(5);
		String PhoneNumber = (String) data.get(6);
		String BirthDay = (String) data.get(7);
		String PostCode = (String) data.get(8);
		String HouseNumber = (String) data.get(9);
		String HouseName = (String) data.get(10);
		String City = (String) data.get(11);
		String Street = (String) data.get(12);
		String Country = (String) data.get(13);
		String Fund = (String) data.get(14);
		String Months = (String) data.get(15);
		String Percent = (String) data.get(16);
		System.out.println(Percent);
		String BirthDay1= (String) data.get(17);
		String Email1 = (String) data.get(18);
		String MobileNumber = (String) data.get(19);
		String PhoneNO = (String) data.get(20);
		String url1 = (String) data.get(21);

		Screenshot();
		//test1.log(Status.PASS, "Login Page");
		
		WriteExtentReport =test1.createNode("Navigate to Application landing page");
		
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		//test1.createNode("Login Page");
		//test1.addScreenCaptureFromPath(sScreenshotFilePath);
		
		 InfinityLogin.login(Username,Password);
		
		Screenshot();
		 WriteExtentReport = test1.createNode("Navigate to Home Page");
		 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		Sleep(5000);
		 InfinityHome.NewProposal();
		//test1.log(Status.PASS, "Click On NewProposal"+ test1.addScreenCaptureFromPath(sScreenshotFilePath));
		
		Screenshot(); 
		 WriteExtentReport =test1.createNode("Navigate NewProposal Page ");
		 WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		
		
		
		InfinityNewProposal.EnterInfinityFundingProposal(CompanyName);
		//extent.flush();
		Sleep(5000);
		InfinityNewProposal.ClickOnInfinityFundingProposal();
		
		//InfinityNewProposal.LimitedCompany();
		
		InfinityNewProposal.ClickOnCompanyName();
		
		InfinityNewProposal.PrimaryDirector();
		//test1.log(Status.PASS, test1.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		//newproposalpage.LimitedCompany();
		//newproposalpage.SearchCompanyName(CompanyName);
		
		//newproposalpage.CompanyName();
		//test1.log(Status.PASS, "Select Company Name");
		// Screenshot();
	    // test1.log(Status.PASS, test1.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		//newproposalpage.PrimaryDirector();
		InfinityNewProposal.EnterEmail(Email);
		InfinityNewProposal.EnterPhoneNumberField(PhoneNumber);
		InfinityNewProposal.EnterBirthDay(BirthDay);
		InfinityNewProposal.EnterPostCode(PostCode);
		InfinityNewProposal.ClickOnFindAddress();
		InfinityNewProposal.SelectAddress();
		InfinityNewProposal.SelectResidentialPropertyYes();
		InfinityNewProposal.BusinessAddressSelectYes();
		InfinityNewProposal.NextButton();
		
		
		
		
		Screenshot(); 
		WriteExtentReport =test1.createNode("Navigate Loan Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		LoanInfo.NucleusBusinessLoan();
		LoanInfo.ConfirmAlert();
		LoanInfo.FundingNeeded(Fund);
		LoanInfo.LoanMonths(Months);
		LoanInfo.SelectPurposeFunding();
		LoanInfo.BrokerPercent(Percent);
		LoanInfo.NextStep();
		
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Director Information Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Sleep(5000);
		
		try{
			
		DirectorInformation.EditDirectorDetails();
		DirectorInformation.DateofBirth(BirthDay1);
		DirectorInformation.Email(Email1);
		Sleep(1000);
		DirectorInformation.DirMobile(MobileNumber);
		
		//DirectorInformation.dirPhone(PhoneNO);
		DirectorInformation.PersonalGuaranteeYes();
		DirectorInformation.EnterPostCode(PostCode);
		DirectorInformation.ClickonFindAddress();
		Sleep(3000);
		DirectorInformation.SelectAddress();
		DirectorInformation.SelectResidentialPropertyYes();
		DirectorInformation.ClickOnSubmit();
		//test1.log(Status.PASS, "Director Information Page");
		} catch(Exception e)  
       {  
           System.out.println(e);  
       }  
		Sleep(3000);
		//DirectorInformation.EditShareholderDetails();
		DirectorInformation.ClickOnNext();
		Screenshot();
		WriteExtentReport =test1.createNode("Navigate Documents Page ");
		WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		Documents.SelectAccountingPackage();
		Sleep(3000);
		Documents.ClickOnDirector();
		Documents.ClickOnOk();
		Documents.SelectBank(Bank);
		Documents.SelectBankAccountType(BankType);
		Sleep(3000);
		Documents.ClickOnDirector1();
		Sleep(3000);
		Documents.ClickOnOk();
		Documents.SubmitButton();
		//test1.log(Status.PASS, "Documents Page");
		driver.get(url1);
		Sleep(6000);
		Set<String> handles3 = driver.getWindowHandles();
		List<String> hList3 = new ArrayList<String>(handles3);
		if(switchToRightWindow("Mail - Shahrukh Aatar - Outlook",hList3)){
		      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
		      }
		    Office.ClickOnPulse();
			Office.SelectFirstEmail();
			//test1.log(Status.PASS, "Office Page");
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
			      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
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
			      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
			      }
			System.out.println(driver.getTitle());
			 myPulse.ClickOnSignIn();
			//test1.pass("ClickOnSignIn");
			myPulse.ClickOnAccount();
			//test1.pass("ClickOnAccount");
			myPulse.ClickOnConnectAccountInformation();
			if(switchToRightWindow("myPulse - Plaid Open Banking",hList1)){
			      System.out.println(driver.getCurrentUrl() + ": " + driver.getTitle());
			      }
			System.out.println(driver.getTitle());
			Sleep(4000);
			myPulse.Congratulations();	
			Sleep(2000);
			Screenshot();
			Sleep(2000);
			WriteExtentReport =test1.createNode("Navigate Congratulations Page ");
			WriteExtentReport.log(Status.PASS, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		   // Screenshot();
		   // test1.log(Status.PASS, test1.addScreenCaptureFromPath(sScreenshotFilePath)+"pass");
		    System.out.println("11111111111111111111111111222222222222222222222222222222222222222222");
	}
	
	catch(Exception e) {
	
		String Error = e.toString();
		
		System.out.println(Error);
		//test1.fail("Error Message" +Error );
		
		Screenshot();
		WriteExtentReport =test1.createNode("Failed Page ");
		WriteExtentReport.log(Status.FAIL, WriteExtentReport.addScreenCaptureFromPath(sScreenshotFilePath)+Error);
		//Screenshot();
		// test1.fail("Test Case failed check screenshot below"+test1.addScreenCaptureFromPath(sScreenshotFilePath));
		// test1.log(Status.FAIL, test1.addScreenCaptureFromPath(sScreenshotFilePath)+Error);
		//String screenshotPath = TestBase.Screenshot();
       //test1.fail("Test Case failed check screenshot below"+test1.addScreenCaptureFromPath(screenshotPath));	
	}
		
		
		
		
	}

	
/*	@DataProvider

    public Object[][] Authentication() throws Exception{

         Object[][] testObjArray = ExcelUtils.getTableArray("C://Users//ShahrukhAatar//Downloads//TestData.xlsx","sheet1");
         return (testObjArray);

		}*/

	
	
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
