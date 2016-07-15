package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.deploy.uitoolkit.impl.fx.Utils;

import bsh.util.Util;
import core.Page;
import utilities.ExcelReader;
import utilities.TestUtil;
import utilities.UI_Action;


public class LoginTest extends Page{
	String actualResult = null;
	
	@BeforeTest
	public void isSkip(){
		if(!TestUtil.isExecutable("TC001")){
			throw new SkipException("Skipping the test as the Run mode is No");
		}
	}

	
	
	
	
	@Test(dataProvider="getData")
	public void loginTest(String TC_id, String S_No, String TC_Steps, String ExpactedResult, String TC_Step_Result,
			String Comments, String KeyWord, String LocatorType, String ElementValue, String sendKeys, 
			String SelectBy_DropDown, String SelectBy_value, String TimeStamp, String TC_Result
			){

		System.out.println(TC_id+","+S_No+","+TC_Steps+","+ExpactedResult+","+TC_Step_Result+","+Comments+","+KeyWord+","+LocatorType+","+ElementValue+","+sendKeys+","+SelectBy_DropDown+","+SelectBy_value+","+TimeStamp+","+TC_Result);

		switch (KeyWord) {
		case "typText":
			UI_Action.typText(LocatorType, ElementValue, sendKeys);
			actualResult = "Entered";
			break;
		case "clickButton":
			UI_Action.clickButton(LocatorType, ElementValue);
			actualResult = "Clicked";
			break;
		case "checkWindowTitle":
			UI_Action.checkWindowTitle();
			actualResult = UI_Action.checkWindowTitle();
			break;
		case "goToUrl":
			UI_Action.goToUrl(sendKeys);
			actualResult = UI_Action.checkWindowTitle();
			break;
		default:
			System.out.println(KeyWord+": Not Found");
			actualResult = "No KeyWord Found";
			break;
		}		
		
		
		if(ExpactedResult.equals(actualResult)){
				excel.setCellData("TestCase Sheet", "TC_Step_Result", TestUtil.strToInt(S_No)+1, "PASSED");
			}
			else {
				System.out.println("at "+(TestUtil.strToInt(S_No)+1));
			excel.setCellData("TestCase Sheet", "TC_Step_Result", TestUtil.strToInt(S_No)+1, "FAILED");
		}

		Assert.assertEquals(ExpactedResult, actualResult);
	

	}
	
	
	@DataProvider
	public static Object[][] getData(){
		return TestUtil.getData("TestCase Sheet","TC001");
	}
	
	
}
