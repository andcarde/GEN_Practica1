package model.fitness;

import java.util.List;

import model.gen.practice1.GenType;

public interface Fitness {

	Double getValue(Input<?> input);
	boolean isMaximization();
	List<Variable> getVariables();
	GenType getGenType();
}
