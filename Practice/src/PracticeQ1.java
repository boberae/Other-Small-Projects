import java.util.HashMap;
import java.util.Map;

public class PracticeQ1 {

	public static void main(String[] args){
		printPalindromes("aabaabccddcc");
		findSeeds(1716);
		lookAndSay(1);
		steppingNumber(4, 300);
		System.out.println(fib(7));
	}
	
	//Print all palindromes of size >= 3 in a given string
	public static void printPalindromes(String str){
		int length = str.length();
		while(length >= 3){
			for(int i = 0; i <= str.length() - length; i++){
				checkIfPalindrome(str.substring(i, i+length));
			}
		length--;
		}
	}
	
	public static void checkIfPalindrome(String str){
		StringBuilder str2 = new StringBuilder(str);
		str2.reverse();
		if(str.equals(str2.toString()))
			System.out.println(str);
	}
	
	//Find the seeds of a number (i.e. seed of 1716 is 143: 1 x 4 x 3 x 143 = 1716)
	public static void findSeeds(int num){
		
		for(int i = 1; i <= num/2; i++){
			int j = i;
			int sum = j;
			while(j != 0){
				sum *= j % 10;
				j /= 10;
			}
			if(sum == num)
				System.out.println(i);
		}
	}
	
	//Look and say
	//Start with a 1
	//We have "One 1": 11
	//We now have "Two 1s": 21
	//We now have "One 2, One 1": 1211
	//etc.
	public static void lookAndSay(int start){
		String oldValue = "" + start;
		for(int i = 0; i < 10; i++){
			StringBuilder newValue = new StringBuilder();
			int count = 1;
			for(int j = 0; j < oldValue.length(); j++){
				if(j < oldValue.length()-1 && oldValue.charAt(j) == oldValue.charAt(j+1)){
					count++;
				}
				else{
					newValue.append(count);
					newValue.append(oldValue.charAt(j));
					count = 1;
				}
			}
			oldValue = newValue.toString();
			System.out.println(oldValue);
		}
	}
	
	//Stepping number
	public static void steppingNumber(int start, int end){
		for(int i = start; i <= end; i++){
			if(steppingHelper(i)){
				System.out.println(i);
			}
			else{
				i+=7;	//If steppingHelper returns false, we know the next 7 will also be false
			}
		}
	}
	
	//Print all numbers between 0 and num where all side-by-side digits
	//must differ by no more than 1
	public static boolean steppingHelper(int num){
		int curr;
		int prev = num;
		while(prev / 10 != 0){
			curr = prev / 10;
			if(Math.abs(curr%10 - prev%10) > 1){
				return false;
			}
			prev = curr;
		}
		return true;
	}
	
	//Fibonacci recursion with a Hashmap implemented to make the algorithm less greedy
	public static int fib(int index){
		if(index <= 2)
			return 1;
		Map<Integer,Integer> stored = new HashMap<Integer, Integer>();
		stored.put(1,1);
		stored.put(2,1);	//Base Cases
		return fibonacci(index, stored);
	}
	
	public static int fibonacci(int index, Map<Integer, Integer> stored){
		if(stored.containsKey(index))
			return stored.get(index);
		
		stored.put(index-1, fibonacci(index-1, stored));
		stored.put(index-2, fibonacci(index-2, stored));
		int newVal = stored.get(index-1) + stored.get(index-2);
		stored.put(index,  newVal);
		return newVal;
	}
}
