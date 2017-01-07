package com.trytech.tasks.automation.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.trytech.tasks.automation.utils.CommonUtils;
import com.trytech.tasks.automation.utils.ConfigProperties;

public class Tasks extends BasePage {

	@FindBy(how = How.XPATH, using = "//a[@href='My-Task.aspx']")
	private WebElement socialMediaTasks;

	@FindBy(how = How.XPATH, using = "//a[@href='youtube_task.aspx']")
	private WebElement youTubeTasks;

	@FindBy(how = How.XPATH, using = "//a[@href='blog-task.aspx']")
	private WebElement blogTasks;

	@FindBy(how = How.XPATH, using = "//a[@href='tryfa-task.aspx']")
	private WebElement webTasks;

	@FindBy(how = How.XPATH, using = "//a[@href='sms-task.aspx']")
	private WebElement smsTasks;

	@FindBy(how = How.XPATH, using = "//a[@href='Mail-task.aspx']")
	private WebElement mailTasks;

	private By byHandImage = By
			.xpath("//input[@src='../images/like-2.png']/..");

	Tasks() {
		PageFactory.initElements(driver, this);
	}

	WebDriverWait wait = new WebDriverWait(driver, 15);

	public void clickSocialMediaLinks() {
		driver.navigate().to(ConfigProperties.getProperty("socialMediaTasks"));
		System.out.println(new java.util.Date().toString()
				+ " :: Perform social media clicks");
		performClicks();
	}

	public void clickYouTubeLinks() {
		driver.navigate().to(ConfigProperties.getProperty("youTubeTasks"));
		System.out.println(new java.util.Date().toString()
				+ " :: Perform youtube clicks");
		performClicks();

	}

	public void clickBlogLinks() {
		driver.navigate().to(ConfigProperties.getProperty("blogTasks"));
		System.out.println(new java.util.Date().toString()
				+ " :: Perform blog clicks");
		performClicks();
	}

	public void clickToWebLinks() {
		driver.navigate().to(ConfigProperties.getProperty("webTasks"));
		System.out.println(new java.util.Date().toString()
				+ " :: Perform web clicks");
		performClicks();
	}

	public void clickSendSMSLinks() {

	}

	public void clickSendMailLinks() {

	}

	private void performClicks() {
		int totalLinks = driver.findElements(byHandImage).size();
		if (totalLinks == 0) {
			System.out.println("there is no link to click");
			return;
		} else {

			System.out.println("There are " + totalLinks + " links to click");

			// stop internet
			CommonUtils.stopInternet();

			List<String> strAddresses = new ArrayList<String>();

			try {
				List<WebElement> elements = driver.findElements(byHandImage);

				for (int i = elements.size() - 1; i >= 0; i--) {
					strAddresses.add(elements.get(i).getAttribute("onclick"));
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			// switch on internet
			CommonUtils.startInternet();

			try {
				int i = 1;
				for (String sUrl : strAddresses) {

					System.out.println(" Opening " + i + " of "
							+ strAddresses.size());

					((JavascriptExecutor) driver).executeScript(sUrl);
					System.out.println(CommonUtils.getTime() + " :: Opening  "
							+ sUrl + " : " + driver.getTitle());

					System.out
							.println(" waiting for "
									+ ConfigProperties
											.getProperty("SecondsToWaitBeforeNextClick")
									+ " seconds before next link click");

					// time interval
					Thread.sleep(Integer.parseInt(ConfigProperties
							.getProperty("SecondsToWaitBeforeNextClick")) * 1000);
					i++;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void navigateToParent() {
		driver.navigate().to(ConfigProperties.getProperty("tasksParent"));
	}

	public void captureScreenShot() {
		
		CommonUtils.captureScreenShot(driver,System.getProperty("java.io.tmpdir") + "\\screenshot.png");
		
	}
}
