package model.chromosome;

import model.MoldI;
import model.fitness.FunctionTSP;
import model.mutation.CityMutationI;
import model.util.Converter;

public class TreeChromosome extends Chromosome {

	private final CityMutationI mutationMethod;
	private ArithmeticNode raiz;
	
	public TreeChromosome(MoldI mold, CityMutationI mutationMethod) {
		super(mold);
		this.mutationMethod = mutationMethod;
	}
	
	private TreeChromosome(TreeChromosome chromosome) {
		super(chromosome);
		this.mutationMethod = chromosome.mutationMethod;
	}
	
	@Override
	public ChromosomeI copy() {
		return new TreeChromosome(this);
	}
	
	@Override
	public void mutate() {
		super.genes = mutationMethod.act(this);
	}
	
	/***
	 * TODO
	 */
	@Override
	public String getGenesToString() {
		String g = "";
		if (genes.isEmpty())
			g = "There are no genes";
		else {
			for (int i = 0; i < genes.size(); i++) {
				String city = FunctionTSP.toCityName(Converter.DoubleToInt(genes.get(i).getValue()));
				g = g.concat(city);
				if (i != genes.size() -1) g = g.concat(" --> ");
				if (i % 6 == 0 && i > 0) g = g.concat("\r\n");
			}
		}
		return g;
	}
	
	/***
	 * Initialize the values of the genes to a random tree.
	 */
	@Override
	public void initialize() {
		
	}

	public CityMutationI getMutation() {
		return mutationMethod;
	}

	@Override
	public int indexOf(int city) {
		return 0;
	}


}
