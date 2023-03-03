package model.fitness;

import model.chromosome.GenType;

public class Function4a extends Function4 {

	public Function4a(Double precision, Integer d) {
		super(precision, d);
	}

	@Override
	public GenType getGenType() {
		return GenType.BINARY;
	}
}
