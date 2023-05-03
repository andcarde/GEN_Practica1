package model.gen.practice3;

import java.util.List;

import model.random.RandomGenerator;

public class BinaryGen {
	
	private static final int CODON_SIZE = 8;
	private List<Boolean> bits;

	public BinaryGen(int codon) {
		for (int i = 0; i < CODON_SIZE; i++)
			bits.add(RandomGenerator.createAleatoryBoolean());
	}
	public BinaryGen(BinaryGen gen) {
		for (int i = 0; i < CODON_SIZE; i++)
			bits.add(Boolean.valueOf(gen.bits.get(i)));
	}
	
	public int toInteger() {
		int integer = 0;
		int multiplier = 1;
		for (int i = 0; i < CODON_SIZE; i++) {
			if (bits.get(i))
				integer += multiplier;
			multiplier *= 2;
		}
		return integer;
	}
	
	public BinaryGen copy() {
		return new BinaryGen(this);
	}
	
	public List<Boolean> getBits() {
		return bits;
	}
}
