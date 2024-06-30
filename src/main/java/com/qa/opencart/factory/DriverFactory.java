package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.exception.BrowserException;
import com.qa.opencart.exception.FrameworkException;



public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This is used to initiate the driver based upon given Browser; 
	 * 
	 * @param browserName
	 */
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		
		System.out.println("The Browser name is: "+browserName);
		optionsManager = new OptionsManager(prop);
		
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			//driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			//driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "safari":
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println("please pass the correct browser name ............."+browserName);
			throw new BrowserException(AppError.BROWSER_NOT_FOUND);
			
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	public Properties initProp() {
		//cross prop logic
		prop = new Properties();
		FileInputStream ip = null;
		//mvn clean install -Denv
		String envName = System.getProperty("env");
		
		
		
			
			
			if(envName == null) {
				System.out.println("env name is null, hence running in the QA env");
				try {
					ip = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}
				
			}
			
			else {
				try {
					switch (envName.trim().toLowerCase()) {
					case "qa":
						ip = new FileInputStream(AppConstants.QA_FILE_PATH);
						break;
					case "dev":
						ip = new FileInputStream(AppConstants.DEV_FILE_PATH);
						break;
					case "stage":
						ip = new FileInputStream(AppConstants.STAGE_FILE_PATH);
						break;
					case "uat":
						ip = new FileInputStream(AppConstants.UAT_FILE_PATH);
						break;
					case "prod":
						ip = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
						break;
						
					default:
						System.out.println("Please enter the correct env...."+envName);
						throw new FrameworkException("=====WRONGENVIROMENT====");
						
					}
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
					
				
			}
		
		
		
		try {
			prop.load(ip);
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return prop;
		
	
	
	}
	
public static String getScreenshot(String methodName) {
		
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);//temp location
		
		String path = System.getProperty("user.dir")+"/screenshots/"+methodName+"_"+System.currentTimeMillis()+".png";
		
		File destination = new File(path);
		
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
	}
	

}
