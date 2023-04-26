package model.fitness;

import java.util.List;

import model.gen.practice1.GenType;
import model.gen.practice3.ArithmeticNode;

public interface Fitness {

	Double getValue(Input<?> input);
	boolean isMaximization();
	List<Variable> getVariables();
	GenType getGenType();
	double[] getXValues();
	double[] getIdealFunction();
	double[] getFunction(ArithmeticNode node);
}
