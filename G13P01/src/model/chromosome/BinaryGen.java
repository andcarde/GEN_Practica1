package model.chromosome;

import java.util.ArrayList;
import java.util.List;

import model.fitness.Variable;
import model.mutation.BinaryMutationI;
import model.random.RandomGenerator;

public class BinaryGen implements BinaryGenI {
	
	private final BinaryMutationI mutationMethod;
	private final String name;
	private final Double belowLimit;
	private final Integer size;
	private final Double bitValue;
	private List<Boolean> bits;

	public static BinaryGen build(Variable variable, BinaryMutationI mutationMethod) {
		String name = variable.getName();
		Double belowLimit = variable.getBelowLimit();
		Double upperLimit = variable.getUpperLimit();
		Double precision = variable.getPrecision();
		Double width = upperLimit - belowLimit;
		Integer size = calculateSize(width, precision);
		Double bitValue = width / Math.pow(2, size);
		return new BinaryGen(mutationMethod, name, belowLimit, size, bitValue);
	}
	
	public static Integer calculateSize(Double width, Double precision) {
		return (int) (Math.log10((width / precision) + 1) / Math.log10(2));
	}
	
	private BinaryGen(BinaryGen gen) {
		this.mutationMethod = gen.mutationMethod;
		this.name = gen.name;
		this.belowLimit = gen.belowLimit;
		this.size = gen.size;
		this.bitValue = gen.bitValue;
		this.bits = new ArrayList<>();
		for (Boolean bit : gen.bits)
			this.bits.add(bit);
	}
	
	private BinaryGen(BinaryMutationI mutationMethod, String name, Double belowLimit, Integer size, Double bitValue) {
		this.mutationMethod = mutationMethod;
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
	public GenI copy() {
		return new BinaryGen(this);
	}
	
	@Override
	public void mutate() {
		for (int i = 0; i < this.size; i++)
			this.mutationMethod.act(this);
	}

	@Override
	public Object getGenoma() {
		return this.bits;
	}

	@Override
	public GenI assimilate(Object genoma) {
		this.bits = (List<Boolean>) genoma;
		return this;
	}

	@Override
	public Double getBelowLimit() {
		return this.belowLimit;
	}

	@Override
	public Double getWidth() {
		return this.bitValue * (Math.pow(2, size) - 1);
	}
}
