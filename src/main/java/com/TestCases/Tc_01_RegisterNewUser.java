package com.TestCases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.GenericMethods.GenericMethods;
import com.ScreenFunction.Authentication;
import com.ScreenFunction.Homepage;
import com.TestNGAnnotations.Annotations;

public class Tc_01_RegisterNewUser extends  Annotations{
	
	
	
	@Test
	
	public void Tc_01_RegisterNewUser()
	{
		GenericMethods generic=new GenericMethods();
		
		boolean status=generic.launchBrowser("Chrome");
		
		if(status)
		{
			Homepage home=PageFactory.initElements(driver, Homepage.class);
			
			status=home.click_SignIn();
			
			if(status)
			{
			Authentication authentication=PageFactory.initElements(driver, Authentication.class);
			authentication.create_NewUser();
			
			
			}
			
			
		}
		
		
		
		
	}
	
	

}
