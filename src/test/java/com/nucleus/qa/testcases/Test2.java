package com.nucleus.qa.testcases;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.Properties;
		
	import  java.sql.Connection;		
	import  java.sql.Statement;		
	import  java.sql.ResultSet;		
	import  java.sql.DriverManager;		
	import  java.sql.SQLException;	
	public class Test2 {
	    	public static void  main(String[] args) throws  ClassNotFoundException, SQLException {													
					ArrayList<String> list= new ArrayList<String>();
					
					list.add("shahrukh");
					list.add("abcd");
					list.add("sdsd");
					
					System.out.println(list);
					
					
			}
	}