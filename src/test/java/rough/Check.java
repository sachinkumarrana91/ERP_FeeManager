package rough;

import utilities.ExcelReader;

public class Check {

	public static ExcelReader excel = new ExcelReader("C:\\Users\\SANJU\\Desktop\\TestAutomation Sheet.xlsx");
	
	public static void main(String[] args) {

		System.out.println(getData("TestCase Sheet","TC001"));
		
/*		for(){
		excel.setCellData("TestCase Sheet", TC_Step_Result, TestUtil.strToInt(S_No), "PASSED");
		}
*/
	}
	
	public static Object[][] getData(String sheetName, String TC_id){
		
/*		int rows = excel.getRowCount(sheetName);
		int cols= excel.getColumnCount(sheetName);
*/
		int rows = excel.getStepsCount(sheetName, TC_id);	//3
		int cols= excel.getColumnCount(sheetName);			//14

		
		Object[][] data = new Object[rows-1][cols];  // 2,14
		
		for(int rowNum = 2 ; rowNum <= rows ; rowNum++){ // 2
			
			
			
			for(int colNum=0 ; colNum< cols; colNum++){
				data[rowNum-2][colNum]=excel.getCellData(sheetName, colNum, rowNum); //-2
			}
		}
		
		return data;
		
		
		
	}

	


}
