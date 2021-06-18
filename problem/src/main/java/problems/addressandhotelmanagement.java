package problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;

import javax.print.DocFlavor.STRING;

import org.bson.Document;




import java.util.*;
import java.sql.*;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class addressandhotelmanagement {
	
	static LinkedHashMap<String , ArrayList<Object>> data = new LinkedHashMap<String , ArrayList<Object>>();
	final static String filepath = "E:/Addressbook.txt";
	public static Scanner s1 = new Scanner(System.in);
	File file = new File(filepath);
	
	String url;
	String user;
	String pass;
	
	Connection conn;
	Statement statement;
	ResultSet res;
	
	public void fillhashmap(HashMap<String, ArrayList<Object>> hashmap , ResultSet result)
	{
		data.clear();
		try
		{
		while(res.next())
		 {
	     String firstname = res.getString(1);
	   	 String lastname = res.getString(2);
	   	 String address = res.getString(3);
	   	 String city = res.getString(4);
	   	 String state = res.getString(5);
	   	 String zipcode = res.getString(6);
	   	 String phonenumber = res.getString(7);
	   	 String emaiid = res.getString(8);
	   	 data.put(firstname, new ArrayList<Object>(Arrays.asList(firstname , lastname ,address , city ,state,zipcode,phonenumber,emaiid)));	    			 	    	
		    }	
			}catch (SQLException e) {
				System.out.println(e.toString());
			}catch (Exception e) {
				System.out.println(e.toString());
			}	
	}
	public void DBConnectionandReadData()   // Create DB Connection and load file into HashMap..
	{	
		try
		{
		 url ="jdbc:mysql://localhost:3306/addressbook";
		 user = "root";
		 pass = "Abinaya@2308";
		 String query = "SELECT * FROM addressbook.details";
		 try
		 {
			 Class.forName("com.sql.jdbc.Driver");
		 }catch (ClassNotFoundException e) {}
		 conn = DriverManager.getConnection(url , user , pass);
		 statement = conn.createStatement();
		 res = statement.executeQuery(query);
		}catch (SQLException e) {
			System.out.println(e.toString());
		}
		fillhashmap(data, res);
	}
	
	public void DBConnectiondWriteData()
	{
		String query ="DELETE FROM details";
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		 for(Entry<String, ArrayList<Object>> entry : data.entrySet())
         {
			 ArrayList<Object> arraylist = entry.getValue();
			  query ="INSERT INTO DETAILS VALUES(";
			 for(int i=0;i<8;i++)
			 {
			    query+="'"+arraylist.get(i)+"'";
				if(i<7)
				{
					query+=",";
				}
			 }
			 query+=")";	
			try {
				statement.executeUpdate(query);
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
         }
		 
	  }
	public void Sorting()
	{
		DBConnectiondWriteData();
		DBConnectionandReadData();
		System.out.println("Enter the Field based on Sorting should done..1.Name , 2. State ,3. ZipCode , 4. City.");
		int choice = s1.nextInt();
		String query="";
		switch (choice) 
		{
		case 1: query = "SELECT * FROM DETAILS ORDER BY fistname";
		break;
		case 2: query = "SELECT * FROM DETAILS ORDER BY state";
		break;
		case 3: query = "SELECT * FROM DETAILS ORDER BY zipcode";
		break;
		case 4: query = "SELECT * FROM DETAILS ORDER BY city";
		break;
		}
		try {
			 res = statement.executeQuery(query);
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		fillhashmap(data, res);
		System.out.println("Sorting is Done, You can see the result in Display Option..");
		
	}
	
	public void Searching()
	{
		DBConnectiondWriteData();
		DBConnectionandReadData();
		System.out.println("1.Normal Search 2. Advanced Search");
		int choice = s1.nextInt();
		if(choice==1)
		{
			System.out.println("Enter the Name of the Person you are Searching");
			String searchname = s1.next();
			if(data.containsKey(searchname))
			{
				System.out.println(searchname+" is Found");
				System.out.println(data.get(searchname));
			}
			else
			{
				System.out.println("Sorry Name is not Found..");
			}
		}
		else
		{
			System.out.println("Filter should done on 1.City or 2.State");
			int choice1 = s1.nextInt();
			String cityorstatename;
			String query = "";
			if(choice1 ==1)
			{
	          System.out.println("Enter the City Name");
	          cityorstatename = s1.next();
	          query = "SELECT * FROM DETAILS WHERE city = '"+cityorstatename+"'";
	          AdvancedSearch(query);
			}
			if(choice1 == 2)
			{
			  System.out.println("Enter the State Name");
	          cityorstatename = s1.next();
	          query = "SELECT * FROM DETAILS WHERE state = '"+cityorstatename+"'";
	          AdvancedSearch(query);
			}
						
		}
		
	}
	
	public void AdvancedSearch(String cityorstatename)
	{
		System.out.println(" Advanced Search..");
		System.out.println(" ADS Query " + cityorstatename);
		 try {
			res = statement.executeQuery(cityorstatename);
		    int count =0;
			while(res.next())
			{
				count++;
				for(int i=1;i<9;i++)
				{
					System.out.print(res.getString(i)+"   ");
				}
				System.out.println();
			}
			System.out.println("Total Count :"+count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
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
		  System.out.println(data);
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
		addressandhotelmanagement objectforcalss = new addressandhotelmanagement();
		objectforcalss.DBConnectionandReadData();
		//objectforcalss.ReadFile();
		do
		{
			System.out.println("1.Add User , 2.Update User , 3.Delete User , 4.Display Users , 5.Sorting , 6.Searching");
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
			case 5: objectforcalss.Sorting();
			break;
			case 6: objectforcalss.Searching();
			break;
			}
			System.out.println("Press 0 to Continue or Any Key to Exit :");
		}while(s1.nextInt()==0);
		objectforcalss.DBConnectiondWriteData();
		//objectforcalss.WriteFile();
		System.out.println("***Thank You!!!***");		
	}

}

/*
public void mongodbconnection()
{
	
	System.setProperty("jdk.tls.trustNameService", "true");
	
	System.out.println(" Inside the DB Connection");
	MongoClientURI uri = new MongoClientURI("mongodb+srv://Siva:6009 @addressbook.12tz9.mongodb.net/Details?retryWrites=true&w=majority");
	
	MongoClientOptions.Builder options = MongoClientOptions.builder();
	 options.socketKeepAlive(true);
	 
	try(MongoClient mongoclient = new MongoClient(uri))
	{
		System.out.println("Inside Try..");
		DB mongodatabase = mongoclient.getDB("Details");
		DBCollection mongocollection = mongodatabase.getCollection("AddressDetails");
		
		System.out.println(" Above  Cursor...");
		DBCursor cursor = mongocollection.find();
		
		while(cursor.hasNext())
		{
			System.out.println(" Data "+cursor.next().toString());
		}
		
		System.out.println(" Below While...");
		//System.out.println(" Collection Name " + mongocollection.getNamespace().toString());
	}
}*/

/*	public void Sorting()    // Sorting Based on Alpahabetical Order (First Name..);
{
	

	//data.entrySet().stream().sorted();
	
	//System.out.println(data.values(7);)
//	
/*	System.out.println("Enter the Field based on Sorting should Done..");
	System.out.println("1.Name , 2.City , 3.State , 4.ZipCode..");
	int choice = s1.nextInt();
	
	
	
	System.out.println(" Entry Set " + data.entrySet());
	TreeMap sortedahashmapdata = new TreeMap(data);
	System.out.println(" Tree Map " + sortedahashmapdata);
	data.clear();
	data.putAll(sortedahashmapdata);
	
	
	
	for(Map.Entry<String, ArrayList<Object>> entry : entries)
	{
		System.out.println(entry.getKey() + "  ==> " + entry.getValue());
		data.put(entry.getKey(), entry.getValue());
		System.out.println(" Data in HashMao " +data);
	}
	
	
	
	Set<Map.Entry<String, ArrayList<Object>>> newdata = sortedahashmapdata.entrySet();
//	newdata.re
	System.out.println(" Reverse Data " +newdata);
	data.clear();
	System.out.println(" Data Clear " + data);
	System.out.println(" Data Cleared..");
	
	data.putAll(sortedahashmapdata);
	
	System.out.println(" Datas " + data);
	
	/*for(Map.Entry<String, ArrayList<Object>> entry : newdata)
	{
		System.out.println(entry.getKey() + "  ==> " + entry.getValue());
		data.put(entry.getKey(), entry.getValue());
		System.out.println(" Data in HashMao " +data);
	}*/
	//for(Map.Entry<K, V>)
	
//	for(Entry<String, ArrayList<Object>> entry : sortedahashmapdata.entrySet())
  //  {
   	//bufferwriter.write( entry.getKey() + ":" + entry.getValue());
   //	bufferwriter.newLine();
   //}
//System.out.println(" Hash Map " + data);
	
	//data.clear();
	
	//data = (HashMap<String, ArrayList<Object>>)sortedahashmapdata;
	
//	HashMap<String, ArrayList<Object>> newdata = (HashMap<String, ArrayList<Object>>)data.entrySet().stream().sorted(Map.Entry.<String , ArrayList<Object>>comparingByKey());
	
//	System.out.println(newdata.values().toString());
//}

