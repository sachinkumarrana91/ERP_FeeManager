package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utilities.ExcelReader;

public class Page{
	
	
	//public static String type
	public static WebDriver driver;
	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\java\\properties\\TestAutomation Sheet.xlsx");
	//public static ExcelReader excel = new ExcelReader("C:\\Users\\SANJU\\Desktop\\TestAutomation Sheet.xlsx");
	//public static ExcelReader excel = new ExcelReader("C:\\Users\\Gulfam\\Desktop\\TestAutomation Sheet.xlsx");
	public static Logger logs = Logger.getLogger("devpinoyLogger");
	
	
	

	@BeforeSuite
	public void init() throws IOException, AddressException, SQLException, ClassNotFoundException, MessagingException{

		if(driver==null){
			
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\properties\\Config.properties");
			Config.load(fis);
			logs.debug("Loaded the Config property file");
			
			System.setProperty("webdriver.chrome.driver","chromedriver.exe");
			driver = new ChromeDriver();
			logs.debug("Chrome Broser Opened");
			driver.get(Config.getProperty("testsiteurl"));
			logs.debug("Test Application Opened");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			logs.debug("Test Case Execution Started");
		}
	}
	
	@AfterSuite
	public void QuitDriver(){
		
		//send mail
		driver.quit();
		
	}

}
