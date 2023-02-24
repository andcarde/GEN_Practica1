package model.fitness;

import java.util.ArrayList;
import java.util.List;

public class Function1 extends Function {

	/* La primera funci�n se llama de calibraci�n porque es simple y
	 * por lo tanto debe converger de forma m�s simple.
	 */
	
	public Function1(Double precision) {
		super(precision);
	}
	
	@Override
	public Double getValue(Input input) {
		Double value = 21.5;
		value += input.get("x1") * Math.sin(4 * Math.PI * input.get("x1"));
		value += input.get("x2") * Math.sin(20 * Math.PI * input.get("x2"));
		return value;
	}
	
	@Override
	public List<Variable> getVariables() {
		List<Variable> variables = new ArrayList<>();
		variables.add(new Variable("x1", -3.0, 12.1, this.precision));
		variables.add(new Variable("x2", 4.1, 5.8, this.precision));
		return variables;
	}
}
