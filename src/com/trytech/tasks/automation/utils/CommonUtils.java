package com.trytech.tasks.automation.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonUtils {

	public static void click(WebDriver driver, WebElement we,
			long timeOutInSeconds) {

	}

	public static void stopInternet() {
		try {
			System.out.println(new java.util.Date().toString()
					+ " :: GOING TO STOP INTERNET....");
			Process p = Runtime.getRuntime().exec("ipconfig /release");
			p.waitFor();
			System.out.println(new java.util.Date().toString()
					+ " :: INTERNET  DISCONNECTED");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void startInternet() {
		try {
			System.out.println(new java.util.Date().toString()
					+ " :: GOING TO START INTERNET....");
			Process p = Runtime.getRuntime().exec("ipconfig /renew");
			p.waitFor();
			System.out.println(new java.util.Date().toString()
					+ " :: INTERNET  CONNECTED");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getTime() {
		return new java.util.Date().toString();
	}

	public static void captureScreenShot(WebDriver driver, String file) {

		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy
		// somewhere
		try {
			FileUtils.copyFile(scrFile, new File(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void sendEmail() {

		if (ConfigProperties.getProperty("sendEmail").equals("N")) {
			return;
		} else {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			Session session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									ConfigProperties.getProperty("gmailUserID"), 
									ConfigProperties.getProperty("gmailPassword"));
						}
					});

			try {

				Message message = new MimeMessage(session);

				message.setFrom(new InternetAddress("verma9@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse("vermaavinash859@gmail.com"));
				message.setSubject("Trytech Automation");
				// Create the message part
				BodyPart messageBodyPart = new MimeBodyPart();

				messageBodyPart = new MimeBodyPart();

				messageBodyPart.setText("Result for ");

				String filename = System.getProperty("java.io.tmpdir")
						+ "\\screenshot.png";

				DataSource source = new FileDataSource(filename);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(new SimpleDateFormat(
						"yyyy-MM-dd hh-mm-ss").format(new Date())
						+ "_tasklist.png");

				// Create a multipar message
				Multipart multipart = new MimeMultipart();

				multipart.addBodyPart(messageBodyPart);

				message.setContent(multipart);

				Transport.send(message);

				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
