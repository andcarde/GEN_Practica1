package model.chromosome;

public interface GenI {

	String getName();
	Double getValue();
	void initialize();
	GenI copy();
	void mutate();
	Object getGenoma();
	GenI assimilate(Object genoma);
	Double getBelowLimit();
	Double getWidth();
}
