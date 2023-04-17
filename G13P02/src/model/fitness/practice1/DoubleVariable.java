package model.fitness.practice1;

import model.fitness.Variable;

public class DoubleVariable extends Variable {

	private Double belowLimit;
	private Double upperLimit;
	private Double precision;
	
	public DoubleVariable(String name, Double belowLimit, Double upperLimit, Double precision) {
		super(name);
		this.belowLimit = belowLimit;
		this.upperLimit = upperLimit;
		this.precision = precision;
	}

	public Double getBelowLimit() {
		return this.belowLimit;
	}

	public Double getUpperLimit() {
		return this.upperLimit;
	}

	public Double getPrecision() {
		return this.precision;
	}
}
