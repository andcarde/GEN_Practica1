package model.fitness.practice1;

import model.gen.practice1.GenType;

public class Function4b extends Function4 {

	public Function4b(Double precision, Integer d) {
		super(precision, d);
	}

	@Override
	public GenType getGenType() {
		return GenType.REAL;
	}
}
