package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.io.*;

public class addressmanagementupdation {

	static HashMap<String , ArrayList<Object>> data = new HashMap<String , ArrayList<Object>>();
	final static String filepath = "E:/Addressbook.txt";
	public static Scanner s1 = new Scanner(System.in);
	File file = new File(filepath);
	public  void ReadFile()  // Copying Data from File to HashMap
	{
		
		BufferedReader bufferreader = null;
		try
		{
			bufferreader = new BufferedReader( new FileReader(file) );				 
			String line = null;
	        while ((line = bufferreader.readLine()) != null) 
	        {
	        	String replacedstring = line.replaceAll("\\[","").replaceAll("\\]","").replaceAll(":",",");
                String[] stringarray = replacedstring.split(",");
                String firstname = stringarray[1].trim();
                String lastname = stringarray[2].trim();
                String address = stringarray[3].trim();
                String city = stringarray[4].trim();
                String state = stringarray[5].trim();
                String zipcode = stringarray[6].trim();
                String phonenumber = stringarray[7].trim();
                String emaiid = stringarray[8].trim();         
                data.put(firstname, new ArrayList<Object>(Arrays.asList(firstname , lastname ,address , city ,state,zipcode,phonenumber,emaiid)));
	          }
		}catch(IOException e){
	            e.printStackTrace();
	        }finally{
	            try{
	              bufferreader.close();
	            }catch(Exception e){}
		}
		
	}
	public void AddUser()			// Adding Data into HashMap
	{
		System.out.println("Enter the First Name :");
		String firstname = s1.next();
		
		if(data.containsKey(firstname))
		{
			System.out.println("Sorry This Name is Aldreay Exist..");
			return;
		}
		System.out.println("Enter the Last Name :");
		String lastname = s1.next();
		System.out.println("Enter the Address :");
		String address = s1.next();
		System.out.println("Enter the City :");
		String city = s1.next();
		System.out.println("Enter the State :");
		String state = s1.next();
		System.out.println("Enter the Zip Code :");
		String zipcode = s1.next();
		System.out.println("Enter the Phone Number :");
		String phonenumber = s1.next();
		System.out.println("Enter the Email ID :");
		String emaiid = s1.next();
		data.put(firstname, new ArrayList<Object>(Arrays.asList(firstname , lastname ,address , city ,state,zipcode,phonenumber,emaiid)));		
		System.out.println("User Added Successfully..");
	}
	public void UpdateUser()		// Update Data in HashMap
	{
		if(data.isEmpty())
		{
			System.out.println("Sorry You have No Data to Update..");
		}
		else
		{
		  System.out.println("Enter the User Name to Edit the User :");
		  String username = s1.next();
		  if(data.containsKey(username))
		  {
			  System.out.println("Enter the field that you want to Edit : 1.FirstName ,2.LastName ,3.Address ,4.City ,5.State ,6.Zipcode ,7.PhoneNumber ,8.EmailID");
		      int updatechoice = s1.nextInt();
		      updatechoice--;
		      System.out.println("Enter the Data you want to Edit:");
		      String updateddata = s1.next();
		      if(data.containsKey(updateddata))
				{
					System.out.println("Sorry This Name is Aldreay Exist..");
					return;
				}
		      ArrayList<Object> newarraylist = data.get(username);
		      if(updatechoice == 0)
		      {
		    	  data.remove(username);
		    	  data.put(updateddata, newarraylist);
		      }		      
		      newarraylist.set(updatechoice, updateddata);
		      data.replace(username, newarraylist); 
		      System.out.println("User Updated Successfully..");
		  }
		  else
		  {
			 System.out.println("Sorry User Cannot find.."); 
		  }
		  
		}
		
	}
	public void DeleteUser()		// Delete Data in HashMap
	{
		if(data.isEmpty())
		{
			System.out.println("Sorry You have No Data to Delete..");
		}
		else
		{
		  System.out.println("Enter the User Name to Delete the User :");
		  data.remove(s1.next());
		  System.out.println("User Deleted Successfully..");
		}				
	}
	public void DisplayUser()		// Display Data from Hashmap..
	{
		if(data.isEmpty())
		{
			System.out.println("Sorry You have No Data to Display..");
		}
		else
		{
		  System.out.println(data.values().toString());
		}
	}
	public void WriteFile()   		// Writing Data into File..
	{   
      BufferedWriter bufferwriter = null;   
        try{
             bufferwriter = new BufferedWriter( new FileWriter(file) );
             for(Entry<String, ArrayList<Object>> entry : data.entrySet())
             {
            	bufferwriter.write( entry.getKey() + ":" + entry.getValue());
            	bufferwriter.newLine();
            }
            bufferwriter.flush();
 
        }
        catch(IOException e){
            e.printStackTrace();
        }finally{
            try {
            	bufferwriter.close();
            }catch(Exception e){}
        }		
	}
	public static void main(String args[])
	{	
		System.out.println("***Welcome to address book Management System***");
		addressmanagementupdation objectforcalss = new addressmanagementupdation();
		objectforcalss.ReadFile();
		do
		{
			System.out.println("1.Add User , 2.Update User , 3.Delete User , 4.Display Users");
			System.out.println("Enter Your Choice : ");
			int choice = s1.nextInt();
			switch(choice)
			{
			case 1: objectforcalss.AddUser();
			break;
			case 2: objectforcalss.UpdateUser();
			break;
			case 3: objectforcalss.DeleteUser();
			break;
			case 4: objectforcalss.DisplayUser();
			break;
			}
			System.out.println("Press 0 to Continue or Any Key to Exit :");
		}while(s1.nextInt()==0);
		objectforcalss.WriteFile();
		System.out.println("***Thank You!!!***");		
	}

}
