package com.amazon.MailUtility;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.amazon.ExtentUtility.ExtentListeners;



public class SendEmail {

	public static void sendEmailWithAttachment(String to) {
		
		File file = new File(System.getProperty("user.dir")+"/reports.zip");
//		String from = "ingle12ajay@gmail.com";
//	//	Date d = new Date();
//		//String host = "smtp.gmail.com";
//		
		int totaltc = ExtentListeners.pass+ExtentListeners.fail+ExtentListeners.skip;
//		
		String html = "<html><head><style>table, th, td { border: 1px solid black;}</style</head><body>"+
						"<p><b>Hi All,</b></p>"+
						"<table> <tr> <th> Total Testcases</th>"+
						"<th style=\"background-color:green\">Passed </th>\r\n"+
						"<th style=\"background-color:red\">Failed</th>"+
						"<th style=\"background-color:yellow\">Skipped</th>"+
						"<th style=\"background-color:Orange\">Version</th>"+
						"</tr> <tr>"+"<td>"+totaltc+"</td>\r\n"+
						"<td>"+ExtentListeners.pass+"</td>\r\n"+
						"<td>"+ExtentListeners.fail+"</td>\r\n"+
						"<td>"+ExtentListeners.skip+"</td>"+
						"<td>V1.0.0.44</td>"+"</tr></table>"+"<p><b> Do not reply to this mail. This is an auto generated email.\n Please reach out to Automation testing team for any queries...</b></p>"
						+"<p><b>Regards,</b></p>"+"<p><b>Automation Team</b></p>"+"</body></html>";
		
//		Properties properties = System.getProperties();
//		// this will set host of server
//		properties.put("mail.smtp.host", "smtp.gmail.com");
//		// set the port of socket factory
//		properties.put("mail.smtp.socketFactory.port", "465");//25
//		// set socket factory
//		properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
//		// set the authentication to true
//		properties.put("mail.smtp.auth", "true");
//		// set the port of SMTP server
//		properties.put("mail.smtp.port", "465");//465
//		properties.put("mail.smtp.starttls.enabletrue", "true");
//		// This will handle the complete authentication
//				Session session = Session.getDefaultInstance(properties,
//
//						new javax.mail.Authenticator() {
//
//							protected PasswordAuthentication getPasswordAuthentication() {
//
//							return new PasswordAuthentication(from, "Ajay@2022");
//
//							}
//
//						});
//		//Session session = Session.getDefaultInstance(properties);
//		
//		try {
//			// Create object of MimeMessage class
//			MimeMessage message = new MimeMessage(session);
//			// Set the from address
//			message.setFrom(new InternetAddress(from));
//			// Set the recipient address
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//			// Add the subject link
//			message.setSubject("Selenium Automation Test Results for CM3");
//			// Create object to add multimedia type content
//			BodyPart messageBodyPart = new MimeBodyPart();
//			
//			messageBodyPart.setContent(html,"text/html");
//			// Create object of MimeMultipart class
//			Multipart multipart = new MimeMultipart();
//			// add body part 
//			multipart.addBodyPart(messageBodyPart);
//			
//			messageBodyPart = new MimeBodyPart();
//			
//			DataSource source = new FileDataSource(file);
//			messageBodyPart.setDataHandler(new DataHandler(source));
//			messageBodyPart.setFileName("Automation Test Report"+".zip");
//			multipart.addBodyPart(messageBodyPart);
//			// set the content
//			message.setContent(multipart);
//			// finally send the email
//			Transport.send(message);
//			
//			System.out.println("Sent message successfully...");
//		}catch(MessagingException mex) {
//			mex.printStackTrace();
//		}
		
		
		
		// Create object of Property file
				Properties props = new Properties();

				// this will set host of server- you can change based on your requirement 
				props.put("mail.smtp.host", "smtp.gmail.com");

				// set the port of socket factory 
				props.put("mail.smtp.socketFactory.port", "465");

				// set socket factory
				props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

				// set the authentication to true
				props.put("mail.smtp.auth", "true");

				// set the port of SMTP server
				props.put("mail.smtp.port", "465");

				// This will handle the complete authentication
				Session session = Session.getDefaultInstance(props,

						new javax.mail.Authenticator() {

							protected PasswordAuthentication getPasswordAuthentication() {

							return new PasswordAuthentication("ingle12ajay@gmail.com", "Ajay@2022");

							}

						});

				try {

					// Create object of MimeMessage class
					Message message = new MimeMessage(session);

					// Set the from address
					message.setFrom(new InternetAddress("ingle12ajay@gmail.com"));

					// Set the recipient address
					message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("abingle27@gmail.com"));
		            
		                        // Add the subject link
					message.setSubject("Selenium Automation Test Results for CM3");

					// Create object to add multimedia type content
					BodyPart messageBodyPart1 = new MimeBodyPart();
					
					messageBodyPart1.setContent(html,"text/html");
					// Set the body of email
					//messageBodyPart1.setText("This is message body");

					// Create another object to add another content
					//MimeBodyPart messageBodyPart2 = new MimeBodyPart();

					// Mention the file which you want to send
					//String filename = "G:\\a.xlsx";

					// Create data source and pass the filename
					DataSource source = new FileDataSource(file);
					messageBodyPart1.setFileName("Automation Test Report"+".zip");
					// set the handler
					messageBodyPart1.setDataHandler(new DataHandler(source));

					// set the file
					//messageBodyPart2.setFileName(filename);

					// Create object of MimeMultipart class
					Multipart multipart = new MimeMultipart();

					// add body part 1
					multipart.addBodyPart(messageBodyPart1);

					// add body part 2
					//multipart.addBodyPart(messageBodyPart1);

					// set the content
					message.setContent(multipart);

					// finally send the email
					Transport.send(message);

					System.out.println("=====Email Sent=====");

				} catch (MessagingException e) {

					throw new RuntimeException(e);

				}

			}
	
}
