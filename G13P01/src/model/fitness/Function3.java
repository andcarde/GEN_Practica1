package model.fitness;

import java.util.ArrayList;
import java.util.List;

public class Function3 extends Function {

	private static final Integer d = 2;
	
	public Function3(Double precision) {
		super(precision);
		super.isMaxim = false;
	}

	@Override
	public Double getValue(Input input) {
		Double value = 0.0;
		for (int i = 0; i < d; i++) {
			Double x = input.get("x".concat(Integer.toString(i)));
			value += Math.pow(x, 4);
			value -= 16 * Math.pow(x, 2);
			value += 5 * x;
		}
		value /= 2;
		return value;
	}
	
	@Override
	public List<Variable> getVariables() {
		List<Variable> variables = new ArrayList<>();
		for (int i = 0; i < d; i++) {
			String variableName = "x".concat(Integer.toString(i));
			variables.add(new Variable(variableName, -5.0, 5.0, this.precision));
		}
		return variables;
	}
}
