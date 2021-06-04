package problems;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;

public class SnakeLadder {

	
	private static Logger logger = LogManager.getLogger(SnakeLadder.class);	
	private static Scanner s1 = new Scanner(System.in);
	
	int diceval;
	public int Dicerollingfunction()
	{
		diceval =0;
	    diceval = (int)((Math.random()*10)%7);
		System.out.println(" You Got Dice Value :" + diceval);
		return diceval;
	}
	
	public int checkdice(int dicevalue)
	{
		
		int checkvalue = (int)((Math.random()*10%3));
		if(dicevalue > 100)
		{
			System.out.println(" Sorry You go above 100 , Return to same position..");
			return dicevalue-diceval;
		}
		else
		{
	   System.out.println(" Checking Wheater there is ladder or Snake or Nothing..");
	    switch(checkvalue)
	    {
	      
	      case 0 :  System.out.println(" No Problem You have Nothing..");
	    	        return dicevalue;
	      case 1 :  System.out.println(" YEAH !! You got Ladder..");
	      			dicevalue+=diceval;
	      			if(dicevalue > 100)
	      			{
	      				System.out.println(" Sorry You go above 100 , Return to same position..");
	      				return (dicevalue-diceval);
	      			}
	      			else
	      			{
	      				return dicevalue;
	      			}
	      			
	      case 2 : System.out.println(" Oh...NO... You got Snake..");
	      			dicevalue-=(diceval+diceval);
	      			if(dicevalue <= 0)
	      			{
	      				System.out.println(" Sorry You go less than 0, You can restart from 0..");
	      				return 0;
	      			}
	      			else
	      			{
	      				return dicevalue;
	      			}	      
	    
	    }
		}
		return 1;
	}
   	public static void main(String[] args)	
	{
   		SnakeLadder obj = new SnakeLadder();
   		int player1score = 0 , player2score =0,count=0,flag=1;
		Scanner s1 = new Scanner(System.in);
		System.out.println("***WELCOME TO SNAKE LADDER GAME***");
		System.out.println("Enter Player1 Name: ");
		String player1= s1.next();
		System.out.println("Enter Player2 Name: ");
		String player2 = s1.next();
		System.out.println("Let's PLAY!!!");
		System.out.println(player1+" Vs "+player2);
		
		while(flag==1)
		{
			String playername ="";
			if(count%2==0)
			{
				playername = player1;
			}
			else
			{
				playername = player2;
			}
			count++;
			System.out.println(" Now Its " + playername +" turn..");
			System.out.println(" Press 0 to roll a Dice");
			int usercommand  = s1.nextInt();
			if(usercommand == 0)
			{
				int dicevalue = obj.Dicerollingfunction();
				if(playername == player1)
				{
					player1score+=dicevalue;
					if(dicevalue!=0 && player1score!=100)
					{
					  player1score=obj.checkdice(player1score);	
					}
					
					
				}
				else
				{
					player2score+=dicevalue;
					if(dicevalue!=0 && player2score!=100)
					{
					 player2score=obj.checkdice(player2score);
					}
				}
				System.out.println(" Position of "+player1+" "+player1score);
				System.out.println(" Position of "+player2+" "+player2score);
	    		if(player1score == 100 || player2score == 100)
				{
					flag=0;
				}
	    		
			}
		}
		if(player1score > player2score)
		{
			System.out.println(" CONGRULATIONS!!!! "+player1+" You are a WINNER..");
		}
		else
		{
			System.out.println(" CONGRULATIONS!!!! "+player2+" You are a WINNER..");
		}
		System.out.println("***Thank You***");
	}
}
		
		
		
       
  
		

