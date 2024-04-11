package plh201.proj2;

/*
 * This class represents the a node in a 2-Dimensional 
 * K-D Tree. Each node has 2 coordinates (x,y) and 2
 * child nodes ,leftChild and rightChild
 * 
 */


public class KDnode {
	
	private int x, y;
	private KDnode leftChild, rightChild;
	
	public KDnode(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	
	/*
	 * Returns a string implementation of the node
	 * in (x,y) format.
	 */
	
	public String toString() {
		
		return "("+x+", "+y+")";
		
	}

	
	/*
	 * Getters and Setters
	 */
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public KDnode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(KDnode leftChild) {
		this.leftChild = leftChild;
	}

	public KDnode getRightChild() {
		return rightChild;
	}

	public void setRightChild(KDnode rightChild) {
		this.rightChild = rightChild;
	}
	
	
}
