package model.chromosome;

import model.MoldI;

public abstract class Chromosome implements ChromosomeI {
	
	protected Double phenotype;
	private Double copytype;
	protected MoldI mold;
	
	public Chromosome(MoldI mold) {
		this.mold = mold;
	}
	
	protected Chromosome(Chromosome chromosome) {
		if (chromosome.phenotype != null)
			this.phenotype = Double.valueOf(chromosome.phenotype);
		if (chromosome.copytype != null)
			this.copytype = Double.valueOf(chromosome.copytype);
		this.mold = chromosome.mold;
	}

	@Override
	public MoldI getMold() {
		return mold;
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
		copytype = phenotype + toSum;
	}
}
