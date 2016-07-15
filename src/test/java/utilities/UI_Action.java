package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import core.Page;
public class UI_Action extends Page{
	
	
	
	public static By find(String type, String value) {
		By by;
		switch (type) {
		case "id":
			by = By.id(value);
			break;
		case "name":
			by = By.name(value);
			break;
		case "xpath":
			by = By.xpath(value);
			break;
		case "css":
			by = By.cssSelector(value);
			break;
		case "linkText":
			by = By.linkText(value);
			break;
		case "partialLinkText":
			by = By.partialLinkText(value);
			break;
		case "tagName":
			by = By.tagName(value);
			break;
		case "className":
			by = By.className(value);
			break;
		default:
			by = null;
			break;
		}
		return by;
	}
	
	public static void typText(String type, String value, String text) {
		try {
			WebElement element = driver.findElement(find(type, value));
			element.clear();
			element.sendKeys(text);
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to enter text" + e);
		}
	}

	public static void clickButton(String type, String value) {
		try {
			WebElement element = driver.findElement(find(type, value));
			element.click();
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to enter text" + e);
		}
	}
	
	public static String checkWindowTitle(){
		return driver.getTitle();
	}

	public static void goToUrl(String text) {
			driver.navigate().to(text);
	}

	
	
	
	
	
}
