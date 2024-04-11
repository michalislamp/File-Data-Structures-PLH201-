package plh201.proj3;

/*
 * Represents a linked list node.
 * The node stores two values. Position(integer) and File Name (string).
 * 
 */

public class LinkedListNode {
	
    private String fileName;
    private int position;
    private LinkedListNode nextNode;

    public LinkedListNode(String stringValue, int intValue) {
    	
        this.fileName = stringValue;
        this.position = intValue;
        this.nextNode = null;
        
    }

    public void addToEnd(String stringValue, int intValue) {
        LinkedListNode newNode = new LinkedListNode(stringValue, intValue);
        LinkedListNode current = this;

        while (current.nextNode != null) {
            current = current.nextNode;
        }

        current.nextNode = newNode;
    }
    
    
    /*
     * Getters and Setters
     */
    
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fn) {
        this.fileName = fn;
    }

    public int getPos() {
        return position;
    }

    public void setPos(int pos) {
        this.position = pos;
    }

    public LinkedListNode getNext() {
        return nextNode;
    }

    public void setNext(LinkedListNode next) {
        this.nextNode = next;
    }

 
}
