package com.nucleus.qa.testcases;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class Test2 {

public static void main(String[] args) {
    final String username = "integration@mypulse-sandbox.io";  // like yourname@outlook.com
    final String password = "Plom55AD!";   // password here

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.isSSL", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtpout.secureserver.net");
    props.put("mail.smtp.port", "465");

    Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
        
      });
    session.setDebug(true);
    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse("shahrukh.aatar@mypulse.io"));   // like inzi769@gmail.com
        message.setSubject("Test");
        message.setText("HI you have done sending mail with outlook");
        
        Transport.send(message);

        System.out.println("Done");

    } catch (MessagingException e) {
    	System.out.println("inside catch block");
        throw new RuntimeException(e);
    }
}
}