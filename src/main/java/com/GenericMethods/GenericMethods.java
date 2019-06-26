package com.GenericMethods;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Reporting.ExtentReports_Reporting;

public class GenericMethods extends ExtentReports_Reporting {
	
	public static WebDriver driver;
	
	public static String crntclass;
	
	/**
	 * @MethodName:launchBrowser
	 * @InputParameters:browsername
	 * @OutPutParameters:boolean
	 * @Author:Prathap
	 * @DesignDate:05/31/2019
	 *  
	 */
	
	public boolean launchBrowser(String browsertype)
	{
		
		
		boolean status=true;
		String url=getData("TestData","Url");
		try
		{
			switch (browsertype.toLowerCase()) {
			case "ff":
				driver=new FirefoxDriver();
				break;
			case "chrome":
				
				System.out.println("Launching Application in the browser : "+browsertype);
				
				
				// step1: setup the .exe path
				
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				
		//step 2: Launch the browser 
				
				driver=new ChromeDriver();				
				
				break;

			default:
				break;
			}
			
			//pass the url
			
			driver.get(url);
			
	//maximize the browser
			
			driver.manage().window().maximize();
			System.out.println("Launching Application is completed successfully");	
			logEvent("pass", "Browser Launched Sucessfully");
			
		}catch(Exception e)
		{
			logEvent("fail", "Browser is not Launched Sucessfully");
			System.out.println(e.getMessage());
			status=false;
		}
		return status;
		
		
	}
	/**
	 * @MethodName:clickAndSendData
	 * @InputParameters:WebElement,String
	 * @OutPutParameters:boolean
	 * @Author:Prathap
	 * @DesignDate:05/31/2019
	 *  
	 */	
	public boolean clickAndSendData(String elementname,String pagename,WebElement element,String data)
	{
		boolean status=true;
		
		try {
			element.click();
			element.clear();
			element.sendKeys(data);
			element.sendKeys(Keys.TAB);
			logEvent("pass", "Data "+data+"  is entered sucessfully into the field :"+elementname+ " on the page : "+pagename);
			
		}catch(Exception e)
		{
			status=false;
			logEvent("fail", "Data is not entered sucessfully into the field :"+elementname+ " on the page : "+pagename);
		}
		return status;
	}
	
	
	/**
	 * @MethodName:clickElement
	 * @InputParameters:WebElement,String
	 * @OutPutParameters:boolean
	 * @Author:Prathap
	 * @DesignDate:05/31/2019
	 *  
	 */	
	public boolean clickElement(String pagename,String elememtname,WebElement element)
	{
		boolean status=true;
		
		try {
			element.click();
			System.out.println("The element "+elememtname +" is clicked sucessfully on "+pagename);
		}catch(Exception e)
		{
			System.out.println("The element "+elememtname +" is not clicked sucessfully on "+pagename);
			status=false;
		}
		return status;
	}
	
	/**
	 * @MethodName:getText
	 * @InputParameters:WebElement
	 * @OutPutParameters:String
	 * @Author:Prathap
	 * @DesignDate:05/31/2019
	 *  
	 */	
	public String getText(WebElement element)
	{
		String data="";
		
		try {
			data=element.getText();
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
		return data;
	}
	
	/**
	 * @MethodName:getAttributeValue
	 * @InputParameters:WebElement
	 * @OutPutParameters:String
	 * @Author:Prathap
	 * @DesignDate:05/31/2019
	 *  
	 */	
	public String getAttributeValue(WebElement element,String attribute)
	{
		String data="";
		
		try {
			data=element.getAttribute(attribute);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
		return data;
	}
	
	
	
	
	
	
	/**
	 * @MethodName:hoverAndClick
	 * @InputParameters:WebElement
	 * @OutPutParameters:boolean
	 * @Author:Prathap
	 * @DesignDate:05/31/2019
	 *  
	 */	
	public boolean hoverAndClick(String pagename,String elememtname,WebElement element)
	{
		boolean status=true;
		
		try {
			Actions acc=new Actions(driver);
			
			acc.moveToElement(element).click(element).build().perform();
			
			logEvent("pass", "The element :"+elememtname+ " is clicked sucessfully on the page : "+pagename);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			status=false;
			logEvent("fail", "The element :"+elememtname+ " is not clicked sucessfully on the page : "+pagename);
		}
		return status;
	}
	
	
	/**
	 * @MethodName:verify_ElementExist
	 * @InputParameters:WebElement
	 * @OutPutParameters:boolean
	 * @Author:Prathap
	 * @DesignDate:05/31/2019
	 *  
	 */	
	public boolean verify_ElementExist(String pagename,String elememtname,WebElement element)
	{
		boolean status =true;
		
		try
		{
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(element));
			
			if(element.isDisplayed())
			{
				logEvent("pass", "Element : "+elememtname+" is displayed on the page : "+pagename);
			}
			else
			{
				logEvent("fail", "Element : "+elememtname+" is not displayed on the page : "+pagename);
			}
			
			
			
		}catch(Exception e)
		{
			status=false;
			logEvent("fail", "Element : "+elememtname+" is not displayed on the page : "+pagename);
		}
		return status;
	}
	
	/**
	 @name:IsDisplayed
	 @Purpose:This method is used for is Element successfully displayed on the application
	 @Input:WebElement element,String elementname,String Pagename
	 @Output:boolean
	 @author:ranjith
	 @DesignDate:06/26/2019
	 **/
	
	public boolean IsDisplayed(WebElement element,String elementname,String Pagename)
	{
		boolean status = true;
		
		try
		{
			boolean isDisplayeStatus = element.isDisplayed();
			if(isDisplayeStatus)
			{
				System.out.println("The element"+elementname+ "is successfully displayed on page"+Pagename);
			}
			else
			{
				System.out.println("The element"+elementname+ "is not successfully displayed on page"+Pagename);
				System.out.println("unable to find the element"+elementname);
				status=false;
			}
		}
		catch(Exception e)
		{
		System.out.println(e.getMessage());
			
		}
		return status;
	}

	

}
