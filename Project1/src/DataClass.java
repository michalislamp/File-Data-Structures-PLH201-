package plh201.proj1;

import java.nio.ByteOrder;

/*
 *Data class represents the data we store and read from main file
 *Contains a key number and a string 
 *The pageOfRecord variable stores the disk page in witch the dataClass object is stored
 */


public class DataClass {
	 
	private int recordSize;				 //Record size (bytes of string + 4 bytes integer)
	private int key;		  	 		
	private int pageOfRecord;
	
	private String string;				
	
	public DataClass(int k, String s, int numOfStringChar) {
		this.key = k;
		this.string = s;
		this.recordSize = numOfStringChar+4;
	}
	
	
	/*
	 * Given function that merge the key with the string and returns a byte array
	 */
	
	public byte[] byteArray() {			
		
	    java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(recordSize); 				// allocate size of record(59 bytes)
	    bb.order(ByteOrder.BIG_ENDIAN); 												// ByteOrder.BIG_ENDIAN is the default
	    bb.putInt(this.key);   															//put key in buffer
		bb.put(this.string.getBytes(java.nio.charset.StandardCharsets.US_ASCII));
	    
		return bb.array();
	}
	
	
	/*
	 * Getters and setters 
	 */
	

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public int getRecordSize() {
		return recordSize;
	}


	public int getPageOfRecord() {
		return pageOfRecord;
	}


	public void setPageOfRecord(int pageOfRecord) {
		this.pageOfRecord = pageOfRecord;
	}
	
	
	

}
