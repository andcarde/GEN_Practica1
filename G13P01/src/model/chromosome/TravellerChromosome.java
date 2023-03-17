package model.chromosome;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.mutation.CityMutationI;
import model.random.RandomGenerator;

public class TravellerChromosome extends Chromosome {
	
	private final CityMutationI mutationMethod;
	
	public TravellerChromosome(MoldI mold, CityMutationI mutationMethod) {
		super(mold);
		this.mutationMethod = mutationMethod;
	}
	
	private TravellerChromosome(TravellerChromosome chromosome) {
		super(chromosome);
		this.mutationMethod = chromosome.mutationMethod;
	}
	
	@Override
	public ChromosomeI copy() {
		return new TravellerChromosome(this);
	}
	
	@Override
	public void mutate() {
		super.genes = mutationMethod.act(this);
	}
	
	// TODO Falta
	@Override
	public String getGenesToString() {
		String g = "";
		if (genes.isEmpty())
			g = "There are no genes";
		else {
			for (int i = 0; i < genes.size(); i++) {
				g = g.concat(genes.get(i).getName() + ": " + genes.get(i).getValue());
				if (i != genes.size() -1)
					g = g.concat("; ");
			}
		}
		return g;
	}
	
	@Override
	public void initialize() {
		List<Integer> cities = new ArrayList<>();
		
		for (int i = 0; i < mold.getNumGenes(); i++)
			cities.add(i);
		cities.set(25, 27);
		int i = 0;
		genes.clear();
		while (!cities.isEmpty()) {
			Integer city = RandomGenerator.createAleatoryInt(cities.size());
			GenI gen = new CityGen("x".concat(String.valueOf(i)), cities.get(city));
			gen.assimilate(cities.get(city));
			this.genes.add(gen);
			cities.remove(cities.get(city));
			i++;
		}
	}
}
