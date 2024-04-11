package plh201.proj1;

import java.io.IOException;
import java.util.Arrays;

/*
 * Search class creates DataPage and DataClass objects and initialize them.
 * Also calls search methods.  
 *  
 */

public class Search{
	
	private int numOfStringChar;						//Number of characters the string has
	
	private final int numOfKeys = 1000;					//Number of random search keys
	private int numOfRecords;
	private int min = 1;								//Minimum key number
	private int max;									//Maximum key number
	
	private DataClass[] dataArray;						
	private DataPage[] dataPageArray;
	
	private int[] randomNum;
	private int[] randomKeys;
	
	public Search(int numOfRecords, int numOfStringChar ){		//Initialization of DataClass and DataPage objects
		
		
		this.numOfRecords = numOfRecords;
		this.numOfStringChar = numOfStringChar;
		
		this.max = numOfRecords*2;
		
		this.dataArray = new DataClass[numOfRecords];
		this.randomNum =new int[numOfRecords];
		this.randomKeys = new int[numOfKeys];
	
		this.dataPageArray = new DataPage[numOfRecords];
	
		this.randomNum =  RandomGenerator.getUniqueRandomKey(min, max, numOfRecords);
		
		if( this.numOfRecords<=1000) {
			
			this.randomKeys = RandomGenerator.getRandomKey(min, max, numOfKeys);
			
		}else {
			
			this.randomKeys = RandomGenerator.getUniqueRandomKey(min, max, numOfKeys);
			
		}
	}
	
	/*
	 * Open file, write DataClass array to disk 
	 * and search using method A and 1000 random keys / close file.
	 * @param FileHandler fh
	 * @return DiskAccesses for search A
	 * @throws IOException
	 */
	
	
	public int SearchA(FileHandler fh) throws IOException {			
		
		fh.openFile();
		
		for( int i=0; i<numOfRecords; i++) {
			
			dataArray[i] = new DataClass(randomNum[i], RandomGenerator.getAlphaNumericString(numOfStringChar), numOfStringChar);			
			fh.writeBlockFile(dataArray[i].byteArray(), dataArray[i]);
			
		}
		
		for(int j=0; j<numOfKeys; j++) {
			
			fh.FindRecordA(randomKeys[j]);
			
		}
		
		fh.closeFile();
		return fh.getDiskAcceses();
		
	}
	
	
	/*
	 * Open file, write DataClass and DataPage arrays to disk 
	 * and search using method B and 1000 random keys / close file.
	 * @param FileHandler fh
	 * @return DiskAccesses for search B
	 * @throws IOException
	 */
	
	
	public int SearchB(FileHandler fh) {
		
		fh.openFile();
		
		for( int i=0; i<numOfRecords; i++) {
			
			
			dataArray[i] = new DataClass(randomNum[i], RandomGenerator.getAlphaNumericString(numOfStringChar), numOfStringChar);
			
			fh.writeBlockFile(dataArray[i].byteArray(),dataArray[i]);
			
			dataPageArray[i] =  new DataPage(dataArray[i].getKey(), dataArray[i].getPageOfRecord());
			
			fh.writeBlockIndex(dataPageArray[i].byteArray());
			
			
		}
				
		for(int j=0; j<numOfKeys; j++) {
			
			fh.SearchUsingIndexFile(fh.FindIndexFilePage(randomKeys[j]), randomKeys[j]);
			
		}

		fh.closeFile();	
		return fh.getDiskAcceses();

	}
	
	
	/*
	 * Open file, write DataClass and DataPage arrays to disk, sort DataPage array, 
	 * and search using method C and 1000 random keys / close file.
	 * @param FileHandler fh
	 * @return DiskAccesses for search C
	 * @throws IOException
	 */
	
	
	
	public int SearchC(FileHandler fh) {
		
		fh.openFile();
		
		for( int i=0; i<numOfRecords; i++) {
			
			dataArray[i] = new DataClass(randomNum[i], RandomGenerator.getAlphaNumericString(numOfStringChar), numOfStringChar);
			
			fh.writeBlockFile(dataArray[i].byteArray(),dataArray[i]);
			
			
			dataPageArray[i] =  new DataPage(dataArray[i].getKey(), dataArray[i].getPageOfRecord());
			
		}
		
		Arrays.sort(dataPageArray);										//Sort DataPage array.

		for( int i=0; i<numOfRecords; i++) {
			
			fh.writeBlockIndex(dataPageArray[i].byteArray());
			
		}
				
		for(int j=0; j<numOfKeys; j++) {
			
			fh.binarySearch(randomKeys[j],fh.getTotalPagesInd()/2,0);
			
		}
	
		fh.closeFile();		
		return fh.getDiskAcceses();
		
	}
	
	
	/*
	 * Getters and setters
	 */
	


	public int getNumOfRecords() {
		return numOfRecords;
	}


	public int getMin() {
		return min;
	}


	public int getMax() {
		return max;
	}
	
	
	
	
	
	
	
}