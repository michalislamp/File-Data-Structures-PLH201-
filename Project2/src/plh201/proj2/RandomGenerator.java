package plh201.proj2;

/*
 * A Class that contains 3 methods for generating random integer and random strings
 * The implementation of the methods is given.
 */

public class RandomGenerator {
	 
	 /*
	  * function to generate a random string of length n
	  * @param Strig size
	  * @return String
	  */
	
	 public static String getAlphaNumericString(int stringSize) {
	 
	  // choose a Character random from this String
	  String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	         + "0123456789"
	         + "abcdefghijklmnopqrstuvxyz";
	 
	  // create StringBuffer size of AlphaNumericString
	  StringBuilder sb = new StringBuilder();
	 
	  for (int i = 0; i < stringSize; i++) {
	 
	   // generate a random number between
	   // 0 to AlphaNumericString variable length
	   int index = (int)(AlphaNumericString.length() * Math.random());
	 
	   // add Character one by one in end of sb
	   sb.append(AlphaNumericString.charAt(index));
	  }
	 
	  return sb.toString();
	 }
	 
	 /*
	  * Function that generates unique random integer array
	  * @param minimum number
	  * @param max number
	  * @param lenth of the array
	  * @return int array
	  */
	 
	 public static int[] getUniqueRandomKey(int min,int max, int numberOfNumbers) {
		 
		 java.util.Random randomGenerator = new java.util.Random();
		 int[] randomInts = randomGenerator.ints(min,max+1).distinct().limit(numberOfNumbers).toArray();
		 
		 return randomInts;
	 }
	 
	 /*
	  * Function that generates random integer array
	  * @param minimum number
	  * @param max number
	  * @param lenth of the array
	  * @return int array
	  */
	 public static int[] getRandomKey(int min,int max, int numberOfNumbers) {
		 
		 java.util.Random randomGenerator = new java.util.Random();
		 int[] randomInts = randomGenerator.ints(min,max+1).limit(numberOfNumbers).toArray();
	
		 return randomInts;
	 
	}
}


	