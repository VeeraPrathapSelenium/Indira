package com.TestNGAnnotations;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Excelplugin.ExcelPlugin;
import com.GenericMethods.GenericMethods;
import com.Reporting.ExtentReports_Reporting;

public class Annotations extends GenericMethods{
	
	@BeforeSuite
	
	/**
	 * @Purpose:loading the excel data before the suite
	 * @author Hanshik
	 * @throws IOException
	 */
	
	
	
	
	public void beforeSuite() throws IOException
	{
		System.out.println("Before Suite ++ Load excel data");
		
		// load excel data
		ExcelPlugin plugin=new ExcelPlugin();
		plugin.intailizeTestData();	
		
	}
	
	@AfterSuite
	
	public void afterSuite()
	{
		System.out.println("After Suite ++ Close excel data");
	}
	
	
	@BeforeTest
	
	public void beforeTest()
	{
		System.out.println("Before Test ++ Intialize Test Report");	
	}
	
	@AfterTest
	
	public void afterTest()
	{
		
		
	}
	
	
	@BeforeMethod()
	public void beforeMethod(Method method)
	{
		GenericMethods.crntclass=method.getName();
		System.out.println(GenericMethods.crntclass);
		
		ExtentReports_Reporting extent=new ExtentReports_Reporting();
		// extent report will get intailized here
		extent.intializeReport();
		// start your test report 
		extent.startTest(GenericMethods.crntclass);
		
	}
	
	
	@AfterMethod
	public void afterMethod() throws InterruptedException
	{
		Thread.sleep(2000);
		//GenericMethods.driver.close();
		ExtentReports_Reporting extent=new ExtentReports_Reporting();
		extent.flushReport();
	}

}
