package com.trytech.tasks.automation.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.trytech.tasks.automation.utils.DriverFactory;

public class BasePage {
	
	final String URL = "http://www.trytech.in/index.aspx";
	static WebDriver driver;

	
	void initBrowser(){
		System.setProperty("webdriver.chrome.driver",
				"drivers/chromedriver.exe");
		
		driver = DriverFactory.getInstance().getDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	void openUrl(){
		driver.get(URL);
	}
	
	

}
