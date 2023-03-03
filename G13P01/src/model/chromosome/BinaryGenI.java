package model.chromosome;

public interface BinaryGenI extends GenI {

	Integer getSize();
	void invertElement(int i);
	Boolean getBit(int i);
}
