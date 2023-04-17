package model.fitness.practice1;

import model.fitness.Function;

public abstract class NumericFunction extends Function {

	protected Double precision;
	protected boolean isMaxim;
	
	public NumericFunction(Double precision) {
		this.precision = precision;
	}
}
