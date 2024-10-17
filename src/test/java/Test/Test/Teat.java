package Test.Test;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Teat {
	
	
	public  String getColumnNumber(String sColumName) throws Exception 
	{
		String FilePath="C:\\Users\\ShahrukhAata_l4\\BDD\\MobileBDDAndroid\\TestData\\TestData.xlsx";
		//String sColumName="name";
		String sTestcaseID="TC_05";
		
		
		
		
		//String sTestcaseID=Runner.sTestcase;
		File src =new File(FilePath);
		FileInputStream fis=new FileInputStream(src);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet1=wb.getSheetAt(0);
		//XSSFSheet sheet1=Runner.sheet1;
		int iCloumnCount = 0;
		for(int iColCount=0;iColCount<=1000;iColCount++)
		{
			String sCloumnvalues = sheet1.getRow(0).getCell(iColCount).getStringCellValue();
			if(sCloumnvalues.equalsIgnoreCase(sColumName))
			{
				iCloumnCount=iColCount;
				break;
			}
		}
		
		int iRowCount =0;
		for(int iRCount=0;iRCount<=1000;iRCount++) {
			String sRowvalues = sheet1.getRow(iRCount).getCell(0).getStringCellValue();
			if(sRowvalues.equalsIgnoreCase(sTestcaseID))
			{
				iRowCount=iRCount;
				break;
			}
		}
		
		String sFieldValue = sheet1.getRow(iRowCount).getCell(iCloumnCount).getStringCellValue();
		sFieldValue = sFieldValue.replace("'", "");
		
		System.out.println(sColumName);
		
		//wb.close();
		return sFieldValue;
		
	}
	
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Teat ha = new Teat();
		
		ha.getColumnNumber("name");
		
		

	}

}
