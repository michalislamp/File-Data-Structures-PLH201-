package plh201.proj1;

import java.io.IOException;


public class Main {
	
	/*
	 * For each number of records and each number of strings
	 * we call 3 search methods, searchA, searchB, searchC
	 * For each method we create a new file handler and measure both disk accesses and time
	 * 
	 * We just run the program and it prints Disk Accesses and Time for each number of records,
	 * each string size and each method.
	 * 
	 * Prints the total number of disk Acceses so to find the average, we divide by 1000 and keep one decimal.
	 * Print the total time of the search so to find the average, we divide by 1000 and keep one decimal.
	 */
	
	

	public static void main(String[] args) throws IOException {
		
		int recArr[] = {50,100,200,500,800,1000,2000,5000,10000,50000,100000,200000};
		int byteArr[] = {27,55};
		
		
		for(int j=0; j<byteArr.length; j++) {

			for(int i=0; i<recArr.length; i++) {
			
				System.out.println("==========//==========");
				
				Search s = new Search(recArr[i],byteArr[j]);
			
				FileHandler fhA = new FileHandler("test","index",byteArr[j]);
			
				long startTimeA = System.nanoTime();
				System.out.println("Disk Accesses for Search A and "+recArr[i]+" records of "+byteArr[j]+" bytes : "+ s.SearchA(fhA));

				long endTimeA = System.nanoTime();
				long elapsedTimeA = endTimeA - startTimeA;
				System.out.println("Elapsed time for Search A (in nanoseconds): " + elapsedTimeA);
			
				FileHandler fhB = new FileHandler("test","index",byteArr[j]);
			
				long startTimeB = System.nanoTime();
				System.out.println("Disk Accesses for Search B and "+recArr[i]+" records of "+byteArr[j]+" bytes : "+ s.SearchB(fhB));
			
				long endTimeB = System.nanoTime();
				long elapsedTimeB = endTimeB - startTimeB;
				System.out.println("Elapsed time for Search B (in nanoseconds): " + elapsedTimeB);
			
				FileHandler fhC = new FileHandler("test","index",byteArr[j]);
			
				long startTimeC = System.nanoTime();
				System.out.println("Disk Accesses for Search C and "+recArr[i]+" records of "+byteArr[j]+" bytes : "+ s.SearchC(fhC));
			
				long endTimeC = System.nanoTime();
				long elapsedTimeC = endTimeC - startTimeC;
				System.out.println("Elapsed time for Search C (in nanoseconds): " + elapsedTimeC);

			}
		}
		
		System.out.println("Proccess Finished");
		
	}		
}
