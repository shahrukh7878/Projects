package com.nucleus.qa.testcases;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.nucleus.qa.base.TestBase;

	public class EmailSend extends TestBase
	{
		public static Object sendEmailWithSqData(String messageToSend)
		{

			LocalDateTime localDate = LocalDateTime.now().minusDays(0);
			DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
			
			String host = "smtpout.secureserver.net";

			final String user = "integration@mypulse-sandbox.io";
			final String password = "Plom55AD!";

			String to = "shahrukh.aatar@mypulse.io";
			String to1 = "";
			String to2 = "";
			String cc = "";

			Properties props = new Properties();
			props.put("mail.smtp.host", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.trust", host);
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(user, password);
				}
			});

			try
			{
		
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(user));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				//message.addRecipient(Message.RecipientType.TO,new
			   //InternetAddress(to1));
				// message.addRecipient(Message.RecipientType.TO,new
				// InternetAddress(to2));
				// message.addRecipient(Message.RecipientType.CC,new
				// InternetAddress(cc));
				message.setSubject("Core Systems Check : " + customFormat.format(localDate));
				//message.setContent(null);
				//message.setText("Hi Julius");
				//message.setText("Please find below the Core Systems Checks done");
				message.setContent(messageToSend, "text/html");
				// message.setText(messageToSend);
				//message.setDescription(htmlContent);

				Transport.send(message);
				System.out.println("message sent successfully...");
				
				

			}
			catch(MessagingException e)
			{
				System.out.println("Exception occured is :" + e);
			}
			return null;
		}
		
		
		public static void main(String[] args) throws InterruptedException
		{
			
			LocalDateTime localDate = LocalDateTime.now().minusDays(0);
			DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
			
			String Date = customFormat.format(localDate);
			
			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			//System.out.println(date.format(formatter));
			String Date1=date.format(formatter);
			
			
			StringBuilder htmlContent = new StringBuilder();
			htmlContent.append("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
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
					+ "\r\n"
					+ "<table style=\"width:100%\">\r\n"
					+ "<table style=\"text-align: center\"> \r\n"
					+ "\r\n"
					+ "  <tr>\r\n"
					+ "    <th style=\"width:150%\" rowspan=\"2\">Date</th>\r\n"
					+ "    <th style=\"width:100%\" rowspan=\"2\">NCF Website</th>\r\n"
					+ "    <th style=\"width:150%\" rowspan=\"2\">Infinity Website</th>\r\n"
					+ "    <th style=\"width:100%\" rowspan=\"2\">Pulse Website</th>\r\n"
					+ "    <th style=\"width:100%\" colspan=\"2\">My Nucleus Portal</th>\r\n"
					+ "    <th style=\"width:100%\" colspan=\"4\">My Funding Portal</th>\r\n"
					+ "    <th style=\"width:100%\" rowspan=\"2\">MyFunding Portal - Reports</th>\r\n"
					+ "    <th style=\"width:100%\" rowspan=\"2\">MyAdmin Portal - Reports</th>\r\n"
					+ "    <th style=\"width:100%\" rowspan=\"2\">My collections Portal</th>\r\n"
					+ "\r\n"
					+ "  </tr>\r\n"
					+ "  <tr>\r\n"
					+ "    <th>Broker</th>\r\n"
					+ "    <th>Admin</th>\r\n"
					+ "    <th>Direct Role-Broker</th>\r\n"
					+ "    <th>Direct Role-Admin</th>\r\n"
					+ "    <th>Tele Role-Broker</th>\r\n"
					+ "    <th>Tele Role-Admin</th>\r\n"
					+ "    \r\n"
					+ "  </tr>\r\n"
					+ "  <tr>\r\n"
					+ "    <td>"+Date1+"</td>\r\n"
					+ "    <td>Working</td>\r\n"
					+ "    <td>Working</td>\r\n"
					+ "    <td>Working</td>\r\n"
					+ "    <td>Working</td>\r\n"
					+ "    <td>Working</td>\r\n"
					+ "    <td>Working</td>\r\n"
					+ "    <td>Working</td>\r\n"
					+ "    <td>Working</td>\r\n"
					+ "    <td>Working</td>\r\n"
					+ "    <td>Working</td>\r\n"
					+ "    <td>Working</td>\r\n"
					+ "    <td>Working</td>\r\n"
					+ "  </tr>\r\n"
					+ "</table>\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "</body>\r\n"
					+ "</html>\r\n"
					+ "\r\n"
					+ "");
	    
			
			String Content= htmlContent.toString();
		
			 while(true)
				{
					Date sDate = new Date();
					int iHour = sDate.getHours();
					int iMinut = sDate.getMinutes();
					if(iHour==17){
						if(iMinut==30)
						{
							System.out.println("5555555555555555555");
							
							sendEmailWithSqData(Content);
						
						}		
					{
					}
					}
					System.out.println(iHour);
					Thread.sleep(15000);
					
				}	
		}

	}
