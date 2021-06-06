package problems;
import java.util.*;

public class addressmanagement {
	
	HashMap<String , ArrayList<Object>> data = new HashMap<String , ArrayList<Object>>();
	public static Scanner s1 = new Scanner(System.in);
	public void AddUser()
	{
		System.out.println("Enter the First Name :");
		String firstname = s1.next();
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
	public void UpdateUser()
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
	public void DeleteUser()
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
	public void DisplayUser()
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
	public static void main(String args[])
	{	
		System.out.println("***Welcome to address book Management System***");
		addressmanagement obj = new addressmanagement();
		do
		{
			System.out.println("1.Add User , 2.Update User , 3.Delete User , 4.Display Users");
			System.out.println("Enter Your Choice : ");
			int choice = s1.nextInt();
			switch(choice)
			{
			case 1: obj.AddUser();
			break;
			case 2: obj.UpdateUser();
			break;
			case 3: obj.DeleteUser();
			break;
			case 4: obj.DisplayUser();
			break;
			}
			System.out.println("Press 0 to Continue or Any Key to Exit :");
		}while(s1.nextInt()==0);
		System.out.println("***Thank You!!!***");
		
	}

}
