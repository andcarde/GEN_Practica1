package model.chromosome;

import java.util.List;

public interface BinaryGenI {

	Integer getSize();
	void invertElement(int i);
	Boolean getBit(int i);
	void assimilate(List<Boolean> bits);
}
