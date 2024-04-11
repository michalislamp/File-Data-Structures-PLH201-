package plh201.proj3;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


import plh201.proj3.util.StandardInputRead;

/*
 * This class is responsible for the utilities of the program.
 */


public class Functionality {
	
	BTree<String,LinkedList> btree;
	
	/*
	 * Constructor
	 */
	
	public Functionality(BTree<String,LinkedList> btree) {
		
		this.btree=btree;
		
	}
	
	/*
	 * Utility A.
	 * Asks for the file name, reads the file and
	 * insert data to the B+Tree. If we find an identical word
	 * we create a linked list and add the word in the list.
	 * 
	 * Add the word as the key of the b+tree and the linked list as the value.
	 */
	
	public void option1() {
		
		
		StandardInputRead read = new StandardInputRead();
		String fileName = read.readString("Enter file name: ");
		String[] words;

	
		int j = 0;
		
		/*
		 * Reading words seperated by " " (space)
		 */
		  try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
		        String line = reader.readLine();
		        if (line != null) {
		            words = line.split(" ");
		     
		            
		            for (String word : words) {
		            	
		            	/*
		            	 * For new words create a linked list.
		            	 */
						  if(btree.search(word)  == null )
						  {
									
							  LinkedList list = new LinkedList(); //Create linked list
							  list.addNode(fileName, j); 		  //add node
							  btree.insert(word, list);			  //insert in the tree the word(as the key) and the linked list (as the value)
							  
						/*
						 * Else adding the word in the linked list witch contains all the same words.	  
						 */
						  } else {
							  
				
							  btree.search(word).addNode(fileName, j);
							  
						  }
						  
						  j++;
			        }
		        }
		        
		        
		        
		    } catch (FileNotFoundException e) {
		    	
		    	System.out.println("File not found: " + e.getMessage());
		    	return;
		    
		    } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return;
			}
		  
		  System.out.println("Succes Insertion");
		
	}

	/*
	 * Utility 2
	 * Search for a word and print Accessed Nodes number in the Tree
	 * and how many keys has compared. 
	 */
	
	public void option2() {

		StandardInputRead read = new StandardInputRead();
		String word = read.readString("Enter word: ");
		
		if(btree.search(word)  == null ) {
			
			System.out.println("Word: "+word+" is not in the Tree");
			System.out.println("Nodes accesed: "+btree.getNodeCount());
			System.out.println("Keys compared: "+btree.getKeyCompCount());
			
		}else {
			
			btree.search(word).printAll();
			System.out.println("Nodes accesed: "+btree.getNodeCount());
			System.out.println("Keys compared: "+btree.getKeyCompCount());

			
		}
		System.out.println();
	}
	
	/*
	 * Utility 3
	 * Creating 2 trees ( for M = 10 and M = 20 ).
	 * Insert files "inputA.txt" and "inputB.txt" and perform
	 * 100 random searches with words that are contained in the tree (50 from 1st and 50 from 2nd)
	 * so as to find the average nodes acces and the average key comparisons. 
	 */
	
	public void option3() {
		
		for (int m = 10; m <= 20; m += 10) {
			
			BTree<String,LinkedList> btreeC = new BTree<String, LinkedList>(m);
			
			
			String[] randA = new String[50];			//random words from "inputA.txt"
			randA = this.insert("1.txt", btreeC);
			
			String[] randB = new String[50];			//random words from "inputB.txt"
			randB = this.insert("2.txt", btreeC);
			
			for( String word : randA ) {
				
				btreeC.search(word);
				
			}
			
			for( String word : randB ) {
				
				btreeC.search(word);
				
			}
			
			
			System.out.println("Average nodes accessed (M = "+m+"): "+String.format("%.1f", btreeC.getTotalNodeCount() / 100.0) );
			System.out.println("Average keys compared (M = "+m+"): "+String.format("%.1f", btreeC.getTotalKeyCompCount() / 100.0) );
			
		}

	}
	
	/*
	 * Same insert function as option A but modified to return
	 * 50 random words from the file.
	 * So as to perform  searches.
	 */
	
	public String[] insert(String fileName, BTree<String,LinkedList> btreeC) {
	
	String[] words;
	String[] randWords = new String[50];	
		
		int j = 0;
		
		  try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
		        String line = reader.readLine();
		        if (line != null) {
		            words = line.split(" ");
		     
		            
		            for (String word : words) {
						  
						  if(btreeC.search(word)  == null )
						  {
							  
							  //System.out.println("Word: "+word+" is a new word!, Position:"+j);
							  LinkedList list = new LinkedList();
							  list.addNode(fileName, j);
							  btreeC.insert(word, list);
							  
							  
						  } else {
							  
							  //System.out.println("Word: "+word+" is already in the tree, Position:"+j);
							  btreeC.search(word).addNode(fileName, j);
							  
						  }
						  
						  j++;		 
			        }
		            
		            /*
		             * Random choose 50 words from the file
		             * and returns them.
		             */
		           
		            
		            Random random = new Random();

		            for (int l = 0; l < 50; l++) {
		                int randomIndex = random.nextInt(words.length);
		                randWords[l] = words[randomIndex];
		            }

		        }
		        
		        
		        
		    } catch (FileNotFoundException e) {
		    	
		    	System.out.println("File not found: " + e.getMessage());
		    	return null;
		    
		    } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return null;
			}
		
		
		return randWords;
		
	}

}
