package model;

import model.fitness.Fitness;
import model.mutation.MutationI;

public interface MoldI {

	Fitness getFunction();
	
	boolean getBloating();

	MutationI getMutation();
	
	void setK(double k);
	
	double getK();

	int getMaxHeigth();
	
	int getNumWraps();
}
