package com.nucleus.qa.testcases;

import java.util.Date;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class Runner extends CoreSystemTesting  {

	public static void main(String[] args) throws Exception {
		
		
		
		
		 while(true)
			{
				Date sDate = new Date();
				int iHour = sDate.getHours();
				int iMinut = sDate.getMinutes();
				if(iHour==19){
					if(iMinut==41)
					{
						System.out.println("5555555555555555555");
						TestListenerAdapter tla = new TestListenerAdapter();
					    TestNG testng = new TestNG();
					    testng.setTestClasses(new Class[] { CoreSystemTesting.class });
					    testng.addListener(tla);
					    testng.run();
					}		
				{
				}
				}
				System.out.println(iHour);
				//Thread.sleep(300*1000);
				//Thread.sleep(300*1000);
				//Thread.sleep(300*1000);
				Thread.sleep(15000);
				
			}
		

	}

	

}
