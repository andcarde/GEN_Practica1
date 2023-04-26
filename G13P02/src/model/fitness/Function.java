package model.fitness;

import model.gen.practice3.ArithmeticNode;

public abstract class Function implements Fitness {

	protected boolean isMaxim;
	
	public Function() {}
	
	public boolean isMaximization() { return isMaxim;}	
	
	public double[] getXValues() { return null; }
	
	public double[] getIdealFunction() { return null; }

	public double[] getFunction(ArithmeticNode node) { return null; }
}
