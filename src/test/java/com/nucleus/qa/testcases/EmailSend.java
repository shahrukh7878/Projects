package com.nucleus.qa.testcases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

			LocalDateTime localDate = LocalDateTime.now().minusDays(1);
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
		
		
		/*public static void main(String[] args)
		{
			String htmlContent = "<h1>Core System</h1>"
                    + "<table border='1'>"
                    + "<tr><th>Application</th><th>Status</th></tr>"
                    + "<tr><td>Pulse Website</td><td>working</td></tr>"
                    + "<tr><td>nucleus</td><td>working</td></tr>"
                    + "</table>";
			sendEmailWithSqData(htmlContent);
		}*/

		

		

	}
