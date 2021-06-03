package problems;


import java.util.*;
public class linequality {

	public static void main(String[] args)
	{
		System.out.println("Line Equality Program..");
		Scanner s1 = new Scanner(System.in);
		float l1=0,l2=0;
		for(int i=0;i<2;i++)
		{
			System.out.println("Enter Coordinate X1: ");
			float x1= s1.nextFloat();
			System.out.println("Enter Coordinate Y1: ");
			float y1= s1.nextFloat();
			System.out.println("Enter Coordinate X2: ");
			float x2= s1.nextFloat();
			System.out.println("Enter Coordinate Y2: ");
			float y2= s1.nextFloat();
			
			if(i==0)
			{
				l1=(float)Math.sqrt((Math.pow(x2-x1, 2)) + (Math.pow(y2-y1, 2)));
			}
			else
			{
				l2=(float)Math.sqrt((Math.pow(x2-x1, 2)) + (Math.pow(y2-y1, 2)));	
			}
			
		}
		if(l1==l2)
		{
			System.out.println("Both lines are Equal..");
		}
		else if(l1>l2)
		{
			System.out.println("Line1 is Greater the Line 2..");
		}
		else
		{
			System.out.println("Line2 is Greater the Line 1..");
		}

	}

}
