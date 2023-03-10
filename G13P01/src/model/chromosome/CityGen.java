package model.chromosome;

import model.fitness.Variable;

public class CityGen implements GenI {

	private String name;
	private Double value;
	
	public static GenI build(Variable var) {
		String name = var.getName();
		return new CityGen(name);
	}
	
	public CityGen(String name) {
		this.name = name;
	}
	
	private CityGen(CityGen cityGen) {
		this.name = cityGen.name;
		this.value = cityGen.value;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Double getValue() {
		return value;
	}

	@Override
	public GenI copy() {
		return new CityGen(this);
	}

	@Override
	public Object getGenoma() {
		return value;
	}

	@Override
	public GenI assimilate(Object genoma) {
		value = (Double) genoma;
		return this;
	}
}
