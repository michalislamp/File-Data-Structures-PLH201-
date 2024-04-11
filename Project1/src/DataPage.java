package plh201.proj1;

import java.nio.ByteOrder;

/*
 * DataPage Class represents a pair of key and page number in witch the DataClass
 * object is stored to the main file
 * 
 * 
 */

public class DataPage implements Comparable<DataPage> {
	
	private final int recordSize = 8; 	//size of 2 integers  ( 4 + 4 )
	
	private int keyOfRec;
	private int pageNum;
	
	public DataPage(int k, int p) {
		
		this.keyOfRec = k;
		this.pageNum = p;
		
	}
	
	
	/*
	 * Given function that merge the key with the page number and returns a byte array
	 */
	
	public byte[] byteArray() {			
		
	    java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(recordSize); 			// allocate size of record(59 bytes)
	    bb.order(ByteOrder.BIG_ENDIAN); 										    // ByteOrder.BIG_ENDIAN is the default
	    bb.putInt(this.keyOfRec);  													//put key in buffer
	    bb.putInt(this.pageNum);   													//put key in buffer
		
	    
		return bb.array();
	}
	
	
	/*
	 * Getters and setters 
	 */
	

	public int getKeyOfRec() {
		return keyOfRec;
	}

	public void setKeyOfRec(int keyOfRec) {
		this.keyOfRec = keyOfRec;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getRecordSize() {
		return recordSize;
	}
	
	@Override
	public int compareTo(DataPage d) {
		
		return Integer.compare(this.keyOfRec, d.getKeyOfRec());
		
	}
	
	
}
