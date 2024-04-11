package plh201.proj1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

/*
 * FileHandler class contains every function that has to do with
 * files. 
 * Also contains the search functions for given methods
 * 
 */


public class FileHandler {
	
	private int diskAcceses;
	
	private String fileName;
	private String indexFile;
	
	private final int pageSize = 256;
	private int recordSize;

	
	private RandomAccessFile raf;
	private int curRecord;					    //A pointer that shows the current record in the main file
	private int totalPages;

	private final int indexRecordSize = 8;     //size of 2 integers (4 + 4)
	
	private RandomAccessFile ind;
	private int curRecordInd;				   //A pointer that shows the current record in the index file
	private int totalPagesInd;
	
	public FileHandler(String fileName, String indexFileName,int stringSize) throws IOException {
		
		this.fileName = fileName;
		this.indexFile = indexFileName;

		this.recordSize = stringSize+4;							//Record size = string size + integer size(always 4 bytes)
				
			raf = new RandomAccessFile(fileName, "rw");
			ind = new RandomAccessFile(indexFile, "rw");
			
			this.diskAcceses = 0;                              //Set the number of disk accesses = 0			
			
			this.curRecord = 0;								   //Set up current page pointer = 0
			this.totalPages = 1;							   //Set up total pages counter = 1
			
			this.curRecordInd = 0;							   //Set up current page pointer = 0
			this.totalPagesInd = 1;							   //Set up total pages counter = 1
			
			
			
			raf.close();          								//close
			ind.close();
			
		}
		
	
	/*
	 *Open  both files 
	 */
	
	public void openFile() {
		
		try {
			raf = new RandomAccessFile(fileName, "rw");
			ind = new RandomAccessFile(indexFile, "rw");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Close both files
	 */
	
	public void closeFile() {
		
		try {
			raf.close();
			ind.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Write data to main file
	 * 
	 */
	
	public void writeBlockFile( byte[] data, DataClass d ) {
		
		try {
				
			if ( curRecord + recordSize < totalPages*pageSize) {
					
					raf.seek(curRecord);										//Set file pointer to the right position
					raf.write(data);
					curRecord = curRecord+recordSize;							//Increase current record pointer 
					
					d.setPageOfRecord(totalPages);    							//Set the page number in witch the current record is stored to the main file
		    }else {
					
		    		curRecord = curRecord + pageSize - (pageSize/recordSize) * recordSize;  //Increase current record pointer
		    																				//when we reach the end of the page 
		    																				//so we need to write at the next one
					totalPages++;															//and increase totalPage counter
					raf.seek(curRecord);
					raf.write(data);
					curRecord = curRecord+recordSize;	
					
					d.setPageOfRecord(totalPages);								//Set the page number in witch the current record is stored to the main file
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	
	
	
	/*
	 * Write data to index file
	 * 
	 */
		
	public void writeBlockIndex(  byte[] data ) {
		
		try {
				
			if ( curRecordInd + indexRecordSize <= totalPagesInd*pageSize) {
					
					ind.seek(curRecordInd);												//Set file pointer to the right position
					ind.write(data);
					curRecordInd = curRecordInd+indexRecordSize;						//Increase current record pointer 
					
		    }else {																		//In every index file page is stored 32 records
																						//Then increase total page counter
					totalPagesInd++;
					ind.seek(curRecordInd);												//Set file pointer to the right position
					ind.write(data);
					curRecordInd = curRecordInd+indexRecordSize;			
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	
	
	
	/*
	 * Search for record using Method A (serial search main file )
	 * Also converts bytes to DataClass objects
	 * @param key of the record we search for
	 * @throws IOException
	 * 
	 */
	
	public void FindRecordA(int key) throws IOException {
	
		for(int pageNum =1;pageNum<=totalPages;pageNum++) {			//Read every page
		
		diskAcceses++;												//Every time we read a page increase disk accesses by 1
			
			for(int i=0; i<pageSize-recordSize; i = i+recordSize) {	//Read all records from each page and converts them from bytes to DataClass objects
				
				int j =((pageNum-1)*pageSize)+i;
				
				if(j<curRecord) {
					
				try {
					
				
					byte[] data = new byte[pageSize];
					raf.seek(j);
					raf.read(data);
					
					ByteBuffer bb = ByteBuffer.wrap(data);
					int someInt = bb.getInt();
					
					if( key == someInt) {							//If we find the given key returns;
						return;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}		
				}				
			}
		}
	}
	
	
	/*
	 * A function that search index file an returns the page
	 * in witch the record is stored in main file
	 * @param key of the record we search for
	 * @return the page number in witch the record is stored
	 */
	
	
	
	public int FindIndexFilePage(int key) {
		
		
		for(int pageNum =1;pageNum<=totalPagesInd;pageNum++) {			//Read every index file page
			
		diskAcceses++;   												//Every time we read a page increase disk accesses by 1
	
			
			for(int i=0; i<pageSize; i = i+indexRecordSize) {			//Read all records for each page until we find the one with the given key
																		//Also converts byte to DataPage object
				int j =((pageNum-1)*pageSize)+i;
				
				if(j<curRecordInd) {
					
				try {

					byte[] data = new byte[pageSize];
					ind.seek(j);
					ind.read(data);
					
					ByteBuffer bb1 = ByteBuffer.wrap(data);
					int someInt1 = bb1.getInt();
					
					ind.seek(j+4);
					ind.read(data);
					
					ByteBuffer bb2 = ByteBuffer.wrap(data);
					int someInt2 = bb2.getInt();

					if( key == someInt1 ) {								//If we find the given key returns the page number ;
						return someInt2;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}    //read page 
							}
			
						}
					}
					return 0;										 //Else returns 0
				}
		
	
	/*
	 * A function that reads the current page
	 * from main file and search for the DataClass object of the given key number
	 * @param key of the record we search for
	 * @param page of the main file in witch the record is stored
	 */
	
	public void SearchUsingIndexFile(int pageNum, int key) {
		
		if( pageNum>0 && pageNum<=totalPages ) {
			
		diskAcceses++;   												//Every time we read a page increase disk acceses by 1

			
			for(int i=0; i<pageSize-recordSize; i = i+recordSize) {		//Read all page
				
				int j =((pageNum-1)*pageSize)+i;						//Start from given page number
				
				if(j<curRecord) {
					
				try {

					byte[] data = new byte[pageSize];
					raf.seek(j);
					raf.read(data);
					
					ByteBuffer bb = ByteBuffer.wrap(data);
					int someInt = bb.getInt();
						
					if( key == someInt) {								//If we find the given key returns;
						return;		
						}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					} 
				}
			}
		}
	}
	
	/*
	 * A function that reads an index file page 
	 * and converts bytes to DataPage array
	 * @param page number
	 * @return array of data page objects
	 */
	
	
	public DataPage[] readIndexPage(int pageNum) {
		
		diskAcceses++;   												//Every time we read a page increase disk acceses by 1
		
		int counter = 0;
		DataPage[] tempData = new DataPage[32];
		
		for(int i=0; i<pageSize; i = i+indexRecordSize) {
			
			int j =((pageNum-1)*pageSize)+i;
			
			if( (j<curRecordInd) && (counter<32) ) {
				
			try {

				byte[] data = new byte[pageSize];
				ind.seek(j);
				ind.read(data);
				
				ByteBuffer bb1 = ByteBuffer.wrap(data);
				int someInt1 = bb1.getInt();
				
				ind.seek(j+4);
				ind.read(data);
				
				ByteBuffer bb2 = ByteBuffer.wrap(data);
				int someInt2 = bb2.getInt();
				
				tempData[counter] = new DataPage(someInt1, someInt2);
				
				counter++;
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    //read page 
			
						}
		
					}
			DataPage[] readyData = new DataPage[counter];
			for(int i = 0; i<counter; i++) {
				
				readyData[i] = tempData[i];
			}
				return readyData;
			}
	
	/*
	 * An implementation of binary search algorithm
	 * We read the mid page of the sorted index page file
	 * If the key is in this page, returns
	 * If the key is greater than the greatest number of the current page,
	 * we read the right half of index file total pages
	 * If the key is less that the least number of the current page,
	 * we read the left half of index file total pages
	 * If we find the key we search for, call SearchUsingIndexFile function to find the record.
	 * 
	 * @param key we use for the search
	 * @param page in witch we search for ( we start from total pages / 2 )
	 * @param the number the function has called itself so as to avoid infinite loops
	 */
	
	
	
	public void binarySearch(int key, int totalP, int numOfBinarySearchCalls) {
		numOfBinarySearchCalls++;
		
		if( numOfBinarySearchCalls > totalPagesInd/2 ) {			//if the binary search method is called more times than the total number
																	//of index file pages / 2, return. It means the function is stacked in an infinite loop
			return;
		}
			
		if(totalP > totalPagesInd ) {								//If the number of page in witch we search for, is greater than 
																	//total page number return to avoid null pointer exception
			return;
		}
		
		
		DataPage[] tempData = new DataPage[32];
		
		if(totalP==0 || totalP==1) {								//If page number = 0 or 1, read first page and return;
			
			
			tempData = readIndexPage(1);
			
			for(int i=0;i<tempData.length;i++) {
			
				if(tempData[i].getKeyOfRec() == key) {
					
					SearchUsingIndexFile(tempData[i].getPageNum(), key);
					return;
				}
			}

			return;

		}else {														//Else read page
			
			tempData = readIndexPage(totalP);
			
			for(int i=0;i<tempData.length;i++) {
				
				if(tempData[i].getKeyOfRec() == key) {
				
					SearchUsingIndexFile(tempData[i].getPageNum(), key);
					return;
				
				}
			
			}
				
			if( key > tempData[tempData.length-1].getKeyOfRec()) {					//If the key is greater than the greatest number of the current page,
				 																	//we read the right half of index file total pages
				binarySearch(key, totalP + totalP/2, numOfBinarySearchCalls++);
				
			}else if( key < tempData[0].getKeyOfRec() ) {							//If the key is less that the least number of the current page,
				 																	// we read the left half of index file total pages
				binarySearch(key, totalP - totalP/2,numOfBinarySearchCalls++);
				
			}else {

				return;
				
			}
			
			
		}
		
	}
	

	/*
	 * Getters and setters
	 * 
	 */
	
	
	
	public void setDiskAcceses(int diskAcceses) {
		this.diskAcceses = diskAcceses;
	}
	
	public int getCurRecord() {
		return curRecord;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getCurRecordInd() {
		return curRecordInd;
	}


	public int getTotalPagesInd() {
		return totalPagesInd;
	}


	public int getDiskAcceses() {
		return diskAcceses;
	}
	
}
