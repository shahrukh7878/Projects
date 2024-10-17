package com.nucleus.qa.testcases;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
//import org.junit.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
//import com.sun.xml.internal.ws.client.sei.ResponseBuilder.Body;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

//import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;


import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import org.json.simple.JSONObject;



public class Test {

	public static void main(String[] args) throws Exception {
	
	File src = new File("C:\\Users\\ShahrukhAata_l4\\BDD\\MobileBDDAndroid\\TestData\\TestData.xlsx");
	FileInputStream fis=new FileInputStream(src);
	XSSFWorkbook wb=new XSSFWorkbook(fis);
	XSSFSheet sheet1=wb.getSheetAt(0);
	for(int i=1;i<=10;i++)
	{
		String sRequest31= sheet1.getRow(i).getCell(0).getStringCellValue();
		initialization("Api_01"+i);
		
		
		
		System.out.println(sRequest31);	
	}
	

	wb.close();

}
	
	public static void initialization(String sTestCaseidval) {
		String SPath = "C://Users//xaatars//Desktop//Shahrukh//TestData1.xlsx";
		String sTestCaseid = sTestCaseidval;//;"Api_32(updateRetailProspects-KFS Consents and Declarations)";
		//String sFieldValue = getColumnNumber(SPath, "URL",sTestCaseid);
		//String sServiceType = getColumnNumber(SPath, "Service Type",sTestCaseid);
		//String StringkfdConsentDone= getColumnNumber(SPath, "kfdConsentDone",sTestCaseid);
		
		//ArrayList data=d.getData("");
		//String url =(String) data.get(1);
		System.setProperty("webdriver.edge.driver", "C://Users//ShahrukhAata_l4//Project//Test//driver//edgedriver_win64//msedgedriver.exe");
		EdgeDriver driver = new EdgeDriver();
		//WebDriver driver = new EdgeDriver();
		
		/*String browserName=prop.getProperty("browser");
		 * 
		 
		
		if(browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", "C://Users//ShahrukhAatar//Downloads//edgedriver_win64//msedgedriver.exe");
			driver = new EdgeDriver();
		}else if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C://Users//ShahrukhAatar//Downloads//edgedriver_win64//chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", "C://Users//ShahrukhAatar//Downloads//edgedriver_win64//geckodriver.exe");
			driver = new FirefoxDriver();
	    }*/
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(90, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(90,TimeUnit.SECONDS);
}
	
	
}