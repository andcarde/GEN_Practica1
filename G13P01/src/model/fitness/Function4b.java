package model.fitness;

import model.chromosome.GenType;

public class Function4b extends Function4 {

	public Function4b(Double precision, Integer d) {
		super(precision, d);
	}

	@Override
	public GenType getGenType() {
		return GenType.REAL;
	}
}
