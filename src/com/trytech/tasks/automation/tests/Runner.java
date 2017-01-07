package com.trytech.tasks.automation.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.trytech.tasks.automation.pages.Dashboard;
import com.trytech.tasks.automation.pages.HomePage;
import com.trytech.tasks.automation.pages.Tasks;
import com.trytech.tasks.automation.utils.CommonUtils;

public class Runner {

	HomePage homepage;
	Dashboard dashboard;
	Tasks tasks;

	@BeforeSuite
	public void setup() {

		//open homepage and login 
		homepage = new HomePage();
		dashboard = homepage.login();
		// click to tasks
		tasks = dashboard.clickTask();
	}

	@AfterSuite
	public void teardown() {
		
		tasks.navigateToParent();
		
		tasks.captureScreenShot();
		
		
	    
		// click on logout
		if(null!=homepage)
		{
			homepage.logout();
		}
		
		CommonUtils.sendEmail();

	}
	
	@Test
	public void peformSocialMediaClicks(){
		// click on social media tasks
		tasks.clickSocialMediaLinks();
	}

	@Test
	public void peformYoutubeLinksClick(){
		// click on youtube tasks
		tasks.clickYouTubeLinks();
	}
	
	@Test
	public void peformBlogLinksClick(){
		// click on blog tasks
		tasks.clickBlogLinks();
	}

	@Test
	public void peformWebLinksClick(){
		// click on webtasks
		tasks.clickToWebLinks();
	}

	@Test
	public void peformSmsLinksClick(){
		// click on sms tasks
		tasks.clickSendSMSLinks();
	}

	
	@Test
	public void peformSendMailClicks() {

	// click on mail tasks
		tasks.clickSendMailLinks();

		
	}

}
