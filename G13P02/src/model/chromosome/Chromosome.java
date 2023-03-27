package model.chromosome;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.fitness.Input;

public abstract class Chromosome implements ChromosomeI {
	
	protected Double phenotype;
	private Double copytype;
	protected List<GenI> genes;
	protected MoldI mold;
	
	public Chromosome(MoldI mold) {
		this.mold = mold;
		List<GenI> moldGenes = mold.getGenes();
		this.genes = new ArrayList<>();
		for (GenI gen : moldGenes)
			this.genes.add(gen.copy());
	}
	
	protected Chromosome(Chromosome chromosome) {
		this.phenotype = chromosome.phenotype;
		this.copytype = chromosome.copytype;
		this.genes = new ArrayList<>();
		for (GenI gen : chromosome.genes)
			this.genes.add(gen.copy());
		this.mold = chromosome.mold;
	}
	
	@Override
	public void evaluate() {
		Input input = new Input();
		for (GenI gen : genes)
			input.put(gen.getName(), gen.getValue());
		this.phenotype = this.mold.getFunction().getValue(input);
	}

	@Override
	public Integer getSize() {
		return this.mold.getSize();
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

	@Override
	public List<GenI> getGenes() {
		return this.genes;
	}
	
	public abstract String getGenesToString();
	
	public abstract ChromosomeI copy();

	@Override
	public void displace(double toSum) {
		this.copytype = this.phenotype + toSum;
	}

	@Override
	public void assimilate(List<Object> genome) {
		for (int i = 0; i < this.genes.size(); i++) 
			this.genes.get(i).assimilate(genome.get(i));
	}

	@Override
	public GenI getGen(int i) {
		return this.genes.get(i);
	}

	public void setGen(int i, GenI gen) {
		this.genes.set(i, gen);
	}
}
