package model.chromosome;

import java.text.DecimalFormat;

import model.MoldI;

public class BoundedChromosome extends Chromosome {

	public BoundedChromosome(MoldI mold) {
		super(mold);
	}
	
	private BoundedChromosome(BoundedChromosome chromosome) {
		super(chromosome);
	}
	
	@Override
	public ChromosomeI copy() {
		return new BoundedChromosome(this);
	}

	@Override
	public void initialize() {
		for (GenI gen : this.genes)
			((BoundedGenI) gen).initialize();
	}
	
	@Override
	public void mutate() {
		for (GenI gen : this.genes)
			((BoundedGenI) gen).mutate();
	}
	
	@Override
	public String getGenesToString() {
		String g = "";
		if (genes.isEmpty())
			g = "There are no genes";
		else {
			for (int i = 0; i < genes.size(); i++) {
				g = g.concat(genes.get(i).getName() + ": " + new DecimalFormat("#.0000").format(genes.get(i).getValue()));
				if (i != genes.size() -1)
					g = g.concat("; ");
			}
		}
		return g;
	}
}
