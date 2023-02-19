package model.fitness;

import java.util.List;

public interface Fitness {

	Double getValue(Input input);
	List<Variable> getVariables();
}
