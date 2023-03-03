package model.chromosome;

public interface GenI {

	String getName();
	Double getValue();
	void initialize();
	GenI copy();
	void mutate();
}
