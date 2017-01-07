package com.trytech.tasks.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dashboard extends BasePage
{
	@FindBy( how=How.XPATH,using = "//a[@href='Task-Parents.aspx']")
    private WebElement tasks;
	
	
	Dashboard(){
		PageFactory.initElements(driver, this);
	}
	public Tasks clickTask() {
		
		//see if there is any pop  if yes, close this
		WebElement popupClose = (new WebDriverWait(driver, 15))
				   .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='close']")));
		if(null!=popupClose)
		{
			popupClose.click();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tasks.click();
		
		return new Tasks();
		
	}
	
	

}
