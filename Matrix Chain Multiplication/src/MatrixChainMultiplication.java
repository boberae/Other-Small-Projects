import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/******************************************************************
Java program that will determine the least expensive order to
multiply n matrices 

@author Tony Bober
@version 1.0
 *****************************************************************/
public class MatrixChainMultiplication {

	// Array to store outer-most parenthesis locations
	public static int[] parenLocations;
	public static StringBuilder[] optimal;


	/** Recursive method to find optimal cost */
	public static int FindMin(int rows[], int cols[], int start, int end){

		if(start == end)
			return 0;	//0 instructions for 1 matrix

		int min = Integer.MAX_VALUE;	//Initially set min to a huge value
		int middle = start;		//Middle variable to store optimal parenthesis placement

		//Place parenthesis at various places, recurse
		for(int i = start; i < end; i++){
			int count = FindMin(rows, cols, start, i) +
					FindMin(rows, cols, i+1, end) +
					rows[start]*cols[i]*cols[end];

			if(count < min){
				min = count;
				middle = i;
			}
		}

		//Store optimal parenthesis locations in array
		parenLocations[0] = start;
		parenLocations[1] = middle;
		parenLocations[2] = middle + 1;
		parenLocations[3] = end;

		return min;
	}


	/** Method for placing parenthesis in the string */
	public static void placeParenthesis(){

		if(parenLocations[0] != parenLocations[1]){
			optimal[parenLocations[0]].insert(0, '(');
			optimal[parenLocations[1]].append(')');
		}
		if(parenLocations[2] != parenLocations[3]){
			optimal[parenLocations[2]].insert(0, '(');
			optimal[parenLocations[3]].append(')');
		}

	}


	/** Main method to read in file and store matrix dimensions */
	public static void main(String[] args){

		//Make sure one file was provided
		if(args.length != 1)
			throw new IllegalArgumentException("One file needed to run");


		//Create ArrayList to store data from file
		String filename = args[0];
		ArrayList<Integer> data = new ArrayList<Integer>(); 


		//Attempt to read data from file
		try {
			Scanner fileReader = new Scanner(new File(filename)); 
			String line;
			while (fileReader.hasNextLine()){
				line = fileReader.nextLine();
				data.add(Integer.parseInt(line));
			}
			fileReader.close();
		} catch (Exception ex) {
			System.out.println(ex);
			System.out.println("Error in loading " + filename);
		}


		//Check if number of matrices is valid
		int numMatrices = data.get(0)-1;
		if(numMatrices != data.size()-2){
			throw new IllegalArgumentException("Row/column data provided doesn't match"
					+ " specified number of matrices.");
		}
		if(numMatrices < 2 || numMatrices > 10){
			throw new IllegalArgumentException("Must have between 2 and 10 matrices");
		}


		//Move ArrayList data into separate arrays for rows and columns
		//Also, start working on string to display optimal solution
		int[] rows = new int[numMatrices];
		int[] cols = new int[numMatrices];				
		optimal = new StringBuilder[numMatrices];
		System.out.println("Matrix sizes:");
		for(int i = 1; i <= numMatrices; i++){
			rows[i-1] = data.get(i);
			cols[i-1] = data.get(i+1);
			System.out.println("" + rows[i-1] + " x " + cols[i-1]);
			optimal[i-1] = new StringBuilder("Matrix" + i);
		}
		System.out.println("");


		//Array to contain first, middle, last parenthesis locations
		parenLocations = new int[4];


		// Send recursive method "FindMin" rows, cols, start point, length-1
		// This method also stores location of first set of parenthesis
		int optimalCost = FindMin(rows, cols, 0, numMatrices-1);
		System.out.println("Optimal cost: " + optimalCost + "\n");


		// Place the parenthesis by the proper matrices
		placeParenthesis();


		// Create ArrayList to store parenthesis locations
		ArrayList<Integer> parenthesis = new ArrayList<Integer>();
		for(int i = 0; i < parenLocations.length; i++){
			parenthesis.add(parenLocations[i]);
		}		


		// Repeatedly call the method "FindMin" until all necessary parenthesis are placed
		for(int i = 0; i < parenthesis.size()-1; i++){
			if(parenthesis.get(i+1) - parenthesis.get(i) > 1){
				// If there's a gap, call FindMin on that section
				// Then add the new parenLocations to the parenthesis ArrayList
				int temp = FindMin(rows, cols, parenthesis.get(i), parenthesis.get(i+1));
				for(int j = 0; j < parenLocations.length; j++){
					parenthesis.add(parenLocations[j]);
				}
				// Place the parenthesis by the proper matrices in the strings
				placeParenthesis();
				//Sort parenthesis ArrayList so we know where the remaining gaps are
				Collections.sort(parenthesis);
			}
		}


		//Print optimal string
		System.out.print("Optimal Order: ");
		for(int i = 0; i < numMatrices-1; i++){
			System.out.print(optimal[i] + "*");		//Print each matrix and a '*'	
		}
		System.out.print(optimal[numMatrices-1]);	//Print final matrix without a '*'

	}

}



