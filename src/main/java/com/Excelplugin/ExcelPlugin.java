package com.Excelplugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.GenericMethods.GenericMethods;

public class ExcelPlugin {
	
	public static XSSFWorkbook workbook;
	
	public void intailizeTestData() throws IOException
	{
		System.out.println("Loading excel workbook ......");
		String path=System.getProperty("user.dir")+"\\TestData\\Testadata.xlsx";
		File f=new File(path);
		FileInputStream fis=new FileInputStream(f);
		workbook=new XSSFWorkbook(fis);
		System.out.println("Excel workbook is loaded sucessfully !!!");
	}
	
	public int getRowCount(String sheetname)
	{
				
		XSSFSheet sheet=workbook.getSheet(sheetname);
		
		
		return sheet.getPhysicalNumberOfRows();
		
	}
	
	
	public int getColCount(String sheetname)
	{
		
		return workbook.getSheet(sheetname).getRow(0).getPhysicalNumberOfCells();
		
	}

	
	public int searchTestCase(String sheetname,String testcase)
	{
		int testcasefound=0;
		
		int rowcount=getRowCount(sheetname);
		
		
		for(int r=1;r<=rowcount-1;r++)
		{
			
			String cellvalue=workbook.getSheet(sheetname).getRow(r).getCell(0).getStringCellValue();
			if(cellvalue.trim().equals(testcase))
			{
				System.out.println("Test Case found at the row num : "+r);
				testcasefound=r;
				break;
			}
		}
		
		
		if(testcasefound==0)
		{
			System.out.println("No Test case : "+testcase +" is not available in the sheet :"+sheetname);
		}
		return testcasefound;
	}
	
	
	
	public int searchColumn(String sheetname,String columnheader)
	{
		int colfound=0;
		
		int colcount=getColCount(sheetname);
		
		
		for(int c=1;c<=colcount-1;c++)
		{
			
			String cellvalue=workbook.getSheet(sheetname).getRow(0).getCell(c).getStringCellValue();
			if(cellvalue.trim().equals(columnheader))
			{
				System.out.println("Column header found at the cell num : "+c);
				colfound=c;
				break;
			}
		}
		
		
		if(colfound==0)
		{
			System.out.println("Column header  : "+columnheader +" is not available in the sheet :"+sheetname);
		}
		return colfound;
	}
	
	
	public String getData(String sheetname,String columnheader)
	{
		
		String data="";
		
		int row=searchTestCase(sheetname,GenericMethods.crntclass);
		
		int col=searchColumn(sheetname,columnheader);
		
		if(!(row==0) &&!(col==0))
		{
			XSSFSheet sheet=workbook.getSheet(sheetname);
			
			switch(sheet.getRow(row).getCell(col).getCellTypeEnum())
			{
			
			case STRING:
				data=sheet.getRow(row).getCell(col).getStringCellValue();
				break;
			case NUMERIC:
				
				data=String.valueOf(sheet.getRow(row).getCell(col).getNumericCellValue());
				break;
			
			}
		}
		
		
		return data;
		
		
		
	}
	
	
	
}
