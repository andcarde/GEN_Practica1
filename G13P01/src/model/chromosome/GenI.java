package model.chromosome;

import java.util.List;

public interface GenI {

	String getName();
	Double getValue();
	Integer getSize();
	void invertElement(int i);
	void initialize();
	Boolean getBit(int i);
	void assimilate(List<Boolean> bits);
}
