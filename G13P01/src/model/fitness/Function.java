package model.fitness;

public abstract class Function implements Fitness {

	protected Double precision;
	
	public Function(Double precision) {
		this.precision = precision;
	}
}
