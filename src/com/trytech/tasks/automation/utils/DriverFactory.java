package com.trytech.tasks.automation.utils;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class DriverFactory {

	private DriverFactory() {
		// Do-nothing..Do not allow to initialize this class from outside
	}

	private static DriverFactory instance = new DriverFactory();

	public static DriverFactory getInstance() {
		return instance;
	}

	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() // thread local
																	// driver
																	// object
																	// for
																	// webdriver
	{
		@Override
		protected WebDriver initialValue() {
			if (ConfigProperties.getProperty("browser").equalsIgnoreCase("chrome")) {

				if (OSValidator.isWindows())
					System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
				else
					System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_linux");

				return new ChromeDriver(); // can be replaced with other browser
											// drivers
			} else if (ConfigProperties.getProperty("browser").equalsIgnoreCase("firefox")) {
				if (OSValidator.isWindows())
					System.setProperty("webdriver.gecko.driver", "drivers/geckodriver_32bit.exe");
				else
					System.setProperty("webdriver.gecko.driver", "drivers/geckodriver_linux");
				return new FirefoxDriver();
			} else if (ConfigProperties.getProperty("browser").equalsIgnoreCase("phantomjs")) {
				File file;
				if (OSValidator.isWindows())
					file = new File("drivers/phantomjs.exe");
				else
					file = new File("drivers/phantomjs_linux");

				System.setProperty("phantomjs.binary.path", file.getAbsolutePath());

				return new PhantomJSDriver();

			} else {
				System.out.println("set browser in the config file");
				return null;
			}

		}
	};

	public WebDriver getDriver() // call this method to get the driver object
									// and launch the browser
	{
		return driver.get();
	}

	public void removeDriver() // Quits the driver and closes the browser
	{
		driver.get().quit();
		driver.remove();
	}

}
