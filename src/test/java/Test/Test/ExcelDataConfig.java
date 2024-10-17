package Test.Test;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig {
	
	XSSFWorkbook wb;
	
	XSSFSheet sheet1;
	
	public  ExcelDataConfig(String excelPath) {
		
		
		
		try {
			File src=new File(excelPath);
			FileInputStream fis=new FileInputStream(src);
			wb=new XSSFWorkbook(fis);
		}
		catch(Exception e) {
	
		}
	}
	
	
	public String getData(int sheetNumber,int row, int column) {
		
		sheet1=wb.getSheetAt(sheetNumber);
		String data=sheet1.getRow(row).getCell(column).getStringCellValue();
	return data;	
	}
	
	public int getRowCount(int sheetIndex) {
		
		int row = wb.getSheetAt(sheetIndex).getLastRowNum();
		row=row=1;
		return row;
	}
		
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		ExcelDataConfig Data=new ExcelDataConfig("C:\\Users\\ShahrukhAata_l4\\BDD\\MobileBDDAndroid\\TestData\\TestData.xlsx");
		int rows=Data.getRowCount(0);
		Object[][] data=new Object[rows][2];
		
		for(int i=0; i<rows; i++) {
			
			String name=(String) (data[i][0]=Data.getData(0,i,0));
			String name1=(String) (data[i][1]=Data.getData(0,i,1));
			
			System.out.println(name);
			
		}
		
		
		
		

	}

}
