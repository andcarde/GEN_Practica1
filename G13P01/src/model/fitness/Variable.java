package model.fitness;

public class Variable {

	private String name;
	private Double belowLimit;
	private Double upperLimit;
	private Double precision;
	
	public Variable(String name, Double belowLimit, Double upperLimit, Double precision) {
		this.name = name;
		this.belowLimit = belowLimit;
		this.upperLimit = upperLimit;
		this.precision = precision;
	}

	public String getName() {
		return this.name;
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
