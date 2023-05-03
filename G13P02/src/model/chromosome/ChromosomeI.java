package model.chromosome;

import model.MoldI;

public interface ChromosomeI {

	Integer getSize();
	
	Double getValue();
	
	MoldI getMold();
	
	void evaluate();
	
	String getGenesToString();
	
	ChromosomeI copy();
	
	void displace(double toSum);
	
	Double getAlterValue();
	
	double getBasicValue();
	
	void mutate();
}
