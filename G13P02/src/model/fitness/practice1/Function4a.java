package model.fitness.practice1;

import model.gen.practice1.GenType;

public class Function4a extends Function4 {

	public Function4a(Double precision, Integer d) {
		super(precision, d);
	}

	@Override
	public GenType getGenType() {
		return GenType.BINARY;
	}
}
