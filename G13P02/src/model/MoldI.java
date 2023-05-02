package model;

import java.util.List;

import model.fitness.Fitness;
import model.gen.practice3.GenI;
import model.mutation.MutationI;

public interface MoldI {

	Fitness getFunction();

	List<GenI> getGenes();
	
	Integer getNumGenes();
	
	boolean getBloating();

	MutationI getMutation();

	int getPopulationAmount();
	
	void setK(double k);
	
	double getK();
}
