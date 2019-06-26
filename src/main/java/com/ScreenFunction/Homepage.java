package com.ScreenFunction;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.GenericMethods.GenericMethods;

public class Homepage extends GenericMethods{
	
//************************ WebElements *****************************
	
	@FindBy(how=How.XPATH,using="//a[text()='Contact us']")
	public WebElement lnk_ConatctUs;
	
	@FindBy(how=How.XPATH,using="//a[normalize-space(text())='Sign in']")
	public WebElement lnk_Signin;
	
//*********************** WebMethods ******************************
	
	public boolean click_SignIn()
	{
		boolean status=true;
		
		try
		{
			status=clickElement("Home","SignIn",lnk_Signin);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
		return status;
	}
	

}
