package model.chromosome;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.fitness.Input;

public class Chromosome implements ChromosomeI {
	
	private Double phenotype;
	private List<GenI> genes;
	private MoldI mold;
	
	public Chromosome(MoldI mold) {
		this.mold = mold;
		List<Gen> moldGenes = mold.getGenes();
		this.genes = new ArrayList<>();
		for (Gen gen : moldGenes)
			this.genes.add(new Gen(gen));
	}
	
	private Chromosome(Chromosome chromosome) {
		this.phenotype = chromosome.phenotype;
		this.genes = new ArrayList<>();
		for (GenI gen : chromosome.genes)
			this.genes.add(new Gen((Gen) gen));
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
	public void initialize() {
		for (GenI gen : this.genes)
			gen.initialize();
	}

	@Override
	public Integer getSize() {
		return this.mold.getSize();
	}
	
	@Override
	public Boolean getElement(Integer i) {
		Integer accumulated = 0;
		for (GenI gen : this.genes) {
			if (i < gen.getSize() + accumulated)
				return gen.getBit(i - accumulated);
			accumulated += gen.getSize();
		}
		return null;
	}

	@Override
	public MoldI getMold() {
		return this.mold;
	}

	@Override
	public Double getValue() {
		return this.phenotype;
	}

	@Override
	public List<GenI> getGenes() {
		return this.genes;
	}

	@Override
	public void assimilate(List<Boolean> genome) {
		Integer accumulated = 0;
		for (GenI gen : this.genes) {
			List<Boolean> bits = genome.subList(accumulated, accumulated + gen.getSize());
			gen.assimilate(bits);
			accumulated += gen.getSize();
		}
	}

	@Override
	public String getGenesToString() {
		String g = "";
		if (genes.isEmpty())
			g = "There are no genes";
		else {
			for (int i = 0; i < genes.size(); i++) {
				g = g.concat(genes.get(i).getName() + ": " + genes.get(i).getValue());
				if (i != genes.size() -1)
					g = g.concat(", ");
			}
		}
		return g;
	}

	@Override
	public ChromosomeI copy() {
		return new Chromosome(this);
	}

	@Override
	public void displace(double toSum) {
		this.phenotype += toSum;
	}
}
