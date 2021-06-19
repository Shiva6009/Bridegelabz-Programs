package Testing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

class Validation
{	
	public boolean FirstnameandLastnameValidation(String username)
	{
		if(username.length()>3 && ((int)username.charAt(0) > 65 && (int)username.charAt(0) < 90))
		{
			return true;
		}
	    return false;
	}
	
	public boolean PhonenumberValidation(String mobilenumber)
	{
		String mobilenum = String.valueOf(mobilenumber);
		if(mobilenum.length() == 13 && mobilenum.charAt(2) == ' ')
		{
			return true;
		}
		return false;
	}
	
	
	public boolean EmailValidation(String email)
	{
		System.out.println(" Inside the Email Validation Function..");
		
		int atcount = email.length()-email.replaceAll("\\@", "").length();
		if(atcount == 1)
		{
			String[] beforeatsubstring = email.split("@");
			String Beforeat = beforeatsubstring[0];
			String Afterat = beforeatsubstring[1];
		
			int specialcharctercount = Beforeat.length()-Beforeat.replaceAll("[^A-Za-z0-9 \s\\-\\+\\.]","").length();
			int specialcharctercount1 = Afterat.length()-Afterat.replaceAll("[^A-Za-z0-9 \s\\.]","").length();
			
			if(Beforeat.charAt(0)!='.' && Beforeat.charAt(Beforeat.length()-1)!='.'&&  Afterat.charAt(0)!='.' && specialcharctercount ==0 
					&& specialcharctercount1 ==0)
			{
				int dotcountbefore = Beforeat.length()-Beforeat.replaceAll("[^A-Za-z0-9]","").length();
				int dotcountafter = Afterat.length()-Afterat.replaceAll("[^A-Za-z0-9]","").length();
			
				if(dotcountbefore <=1 && dotcountafter <=2)
				{
					String[] dotsubstring  = Afterat.split("\\.");
					for(int i=1 ;i<dotsubstring.length;i++)
					{
						if(dotsubstring[i].length()>=2)
						{
							Pattern numberpattern = Pattern.compile("[1-9]");
							Matcher numbermatcher =numberpattern.matcher(dotsubstring[i]);		
							boolean numberboolen= numbermatcher.find();
							if(numberboolen)
							{
								return false;
							}
						}
						else
						{
							return false;
						}
					}
					return true;
		
				}
			
			}
			
 		}
		return false;
		
	}
	
	public boolean PasswordValidation(String password)
	{	
		Pattern uppercasepatter = Pattern.compile("[A-Z]");
		Matcher uppercasematcher =uppercasepatter.matcher(password);		
		boolean uppercaseboolen= uppercasematcher.find();
		
		Pattern lowercasepattern = Pattern.compile("[a-z]");
		Matcher lowercasematcher =lowercasepattern.matcher(password);		
		boolean lowercaseboolean= lowercasematcher.find();
		
		Pattern numberpattern = Pattern.compile("[1-9]");
		Matcher numbermatcher =numberpattern.matcher(password);		
		boolean numberboolen= numbermatcher.find();
		
		int specialcharctercount = password.length()-password.replaceAll("[^A-Za-z0-9 ]","").length();
		
		if(password.length()>7 && uppercaseboolen && lowercaseboolean && numberboolen && specialcharctercount ==1)
		{
			return true;
		}
		return false;
	}
}
