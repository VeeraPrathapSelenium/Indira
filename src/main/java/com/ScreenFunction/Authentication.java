package com.ScreenFunction;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.GenericMethods.GenericMethods;

public class Authentication extends GenericMethods{
	
	//************************ WebElements *****************************
	
		@FindBy(how=How.XPATH,using="//input[@id='email_create']")
		public WebElement edi_CreateAccount_EmailID;
		
		@FindBy(how=How.XPATH,using="//button[@id='SubmitCreate']")
		public WebElement btn_CreateAccount_Createaccount;
		
	//*********************** WebMethods ******************************
	
	public boolean create_NewUser()
	{
		boolean status=true;
		status=verify_ElementExist("Authentication", "Email ID", edi_CreateAccount_EmailID);
		
		if(status)
		{
			SimpleDateFormat sdf=new SimpleDateFormat("hhmmss");

			Date d=new Date();


			String emailid="abc@gmail.com";

			String email=emailid.split("@")[0]+"_"+sdf.format(d)+"@"+emailid.split("@")[1];
			
			status=clickAndSendData("Email id", "Authentication", edi_CreateAccount_EmailID, email);
			
			if(status)
			{
				status=clickElement("Authentication", "Create Account", btn_CreateAccount_Createaccount);
			}
			else
			{
				System.out.println("Unable to proceed further , as email id is not available");
			}
			
		}
		
		return status;
	}
		
		
	
	

}
