import java.io.File;
import java.util.Scanner;

/******************************************************************
Java program that will determine the smallest dominating set for
a graph of up to 24 nodes

@author Tony Bober
@version 1.0
 *****************************************************************/
public class DomSetFinder {

	public static Scanner fileReader;
	
	/** Recursive method to find smallest dominating set of nodes */
	public static int findSmallestSet(int[] matrix, int numNodes, int logicalSum, int level, int startPos){
		//Base case: logicalSum is all 1s
		if(logicalSum == Math.pow(2, numNodes) - 1)
			return level;
		
		int min = Integer.MAX_VALUE;	//Initialize min to a huge value
		int curr;
		
		//Logical OR each value with the logicalSum and recurse
		int newLogicalSum;
		for(int i = startPos; i < numNodes && level + 1 < min; i++){
			newLogicalSum = logicalSum | matrix[i];
			curr = findSmallestSet(matrix, numNodes, newLogicalSum, level+1, i+1);
			min = Math.min(curr, min);
		}
		
		return min;
	}
	
	/** Main method to read in file and store matrix dimensions */
	public static void main(String[] args){
		
		//Make sure one file was provided
		if(args.length != 1)
			throw new IllegalArgumentException("One file needed to run");
			
		String filename = args[0];	//Name of file containing data	
		int numNodes = 0;	//Number of nodes in the graph
		
		//Get number of nodes from file
		try{
			fileReader = new Scanner(new File(filename)); 
			numNodes = Integer.parseInt(fileReader.nextLine());		//First line of file = number of nodes
		} catch (Exception ex) {
			System.out.println(ex);
			System.exit(0);
		}
		
		//Make sure graph has <= 24 nodes
		if(numNodes < 0 || numNodes > 24){
			System.out.println("Error: Graph must contain 0-24 nodes.");
			System.exit(0);
		}
		
		int[] matrix = new int[numNodes];	//Array to store matrix. Each value is a decimal representation of that node's members
		int[] covered = new int[numNodes];	//1D array to store what node values are included in selected set
		
		for(int i = 0; i < numNodes; i++){
			covered[i] = 0;
		}
		
		//Attempt to read matrix from file
		String val;
		try {
			for(int i = 0; i < numNodes; i++){
					val = fileReader.nextLine();
					val = val.replaceAll("\\s+","");	//Eliminates all white spaces
					if(val.length() != numNodes)
						throw new Exception();	//Each value should be length numNodes
					matrix[i] = Integer.parseInt(val, 2);	//Parse the string val into decimal equivalent for that row
			}
			if(fileReader.hasNext()){
				throw new Exception();
			}
			fileReader.close();
		} catch (Exception ex) {
			System.out.println("Error in scanning. Check that matrix has correct number of nodes, and only uses values of 0 and 1.");
			System.exit(0);
		}
		
		//Find and print smallest dominating set
		int smallestSet = findSmallestSet(matrix, numNodes, 0, 0, 0);
		if(smallestSet > 24)
			System.out.println("No dominating set found. Check that your matrix is correct");
		else
			System.out.println("Minimum dominating set contains " + smallestSet + " nodes.");
		
	}
	
}
