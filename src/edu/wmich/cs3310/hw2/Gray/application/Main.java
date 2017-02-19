package edu.wmich.cs3310.hw2.Gray.application;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import edu.wmich.cs3310.hw2.Gray.sorts.Sort;

/**
 * This class deals with all user input, timing, testing, and generation for 
 * the required sort functions in Sort.
 * @author John Gray
 *
 */
public class Main {
	
	/**
	 * The start of the program.
	 * Gets user input, tests searches, and prints results.
	 * @param args Command line arguments
	 */
	public static void main(String[] args){
		//Initialize objects
		Scanner scnr = new Scanner(System.in);
			
		//Declare array/loop variables
		int n;
		float m;
		int loops;
				
				
		//Gets user input for array specifications
		m = getUserFloat("Enter upper bound of random numbers: ", scnr);
		n =	getUserInt("Enter how many random numbers to generate: ",scnr);
		loops = getUserInt("Enter how many times to test each function: ", scnr);
		
		//to avoid divide by 0 error
		if(loops<1)
			loops=1;
		
		//Declare timing variables
		Float[] inputArray;
		LinkedList<Float> inputList;
		long[] outputs = new long[12];
		long[] times = new long[12];
				
		/* Main loop. For each loop, it will generate a random array,
		   sort the array, and store the amount of time.
		*/
		for(int i  = 0; i < loops; i++){
			inputArray = randomSortedArray(n,m);
			inputList = arrayToLinked(inputArray);
			outputs = testSorts(outputs, inputArray, inputList, i); //retrieves all run times
			times = addArrays(times, outputs); //sums them all up into times
		}
		
		//Prints out the average times of each sort method.
		System.out.printf("\n\nArray bubble sort avg time: %d ns\n"
			+ "Linked bubble sort avg time: %d ns\n"
			+ "Array merge sort avg time: %d ns\n"
			+ "Linked merge sort avg time: %d ns\n"
			+ "Array quick sort avg time: %d ns\n"
			+ "Linked quick sort avg time: %d ns\n"
			+ "Array insertion sort avg time: %d ns\n"
			+ "Linked insertion sort avg time: %d ns\n"
			+ "Array java sort avg time: %d ns\n"
			+ "Linked java sort avg time: %d ns\n"
			+ "(Extra Credit) Array selection sort avg time: %d ns\n"
			+ "(Extra CrediT) Linked selection sort avg time: %d ns\n",
			times[0]/loops, times[1]/loops, times[2]/loops, //divides all sums by how many
			times[3]/loops, times[4]/loops, times[5]/loops, //loops it ran, giving an average time.
			times[6]/loops, times[7]/loops, times[8]/loops,
			times[9]/loops, times[10]/loops, times[11]/loops);	
		
		//my answers to required questions
		System.out.println("\nMy answers are: \na. Array \nb. LinkedList \nc. LinkedList");
		
		//close resources
		scnr.close();
	}
	
	/**
	 * I wanted to test each sort with the same data,
	 * so this method converts an array with random data to
	 * a linked list with the same random data.
	 * @param inputArray - A random array
	 * @return - A random linked list
	 */
	public static LinkedList<Float> arrayToLinked(Float[] inputArray) {
		LinkedList<Float> output = new LinkedList<Float>();
		for(Float x: inputArray){
			output.add(x);
		}
		return output;
	}

	/**
	 * Adds the times of each loop iteration into one
	 * total array. Used to take the average of the methods.
	 * @param times - holds total times of each method execution
	 * @param outputs - holds the time of one method execution
	 * @return returns outputs+time into time
	 */
	public static long[] addArrays(long[] times, long[] outputs) {
		for(int i = 0; i < times.length; i++){
			times[i] = times[i]+outputs[i];
		}
		return times;
	}

	/**
	 * The main test method. Tests each method, stores the time it took to
	 * execute into the output array. 
	 * 
	 * @param outputs the times of each method execution
	 * @param arrayHolder the unsorted array
	 * @param listHolder the unsorted linked list
	 * @param i Current loop iteration. Used to only print array contents once.
	 * @return returns the times of each method execution
	 */
	@SuppressWarnings("unchecked")
	private static long[] testSorts(long[] outputs, Float[] arrayHolder, LinkedList<Float> listHolder, int i) {
		
		//tests to see if it should print the array contents
		boolean printSteps = (arrayHolder.length<101);
		
		//initialize timing objects and variables
		Sort<Float> sort = new Sort<Float>();
		long begin, end;
 		Float[] array;
 		LinkedList<Float> list;
 		
 		//make copy of current data, so each method has to sort the same data.
 		array = Arrays.copyOf(arrayHolder, arrayHolder.length);
 		list = (LinkedList<Float>)listHolder.clone();
 		
 		//prints initial array contents (only once, only if n<=100)
 		if(printSteps&&i==0){
 			System.out.print("initial: ");
 			for(Float x: array){
 				System.out.printf("%.3f, ",x);
 			}
 		}
 		
 		//bubble sort array test
		begin = System.nanoTime();
		sort.arrayBubble(array);
		end = System.nanoTime();
		outputs[0] = end-begin;
		if(printSteps&&i==0){
			System.out.print("\nAfter array bubble sort: ");
			for(Float x: array){
				System.out.printf("%.3f, ",x);
			}
		}
 		array = Arrays.copyOf(arrayHolder, arrayHolder.length); //reset data
 		
 		//bubble sort linked list test
		begin = System.nanoTime();
		sort.linkedBubble(list);
		end = System.nanoTime();
		outputs[1] = end-begin;
 		if(printSteps&&i==0){
 	 		System.out.print("\nAfter linked bubble sort: ");
 	 		for(Float x: list){
 	 			System.out.printf("%.3f, ",x);
 	 		}
 	 	}
 		list = (LinkedList<Float>)listHolder.clone(); //reset data
		
 		//merge sort array test
		begin = System.nanoTime();
		sort.arrayMergeSort(array,0,array.length);
		end = System.nanoTime();
		outputs[2] = end-begin;
		if(printSteps&&i==0){
			System.out.print("\nAfter array merge sort: ");
			for(Float x: array){
				System.out.printf("%.3f, ",x);
			}
		}
 		array = Arrays.copyOf(arrayHolder, arrayHolder.length); //reset data
 		
		//merge sort linked list test
		begin = System.nanoTime();
		sort.linkedMergeSort(list,0,list.size());
		end = System.nanoTime();
		outputs[3] = end-begin;
		if(printSteps&&i==0){
			System.out.print("\nAfter linked merge sort: ");
			for(Float x: list){
				System.out.printf("%.3f, ",x);
			}
		}
 		list = (LinkedList<Float>)listHolder.clone(); //reset data

		//quick sort array test
		begin = System.nanoTime();
		sort.arrayQuick(array,0,array.length-1);
		end = System.nanoTime();
		outputs[4] = end-begin;
		if(printSteps&&i==0){
			System.out.print("\nAfter array quick sort: ");
			for(Float x: array){
				System.out.printf("%.3f, ",x);
			}
		}
 		array = Arrays.copyOf(arrayHolder, arrayHolder.length); //reset data

		//quick sort linked list test
		begin = System.nanoTime();
		sort.linkedQuick(list,0,list.size()-1);
		end = System.nanoTime();
		outputs[5] = end-begin;
		if(printSteps&&i==0){
			System.out.print("\nAfter linked quick sort: ");
			for(Float x: list){
				System.out.printf("%.3f, ",x);
			}
		}
 		list = (LinkedList<Float>)listHolder.clone(); //reset data

		//insertion sort array test
		begin = System.nanoTime();
		sort.arrayInsertion(array);
		end = System.nanoTime();
		outputs[6] = end-begin;
		if(printSteps&&i==0){
			System.out.print("\nAfter array insertion sort: ");
			for(Float x: array){
				System.out.printf("%.3f, ",x);
			}
		}
 		array = Arrays.copyOf(arrayHolder, arrayHolder.length); //reset data

		//insertion sort linked list test
		begin = System.nanoTime();
		sort.linkedInsertion(list);
		end = System.nanoTime();
		outputs[7] = end-begin;
		if(printSteps&&i==0){
			System.out.print("\nAfter linked insertion sort: ");
			for(Float x: list){
				System.out.printf("%.3f, ",x);
			}
		}
 		list = (LinkedList<Float>)listHolder.clone(); //reset data

		//build in sort array test
		begin = System.nanoTime();
		sort.arrayJava(array);
		end = System.nanoTime();
		outputs[8] = end-begin;
		if(printSteps&&i==0){
			System.out.print("\nAfter built in array sort: ");
			for(Float x: array){
				System.out.printf("%.3f, ",x);
			}
		}
 		array = Arrays.copyOf(arrayHolder, arrayHolder.length); //reset data

		//build in sort linked list test
		begin = System.nanoTime();
		sort.linkedJava(list);
		end = System.nanoTime();
		outputs[9] = end-begin;
		if(printSteps&&i==0){
			System.out.print("\nAfter built in linked sort: ");
			for(Float x: list){
				System.out.printf("%.3f, ",x);
			}
		}
 		list = (LinkedList<Float>)listHolder.clone(); //reset data

		//extra credit: selection sort array test
		begin = System.nanoTime();
		sort.arraySelection(array);
		end = System.nanoTime();
		outputs[10] = end-begin;
		if(printSteps&&i==0){
			System.out.print("\nAfter array selection sort: ");
			for(Float x: array){
				System.out.printf("%.3f, ",x);
			}
		}
 		//extra credit: selection sort linked list test
		begin = System.nanoTime();
		sort.linkedSelection(list);
		end = System.nanoTime();
		outputs[11] = end-begin;
		if(printSteps&&i==0){
			System.out.print("\nAfter linked selection sort: ");
			for(Float x: list){
				System.out.printf("%.3f, ",x);
			}
		}
		
		//Returns an array of all run times.
		return outputs;
	}

	/**
	 * When called, it will validate and accept user input.
	 * Only accepts a valid integer. Continues until it gets one.
	 * @param question A string containing the question to print.
	 * @param scnr A scanner object from main. 
	 * @return Returns valid user input to store in variable.
	 */
	public static int getUserInt(String question, Scanner scnr){
		//Asks user for what is to be inputed
		System.out.println(question);
		
		//Attempts to get input
		try{
			String input = scnr.nextLine();
			int output = Integer.parseInt(input.trim());
			return output;
		}catch(NumberFormatException e){		//if not valid, retry
			return getUserInt("Sorry. Please enter valid Integer: ",scnr);
		}
		
	}	
	
	/**
	 * When called, it will validate and accept user input.
	 * Only accepts a valid float. Continues until it gets one.
	 * @param question A string containing a question to print.
	 * @param scnr A scanner object from main. 
 	 * @return Returns valid user input to store in variable.
	 */
	public static float getUserFloat(String question, Scanner scnr){
		//Asks user for what is to be inputed
		System.out.println(question);
		
		
		//Attempts to get input
		try{
			String input = scnr.nextLine();
			float output = Float.parseFloat(input.trim());
			return output;
		}catch(NumberFormatException e){		//if not valid, retry
			return getUserFloat("Sorry. Please enter valid float: ", scnr);
		}
	}
	
	/**
	 * Produces an array filled with n random numbers of up to m value.
	 * @param n Length of array
	 * @param m Upper bound of possible generated numbers
	 * @return Returns the random array
	 */
	public static Float[] randomSortedArray(int n, float m){
		//try/catch in case user inputs too big of numbers
		try{ 
			
			//initialize objects
			Float[] randOutput = new Float[Math.abs(n)]; //Math.abs to eliminate negative array possibility
			Random rand = new Random();
		
			
			//fill array with n random numbers in range (1,M]
			for(int i = 0; i < n; i++){
				randOutput[i] = (rand.nextFloat()*(m-1)+1);
			}
			
			return randOutput;
			
		}catch(OutOfMemoryError e){
			System.out.println("Inputted Array size to big. Restart and try again.");
			System.exit(0);
		}
		return null;
	}
}