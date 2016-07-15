package utilities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import core.Page;

public class TestUtil extends Page {
	
	
	
public static String mailscreenshotpath;


	public static String generateTimeStamp(){
		 
		Calendar cal = new GregorianCalendar();
		  int month = cal.get(Calendar.MONTH); //3
		  int year = cal.get(Calendar.YEAR); //2014
		  int sec =cal.get(Calendar.SECOND);
		  int min =cal.get(Calendar.MINUTE);
		  int date = cal.get(Calendar.DATE);
		  int day =cal.get(Calendar.HOUR_OF_DAY);
		
		String timestamp = year+"_"+date+"_"+(month+1)+"_"+day+"_"+min+"_" +sec;
		return timestamp;
	}

	
	public static void CaptureScreenshot() throws IOException{
		
		  Calendar cal = new GregorianCalendar();
		  int month = cal.get(Calendar.MONTH); //3
		  int year = cal.get(Calendar.YEAR); //2014
		  int sec =cal.get(Calendar.SECOND);
		  int min =cal.get(Calendar.MINUTE);
		  int date = cal.get(Calendar.DATE);
		  int day =cal.get(Calendar.HOUR_OF_DAY);
		
		  mailscreenshotpath = System.getProperty("user.dir")+"\\screenshots\\"+year+"_"+date+"_"+(month+1)+"_"+day+"_"+min+"_" +sec+".jpeg";
			
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		   FileUtils.copyFile(scrFile, new File(mailscreenshotpath));
		
		
	}
	
	
	
	
	public static boolean isExecutable(String TC_id){
		
		for(int rowNum=2; rowNum<=excel.getRowCount("TestCase RunMode"); rowNum++){
			if(excel.getCellData("TestCase RunMode", "TC_id", rowNum).equals(TC_id)){
				if(excel.getCellData("TestCase RunMode", "willExecute", rowNum).equalsIgnoreCase("Y")){
					return true;
				}else{
					for(int S_No= excel.getFirstRowOfStep("TestCase Sheet", TC_id) ; S_No<=excel.getStepsCount("TestCase Sheet", TC_id); S_No++){
						excel.setCellData("TestCase Sheet", "TC_Step_Result", S_No, "SKIPED");
					}
					return false;
				}
			}
		}
		return false;
	}
	
	public static int strToInt(String s){
		int number = 0;
		int k = 1;
		for(int j = 1; j <= s.length()-1;j++){
			k = k*10;
		}
		for(int i = 0; i <= s.length()-1; i++ ){
			number = number + ((s.charAt(i)-48) * k);
			k = k / 10;
		}
		return number;
	}
	
	
	
	public static Object[][] getData(String sheetName, String TC_id){
		
/*		int rows = excel.getRowCount(sheetName);
		int cols= excel.getColumnCount(sheetName);
*/

		int rows = excel.getStepsCount(sheetName, TC_id);	//3
		//System.out.println(rows);
		int cols= excel.getColumnCount(sheetName);			//14

		
		Object[][] data = new Object[rows][cols];  // 2,14
		
		int i = 0;
		for(int rowNum = excel.getFirstRowOfStep(sheetName, TC_id); rowNum <= excel.getLastRowOfStep(sheetName, TC_id); rowNum++){ // 2
				for(int colNum=0 ; colNum< cols; colNum++){
				data[i][colNum]=excel.getCellData(sheetName, colNum, rowNum); //-2  //0
			}
				i++;
		}
		return data;
		
		
		
	}
	
	public static void zip(String filepath){
	 	try
	 	{
	 		File inFolder=new File(filepath);
	 		File outFolder=new File("Reports.zip");
	 		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
	 		BufferedInputStream in = null;
	 		byte[] data  = new byte[1000];
	 		String files[] = inFolder.list();
	 		for (int i=0; i<files.length; i++)
	 		{
	 			in = new BufferedInputStream(new FileInputStream
	 			(inFolder.getPath() + "/" + files[i]), 1000);  
	 			out.putNextEntry(new ZipEntry(files[i])); 
	 			int count;
	 			while((count = in.read(data,0,1000)) != -1)
	 			{
	 				out.write(data, 0, count);
	 			}
	 			out.closeEntry();
  }
	 		out.flush();
	 		out.close();
	 	
}
  catch(Exception e)
  {
	  e.printStackTrace();
  } 
 }	
	
	
	

}
