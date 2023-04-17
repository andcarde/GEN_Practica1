package model;

import java.util.List;

import model.fitness.Fitness;
import model.gen.practice1.GenI;

public interface MoldI {

	Fitness getFunction();

	List<GenI> getGenes();

	Integer getSize();
	
	Integer getNumGenes();
}
