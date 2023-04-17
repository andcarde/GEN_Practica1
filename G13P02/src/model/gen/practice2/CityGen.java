package model.gen.practice2;

import model.fitness.Variable;
import model.gen.practice1.GenI;

public class CityGen implements GenI {

	private String name;
	private double value;
	
	public static GenI build(Variable var) {
		String name = var.getName();
		return new CityGen(name);
	}
	
	public CityGen(String name) {
		this.name = name;
	}
	
	public CityGen(String name, Integer val) {
		this.name = name;
		value = val + 0.0;
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
	public Object getGenome() {
		return value;
	}

	@Override
	public GenI assimilate(Object genoma) {
		if (genoma instanceof Integer)
			value = (Integer) genoma + 0.0;
		else if (genoma instanceof Float)
			value = (Float) genoma + 0.0;
		else if (genoma instanceof Double)
			value = (Double) genoma;
		return this;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "<" + name + ", " + value +">";
	}
}
