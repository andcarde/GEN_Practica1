package model.tree;

public class ArithmeticNode {

	private ArithmeticEnum knot;
	private ArithmeticNode leftBranch;
	private ArithmeticNode rightBranch;
	private TerminalEnum fruit;
	
	ArithmeticNode(ArithmeticEnum knot, ArithmeticNode leftBranch, ArithmeticNode rightBranch) {
		this.knot = knot;
		this.leftBranch = leftBranch;
		this.rightBranch = rightBranch;
		this.fruit = null;
	}
	
	ArithmeticNode(ArithmeticEnum knot, TerminalEnum fruit) {
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
	
	ArithmeticNode getLeftBranch() {
		return this.leftBranch;
	}
	
	ArithmeticNode getRightBranch() {
		return this.rightBranch;
	}
	
	public double getValue(double xvalue) {
		if (isLeaf()) {
			if (fruit == TerminalEnum.x) return xvalue;
			else return TerminalEnum.valueToInt(fruit);
		}
		return ArithmeticEnum.calculate(leftBranch.getValue(xvalue), knot, rightBranch.getValue(xvalue));
	}
	
	TerminalEnum getFruit() {
		return this.fruit;
	}
}
