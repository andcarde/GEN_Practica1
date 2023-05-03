package model.util;

public class Node<T> {

	private Double knot;
	private Node<T> leftBranch;
	private Node<T> rightBranch;
	private T fruit;
	
	Node(Double knot, Node<T> leftBranch, Node<T> rightBranch) {
		this.knot = knot;
		this.leftBranch = leftBranch;
		this.rightBranch = rightBranch;
		this.fruit = null;
	}
	
	Node(Double knot, T fruit) {
		this.knot = knot;
		this.leftBranch = null;
		this.rightBranch = null;
		this.fruit = fruit;
	}
	
	boolean isLeaf() {
		return fruit != null;
	}
	
	Double getKnot() {
		return this.knot;
	}
	
	Node<T> getLeftBranch() {
		return this.leftBranch;
	}
	
	Node<T> getRightBranch() {
		return this.rightBranch;
	}
	
	T getFruit() {
		return this.fruit;
	}
}
