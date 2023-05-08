package model.chromosome;

import model.MoldI;

public interface ChromosomeI {

	Integer getSize();
	
	Double getValue();
	
	MoldI getMold();
	
	String getGenesToString();
	
	ChromosomeI copy();
	
	void displace(double toSum);
	
	Double getAlterValue();

	String pretty();

	double getFunctionValue();

	void evaluateValue();

	void evaluateFitness();

	ChromosomeI createMutatedCopy();
}
