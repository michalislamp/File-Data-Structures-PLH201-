package plh201.proj3;


import plh201.proj3.util.StandardInputRead;
/*
 * Main class, responsible for the menu's utilities 
 * For reading from the user we use StandardInputRead package.
 */

public class Main {

	public static void main(String[] args) {
		
		/*
		 * Creating a b+tree ( M = 5 ) used in the first two utilities of the program
		 * and a functionality object.
		 */
		BTree<String,LinkedList> btree = new BTree<String, LinkedList>(5);
		Functionality f = new Functionality(btree);
		
		
		StandardInputRead reader = new StandardInputRead();
		int choice = 0;
		
			while (choice != 4) {
		        printMenu();
				
		        choice = reader.readPositiveInt("Enter your choice: ");
		      

		        switch (choice) {
		        	case 1:
		        		System.out.println();
		                f.option1();
		                break;
		            case 2:
		            	System.out.println();
		            	f.option2();
		            	break;
		            case 3:
		            	System.out.println();
		            	f.option3();
		            	break;
		            case 4:
		            	System.out.println("Exiting...");
		            	break;
		            default:
		            	System.out.println("Invalid choice. Please try again.");
		            	break;
		            }

		            System.out.println(); // Print a blank line for separation
		        }

	}
	/*
	 * Printing the menu in the console
	 */
	
	public static void printMenu() {
        System.out.println("                 	DATA BASE");
        System.out.println("==============================================================");
        System.out.println("1. Read file & and tree insertion.............................");
        System.out.println("2. Search for word............................................");
        System.out.println("3. Perform 100 searches.......................................");
        System.out.println("4. Exit......................................................."); 
        System.out.println("==============================================================");
    }

}
