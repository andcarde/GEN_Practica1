package model.fitness;

import java.util.ArrayList;
import java.util.List;

public class Function2 extends Function {

	private static final Integer d = 2;
	
	public Function2(Double precision) {
		super(precision);
		super.isMaxim = false;
	}

	@Override
	public Double getValue(Input input) {
		Double value = 0.0;
		Double producer = 1.0;
		for (int i = 1; i <= d; i++) {
			Double x = input.get("x".concat(Integer.toString(i - 1)));
			value += Math.pow(x, 2);
			producer *= Math.cos(x / Math.sqrt(i));
		}
		value /= 4000;
		value -= producer;
		value += 1;
		return value;
	}
	
	@Override
	public List<Variable> getVariables() {
		List<Variable> variables = new ArrayList<>();
		for (int i = 0; i < d; i++) {
			String variableName = "x".concat(Integer.toString(i));
			variables.add(new Variable(variableName, -600.0, 600.0, this.precision));
		}
		return variables;
	}
}
