package com.nucleus.qa.testcases;

import java.util.HashMap;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Testing {
	


		
	
			    public static void main(String[] args) {
			        // Set up email properties
			    	String host = "smtpout.secureserver.net";
			    	
			    	Properties props = new Properties();
					props.put("mail.smtp.host", "true");
					props.put("mail.smtp.starttls.enable", "true");
					props.put("mail.smtp.host", host);
					props.put("mail.smtp.port", "587");
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.ssl.trust", host);
					props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			    	
			       /* Properties props = new Properties();
			        props.put("mail.smtp.host", "smtp.example.com");
			        props.put("mail.smtp.port", "587");
			        props.put("mail.smtp.auth", "true");
			        props.put("mail.smtp.starttls.enable", "true");*/

			        // Create a session with an authenticator
			        Session session = Session.getInstance(props, new Authenticator() {
			            protected PasswordAuthentication getPasswordAuthentication() {
			                return new PasswordAuthentication("integration@mypulse-sandbox.io", "Plom55AD!");
			            }
			        });

			        try {
			        	
			    
			        	
			            // Create a new email message
			            Message message = new MimeMessage(session);
			            message.setFrom(new InternetAddress("integration@mypulse-sandbox.io", "Integration"));
			            
			           // message.addHeader("integration@mypulse-sandbox.io", "Name");
			            
			            //message.(Message.RecipientType.TO, InternetAddress.parse("integration@mypulse-sandbox.io"));
			            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("shahrukh.aatar@mypulse.io"));
			            message.setSubject("Test Email");
			            message.setText("This is a test email with a custom 'From' name.");

			            // Send the email
			            Transport.send(message);

			            System.out.println("Email sent successfully!");

			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			    }
			}
			
	

