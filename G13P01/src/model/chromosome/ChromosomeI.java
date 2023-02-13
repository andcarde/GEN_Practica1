package model.chromosome;

public interface ChromosomeI {

	Integer getSize();
	Boolean getElement(Integer i);
	InterpreterI getInterpreter();
	void invertElement(int i);
	Double getValue();
}
