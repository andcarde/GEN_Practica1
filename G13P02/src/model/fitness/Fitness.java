package model.fitness;

import java.util.List;

import model.chromosome.ChromosomeI;
import model.gen.practice3.GenType;

public interface Fitness {

	Double getValue(Input<?> input);
	boolean isMaximization();
	List<Variable> getVariables();
	GenType getGenType();
	double[] getXValues();
	double[] getIdealFunction();
	double[] getFunction(ChromosomeI chromosome);
}
