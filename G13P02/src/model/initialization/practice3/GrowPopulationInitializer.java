package model.initialization.practice3;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.chromosome.practice3.TreeChromosome;
import model.mutation.practice3.TreeMutationI;

public class GrowPopulationInitializer extends BinaryTreePopulationInitializer {

	public GrowPopulationInitializer(MoldI mold, int maxDepth, int populationAmount, TreeMutationI mutationMethod) {
		super(mold, maxDepth, populationAmount, mutationMethod);
	}
	
	@Override
	public List<TreeChromosome> initialize() {
		List<TreeChromosome> population = new ArrayList<>();
		for (int i = 0; i < populationAmount; i++)
			population.add(new GrowInitializer(mold, maxDepth, mutationMethod).initialize());
		return population;
	}
}
