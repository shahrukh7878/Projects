package com.nucleus.qa.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class Runner extends CoreSystemTesting  {
	
	 static String NucleusWebsite= "";
	 static  String InfinityFundingWebsite="";
	 static  String MypulseWebsite= "";
	 static String MyNucleusPortalBroker= "";
	 static  String MyNucleusPortalAdmin= "";
	 static String InfinityPortalDirectRoleBroker= "";
	 static String InfinityPortalTeleRoleBroker= "";
	 static String InfinityPortalDirectRoleAdmin= "";
	 static String InfinityPortalTeleRoleAdmin= "";
	 static String Mycollection= "";
	 static String MyReportingPortal= "";
	 static String MyAdminPortal= "";
	 static String Date1 = "";

	public static void main(String[] args) throws Exception {
		 while(true)
			{
			    Date sDate = new Date();
				int iHour = sDate.getHours();
				int iMinut = sDate.getMinutes();
				if(iHour==17){
					if(iMinut==45)
					{
					
						TestListenerAdapter tla = new TestListenerAdapter();
					    TestNG testng = new TestNG(); 
					    testng.setTestClasses(new Class[] { CoreSystemTesting.class });
					    testng.addListener(tla);
					    testng.run();
			
			
			
					    LocalDateTime localDate = LocalDateTime.now().minusDays(0);
						DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
						String Date = customFormat.format(localDate);
						LocalDate date = LocalDate.now();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						 Date1=date.format(formatter);
						File file =    new File("C://Users//ShahrukhAata_l4//Project//Test//TestData//TestData.xls");
						FileInputStream inputStream = new FileInputStream(file);
						HSSFWorkbook wb=new HSSFWorkbook(inputStream);
						HSSFSheet sheet1=wb.getSheetAt(0);
						HSSFRow row2=sheet1.getRow(1);
				        HSSFCell cell=row2.getCell(1); 
				        HSSFCell cell1=row2.getCell(2);
				        HSSFCell cell2=row2.getCell(3);
				        HSSFCell cell3=row2.getCell(4);
				        HSSFCell cell4=row2.getCell(5);
				        HSSFCell cell5=row2.getCell(6);
				        HSSFCell cell6=row2.getCell(7);
				        HSSFCell cell7=row2.getCell(8);
				        HSSFCell cell8=row2.getCell(9);
				        HSSFCell cell9=row2.getCell(10);
				        HSSFCell cell10=row2.getCell(11);
				        HSSFCell cell11=row2.getCell(12);
				    
				        
				        
				        
				        String NucleusWebsite= cell.getStringCellValue();
				        String  InfinityFundingWebsite= cell1.getStringCellValue();
				        String  MypulseWebsite= cell2.getStringCellValue();
				        String  MyNucleusPortalBroker= cell3.getStringCellValue();
				        String  MyNucleusPortalAdmin= cell4.getStringCellValue();
				        String InfinityPortalDirectRoleBroker= cell5.getStringCellValue();
				        String InfinityPortalTeleRoleBroker= cell6.getStringCellValue();
				        String  InfinityPortalDirectRoleAdmin= cell7.getStringCellValue();
				        String  InfinityPortalTeleRoleAdmin= cell8.getStringCellValue();
				        String  Mycollection= cell9.getStringCellValue();
				        String  MyReportingPortal= cell10.getStringCellValue();
				        String  MyAdminPortal= cell11.getStringCellValue();
						
						StringBuilder htmlContent = new StringBuilder();
						htmlContent.append("<html>\r\n"
								+ "<style>\r\n"
								+ "table, th, td {\r\n"
								+ " border: 1px solid black;\r\n"
								+ "  border-collapse: collapse;\r\n"
								+ "}\r\n"
								+ "</style>\r\n"
								+ "<body>\r\n"
								+ "\r\n"
								+ "<p>Hi Julius,</p>\r\n"
								+ "<p>Please find below the Core Systems Checks done for "+Date+".</p>\r\n"
								+ "\r\n"
								+ "\r\n"
								+ "<div style=\"width:200%;min-width:1640px;overflow-x:auto\">\r\n"
								+ "<table style=\"width:200%;text-align: center\">\r\n"
								+ "\r\n"
								+ "\r\n"
								+ "  <tr>\r\n"
								+ "    <th style=\"width:5%; white-space: nowrap;\"  rowspan=\"2\">Date</th>\r\n"
								+ "    <th style=\"width:7%; white-space: nowrap;\" rowspan=\"2\">NCF Website</th>\r\n"
								+ "    <th style=\"width:8%; white-space: nowrap;\" rowspan=\"2\">Infinity Website</th>\r\n"
								+ "    <th style=\"width:11%; white-space: nowrap;\" colspan=\"2\">My Nucleus Portal</th>\r\n"
								+ "    <th style=\"width:13%; white-space: nowrap;\" colspan=\"4\">My Funding Portal</th>\r\n"
								+ "    <th style=\"width:11%; white-space: nowrap;\" rowspan=\"2\">MyFunding Portal - Reports</th>\r\n"
								+ "    <th style=\"width:11%; white-space: nowrap;\" rowspan=\"2\">MyAdmin Portal - Reports</th>\r\n"
								+ "    <th style=\"width:11%; white-space: nowrap;\" rowspan=\"2\">My collections Portal</th>\r\n"
								+ "\r\n"
								+ "  </tr>\r\n"
								+ "  <tr>\r\n"
								+ "    <th style=\"width:5%\">Broker</th>\r\n"
								+ "    <th style=\"width:5%\">Admin</th>\r\n"
								+ "    <th style=\"width:10%; white-space: nowrap;\">Direct Role-Broker</th>\r\n"
								+ "    <th style=\"width:10%; white-space: nowrap;\">Direct Role-Admin</th>\r\n"
								+ "    <th style=\"width:9%; white-space: nowrap;\">Tele Role-Broker</th>\r\n"
								+ "    <th style=\"width:9%; white-space: nowrap;\">Tele Role-Admin</th>\r\n"
								+ "    \r\n"
								+ "  </tr>"
								+ "  <tr>\r\n"
								+ "    <td>"+Date1+"</td>\r\n"
								+ "    <td>"+NucleusWebsite+"</td>\r\n"
								+ "    <td>"+InfinityFundingWebsite+"</td>\r\n"
								+ "    <td>"+MyNucleusPortalBroker+"</td>\r\n"
								+ "    <td>"+MyNucleusPortalAdmin+"</td>\r\n"
								+ "    <td>"+InfinityPortalDirectRoleBroker+"</td>\r\n"
								+ "    <td>"+InfinityPortalTeleRoleBroker+"</td>\r\n"
								+ "    <td>"+InfinityPortalDirectRoleAdmin+"</td>\r\n"
								+ "    <td>"+InfinityPortalTeleRoleAdmin+"</td>\r\n"
								+ "    <td>"+Mycollection+"</td>\r\n"
								+ "    <td>"+MyReportingPortal+"</td>\r\n"
								+ "    <td>"+MyAdminPortal+"</td>\r\n"
								+ "  </tr>\r\n"
								+ "</table>\r\n"
								+ "\r\n"
								+ "\r\n"
								+ "</body>\r\n"
								+ "</html>\r\n"
								+ "\r\n"
								+ "");
						String Content= htmlContent.toString();
						Thread.sleep(9000);
					    EmailSend ob = new EmailSend();
					    EmailSend.sendEmailWithSqData(Content);    
					    
					}
						
				{
				}
				}
				
				System.out.println("111111111111111");
			/*	if(iHour==1){
					if(iMinut==55)
					{
						
						
						File file =    new File("C://Users//ShahrukhAata_l4//Project//Test//TestData//TestData.xls");
						FileInputStream inputStream = new FileInputStream(file);
						HSSFWorkbook wb=new HSSFWorkbook(inputStream);
						HSSFSheet sheet1=wb.getSheetAt(0);
						HSSFRow row2=sheet1.getRow(1);
				        HSSFCell cell=row2.getCell(1); 
				        HSSFCell cell1=row2.getCell(2);
				        HSSFCell cell2=row2.getCell(3);
				        HSSFCell cell3=row2.getCell(4);
				        HSSFCell cell4=row2.getCell(5);
				        HSSFCell cell5=row2.getCell(6);
				        HSSFCell cell6=row2.getCell(7);
				        HSSFCell cell7=row2.getCell(8);
				        HSSFCell cell8=row2.getCell(9);
				        HSSFCell cell9=row2.getCell(10);
				        HSSFCell cell10=row2.getCell(11);
				        HSSFCell cell11=row2.getCell(12);
				       
				         NucleusWebsite= cell.getStringCellValue();
				         InfinityFundingWebsite= cell1.getStringCellValue();
				         MypulseWebsite= cell2.getStringCellValue();
				         MyNucleusPortalBroker= cell3.getStringCellValue();
				         MyNucleusPortalAdmin= cell4.getStringCellValue();
				        InfinityPortalDirectRoleBroker= cell5.getStringCellValue();
				        InfinityPortalTeleRoleBroker= cell6.getStringCellValue();
				        InfinityPortalDirectRoleAdmin= cell7.getStringCellValue();
				        InfinityPortalTeleRoleAdmin= cell8.getStringCellValue();
				        Mycollection= cell9.getStringCellValue();
				        MyReportingPortal= cell10.getStringCellValue();
				        MyAdminPortal= cell11.getStringCellValue();
						 ArrayList<String> list = new ArrayList<>();
					        list.add(NucleusWebsite);
					        list.add(InfinityFundingWebsite);
					        list.add(MyNucleusPortalBroker);
					        list.add(MyNucleusPortalAdmin);
					        list.add(InfinityPortalDirectRoleBroker);
					        list.add(InfinityPortalTeleRoleBroker);
					        list.add(InfinityPortalDirectRoleAdmin);
					        list.add(InfinityPortalTeleRoleAdmin);
					        list.add(Mycollection);
					        list.add(MyReportingPortal);
					        list.add(MyAdminPortal);
					        
					        String searchString = "Not Working";

					        // Find the matching string
					        boolean found = false;
					        for (String str : list) {
					        	System.out.println("1111111111111111"+str);
					            if (str.equals(searchString)) {
					                found = true;
					                System.out.println("failed loop running");
					                TestListenerAdapter tla = new TestListenerAdapter();
								    TestNG testng = new TestNG(); 
								    testng.setTestClasses(new Class[] { CoreSystemTesting.class });
								    testng.addListener(tla);
								    testng.run();
				
								    
								    
								    }
					             
					            break;
					            }
					        
					}
					}*/
				System.out.println(iHour);
				 Thread.sleep(15000);
				
					}
				//Thread.sleep(300*1000);
				//Thread.sleep(300*1000);
				//Thread.sleep(300*1000);
		       
			}
	}

