package plh201.proj3;

/*
 * The given code represents a basic implementation of a singly linked list in Java
 * It contains two methods. Add Node and Print all elements of the list
 * 
 */

public class LinkedList {
	
	private LinkedListNode head;
	
	public LinkedList() {
		
		
		
	}
	
	public void addNode(String fname, int pos) {
		
		if( head == null ) {
			
			this.head = new LinkedListNode(fname, pos);
			
		}else {
			
			head.addToEnd(fname, pos);
			
		}
	}
	
	
	public void printAll() {
		
		LinkedListNode curr = head;
		
		while (curr != null) {
            System.out.println("File Name: " + curr.getFileName() + ", Position: " + curr.getPos());
            curr = curr.getNext();
        }
		
	}
	

}
