package com.trytech.tasks.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.trytech.tasks.automation.utils.CommonUtils;
import com.trytech.tasks.automation.utils.ConfigProperties;

public class HomePage extends BasePage{
	
	//	@FindBy( xpath ="//a[@href='login.aspx']") // changed due to a change in application on 3-Jan-2017

	@FindBy( xpath ="//input[@name='ctl00$ContentPlaceHolder1$btnsignin']")
    private WebElement signIn;
	
	@FindBy( xpath ="//input[@name='ctl00$ContentPlaceHolder1$txtuserid']")
    private WebElement userId;
	
	@FindBy( xpath ="//input[@name='ctl00$ContentPlaceHolder1$txtpassword']")
    private WebElement password;
	
	@FindBy( xpath ="//input[@name='ctl00$ContentPlaceHolder1$userlogin']")
    private WebElement login;
	
	private final String LOG_OUT = "http://www.trytech.in/user/logout.aspx";
	
	public HomePage(){
		super();
		initBrowser();
		openUrl();
		PageFactory.initElements(driver, this);
	}
	
	public Dashboard login(){
		
		//click sign in 
		signIn.click();
		//enter user id
		userId.sendKeys(ConfigProperties.getProperty("userid"));
		
		//enter password
		password.sendKeys(ConfigProperties.getProperty("password"));
		
		
		login.click();
		
		return new Dashboard();
		
	}

	public void logout() {
		driver.navigate().to(LOG_OUT);	
		
		if(driver.getCurrentUrl().contains("index.aspx")){
			driver.close();
			driver.quit();
		}
	}
}
