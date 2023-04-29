package model.initialization.practice3;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.chromosome.practice3.TreeChromosome;

public class FullPopulationInitializer extends BinaryTreePopulationInitializer {

	public FullPopulationInitializer(MoldI mold, int maxDepth, int populationAmount) {
		super(mold, maxDepth, populationAmount);
	}
	
	@Override
	public List<TreeChromosome> initialize() {
		List<TreeChromosome> population = new ArrayList<>();
		for (int i = 0; i < populationAmount; i++)
			population.add(new FullInitializer(mold, maxDepth).initialize());
		return population;
	}
}
