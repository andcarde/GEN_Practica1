package model.chromosome;

import java.util.ArrayList;
import java.util.List;

import model.fitness.Variable;
import model.random.RandomGenerator;

public class Gen implements GenI {
	
	private final String name;
	private final Double belowLimit;
	private final Integer size;
	private final Double bitValue;
	private List<Boolean> bits;

	public static Gen build(Variable variable) {
		String name = variable.getName();
		Double belowLimit = variable.getBelowLimit();
		Double upperLimit = variable.getUpperLimit();
		Double precision = variable.getPrecision();
		Double width = upperLimit - belowLimit;
		Integer size = calculateSize(width, precision);
		Double bitValue = width / Math.pow(2, size);
		return new Gen(name, belowLimit, size, bitValue);
	}
	
	// TODO Revisar la validez de este algoritmo.
	public static Integer calculateSize(Double width, Double precision) {
		return (int) (Math.log10((width / precision) + 1) / Math.log10(2));
	}
	
	private Gen(Gen gen) {
		this.name = gen.name;
		this.belowLimit = gen.belowLimit;
		this.size = gen.size;
		this.bitValue = gen.bitValue;
		this.bits = new ArrayList<>();
		for (Boolean bit : gen.bits)
			this.bits.add(bit);
	}
	
	private Gen(String name, Double belowLimit, Integer size, Double bitValue) {
		this.name = name;
		this.belowLimit = belowLimit;
		this.size = size;
		this.bitValue = bitValue;
		this.bits = new ArrayList<>();
	}
	
	@Override
	public void initialize() {
		for (int i = 0; i < this.size; i++)
			bits.add(RandomGenerator.createAleatoryBoolean());
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Double getValue() {
		Double value = 0.0;
		Integer pow = 1;
		for (Boolean bit : bits) {
			if (bit)
				value += pow;
			pow *= 2;
		}
		value *= bitValue;
		value += belowLimit;
		return value;
	}

	@Override
	public Integer getSize() {
		return this.size;
	}

	@Override
	public void invertElement(int i) {
		bits.set(i, !bits.get(i));
	}

	@Override
	public Boolean getBit(int i) {
		System.out.println("Bits: " + bits.size());
		return bits.get(i);
	}

	@Override
	public void assimilate(List<Boolean> bits) {
		this.bits = bits;
	}
	
	public GenI copy() {
		return new Gen(this);
	}
}
