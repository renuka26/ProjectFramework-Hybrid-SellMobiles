package com.mindtree.keywordDrivenFramework.utility;

import java.net.MalformedURLException;
import java.net.URL;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class EmailUtility {
public static void sendMail() throws EmailException, MalformedURLException {
	
	EmailAttachment reportAttachment = new EmailAttachment();
	  reportAttachment.setPath("D:\\Selenium\\git_project\\sell-mobiles-automation-codebase\\Test_Execution_Report_display.html");
	  reportAttachment.setDisposition(EmailAttachment.ATTACHMENT);
	  reportAttachment.setDescription("Extent report");
	  reportAttachment.setName("Report");
	
	  EmailAttachment logFile = new EmailAttachment();
	  logFile.setPath("D:\\Selenium\\git_project\\sell-mobiles-automation-codebase\\logfile.log");
	  logFile.setDisposition(EmailAttachment.ATTACHMENT);
	  logFile.setDescription("Log File");
	  logFile.setName("log file");
	
	//  MultiPartEmail email = new MultiPartEmail();
	  HtmlEmail email = new HtmlEmail();
	email.setHostName("smtp.gmail.com");
	email.setSmtpPort(465);
	email.setAuthenticator(new DefaultAuthenticator("renukatamilarasan1@gmail.com", "26-10-1996"));
	email.setSSLOnConnect(true);
	email.setFrom("renukatamilarasan1@gmail.com");
	email.setSubject("Report for sell mobiles project");
	//email.setHtmlMsg("<html>");
	email.setHtmlMsg("<html><body style=\"color:red;\"><B><h3>Documents For Sell Mobile Project</body></html></B></h3>");
	email.setHtmlMsg("<html><body style=\"background-color:rgb(255, 0, 0, 0.5);\"><B><h2>Hi all,I have attached the extent report for sellmobiles project for your reference ... :-)</html><body style=\"color:red;\"></B></h2>");
	
	System.out.println("crct configuration.configuration..");
	
	 // embed the image and get the content id
//	  URL url = new URL("//http://4.bp.blogspot.com/-W1gdfSnvVqM/VUSDbTgPGoI/AAAAAAAABDQ/3XwMFJ--kDg/s1600/photo_1392707321_quiz_image_temp.jpg");
//	  String cid = email.embed(url, "Mindtree logo");
//	  
//	  // set the html message
//	  email.setHtmlMsg("<html> <img src=\"cid:"+cid+"\"></html>");
//	  email.setHtmlMsg("<html><B></h2>Documents For Sell Mobile Project</B></h2></html>");
//
//	  // set the alternative message
//	  email.setTextMsg("Your email client does not support HTML messages");
//	System.out.println("set html msg");
	//http://4.bp.blogspot.com/-W1gdfSnvVqM/VUSDbTgPGoI/AAAAAAAABDQ/3XwMFJ--kDg/s1600/photo_1392707321_quiz_image_temp.jpg
	
	 	email.addTo("Renuka.Thamilarasan@mindtree.com");
		email.addTo("Stefi.Steephen@mindtree.com");
	email.addTo("Onkar.Birajdar2@mindtree.com");
		email.addTo("Bibek.Nath2@mindtree.com");
		email.addTo("Aditya.Singh@mindtree.com");
		email.addTo("Debasmita.Nanda@mindtree.com");
		email.addTo("Raghavendra.Ranjolkar@mindtree.com");
		email.addTo("Tamilarasan.P2@mindtree.com");
		email.addTo("Ronak.Sayta2@mindtree.com");
		email.addTo("Jeeva.Dhanushkodi@mindtree.com");
		email.addTo("Nayana.N2@mindtree.com");
	email.addTo("Umashankar.Mangnale@mindtree.com");
	
	
//	Address[] cc = new Address[] {InternetAddress.parse("Renuka.Thamilarasan@mindtree.com"),
//            InternetAddress.parse("Suganya.Marappan@mindtree.com"), 
//            //InternetAddress.parse("ghi@abc.com")};
//email.addRecipients(Message.RecipientType.CC, cc);
	
	
	email.attach(reportAttachment);
	email.attach(logFile);
	
	email.send();
}}
