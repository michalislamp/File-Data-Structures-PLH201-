package plh201.proj2;

/*
 * QTree class is an implementation of the P-R Quadtree data structure.
 * It contains insert and search functions.
 */

public class Qtree {

	private Qnode root;												// Root of the tree
		
	private int totalDepthOfTrueSearches;							// Total depth of all true searches in the tree
	private int totalDepthOfFalseSearches;							// Total depth of all false searches in the tree
	
	/*
	 * Constructor. 
	 * The parameters of the constructor represent the minimum and maximum values for the 'x' and 'y'
	 * coordinates of the coordinate plane. 
	 * 
	 * In every internal node of the Quadtree, we set 'x' and 'y' coordinates = -1.
	 * ( this is because in the Quadtree, by definition, points are inserted only
	 * in leaf nodes ) 
	 */
	
	public Qtree( int xmin, int ymin, int xmax, int ymax ) {
		
		root = new Qnode(xmin, ymin, xmax, ymax);					// Initializes root node and setLeaf boolean flag = false 
		root.setLeaf(false);										// (root node is not a leaf node)
		
		root.setX(-1);												// In every internal node of the Quadtree
		root.setY(-1);												// We set 'x' and 'y' coordinates = -1.
		
		this.totalDepthOfFalseSearches = 0;  
		this.totalDepthOfTrueSearches = 0;
		
	}
	
	/*
	 * Wrapper method for inserting node in the P-R Quadtree,
	 * using only parameters 'x' and 'y'.
	 */
	
	public void insert( int x, int y ) {
		
		insert(root, x, y);
		
	}
	
	/**
	 * Method that recursively inserts a point into the Quadtree.It takes 4 arguments
	 * @param node represents the root of the current subtree.
	 * @param x, @param y represents the coordinates of the point to be inserted.
	 * 
	 * The "insert" method first checks if the node already exists in the Quadtree.
	 * If it does, then the method simply returns. Otherwise, it checks if the point is
	 * within the bounds of the current node and if the node is a leaf node. 
	 * If both of these conditions are true, then the point is inserted into the 
	 * current node and the method returns.
	 * 
	 * If the node is not a leaf node or the point is not within the bounds of the 
	 * current node, then the method checks if the current node has been divided into 
	 * sub-nodes. If the node has not been divided, then it is divided into four sub-nodes 
	 * and the "divided" variable of the node is set to true. If the node has been divided, 
	 * then the method checks which sub-node the point falls into and recursively calls the 
	 * "insert" method on that sub-node.
	 */
	
	
	public void insert( Qnode node, int x, int y) {
		
		if( node.getX()==x && node.getY()==y) {				//If node already exists
			
			return;
			
		}
		
		
		
		if( (inBounds(x, y, node)==true) && (node.isLeaf()==true) ) {
			
			node.setX(x);
			node.setY(y);
			
			node.setLeaf(false);
			
			return;
			
		} else {
			
			if( (node.isdivided()==false) && (inBounds(x, y, node)==true) ) {
				
				this.divideRect(node);
				node.setdivided(true);
				
				if( (node.getX()!= -1) && (node.getY()!= -1) && (node.isLeaf()==false) ) {
					
					
					if( inBounds(node.getX(), node.getY(), node.getNW()) ) {
						
						node.getNW().setX(node.getX());;
						node.getNW().setY(node.getY());;
						
						node.setX(-1);
						node.setY(-1);
						
						node.getNW().setLeaf(false);
						
					} else if( inBounds(node.getX(), node.getY(), node.getNE()) ) {
						
						node.getNE().setX(node.getX());;
						node.getNE().setY(node.getY());;
						
						node.setX(-1);
						node.setY(-1);
						
						node.getNE().setLeaf(false);
						
					} else if( inBounds(node.getX(), node.getY(), node.getSW()) ) {
						
						node.getSW().setX(node.getX());;
						node.getSW().setY(node.getY());;
						
						node.setX(-1);
						node.setY(-1);
						
						node.getSW().setLeaf(false);
						
					} else if( inBounds(node.getX(), node.getY(), node.getSE()) ) {
						
						node.getSE().setX(node.getX());;
						node.getSE().setY(node.getY());;
						
						node.setX(-1);
						node.setY(-1);
						
						node.getSE().setLeaf(false);
						
					} 
				}
			}
		}
		
		if( inBounds(x, y, node.getNW()) ) {
			
			insert(node.getNW(), x, y);
			
		} else if( inBounds(x, y, node.getNE()) ) {
			
			insert(node.getNE(), x, y);
			
		}else if( inBounds(x, y, node.getSW()) ) {
			
			insert(node.getSW(), x, y);
			
		} else if( inBounds(x, y, node.getSE()) ) {
		
			insert(node.getSE(), x, y);
			
		} 

		return;
	}
	
	
	/*
	 * Wrapper method for inserting node in the P-R Quadtree,3
	 * using only parameters 'x' and 'y'.
	 */
	
	public int searchWithDepth( int x, int y) {
		
		return searchWithDepth(root, x, y, 0);
		
	}
	
	/**
	 * Method that recursively searches for a point in the P-R Quadtree.
	 * 
	 * @param node represents the root node of the current subtree.
	 * @param x, @param y represents the coordinates of the point to be searched for.
	 * @param depth represents the current depth of the recursion.
	 * 
	 * @return the depth of the node where the point is found or the depth where the search ends without
	 * finding the point.
	 * 
	 * If the point is found, increase 'totalDepthOfTrueSearches' by the depth of the current search.
	 * If not, increase 'totalDepthOfFalseSearches' by the depth of the current search.
	 * These variables are used to compute the average depth of successful and unsuccessful searches in the tree.
	 * 
	 * The method first checks if the node being searched is within the bounds of the current node
	 * and if its 'x' and 'y' values match. If this is true, returns the current depth.
	 * 
	 * If the current node is not the searched node, the method recursively checks each of the four  
	 * child nodes, in a depth-first manner, until it finds the searched node. If none of the child  
	 * nodes contain the searched node, returns the current depth.
	 */
	
	public int searchWithDepth( Qnode node, int x, int y, int depth ) {
		
		if( (inBounds(x, y, node)==true) && (node.getX()==x) && (node.getY()==y) ) {
			
			totalDepthOfTrueSearches = totalDepthOfTrueSearches+depth;					// Increase 'totalDepthOfTrueSearches'
			return depth;																// Point found.
			
		}
		
		if( (node.getNW()!=null) && (inBounds(x, y, node.getNW())) ) { 
			
			return searchWithDepth(node.getNW() ,x ,y, depth+1);
			
		} else if( (node.getNE()!=null) && (inBounds(x, y, node.getNE())) ) {
			
			return searchWithDepth(node.getNE() ,x ,y, depth+1);
			
		} else if( (node.getSW()!=null) && (inBounds(x, y, node.getSW())) ) {
			
			return searchWithDepth(node.getSW() ,x ,y, depth+1);
			
		} else if( (node.getSE()!=null) && (inBounds(x, y, node.getSE())) ) {
			
			return searchWithDepth(node.getSE() ,x ,y, depth+1);
			
		}	
		
		totalDepthOfFalseSearches=totalDepthOfFalseSearches+depth;						// Increase 'totalDepthOfFalseSearches'
		return depth;																	// Point not found.
	}
	
	/*
	 * Method that divides the rectangle represented by the input node into four quadrants
	 * and assigns them to the appropriate child nodes of the input node.
	 * 
	 */
	
	public void divideRect( Qnode node ) {
		
		node.setNW( new Qnode( node.getxMin(), node.getyMin(), (node.getxMin()+node.getxMax())/2, (node.getyMin()+node.getyMax())/2 ) );
		
		node.setNE( new Qnode( (node.getxMin()+node.getxMax())/2, node.getyMin(), node.getxMax(), (node.getyMin()+node.getyMax())/2 ) );
		
		node.setSW( new Qnode( node.getxMin(), (node.getyMin()+node.getyMax())/2, (node.getxMin()+node.getxMax())/2, node.getyMax() ) );

		node.setSE( new Qnode( (node.getxMin()+node.getxMax())/2, (node.getyMin()+node.getyMax())/2, node.getxMax(), node.getyMax() ) );
		
	}

	
	/**
	 * This method checks if the given point (x,y) is within the bounds of the 
	 * given Qnode object. If so, @return true.
	 */
	
	public boolean inBounds( int x, int y, Qnode node ) {
		
		if( (x>=node.getxMin()) && (x<=node.getxMax()) && (y>=node.getyMin()) && (y<=node.getyMax()) ) {
			
			return true;
			
		}
		return false;
	}
	
	
	/*
	 * Getters and setters 
	 */

	public Qnode getRoot() {
		return root;
	}

	public int getTotalDepthOfTrueSearches() {
		return totalDepthOfTrueSearches;
	}

	public int getTotalDepthOfFalseSearches() {
		return totalDepthOfFalseSearches;
	}
	
	
	
	
}
