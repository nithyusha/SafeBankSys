package com.ss.service;
import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendMailTLS {
 
	public void sendEmail(String emailId, String data, String input) {
 
		final String username = "corporate.manager.asu@gmail.com";
		final String password = "grandcanyon_123";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("corporate.manager.asu@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailId));
			if(input.equals("OTP")){
				message.setSubject("One Time Password from SBS");
				message.setText("OTP : "
					+ data);
			}
			else if(input.equals("PKI")){
				message.setSubject("Notification From One Of the Users");
				message.setText(data);
			}
			
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			//throw new RuntimeException(e);
			System.out.println("Exception in SendMailTLS"+e);
		}
	}
}