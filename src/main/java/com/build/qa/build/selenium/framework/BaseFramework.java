package com.build.qa.build.selenium.framework;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class BaseFramework {
	protected WebDriver driver;
	protected Wait<WebDriver> wait;
	private static final Logger LOG = LoggerFactory.getLogger(BaseFramework.class);
	private static final String CONFIG_FILE = "./conf/automation.properties";
	private static final String DRIVER_FIREFOX = "firefox";
	private static final String DRIVER_CHROME = "chrome";
	private static Properties configuration;

	@Rule
	public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

	@BeforeClass
	public static void beforeClass() throws IOException {
		configuration = new Properties();
		FileInputStream input;

		LOG.info("Loading in configuration file.");
		input = new FileInputStream(new File(CONFIG_FILE));
		configuration.loadFromXML(input);
		input.close();
	}

	@Before
	public void setUpBefore() {
		DesiredCapabilities capabilities;
		String browser = System.getProperty("BROWSER");
		if(browser==null)
		{
			browser = System.getenv("BROWSER");
			if(browser==null)
			{
				browser= "firefox";
			}
		}
		switch (browser)
		{
			case "chrome":

				if ( StringUtils.isEmpty(System.getProperty("webdriver.chrome.driver")));
				System.setProperty("webdriver.chrome.driver", "//Applications//chrome//chromedriver");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-extensions");
				options.addArguments("--start-maximized");
				options.addArguments("--always-authorize-plugins=false");
				driver = new ChromeDriver(options);
				driver.manage().deleteAllCookies();
				driver.manage().window().setSize(new Dimension(1280, 1024));
				driver = new ChromeDriver();
				break;
			case "firefox":

				capabilities = DesiredCapabilities.firefox();
				driver = new FirefoxDriver(capabilities);

				break;
			case "ie":
				driver = new InternetExplorerDriver();
				break;
			case "safari":
				driver = new SafariDriver();
				break;
			default:
				driver = new ChromeDriver();
				break;
		}
		System.out.println("Opening Browser...."+browser);
	}

	protected WebDriver getDriver() {
		return driver;
	}
	
	protected String getConfiguration(String config) { 
		return configuration.getProperty(config);
	}

	@After
	public void tearDownAfter() {
		LOG.info("Quitting driver.");
		driver.quit();
		driver = null;
	}
}
