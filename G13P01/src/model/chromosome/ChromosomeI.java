package model.chromosome;

import java.util.List;

import model.MoldI;

public interface ChromosomeI {

	public Integer getSize();
	public Boolean getElement(Integer i);
	public Double getValue();
	public List<GenI> getGenes();
	public void initialize();
	public MoldI getMold();
	public void evaluate();
	public void assimilate(List<Boolean> genome);
	public String getGenesToString();
	public ChromosomeI copy();
}
