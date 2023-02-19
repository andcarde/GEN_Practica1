package model.chromosome;

import java.util.List;

import model.MoldI;

public interface ChromosomeI {

	Integer getSize();
	Boolean getElement(Integer i);
	Double getValue();
	List<GenI> getGenes();
	void initialize();
	MoldI getMold();
	void evaluate();
	void assimilate(List<Boolean> genome);
}
