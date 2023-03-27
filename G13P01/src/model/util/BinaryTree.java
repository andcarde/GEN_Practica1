package model.util;

/***
 * This class is not used in the project, but remains for further expansion.
 */

public class BinaryTree<T> {
	
	private Node<T> root;
	
	public BinaryTree() {
		this.root = null;
	}
	
	public void add(T element, Double d) {
		if (this.root == null)
			this.root = new Node<>(d, element);
		else {
			// It's necessary to insert the nodes to balance the tree
		}
	}
	
	public T find(Double d) {
		if (this.root == null)
			return null;
		Node<T> actualNode = this.root;
		while (!actualNode.isLeaf()) {
			if (actualNode.getKnot() < d)
				actualNode = actualNode.getLeftBranch();
			else
				actualNode = actualNode.getRightBranch();
		}
		return actualNode.getFruit();
	}
}
