package model.chromosome;

public interface GenI {

	String getName();
	Double getValue();
	GenI copy();
	Object getGenoma();
	GenI assimilate(Object genoma);
	void setName(String name);
}
