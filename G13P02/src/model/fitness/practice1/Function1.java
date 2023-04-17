package model.fitness.practice1;

import java.util.ArrayList;
import java.util.List;

import model.fitness.DoubleInput;
import model.fitness.Input;
import model.fitness.Variable;
import model.gen.practice1.GenType;

public class Function1 extends NumericFunction {

	/* La primera función se llama de calibración porque es simple y
	 * por lo tanto debe converger de forma más simple.
	 */
	
	public Function1(Double precision) {
		super(precision);
		super.isMaxim = true;
	}
	
	@Override
	public Double getValue(Input<?> inputG) {
		DoubleInput input = (DoubleInput) inputG;
		Double value = 21.5;
		value += input.get("x1") * Math.sin(4 * Math.PI * input.get("x1"));
		value += input.get("x2") * Math.sin(20 * Math.PI * input.get("x2"));
		return value;
	}
	
	@Override
	public List<Variable> getVariables() {
		List<Variable> variables = new ArrayList<>();
		variables.add(new DoubleVariable("x1", -3.0, 12.1, this.precision));
		variables.add(new DoubleVariable("x2", 4.1, 5.8, this.precision));
		return variables;
	}

	@Override
	public GenType getGenType() {
		return GenType.BINARY;
	}
}
