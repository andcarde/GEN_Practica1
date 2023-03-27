package model.chromosome;

public interface GenI {

	String getName();
	Double getValue();
	GenI copy();
	Object getGenome();
	GenI assimilate(Object genoma);
	void setName(String name);
	String toString();
}
