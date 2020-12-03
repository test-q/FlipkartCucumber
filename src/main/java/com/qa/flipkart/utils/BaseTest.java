package com.qa.flipkart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {
	public static Properties prop;
	public static WebDriver driver;
	public static OptionsManager optionsManager;
	public static String flashElement;
	
	/**
	 * 
	 * @return This method return WebDriver
	 */
	public static WebDriver initialization() {
		prop = init_prop();
		driver = init_driver(prop);
		return driver;
	}
	
	public static Properties init_prop() {
		prop = new Properties();
		String path = null;
		String env = null;

		try {
			env = System.getProperty("env");

			if (env == null) {
				path = "src/main/java/com/qa/flipkart/config/Config.prod.properties";
				System.out.println("Default Test Environment: " + "PROD");

			} else {
				switch (env) {
				case "prod":
					path = "src/main/java/com/qa/flipkart/config/Config.prod.properties";
					System.out.println("Test Environment: " + "PROD");
					break;
				case "qa":
					path = "src/main/java/com/qa/flipkart/config/Config.qa.properties";
					System.out.println("Test Environment: " + "QA");
					break;
				default:
					System.out.println("Please Pass the Correct Environment Value...");
					break;
				}
			}

			FileInputStream finput = new FileInputStream(path);
			prop.load(finput);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	
	public static WebDriver init_driver(Properties prop) {
		
		flashElement = prop.getProperty("highlights").trim();
		String browserName = prop.getProperty("browser").trim();
		
		System.out.println("Browser Name: " +browserName );
		
		optionsManager = new OptionsManager(prop);
		
		if(browserName.equalsIgnoreCase("CHROME")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(optionsManager.getChromeOptions());
		}else if(browserName.equalsIgnoreCase("FIREFOX")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
		}else {
			System.out.println("Please Pass The Correct Browser Name : " + browserName);
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("siteUrl").trim());
		driver.manage().timeouts().pageLoadTimeout(ConstantUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		
		return driver;
	}
	
	public static byte[] getScreenshot() {
		TakesScreenshot scr = (TakesScreenshot)driver;
		byte[] scrData = scr.getScreenshotAs(OutputType.BYTES);
		return scrData;
	}
	
	public static void closeBrowser() {
		driver.quit();
	}
	

}
