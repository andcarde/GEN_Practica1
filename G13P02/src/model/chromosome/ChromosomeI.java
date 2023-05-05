package model.chromosome;

import java.util.List;

import model.MoldI;
import model.gen.practice3.BinaryGen;

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
	
	List<BinaryGen> getGenes();

	String pretty();

	double getFunctionValue();
}
