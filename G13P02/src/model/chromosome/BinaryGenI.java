package model.chromosome;

public interface BinaryGenI extends BoundedGenI {

	Integer getSize();
	void invertElement(int i);
	Boolean getBit(int i);
}
