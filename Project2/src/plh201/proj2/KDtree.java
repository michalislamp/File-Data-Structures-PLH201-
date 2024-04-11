package plh201.proj2;

/*
 * KDtree class is an implementation of the K-D tree data structure.
 * It contains insert and search functions.
 * 
 */

public class KDtree {
   
	private KDnode root;      						 // Root of the tree
	
	private int totalDepthOfTrueSearches;			// Total depth of all true searches in the tree
	private int totalDepthOfFalseSearches;			// Total depth of all false searches in the tree
	

	
	public KDtree() {
		
		this.totalDepthOfFalseSearches = 0;  
		this.totalDepthOfTrueSearches = 0;
		
	}
	
	/*
	 * Wrapper method for inserting node in the K-D Tree,
	 * using only parameters 'x' and 'y'.
	 */
	
	
	public void insertNode(int x, int y) {
		
		root = insertNode(root, x, y, true);
		
	}
	
	/**
	 * Method that recursively inserts a point into the K-D Tree. It takes 4 arguments.
	 * @param node represents the root of the current subtree.
	 * @param x, @param y represents the coordinates of the point to be inserted.
	 * @param compVar, witch is a boolean flag that indicates whether to compare
	 * the 'x' or the 'y' coordinate of the point with the corresponding coordinate of the nodes in the tree.
	 * 
	 * @return KDnode
	 * KDnode represents the root of the current subtree after the new point has been inserted into the K-D Tree.
	 * The root node may change during the insertion process as the tree is recursively traversed and modified 
	 * to maintain the correct ordering of the points along the tree.
	 */
	
	private KDnode insertNode( KDnode node, int x, int y, boolean compVar ) {     
																	  			 
		
		if( node == null ) {
		
			return new KDnode(x,y);
			
		}
		
		if(compVar) {
			
			if(x < node.getX() ) {
				
				node.setLeftChild(insertNode(node.getLeftChild(), x, y, !compVar));
				
			} else {
				
				node.setRightChild(insertNode(node.getRightChild(), x, y, !compVar));
				
			}
		} else {
			
			if( y < node.getY() ) {
				
				node.setLeftChild(insertNode(node.getLeftChild(), x, y, compVar));
				
			} else {
				
				node.setRightChild(insertNode(node.getRightChild(), x, y, compVar));
				
			}
		}
			
		return node;
			
	}

	/*
	 * Wrapper method for searching in the K-D Tree
	 * using only  parameters x and y.
	 * 
	 */
	
	
	public int searchWithDepth(int x, int y) {
		   
		return searchWithDepth(root, x, y, true, 0);
	        
	}
	
	
	
	/**
	 * Method that recursively searches for a point in the K-D Tree starting from the root node.
	 *  
	 * @param node represents the root of the current subtree.
	 * @param x, @param y represents the coordinates of the point to be searched for.
	 * @param compVar, witch is a boolean flag that indicates whether to compare the 
	 * 'x' or the 'y' coordinate of the point with the corresponding coordinates of the node in the tree.
	 * The useX flag is toggled at each level of recursion to ensure that the method alternates between 
	 * comparing the 'x' and 'y' coordinates of the nodes in the tree.
	 * @param depth represents the current depth of the recursion
	 * 
	 * @return the depth of the node where the point is found or the depth where the search ends without
	 * finding the point
	 * 
	 * If the point is found, increase 'totalDepthOfTrueSearches' by the depth of the current search.
	 * If not, increase 'totalDepthOfFalseSearches' by the depth of the current search.
	 * These variables are used to compute the average depth of successful and unsuccessful searches in the tree.
	 */
	
	private int searchWithDepth(KDnode node, int x, int y, boolean compVar, int depth) {
		   
	        if (node == null) {
	        	
	        	totalDepthOfFalseSearches = totalDepthOfFalseSearches + depth -1;			// Increase 'totalDepthOfFalseSearches'
	            return depth-1; 															// Point NOT found
	         
	            
	        }
	        
	        if (node.getX() == x && node.getY() == y) {
	        	
	        	totalDepthOfTrueSearches = totalDepthOfTrueSearches + depth;				// Increase 'totalDepthOfTrueSearches'
	            return depth; 																// Point found
	            
	        }
	        
	        if (compVar) {
	        	
	            if (x < node.getX()) {
	            	
	                return searchWithDepth(node.getLeftChild(), x, y, !compVar, depth + 1);
	                
	            } else {
	            	
	                return searchWithDepth(node.getRightChild(), x, y, !compVar, depth + 1);
	                
	            }
	            
	        } else {
	        	
	            if (y < node.getY()) {
	            	
	                return searchWithDepth(node.getLeftChild(), x, y, compVar, depth + 1);
	                
	            } else {
	            	
	                return searchWithDepth(node.getRightChild(), x, y, compVar, depth + 1);
	                
	            }
	        }
	    }    

	
	/*
	 * Getters and Setters
	 */

	public KDnode getRoot() {
		return root;
	}

	public int getTotalDepthOfTrueSearches() {
		return totalDepthOfTrueSearches;
	}

	public int getTotalDepthOfFalseSearches() {
		return totalDepthOfFalseSearches;
	}
}


