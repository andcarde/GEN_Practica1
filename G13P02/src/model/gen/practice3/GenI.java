package model.gen.practice3;

public interface GenI {

	String getName();
	Double getValue();
	GenI copy();
	Object getGenome();
	GenI assimilate(Object genoma);
	void setName(String name);
	String toString();
}
