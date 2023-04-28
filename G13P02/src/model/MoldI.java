package model;

import java.util.List;

import model.fitness.Fitness;
import model.gen.practice3.GenI;

public interface MoldI {

	Fitness getFunction();

	List<GenI> getGenes();
	
	Integer getNumGenes();
	
	boolean getBloating();

	Executor getExecutor();

	void setExecutor(Executor executor);
}
