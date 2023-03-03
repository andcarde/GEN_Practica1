package model.chromosome;

import java.util.ArrayList;
import java.util.List;

import model.fitness.Variable;
import model.random.RandomGenerator;

public class RealGen implements GenI {
	
	private final String name;
	private final Double belowLimit;
	private final Double upperLimit;
	private Double real;

	public static GenI build(Variable variable) {
		String name = variable.getName();
		Double belowLimit = variable.getBelowLimit();
		Double upperLimit = variable.getUpperLimit();
		return new RealGen(name, belowLimit, upperLimit);
	}
	
	private RealGen(String name, Double belowLimit, Double upperLimit) {
		this.name = name;
		this.belowLimit = belowLimit;
		this.upperLimit = upperLimit;
	}
	
	private RealGen(RealGen gen) {
		this.name = gen.name;
		this.belowLimit = gen.belowLimit;
		this.upperLimit = gen.upperLimit;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Double getValue() {
		return this.real;
	}

	@Override
	public Integer getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize() {
		this.real = RandomGenerator.createAleatoryDouble() * (this.upperLimit - this.belowLimit) + this.belowLimit;
	}

	@Override
	public Boolean getBit(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void assimilate(Double real) {
		this.real = real;
	}
	
	@Override
	public GenI copy() {
		return new RealGen(this);
	}
}
