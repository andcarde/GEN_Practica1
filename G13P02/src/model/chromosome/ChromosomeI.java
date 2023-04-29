package model.chromosome;

import java.util.List;

import model.MoldI;
import model.gen.practice3.ArithmeticNode;
import model.gen.practice3.GenI;

public interface ChromosomeI {

	Integer getSize();
	Double getValue();
	List<GenI> getGenes();
	void initialize();
	MoldI getMold();
	void evaluate();
	String getGenesToString();
	ChromosomeI copy();
	void displace(double toSum);
	Double getAlterValue();
	public GenI getGen(int i);
	void setGen(int i, GenI gen);
	void assimilate(List<Object> genome);
	int indexOf(int city);
	double getBasicValue();
	ArithmeticNode getRaiz();
}
