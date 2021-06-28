import java.util.*;

class generic_class<E>
{
	
	public <E extends Comparable<E>>  E Findmaximun(E a, E b, E c)
	{
	/*	int d = c > (a>b
	 *  ? a : b) ? c : ((a > b) ? a : b);
		
		int d = Math.max((Math.max(a, b)), c);
		
		System.out.println(" largest Number "+ d);*/

		E max_number = a;
		
		if(b.compareTo(max_number) > 0)
			max_number  = b;   
		
		if(c.compareTo(max_number) > 0)
			max_number = c;
		
		
		System.out.println(" Maximum value :  "+ max_number);
		return max_number;
	} 
}


public class Generic_Program

{
	public static void main(String[] args) {
			
		generic_class obj = new generic_class();
		obj.Findmaximun("Raj", "Venkat", "Siva");
		obj.Findmaximun(6,7,84);
		obj.Findmaximun(5.7,6.8,45.7);
	}

}
