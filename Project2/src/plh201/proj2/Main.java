package plh201.proj2;

import java.util.Random;

public class Main {
	
	/*
	 * For each number of points we create random points (x,y) and inserts them in 
	 * both the K-D Tree and the P-R Quadtree.
	 * Then we search (for each number of points) 100 times with points that are not in the trees and
	 * 100 times with random points that are inside the trees.
	 * Then we compute the average depth of successful and unsuccessful searches in the 2 trees.
	 * 
	 */

	public static void main(String[] args) {

		int numOfPoints[] = {200, 500, 1000, 10000, 30000, 50000, 70000, 100000};
		int numOfSearches = 100;
		
		int min = 0;     // min and max of the boundary in the 2D space ( N X N )
		int max = 65535;
		
		for(int j = 0; j<numOfPoints.length; j++) {
			
			System.out.println("\n==========//==========");
			System.out.println("Number of Points: "+numOfPoints[j]);
		
			int[] randomX = new int[numOfPoints[j]];
			int[] randomY = new int[numOfPoints[j]];
		
			int[] randSearchX = new int[numOfSearches];
			int[] randSearchY = new int[numOfSearches];
		
			int[] SearchX = new int[numOfSearches];
			int[] SearchY = new int[numOfSearches];
			
		
			randomX = RandomGenerator.getRandomKey(min, max, numOfPoints[j]);
			randomY = RandomGenerator.getRandomKey(min, max, numOfPoints[j]);
		
			randSearchX = RandomGenerator.getRandomKey(min, max, numOfSearches);  //Random (x,y) witch are not in the Tree
			randSearchY = RandomGenerator.getRandomKey(min, max, numOfSearches);
		
		
			Random rand = new Random();
	        
			/*
			 * Select 100 random elements from the randomX array and add them to the SearchX array.
			 * We will make 100 searches with elements that are contained in the trees to see the successful searches (SearchX[], SearchY[] )
			 * and 100 searches with elements that are NOT contained in the trees to see unsuccessful searches ( randSearchX[], randSearchY[] )
			 * 
			 *  
			 */
	     
			for (int i = 0; i < 100; i++) {
				
				int randIndex = rand.nextInt(randomX.length);
				
				SearchX[i] = randomX[randIndex];
				SearchY[i] = randomY[randIndex];
			}
		
			/*
			 * Create new Trees
			 */
	    
			KDtree kdt = new KDtree();
			Qtree qtree =  new Qtree(min, min, max, max);
			
			
			/*
			 * Inserts random elements in the trees
			 */
			
			for(int i = 0; i < numOfPoints[j]; i++) {
			
				kdt.insertNode(randomX[i], randomY[i]);
				qtree.insert(randomX[i], randomY[i]);
			
			}
		
			
			/*
			 * Search in both trees.
			 */
		
			for(int i = 0; i<numOfSearches; i++) {
			
				kdt.searchWithDepth(SearchX[i], SearchY[i]);
				kdt.searchWithDepth(randSearchX[i], randSearchY[i]);
			
				qtree.searchWithDepth(SearchX[i], SearchY[i]);
				qtree.searchWithDepth(randSearchX[i], randSearchY[i]);
			
			}
			
			/*
			 * Print out the results
			 */
			
			System.out.println("//K-D Tree//");
			System.out.printf("Total depth of true searchs: %.2f\n", (double) kdt.getTotalDepthOfTrueSearches() / 100);
			System.out.printf("Total depth of false searches: %.2f\n", (double) kdt.getTotalDepthOfFalseSearches() / 100);
		
		
			System.out.println("");
			System.out.println("//P-R QuadTree//");
			System.out.printf("Total depth of true searches: %.2f\n", (double) qtree.getTotalDepthOfTrueSearches() / 100);
			System.out.printf("Total depth of false searches: %.2f\n", (double) qtree.getTotalDepthOfFalseSearches() / 100);

		}
	}		
}
