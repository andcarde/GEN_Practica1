package model;

import java.util.List;

import model.chromosome.ChromosomeI;
import model.chromosome.InterpreterI;

public class Chromosome implements ChromosomeI {

	private InterpreterI interpreter;
	private List<Boolean> elements;
	private Double value;
	
	public Chromosome(InterpreterI interpreter, List<Boolean> elements) {
		this.interpreter = interpreter;
		this.elements = elements;
	}
	
	@Override
	public Integer getSize() {
		return this.elements.size();
	}

	@Override
	public Boolean getElement(Integer i) {
		return this.elements.get(i);
	}

	@Override
	public InterpreterI getInterpreter() {
		return this.interpreter;
	}

	@Override
	public void invertElement(int i) {
		elements.set(i, !elements.get(i));
	}

	public Double getValue() {
		return this.value;
	}
}
