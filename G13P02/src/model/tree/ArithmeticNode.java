package model.tree;

public class ArithmeticNode {

	private int numSons;
	private ArithmeticEnum knot;
	private ArithmeticNode leftBranch;
	private ArithmeticNode rightBranch;
	private TerminalEnum fruit;
	
	public ArithmeticNode() {
		numSons = 0;
	}
	
	public ArithmeticNode(TerminalEnum fruit) {
		numSons = 0;
		this.fruit = fruit;
	}
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
	
	public ArithmeticNode(ArithmeticNode arithmeticNode) {
		numSons = arithmeticNode.numSons;
		knot = arithmeticNode.knot;
		if (arithmeticNode.leftBranch != null)
			leftBranch = arithmeticNode.leftBranch.copy();
		if (arithmeticNode.rightBranch != null)
			rightBranch = arithmeticNode.rightBranch.copy();
		fruit = arithmeticNode.fruit;
	}

	public boolean isLeaf() {
		return fruit != null;
	}
	
	public void setKnot(ArithmeticEnum ae) {
		this.knot = ae;
	}
	
	public ArithmeticEnum getKnot() {
		return this.knot;
	}
	
	public ArithmeticNode getLeftBranch() {
		return this.leftBranch;
	}
	
	public ArithmeticNode getRightBranch() {
		return this.rightBranch;
	}
	
	public void setRightBranch(ArithmeticNode node) {
		rightBranch =  node;
	}
	
	public void setLeftBranch(ArithmeticNode node) {
		leftBranch =  node;
	}
		
	public int getNumSons() {
		return this.numSons;
	}
	
	public ArithmeticNode getNode(Integer n) {
		if (n < 0 || n > numSons)
			return null;
		if (n == 0)
			return this;
		if (n <= leftBranch.getNumSons() + 1)
			return leftBranch.getNode(n - 1);
		return rightBranch.getNode(n - leftBranch.getNumSons() - 2);
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
	
	// TODO Falta de rellenar
	public String toString() {
		String s = "";
		return s;
	}

	public void setNumSons(int numSons) {
		this.numSons = numSons;
	}

	public void setFruit(TerminalEnum fruit) {
		this.fruit = fruit;
	}

	public ArithmeticNode copy() {
		return new ArithmeticNode(this);
	}
}
