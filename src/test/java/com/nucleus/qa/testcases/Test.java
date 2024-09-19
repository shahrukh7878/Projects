package com.nucleus.qa.testcases;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Test {

	public static void main(String[] args) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtpout.secureserver.net");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("integration@mypulse-sandbox.io", "Plom55AD!"));
		email.setSSLOnConnect(true);
		email.setFrom("integration@mypulse-sandbox.io");
		email.setSubject("myFunding Sandbox");
		email.setMsg("This is a test mail ... :-)"); 
		email.addTo("shahrukh.aatar@mypulse.io");
		email.send();
		System.out.println("1111111111111111");
		
	}

}
