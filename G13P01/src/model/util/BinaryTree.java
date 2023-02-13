package model.util;

public class BinaryTree<T> {
	
	private Node<T> root;
	
	public BinaryTree() {
		this.root = null;
	}
	
	public void add(T element, Double d) {
		if (this.root == null)
			this.root = new Node<>(d, element);
		else {
			// Falta insertar los nodos e equilibrar el árbol.
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
