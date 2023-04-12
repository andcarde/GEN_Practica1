package model.tree;

public class ArithmeticNode<T> {

	private ArithmeticEnum knot;
	private ArithmeticNode<T> leftBranch;
	private ArithmeticNode<T> rightBranch;
	private T fruit;
	
	ArithmeticNode(ArithmeticEnum knot, ArithmeticNode<T> leftBranch, ArithmeticNode<T> rightBranch) {
		this.knot = knot;
		this.leftBranch = leftBranch;
		this.rightBranch = rightBranch;
		this.fruit = null;
	}
	
	ArithmeticNode(ArithmeticEnum knot, T fruit) {
		this.knot = knot;
		this.leftBranch = null;
		this.rightBranch = null;
		this.fruit = fruit;
	}
	
	boolean isLeaf() {
		return fruit != null;
	}
	
	ArithmeticEnum getKnot() {
		return this.knot;
	}
	
	ArithmeticNode<T> getLeftBranch() {
		return this.leftBranch;
	}
	
	ArithmeticNode<T> getRightBranch() {
		return this.rightBranch;
	}
	
	T getFruit() {
		return this.fruit;
	}
}
