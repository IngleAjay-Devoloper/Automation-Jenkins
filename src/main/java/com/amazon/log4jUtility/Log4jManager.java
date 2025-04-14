package com.amazon.log4jUtility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Log4jManager {

	public Logger log;
	
	public void logGen() {
		log = Logger.getLogger(getClass());
		PropertyConfigurator.configure(System.getProperty("user.dir")+"/TestData/Log4j.properties");
		
	}
	
	public void logToExcel() throws FileNotFoundException, IOException {
		String fileName = System.getProperty("user.dir")+"/log/testlog1.txt";
		String excelFileName = System.getProperty("user.dir")+"/log/excelFile.xlsx";
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Sheet1");
		
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
			String line;
			Row row;
			Cell cell;
			int rowIndex = 0;
			
			while((line = br.readLine())!= null){
				row = sheet.createRow(rowIndex);
				String[] tokens = line.split("[|]");
				
				for(int iToken =0; iToken < tokens.length; iToken++) {
					cell = row.createCell(iToken);
					cell.setCellValue(tokens[iToken]);
				}
				rowIndex++;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		try(FileOutputStream outputStream = new FileOutputStream(excelFileName)){
			
			workbook.write(outputStream);
			workbook.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
