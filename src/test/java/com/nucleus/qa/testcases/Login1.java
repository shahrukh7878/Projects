package com.nucleus.qa.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;



public class Login1 extends LoginPageTest  {
	
	public static String sTestcase;	
	public static XSSFSheet sheet1;
	public static XSSFWorkbook wb;
	public static boolean status;

	public static void main(String[] args) throws Exception {
		
		
		File src =new File("C:/Users/ShahrukhAata_l4/BDD/MobileBDDAndroid/TestData/TestData.xlsx");
		FileInputStream fis=new FileInputStream(src);
		//XSSFWorkbook wb=new XSSFWorkbook(fis);
		wb=new XSSFWorkbook(fis);
		sheet1=wb.getSheetAt(0);
		
		
		
		int iRowCount =1;
		for(int iRCount=1;iRCount<=1000;iRCount++) {
			
			try
			{
				String sRowvalues = "";
			try 
			{
				sRowvalues = sheet1.getRow(iRCount).getCell(1).getStringCellValue();
			} 
			catch (Exception e) 
			{
				break;
			}
			if(sRowvalues.equalsIgnoreCase("Yes"))
			{
				try {
					
			
					Thread.sleep(2000);
				} catch (Exception e) {
				}
				sTestcase = sheet1.getRow(iRowCount).getCell(0).getStringCellValue();
				System.out.println(sTestcase);
				
				System.out.println("5555555555555555555");
				TestListenerAdapter tla = new TestListenerAdapter();
			    TestNG testng = new TestNG();
			    testng.setTestClasses(new Class[] { LoginPageTest.class });
			    testng.addListener(tla);
			    testng.run(); 
				
				iRowCount=iRCount;
				break;
			}
			}
			catch(Exception e)
			{
				
			}
		}
		wb.close();
		
		
		

	}

	

}
