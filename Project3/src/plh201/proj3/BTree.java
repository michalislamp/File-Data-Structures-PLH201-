package plh201.proj3;

/**
 * A B+ tree
 * Since the structures and behaviors between internal node and external node are different, 
 * so there are two different classes for each kind of node.
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
public class BTree<TKey extends Comparable<TKey>, TValue> {
	
	private BTreeNode<TKey> root;
	
	private int nodeCount;  //Count the nodes accessed
	private int keyCompCount; //Count key comparisons
	
	private int totalNodeCount; //Count total nodes accessed
	private int totalKeyCompCount; //Count total key comparisons
	
	
	public BTree(int m) {
		
		BTreeInnerNode.INNERORDER=m;
		BTreeLeafNode.LEAFORDER=m;
		
		this.root = new BTreeLeafNode<TKey, TValue>();
		
		
		this.totalKeyCompCount = 0;
		this.totalNodeCount = 0;
		
	}

	/**
	 * Insert a new key and its associated value into the B+ tree.
	 */
	public void insert(TKey key, TValue value) {
		BTreeLeafNode<TKey, TValue> leaf = this.findLeafNodeShouldContainKey(key);
		leaf.insertKey(key, value);
		
		if (leaf.isOverflow()) {
			BTreeNode<TKey> n = leaf.dealOverflow();
			if (n != null)
				this.root = n; 
		}
	}
	
	/**
	 * Search a key value on the tree and return its associated value.
	 */
	public TValue search(TKey key) {
		
		/*
		 * Initialize values
		 */
		
		this.nodeCount = 0;
		this.keyCompCount = 0;
	
		BTreeLeafNode<TKey, TValue> leaf = this.findLeafNodeShouldContainKey(key);
	

		
		/*
		 * node.search function returns in witch child of the node is the area we need.
		 * e.x if we need child(0) it means it has done 1 comparison thats why we add 1
		 */
		
		this.keyCompCount = leaf.search(key) + 1 + this.keyCompCount;
		this.totalKeyCompCount = this.totalKeyCompCount + leaf.search(key) + 1;
		
		int index = leaf.search(key);

		
		if( index == -1 ) {
						
			return null;
			
		}else {
			
			return  leaf.getValue(index);
			
		}
		
	}
	
	/**
	 * Delete a key and its associated value from the tree.
	 */
	public void delete(TKey key) {
		BTreeLeafNode<TKey, TValue> leaf = this.findLeafNodeShouldContainKey(key);
		
		if (leaf.delete(key) && leaf.isUnderflow()) {
			BTreeNode<TKey> n = leaf.dealUnderflow();
			if (n != null)
				this.root = n; 
		}
	}
	
	/**
	 * Search the leaf node which should contain the specified key
	 */
	@SuppressWarnings("unchecked")
	private BTreeLeafNode<TKey, TValue> findLeafNodeShouldContainKey(TKey key) {
		BTreeNode<TKey> node = this.root;
		while (node.getNodeType() == TreeNodeType.InnerNode) {
			
			

			
			/*
			 * node.search function returns in witch child of the node is the area we need.
			 * e.x if we need child(0) it means it has done 1 comparison thats why we add 1
			 */
			
			this.keyCompCount = node.search(key) + 1 + this.keyCompCount;
			this.totalKeyCompCount = this.totalKeyCompCount + node.search(key) + 1;
			
			
			node = ((BTreeInnerNode<TKey>)node).getChild( node.search(key) );
			
			/*
			 * every time we enter the loop we access another node ( so + 1 every time )
			 */
			
			this.nodeCount++;
			this.totalNodeCount++;

		}
		

		
		return (BTreeLeafNode<TKey, TValue>)node;
	}

	/*
	 * Generate getters and setters
	 */
	public int getNodeCount() {
		return nodeCount;
	}

	public void setNodeCount(int nodeCount) {
		this.nodeCount = nodeCount;
	}

	public int getKeyCompCount() {
		return keyCompCount;
	}

	public void setKeyCompCount(int keyCompCount) {
		this.keyCompCount = keyCompCount;
	}

	public int getTotalNodeCount() {
		return totalNodeCount;
	}

	public int getTotalKeyCompCount() {
		return totalKeyCompCount;
	}


	
	
}