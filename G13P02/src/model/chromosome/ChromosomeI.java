package model.chromosome;

import java.util.List;

import model.MoldI;

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
	void mutate();
	public GenI getGen(int i);
	void setGen(int i, GenI gen);
	void assimilate(List<Object> genome);
	int indexOf(int city);
}
