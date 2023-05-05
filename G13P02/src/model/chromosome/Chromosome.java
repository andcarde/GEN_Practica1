package model.chromosome;

import model.MoldI;
import model.gen.practice3.ArithmeticNode;

public abstract class Chromosome implements ChromosomeI {
	
	protected Double phenotype;
	private Double copytype;
	protected MoldI mold;
	
	public Chromosome(MoldI mold) {
		this.mold = mold;
	}
	
	protected Chromosome(Chromosome chromosome) {
		this.phenotype = chromosome.phenotype;
		this.copytype = chromosome.copytype;
		this.mold = chromosome.mold;
	}

	@Override
	public MoldI getMold() {
		return this.mold;
	}

	@Override
	public Double getValue() {
		if (phenotype == null) evaluate();
		return this.phenotype;
	}
	
	@Override
	public Double getAlterValue() {
		if (this.copytype == null)
			return getValue();
		return this.copytype;
	}
	
	public abstract String getGenesToString();
	
	public abstract ChromosomeI copy();

	@Override
	public void displace(double toSum) {
		this.copytype = this.phenotype + toSum;
	}
	
	@Override
	public abstract double getBasicValue();
	
	public ArithmeticNode getRaiz() {
		return null;
	}
}
