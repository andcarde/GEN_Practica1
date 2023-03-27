package model.chromosome;

public interface BoundedGenI extends GenI {

	void initialize();
	void mutate();
	Double getBelowLimit();
	Double getWidth();
}
