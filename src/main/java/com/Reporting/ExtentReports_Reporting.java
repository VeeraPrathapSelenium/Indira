package com.Reporting;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.Excelplugin.ExcelPlugin;
import com.GenericMethods.GenericMethods;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReports_Reporting extends ExcelPlugin{
	
	public static ExtentReports extent;
	
	public static ExtentTest test;
	
	
	/**
	 * Intialize the report
	 */
	
	public void intializeReport()
	{
		extent=new ExtentReports(check_CreateResultFolder()+"\\"+GenericMethods.crntclass+".html");
		
		extent.addSystemInfo("Sprint #"," Sprint 2")
		.addSystemInfo("Environment"," QA");
	}
	
	/**
	 * Start your test
	 */
public void startTest(String testcasename)
{
	test=extent.startTest(testcasename);
}


/**
 * Log your event
 */

public void logEvent(String status,String description)
{
	
	switch(status.toLowerCase())
	{
	case "pass":
		test.log(LogStatus.PASS, description+test.addBase64ScreenShot(getScreenShot()));
		break;
		
	case "fail":
		test.log(LogStatus.FAIL, description+test.addBase64ScreenShot(getScreenShot()));
		break;	
	case "warning":
		test.log(LogStatus.WARNING, description);
		break;		
	}
}



/**
 * close testcase report
 */

public void closeReport()
{
extent.close();
}


/**
 * publish your report
 * 
 */

public void flushReport()
{
	extent.flush();
}

public static String check_CreateResultFolder()
{
	Date d=new Date();

	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yy");

	System.out.println(sdf.format(d));



	String Resultspath=System.getProperty("user.dir")+"\\Results\\"+sdf.format(d);


	File f=new File(Resultspath);
	if(!f.exists())
	{
		System.out.println("No Folder is exist......creating a new folder");
		f.mkdirs();
	}
	return Resultspath;
}


public static String getScreenShot()
{
	TakesScreenshot sht=(TakesScreenshot)GenericMethods.driver;
	
	String screenprint=sht.getScreenshotAs(OutputType.BASE64);
	
	return "data:image/png;base64,"+screenprint;
	
}


}
