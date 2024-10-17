package Test.Test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class Readexceldata {
	
 public static void main(String[] args) {
		        String excelFilePath = "C:\\Users\\ShahrukhAata_l4\\BDD\\MobileBDDAndroid\\TestData\\TestData.xlsx";
		        try (FileInputStream fis = new FileInputStream(excelFilePath);
		             Workbook workbook = new XSSFWorkbook(fis)) {

		            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet
		            for (Row row : sheet) {
		                for (Cell cell : row) {
		                    switch (cell.getCellType()) {
		                        case STRING:
		                        	
		                        	System.out.println(cell.getColumnIndex());
		                        	
		                            //System.out.print(cell.getStringCellValue() + "\t");
		                            break;
		                        case NUMERIC:
		                            //System.out.print(cell.getNumericCellValue() + "\t");
		                            break;
		                        case BOOLEAN:
		                           // System.out.print(cell.getBooleanCellValue() + "\t");
		                            break;
		                        default:
		                            System.out.print("Unknown Cell Type\t");
		                            break;
		                    }
		                }
		                System.out.println();
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
		
	


