package model.gen.practice3;

import model.fitness.practice3.Callback;

public class ArithmeticNode implements Callback {

	private int numSons;
	private ArithmeticEnum knot;
	private ArithmeticNode leftBranch;
	private ArithmeticNode rightBranch;
	private TerminalEnum fruit;
	
	public ArithmeticNode(TerminalEnum terminalEnum) {
		numSons = 0;
		knot = null;
		leftBranch = null;
		rightBranch = null;
		fruit = terminalEnum;
	}
	
	public ArithmeticNode(ArithmeticEnum arithmeticEnum, ArithmeticNode leftBranch, ArithmeticNode rightBranch) {
		numSons = 0;
		if (leftBranch != null)
			numSons += leftBranch.numSons;
		if (rightBranch != null)
			numSons += rightBranch.numSons;
		knot = arithmeticEnum;
		this.leftBranch = leftBranch;
		this.rightBranch = rightBranch;
		fruit = null;
	}
	
	private ArithmeticNode(ArithmeticNode arithmeticNode) {
		numSons = arithmeticNode.numSons;
		knot = arithmeticNode.knot;
		if (arithmeticNode.leftBranch != null)
			leftBranch = arithmeticNode.leftBranch.copy();
		if (arithmeticNode.rightBranch != null)
			rightBranch = arithmeticNode.rightBranch.copy();
		fruit = arithmeticNode.fruit;
	}
	
	public ArithmeticEnum getKnot() {
		return knot;
	}
	
	public void setKnot(ArithmeticEnum arithmeticEnum) {
		this.knot = arithmeticEnum;
	}
	
	public ArithmeticNode getLeftBranch() {
		return leftBranch;
	}
	
	public void setLeftBranch(ArithmeticNode node) {
		leftBranch =  node;
	}
	
	public ArithmeticNode getRightBranch() {
		return rightBranch;
	}
	
	public void setRightBranch(ArithmeticNode node) {
		rightBranch =  node;
	}
	
	public TerminalEnum getFruit() {
		return fruit;
	}
	
	public void setFruit(TerminalEnum terminalEnum) {
		fruit = terminalEnum;
	}
	
	public int getNumSons() {
		return this.numSons;
	}
	
	public ArithmeticNode copy() {
		return new ArithmeticNode(this);
	}
	
	public boolean isLeaf() {
		return fruit != null;
	}
	
	/***
	 * @return
	 * - Return itself if n = 0.
	 * - Returns null if n < 0 OR n > numSons
	 * OR (0 < n <= numSons AND rightBranch = null AND leftBranch = null.
	 * - Otherwise returns the the corresponding son.
	 */
	public ArithmeticNode getNode(int n) {
		int observedSons = 0;
		if (n == 0)
			return this;
		if (n < 0 || n > numSons)
			return null;
		if (leftBranch != null) {
			observedSons = leftBranch.getNumSons() + 1;
			if (n <= observedSons)
				return leftBranch.getNode(n - 1);
		}
		if (rightBranch != null)
			return rightBranch.getNode(n - observedSons - 1);
		return null;
	}
	
	public void setNode(int index, ArithmeticNode node) {
		if (index == 0) {
			numSons = node.numSons;
			knot = node.knot;
			leftBranch = node.leftBranch; 
			rightBranch = node.rightBranch;
			fruit = node.fruit; 
		}
		else if (index <= leftBranch.getNumSons() + 1)
			leftBranch.setNode(index - 1, node);
		else
			rightBranch.setNode(index - leftBranch.getNumSons() - 2, node);
	}
	
	@Override
	public double getValue(double xValue) {
		if (isLeaf()) {
			if (fruit == TerminalEnum.x)
				return xValue;
			return TerminalEnum.valueToInt(fruit);
		}
		return ArithmeticEnum.calculate(knot, leftBranch.getValue(xValue), rightBranch.getValue(xValue));
	}
	
	public String toString() {
		if (isLeaf())
			return TerminalEnum.toString(fruit);
		String s = knot.toString() + "(";
		if (leftBranch == null)
			s += "null";
		else
			s += leftBranch.toString();
		s += ", ";
		if (rightBranch == null)
			s += "null";
		else
			s += rightBranch.toString();
		s += ")";
		return s;
	}
}
