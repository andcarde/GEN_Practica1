package model.fitness;

import java.util.List;

import model.chromosome.GenType;

public interface Fitness {

	Double getValue(Input input);
	boolean isMaximization();
	List<Variable> getVariables();
	GenType getGenType();
}
