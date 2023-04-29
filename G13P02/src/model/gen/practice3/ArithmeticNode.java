package model.gen.practice3;

import model.fitness.practice3.Callback;
import model.random.RandomGenerator;

public class ArithmeticNode implements Callback {

	private int numSons;
	private ArithmeticNode father;
	private ArithmeticEnum knot;
	private ArithmeticNode leftBranch;
	private ArithmeticNode rightBranch;
	private TerminalEnum fruit;
	
	public ArithmeticNode(TerminalEnum terminalEnum) {
		toTerminalNode(terminalEnum);
	}
	
	public ArithmeticNode(ArithmeticEnum arithmeticEnum, ArithmeticNode leftBranch,
			ArithmeticNode rightBranch) {
		numSons = 0;
		knot = arithmeticEnum;
		this.leftBranch = leftBranch;
		leftBranch.father = this;
		this.rightBranch = rightBranch;
		rightBranch.father = this;
		fruit = null;
		updateSons();
	}
	
	private ArithmeticNode(ArithmeticNode arithmeticNode) {
		this.father = arithmeticNode.father;
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
	
	public ArithmeticNode getFather() {
		return father;
	}
	
	public void setFather(ArithmeticNode node) {
		father =  node;
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
	
	public void toTerminalNode(TerminalEnum terminalEnum) {
		numSons = 0;
		knot = null;
		leftBranch = null;
		rightBranch = null;
		fruit = terminalEnum;
		updateSons();
	}
	
	public void updateSons() {
		numSons = 0;
		if (leftBranch != null)
			numSons += leftBranch.numSons + 1;
		if (rightBranch != null)
			numSons += rightBranch.numSons + 1;
		if (father != null)
			father.updateSons();
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
			observedSons = leftBranch.numSons + 1;
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
		else {
			int observedSons = 0;
			if (leftBranch != null) {
				observedSons = leftBranch.numSons + 1;
				if (index <= observedSons)
					leftBranch.setNode(index - 1, node);
			}
			if (rightBranch != null)
				if (index > observedSons)
					rightBranch.setNode(index - observedSons - 1, node);
		}
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
	
	public ArithmeticNode getRandomNoTerminalNode() {
		ArithmeticNode selectedNode;
		do {
			int index = RandomGenerator.createAleatoryInt(numSons);
			selectedNode = this.getNode(index);
		} while (selectedNode.isLeaf());
		return selectedNode;
	}
}
