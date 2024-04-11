package plh201.proj2;

/*
 * This class represents a node in a P-R Quadtree data structure.
 * It stores information about a point in 2-Dimensional space,
 * as well as the limits of the region it covers.
 * 
 */

public class Qnode {

	int x,y;  		            // Coordinates of the point
	
	int xMin, xMax, yMin, yMax; // Limits of the region covered by the node
	
	Qnode NW, NE, SW, SE;       // References to the  NorthWest, NorthEast, SouthWest, SouthEast children nodes of this node respectively.
	
	
	boolean leaf; 				// Boolean flag indicating whether this node is a leaf node ( i.e., it has no children ) 
	boolean divided;			// Boolean flag indicating whether this node has been divided into children nodes. 
	
	
	public Qnode(int xmin, int ymin, int xmax, int ymax) {
		
		this.xMin = xmin;
		this.xMax = xmax;
		this.yMin = ymin;
		this.yMax = ymax;
		
		this.leaf = true;
	}
	
	
	/*
	 * Returns a string implementation of the node
	 * in (x,y) format.
	 */
	
	public String toString() {
		
		return "("+x+", "+y+")";
		
	}
	
	/*
	 * Getters and setters
	 */

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

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

	public int getxMin() {
		return xMin;
	}

	public void setxMin(int xMin) {
		this.xMin = xMin;
	}

	public int getxMax() {
		return xMax;
	}

	public void setxMax(int xMax) {
		this.xMax = xMax;
	}

	public int getyMin() {
		return yMin;
	}

	public void setyMin(int yMin) {
		this.yMin = yMin;
	}

	public int getyMax() {
		return yMax;
	}

	public void setyMax(int yMax) {
		this.yMax = yMax;
	}

	public Qnode getNW() {
		return NW;
	}

	public void setNW(Qnode nW) {
		NW = nW;
	}

	public Qnode getNE() {
		return NE;
	}

	public void setNE(Qnode nE) {
		NE = nE;
	}

	public Qnode getSW() {
		return SW;
	}

	public void setSW(Qnode sW) {
		SW = sW;
	}

	public Qnode getSE() {
		return SE;
	}

	public void setSE(Qnode sE) {
		SE = sE;
	}

	public boolean isdivided() {
		return divided;
	}

	public void setdivided(boolean divided) {
		this.divided = divided;
	}
}
