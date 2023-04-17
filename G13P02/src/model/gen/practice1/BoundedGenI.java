package model.gen.practice1;

public interface BoundedGenI extends GenI {

	void initialize();
	void mutate();
	Double getBelowLimit();
	Double getWidth();
}
