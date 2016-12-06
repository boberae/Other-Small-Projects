package changeJarPack;

import java.io.*;
import java.util.Scanner;

/**********************************************************************
Simulation of a basic change jar that may contain any number of
quarters, dimes, nickels and pennies.

@author Tony Bober
@version 1.0
 ***********************************************************************/
public class ChangeJar {

	/** Number of quarters in jar */
	private int quarters;

	/** Number of dimes in jar */
	private int dimes;

	/** Number of nickels in jar */
	private int nickels;

	/** Number of pennies in jar */
	private int pennies;

	/** Turns 'off' and 'on' takeOut/putIn methods (true = suspended) */
	private static boolean suspended = false;

	/******************************************************************
    Default constructor that sets the ChangeJar to zero.   
	 ******************************************************************/
	public ChangeJar() 
	{
		this.quarters = 0;
		this.dimes = 0;
		this.nickels = 0;
		this.pennies = 0;
	}

	public int getQuarters()
	{
		return quarters;
	}

	public void setQuarters(int quarters)
	{
		if (quarters > 0)
		{
			this.quarters = quarters;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	public int getDimes() 
	{
		return dimes;
	}

	public void setDimes(int dimes) 
	{
		if (dimes > 0)
		{
			this.dimes = dimes;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	public int getNickels() 
	{
		return nickels;
	}

	public void setNickels(int nickels)
	{
		if (nickels > 0)
		{
			this.nickels = nickels;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	public int getPennies() 
	{
		return pennies;
	}

	public void setPennies(int pennies)
	{
		if (pennies > 0)
		{
			this.pennies = pennies;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	public static boolean isSuspended() 
	{
		return suspended;
	}


	/******************************************************************
    A constructor that initializes the instance variables with the 
    parameters. 
    @param quarters The number of quarters in the jar
    @param dimes The number of dimes in the jar
    @param nickels The number of nickels in the jar
    @param pennies The number of pennies in the jar
    @throws IllegalArgumentException When any negative inputs are received
	 ******************************************************************/
	public ChangeJar(int quarters, int dimes, int nickels, int pennies) 
	{
		//Check for valid inputs
		if (quarters < 0 || dimes < 0 || nickels < 0 || pennies < 0)
		{
			throw new IllegalArgumentException();
		}

		this.quarters = quarters;
		this.dimes = dimes;
		this.nickels = nickels;
		this.pennies = pennies;
	}

	/******************************************************************
    A constructor that initializes the instance variables with the 
    other ChangeJar parameter.
    @param other ChangeJar to be copied
	 ******************************************************************/
	public ChangeJar (ChangeJar other)  
	{
		this.quarters = other.quarters;
		this.dimes = other.dimes;
		this.nickels = other.nickels;
		this.pennies = other.pennies;
	}

	/******************************************************************
    A method that returns true if "this" ChangeJar object is exactly
    the same as the other object.  That is, quarters = other.quarters,
    dimes = other.dimes, etc.
    @param other ChangeJar to compare to "this" ChangeJar
    @throws RuntimeException when other is not a ChangeJar
    @return True if "this" ChangeJar object is exactly the same as the 
    other object, false if "this" ChangeJar object is not exactly the same as
    the other object
	 ******************************************************************/
	public boolean equals (Object other) 
	{
		ChangeJar other1;

		// If the object isn't a ChangeJar, throw a RuntimeException
		if (other instanceof ChangeJar) 
		{
			other1 = (ChangeJar) other;
		}
		else
		{
			throw new RuntimeException();
		}

		// If the object is a ChangeJar, find if it's equal to "this"
		return  (this.quarters == other1.quarters &&
				this.dimes == other1.dimes &&
				this.nickels == other1. nickels &&
				this.pennies == other1.pennies);
	}

	/******************************************************************
    A method that returns true if "this" ChangeJar object is exactly
    the same as the other ChangeJar object.
    @param other ChangeJar to compare to "this" ChangeJar
    @return True if "this" ChangeJar object is exactly the same as the 
    other object, false if "this" ChangeJar object is not exactly the same as
    the other object
	 ******************************************************************/
	public boolean equals (ChangeJar other) 
	{
		return (this.quarters == other.quarters &&
				this.dimes == other.dimes &&
				this.nickels == other. nickels &&
				this.pennies == other.pennies);
	}

	/******************************************************************
    A static method that returns true if ChangeJar object jar1 is 
    exactly the same as if ChangeJar object jar2.
    @param jar1 First ChangeJar to be compared
    @param jar2 Second ChangeJar to be compared
    @return True if ChangeJar object jar1 is exactly the same as the 
    ChangeJar object jar2, false if ChangeJar object jar1 is not 
    exactly the same as jar2
	 ******************************************************************/
	public static boolean equals (ChangeJar jar1, ChangeJar jar2)
	{
		return (jar1.quarters == jar2.quarters &&
				jar1.dimes == jar2.dimes &&
				jar1.nickels == jar2.nickels &&
				jar1.pennies == jar2.pennies);
	}

	/******************************************************************
    A method that returns 1 if "this" ChangeJar object is greater than 
    (based upon the total in the ChargeJar) the other ChangeJar object; 
    returns -1 if the "this" ChangeJar object is less than the other 
    ChangeJar; returns 0 if the "this" ChangeJar object is equal to the 
    other ChangeJar object.
    @param other ChangeJar to compare to "this" ChangeJar
    @return 1 if "this" ChangeJar object is greater than 
    (based upon the total in the ChargeJar) the other ChangeJar object; 
    returns -1 if the "this" ChangeJar object is less than the other 
    ChangeJar; returns 0 if the "this" ChangeJar object is equal to the 
    other ChangeJar object
	 ******************************************************************/
	public int compareTo (ChangeJar other)
	{
		if (convertToAmount(this) > convertToAmount(other))
			return 1;
		else if (convertToAmount(this) < convertToAmount(other))
			return -1;
		else 
			return 0;
	}

	/******************************************************************
    A method that returns 1 if  ChangeJar object jar1 is greater than 
    ChangeJar object jar2; returns -1 if the ChangeJar object jar1 is 
    less than ChangeJar jar2;  returns 0 if the ChangeJar object jar1 
    is equal to ChangeJar object jar2.
    @param jar1 First ChangeJar to be compared
    @param jar2 Second ChangeJar to be compared
    @return 1 if  ChangeJar object jar1 is greater than 
    ChangeJar object jar2; returns -1 if the ChangeJar object jar1 is 
    less than ChangeJar jar2;  returns 0 if the ChangeJar object jar1 
    is equal to ChangeJar object jar2
	 ******************************************************************/
	public static int compareTo (ChangeJar jar1, ChangeJar jar2)
	{
		if (convertToAmount(jar1) > convertToAmount(jar2))
			return 1;
		else if (convertToAmount(jar1) < convertToAmount(jar2))
			return -1;
		else
			return 0;
	}

	/******************************************************************
    A method that adds the parameters to the "this" ChangeJar object.
    @param quarters Number of quarters to be added to the "this" ChangeJar
    @param dimes Number of dimes to be added to the "this" ChangeJar
    @param nickels Number of nickels to be added to the "this" ChangeJar
    @param pennies Number of pennies to be added to the "this" ChangeJar
    @throws IllegalArgumentException When any negative inputs are received
	 ******************************************************************/
	public void putIn (int quarters, int dimes, int nickels, int pennies)
	{		
		// Check for valid inputs
		if (quarters < 0 || dimes < 0 || nickels < 0 || pennies < 0)
		{
			throw new IllegalArgumentException();
		}

		// Put coins in (if not suspended)
		if(!suspended)
		{
			this.quarters += quarters;
			this.dimes += dimes;
			this.nickels += nickels;
			this.pennies += pennies;
		}
	}

	/******************************************************************
    A method that add ChangeJar other to the "this" ChangeJar object.
    @param other ChangeJar to be added to the "this" ChangeJar
	 ******************************************************************/
	public void putIn (ChangeJar other)
	{
		// Put in only when not suspended
		if (! suspended)
		{
			this.quarters += other.quarters;
			this.dimes += other.dimes;
			this.nickels += other.nickels;
			this.pennies += other.pennies;
		}
	}


	/******************************************************************
    A method that subtracts the parameters from the "this" ChangeJar 
    object (as long as not suspended).
    @param quarters Number of quarters to be subtracted from the "this" ChangeJar
    @param dimes Number of dimes to be subtracted from the "this" ChangeJar
    @param nickels Number of nickels to be subtracted from the "this" ChangeJar
    @param pennies Number of pennies to be subtracted from the "this" ChangeJar
    @throws RuntimeException if any parameter is greater than the number
    of coins available in "this" ChangeJar
    @throws IllegalArgumentException When any negative inputs are received
	 ******************************************************************/
	public void takeOut (int quarters, int dimes, int nickels, int pennies)
	{
		// Check for valid inputs
		if (quarters < 0 || dimes < 0 || nickels < 0 || pennies < 0)
		{
			throw new IllegalArgumentException();
		}

		// If we have enough quarters, dimes, nickels and pennies,
		// and we're not suspended, take the coins from "this" ChangeJar
		if (this.quarters >= quarters &&
				this.dimes >= dimes &&
				this.nickels >= nickels &&
				this.pennies >= pennies)
		{
			if (!suspended)
			{
				this.quarters -= quarters;
				this.dimes -= dimes;
				this.nickels -= nickels;
				this.pennies -= pennies;
			}
		}

		// If we don't have enough quarters, dimes, nickels, and pennies,
		// throw a RuntimeException
		else
		{
			throw new RuntimeException();
		}
	}

	/******************************************************************
    A method that subtracts ChangeJar other from the "this" ChangeJar 
    object (as long as not suspended).
    @param other ChangeJar to be subtracted from the "this" ChangeJar
    @throws RuntimeException if any parameter is greater than the number
    of coins available in "this" ChangeJar
	 ******************************************************************/
	public void takeOut (ChangeJar other)
	{	
		// If we have enough quarters, dimes, nickels and pennies,
		// and we're not suspended, take the coins from "this" ChangeJar
		if (this.quarters >= other.quarters &&
				this.dimes >= other.dimes &&
				this.nickels >= other.nickels &&
				this.pennies >= other.pennies)
		{
			if(!suspended)
			{
				this.quarters -= other.quarters;
				this.dimes -= other.dimes;
				this.nickels -= other.nickels;
				this.pennies -= other.pennies;
			}
		}	
		// If we don't have enough quarters, dimes, nickels, and pennies,
		// throw a RuntimeException
		else
		{
			throw new RuntimeException();
		}
	}

	/******************************************************************
    This method returns a ChangeJar that has the exact number of quarters, 
    dimes, nickels, pennies that would sum to the amount (maximized such
    that the largest currency available is in the returned ChangeJar).  
    @param amount Amount of change to be removed from the "this" ChangeJar
    @throws IllegalArgumentException When a negative input is received
    @throws RuntimeException When there are insufficient funds in the ChangeJar
    @return A ChangeJar that has the exact number of quarters, 
    dimes, nickels, pennies that would sum to the amount (maximized such
    that the largest currency available is in the returned ChangeJar).
    Returns null if suspended, or if there are sufficient funds in 
    "this" ChangeJar, but exact change cannot be made 
	 ******************************************************************/
	public ChangeJar takeOut (double amount)
	{
		// If the desired amount is greater than the amount in "this" 
		// ChangeJar, throw an exception
		if (convertToAmount(this) < (int)(100*amount))
		{
			throw new RuntimeException();
		}

		// Check that amount is positive
		if (amount < 0)
		{
			throw new IllegalArgumentException();
		}

		// If we're suspended, return null
		if (suspended)
		{
			return null;
		}

		// Multiply amount by 100 to convert to # of cents
		int tempAmount = (int)(100*amount);

		// Then make a new ChangeJar...
		ChangeJar jar1 = new ChangeJar();
		// Load values into jar1 that equate to the desired amount, completely 
		// maximizing so it has the largest currency possible, irrespective
		// of what "this" ChangeJar can actually provide
		jar1.quarters = tempAmount / 25;
		tempAmount -= (jar1.quarters * 25);
		jar1.dimes = tempAmount / 10;
		tempAmount -= (jar1.dimes * 10);
		jar1.nickels = tempAmount / 5;
		tempAmount -= (jar1.nickels * 5);
		jar1.pennies = tempAmount;

		// While any of the coins in our jar1 exceed the amount available
		// in "this" ChangeJar, we know we can't take jar1 out of "this"
		while (jar1.quarters > this.quarters ||
				jar1.dimes > this.dimes ||
				jar1.nickels > this.nickels ||
				jar1.pennies > this.pennies)
		{

			// If # of quarters in jar1 exceeds # of quarters in "this"
			// ChangeJar, we have to subtract some quarters from jar1.
			// In order to still add up to the correct amount when we do this,
			// we have to add some dimes and nickels.
			while (jar1.quarters > this.quarters)
			{
				jar1.quarters--;
				jar1.dimes += 2;
				jar1.nickels++;
			}

			// The quarter subtraction above might have led to a non-maximized
			// amount of nickels and dimes... so replace every 2 nickels with
			// a dime until we're maximized again
			while (jar1.nickels > 1)
			{
				jar1.nickels -= 2;
				jar1.dimes++;
			}

			// If # of dimes in jar1 exceeds # of dimes in "this"
			// ChangeJar, we have to subtract some dimes from jar1.
			// In order to still add up to the correct amount when we do this,
			// we have to add some nickels.
			while (jar1.dimes > this.dimes)
			{
				jar1.dimes--;
				jar1.nickels += 2;
			}

			// If # of nickels in jar1 exceeds # of nickels in "this"
			// ChangeJar, we have to subtract some nickels from jar1.
			// In order to still add up to the correct amount when we do this,
			// we have to add some pennies.
			while (jar1.nickels > this.nickels)
			{
				jar1.nickels--;
				jar1.pennies += 5;
			}

			// If # of pennies in jar1 exceeds # of pennies in "this"
			// ChangeJar, we have to subtract some pennies from jar1.
			// Because we have to do this, we know that we have to change
			// the other amounts in our jar1. So let's subtract one quarter,
			// add three dimes, and repeat this whole process.
			if (jar1.pennies > this.pennies)
			{
				jar1.quarters--;
				jar1.pennies -= 5;
				jar1.dimes += 3;
			}

			// If at this point, we're subtracting a quarter from jar1
			// that we don't actually have, we know there's no combination
			// of coins in "this" ChangeJar that adds up to the desired amount.
			// Return null.
			if (jar1.quarters < 0)
			{
				return null;
			}
		}

		// We made it out of the loop!  Take out jar1 from "this"
		this.takeOut(jar1);
		return jar1;	
	}

	/******************************************************************
    Method that returns a string that represents a ChangeJar.
    @return A string representation of ChangeJar
	 ******************************************************************/
	public String toString()
	{
		// All if statements simply deal with proper pluralization

		String output = "";

		output += this.quarters;

		if (this.quarters == 1)
			output += " quarter, ";
		else
			output += " quarters, ";

		output += this.dimes;

		if (this.dimes == 1)
			output += " dime, ";
		else
			output += " dimes, ";

		output += this.nickels;

		if (this.nickels == 1)
			output += " nickel, ";
		else
			output += " nickels, ";

		output += this.pennies;

		if (this.pennies == 1)
			output += " penny";
		else
			output += " pennies";

		return output;
	}

	/******************************************************************
	A method that saves the "this" ChangeJar to a file; The parameter 
	filename is used for the name of the file.
	@param fileName Name of the file to save "this" ChangeJar to
	 ******************************************************************/
	public void save (String fileName)
	{
		// Create file named fileName.txt
		PrintWriter out = null;
		try 
		{
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
		} 

		// fileName invalid
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		// Print quarters, dimes, nickels, pennies to file
		out.println(this.quarters + " " + this.dimes + " " + this.nickels +
				" " + this.pennies);
		// Close file
		out.close(); 
	}

	/******************************************************************
	A method that loads the "this" ChangeJar object from a file; The parameter 
	filename is the name of the file.
	@param fileName Name of the file from which "this" ChangeJar is loaded
	 ******************************************************************/
	public void load (String fileName)
	{			
		try
		{
			// open the data file
			Scanner fileReader = new Scanner(new File(fileName)); 

			// read in number of quarters, dimes, nickels, pennies
			this.quarters = fileReader.nextInt();
			this.dimes = fileReader.nextInt();
			this.nickels = fileReader.nextInt();
			this.pennies = fileReader.nextInt();
			fileReader.close();
		}

		// could not find file
		catch(Exception error) 

		{
			throw new IllegalArgumentException();
		}
	}

	/******************************************************************
	A method that turns "off" and "on" any takeOut/putIn methods in ChangeJar.
	In other words, when true, this method prevents any takeOut/putIn method from 
	changing (mutating) the state of the "this" object as it relates to 
	the amount in the ChangeJar.
	@param on When on = true the takeOut/putIn methods are disabled
	 ******************************************************************/
	public static void suspend (Boolean on)
	{
		if (on)
		{
			suspended = true;
		}
		else
		{
			suspended = false;
		}
	}

	/*
    This private method returns an integer representing the number of 
    cents in the ChangeJar other. 
	 */
	private static int convertToAmount (ChangeJar other)
	{
		return (other.quarters *25 + 
				other.dimes * 10 +
				other.nickels * 5 +
				other.pennies);
	}

	/******************************************************************
    Main method.
	 ******************************************************************/
	public static void main(String[] args)
	{
		// Test constructors
		ChangeJar s1 = new ChangeJar();
		ChangeJar s2 = new ChangeJar(1,2,3,4);
		ChangeJar s3 = new ChangeJar(s2);
		System.out.println ("Constructors: ChangeJar S1 should have 0q, 0d, 0n, 0p: ");
		System.out.println (s1);
		System.out.println ("Constructors: ChangeJar S2 should have 1q, 2d, 3n, 4p: ");
		System.out.println (s2);
		System.out.println ("Constructors: ChangeJar S3 should have 1q, 2d, 3n, 4p: ");
		System.out.println (s3);
		System.out.println();

		//Test getters and setters
		s1.setQuarters(2);
		s1.setDimes(3);
		s1.setNickels(4);
		s1.setPennies(5);
		int a1 = s1.getQuarters();
		int a2 = s1.getDimes();
		int a3 = s1.getNickels();
		int a4 = s1.getPennies();
		System.out.println("Getters/Setters: Quarters should be 2, is: " + a1);
		System.out.println("Getters/Setters: Dimes should be 3, is: " + a2);
		System.out.println("Getters/Setters: Quarters should be 4, is: " + a3);
		System.out.println("Getters/Setters: Quarters should be 5, is: " + a4 + "\n");

		//Test equals
		ChangeJar s4 = new ChangeJar(4,3,2,1);
		ChangeJar s5 = new ChangeJar(4,3,2,1);
		ChangeJar s6 = new ChangeJar(1,2,3,4);
		if (s4.equals(s5) && (!s4.equals(s6)))
		{
			System.out.println("equals(ChangeJar) works!");
		}
		else
		{
			System.out.println("equals(ChangeJar) doesn't work!");
		}
		if(s4.equals((Object)s5) && (!s4.equals((Object)s6)))
		{
			System.out.println("equals(Object) works!");
		}
		else
		{
			System.out.println("equals(Object) doesn't work!");
		}
		if(ChangeJar.equals(s4,s5) && (!ChangeJar.equals(s4,s6)))
		{
			System.out.println("equals(ChangeJar, ChangeJar) works!");
		}
		else
		{
			System.out.println("equals(ChangeJar, ChangeJar) doesn't work!");
		}
		System.out.println();

		// Test compareTo
		ChangeJar s7 = new ChangeJar(2, 5, 4, 2);
		ChangeJar s8 = new ChangeJar(6, 5, 4, 2);
		ChangeJar s9 = new ChangeJar(2, 3, 4, 2);
		ChangeJar s10 = new ChangeJar(2, 5, 4, 2);
		if((s8.compareTo(s7) == 1) && 
				(s9.compareTo(s7) == -1) && 
				(s10.compareTo(s7) == 0))
		{
			System.out.println("compareTo(ChangeJar) works!");
		}
		else
		{
			System.out.println("compareTo(ChangeJar) doesn't work!");
		}
		if(((ChangeJar.compareTo(s8, s7)) == 1) && 
				((ChangeJar.compareTo(s9, s7)) == -1) && 
				((ChangeJar.compareTo(s10, s7)) == 0))
		{
			System.out.println("compareTo(ChangeJar, ChangeJar) works!");
		}
		else
		{
			System.out.println("compareTo(ChangeJar, ChangeJar) doesn't work!");
		}
		System.out.println();

		// Test putIn
		ChangeJar s11 = new ChangeJar();
		ChangeJar s12 = new ChangeJar(1,1,1,1);
		s11.putIn(2,2,2,2);
		s12.putIn(1,1,1,1);
		if(s11.equals(s12))
		{
			System.out.println("putIn(int,int,int,int) works!");
		}
		else
		{
			System.out.println("putIn(int,int,int,int) doesn't work!");
		}
		ChangeJar s13 = new ChangeJar(3,3,3,3);
		s11.putIn(s13);
		s12.putIn(1,1,1,1);
		s12.putIn(2,2,2,2);
		if(s11.equals(s12))
		{
			System.out.println("putIn(ChangeJar) works!");
		}
		else
		{
			System.out.println("putIn(ChangeJar) doesn't work!");
		}
		System.out.println();
		
		// Test takeOut
		ChangeJar s14 = new ChangeJar(2,3,4,5);
		s14.takeOut(1,1,1,1);
		ChangeJar s15 = new ChangeJar(1,2,3,4);
		if(s14.equals(s15))
		{
			System.out.println("takeOut(int,int,int,int) works!");
		}
		else
		{
			System.out.println("takeOut(int,int,int,int) doesn't work!");
		}
		ChangeJar s16 = new ChangeJar(2,3,4,5);
		ChangeJar s17 = new ChangeJar(1,1,1,1);
		s16.takeOut(s17);
		if(s16.equals(s15))
		{
			System.out.println("takeOut(ChangeJar) works!");
		}
		else
		{
			System.out.println("takeOut(ChangeJar) doesn't work!");
		}
		ChangeJar s18 = new ChangeJar(1,3,1,2);
		ChangeJar s19 = new ChangeJar(1,3,0,2);
		ChangeJar s20 = new ChangeJar(2,10,4,10);
		ChangeJar s21 = s18.takeOut(0.32);
		ChangeJar s22 = s19.takeOut(0.32);
		ChangeJar s23 = s20.takeOut(1.29);
		ChangeJar s24 = new ChangeJar(1,0,1,2);
		ChangeJar s25 = new ChangeJar(0,3,0,0);
		ChangeJar s26 = new ChangeJar(0,3,0,2);
		ChangeJar s27 = new ChangeJar(1,0,0,0);
		ChangeJar s28 = new ChangeJar(2,7,1,4);
		ChangeJar s29 = new ChangeJar(0,3,3,6);
		if(s21.equals(s24) && s18.equals(s25) &&
			s22.equals(s26) && s19.equals(s27) &&
			s23.equals(s28) && s24.equals(s29))
		{
			System.out.println("takeOut(double) works!");
		}
		else
		{
			System.out.println("takeOut(double) doesn't work!");
		}
		System.out.println();
		
		// Test toString
		ChangeJar s30 = new ChangeJar(0,2,3,4);
		ChangeJar s31 = new ChangeJar(1,1,1,1);
		System.out.println("Test toString for a ChangeJar with no 1s:");
		System.out.println(s30);
		System.out.println("Test toString for a ChangeJar with all 1s:");
		System.out.println(s31);
		System.out.println();
		
		// Test suspend
		ChangeJar.suspend(true);
		ChangeJar s32 = new ChangeJar(5,5,5,5);
		s32.putIn(1,1,1,1);
		ChangeJar s33 = new ChangeJar(5,5,5,5);
		ChangeJar s34 = new ChangeJar(1,1,1,1);
		s33.putIn(s34);
		ChangeJar s35 = new ChangeJar(5,5,5,5);
		s35.takeOut(1,1,1,1);
		ChangeJar s36 = new ChangeJar(5,5,5,5);
		ChangeJar s37 = new ChangeJar(1,1,1,1);
		s36.takeOut(s37);
		ChangeJar s38 = new ChangeJar(5,5,5,5);
		s38.takeOut(0.41);
		ChangeJar s39 = new ChangeJar(5,5,5,5);
		ChangeJar.suspend(false);
		if((!s32.equals(s39)) || (!s33.equals(s39)) || (!s34.equals(s39)) ||
				(!s35.equals(s39)) || (!s36.equals(s39)) || (!s37.equals(s39)) ||
				(!s38.equals(s39)))
		{
			System.out.println("suspend(Boolean) works!");
		}
		else
		{
			System.out.println("suspend(Boolean) doesn't work!");
		}
		System.out.println();
		
		// Test save/load
		ChangeJar s40 = new ChangeJar(1,2,3,4);
		s40.save("testFile");
		ChangeJar s41 = new ChangeJar();
		s41.load("testFile");
		if(s40.equals(s41))
		{
			System.out.println("save and load work!");
		}
		else
		{
			System.out.println("save and load don't work!");
		}
		System.out.println();
	}

}