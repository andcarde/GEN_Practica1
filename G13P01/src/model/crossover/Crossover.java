package model.crossover;

import model.chromosome.InterpreterI;

public abstract class Crossover implements CrossoverI {

	protected InterpreterI interpreter;
	
	protected Crossover(InterpreterI interpreter) {
		this.interpreter = interpreter;
	}
}
