package model.fitness;

import java.util.ArrayList;
import java.util.List;

public abstract class Function4 extends Function {

	private static final Integer m = 10;
	private final Integer d;
	
	public Function4(Double precision, Integer d) {
		super(precision);
		this.d = d;
		super.isMaxim = false;
	}

	@Override
	public Double getValue(Input input) {
		Double value = 0.0;
		for (int i = 0; i < d; i++) {
			Double x = input.get("x".concat(Integer.toString(i)));
			Double secondSin = Math.sin((i + 1) * Math.pow(x, 2) / Math.PI);
			value += Math.sin(x) * Math.pow(secondSin, 2 * Function4.m);
		}
		value *= -1;
		return value;
	}
	
	@Override
	public List<Variable> getVariables() {
		List<Variable> variables = new ArrayList<>();
		for (int i = 0; i < d; i++) {
			String variableName = "x".concat(Integer.toString(i));
			variables.add(new Variable(variableName, 0.0, Math.PI, this.precision));
		}
		return variables;
	}
}
