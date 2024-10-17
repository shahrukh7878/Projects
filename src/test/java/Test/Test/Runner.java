package Test.Test;


import java.io.File;
import java.io.FileInputStream;
import java.util.stream.Stream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.runner.RunWith;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import com.nucleus.qa.testcases.CoreSystemTesting;



public class Runner {
	
public static String sTestcase;	
public static XSSFSheet sheet1;
public static XSSFWorkbook wb;
public static boolean status;
	


public static void main(String[] args) throws Throwable {

File src =new File("C:/Users/ShahrukhAata_l4/Project/Test/TestData/TestData1.xlsx");
FileInputStream fis=new FileInputStream(src);
//XSSFWorkbook wb=new XSSFWorkbook(fis);
wb=new XSSFWorkbook(fis);
sheet1=wb.getSheetAt(0);
/*int iCloumnCount = 0;
for(int iColCount=0;iColCount<=1000;iColCount++)
{
	String sCloumnvalues = sheet1.getRow(0).getCell(iColCount).getStringCellValue();
	if(sCloumnvalues.equalsIgnoreCase(sColumName))
	{
		iCloumnCount=iColCount;
		break;
	}
}*/

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
			//BaseClass.driver.closeApp();
			Thread.sleep(2000);
		} catch (Exception e) {
		}
		sTestcase = sheet1.getRow(iRowCount).getCell(0).getStringCellValue();
		
		TestListenerAdapter tla = new TestListenerAdapter();
	    TestNG testng = new TestNG();
	    testng.setTestClasses(new Class[] { CoreSystemTesting.class });
	    testng.addListener(tla);
	    testng.run(); 
		
		//BaseClass.driver.closeApp();
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
