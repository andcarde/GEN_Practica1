package model.initialization.practice3;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.chromosome.practice3.TreeChromosome;
import model.mutation.practice3.TreeMutationI;

public class RampedAndHalfPopulationInitializer extends BinaryTreePopulationInitializer {
	
	public RampedAndHalfPopulationInitializer(MoldI mold, int maxDepth, int populationAmount, TreeMutationI mutationMethod) {
		super(mold, maxDepth, populationAmount, mutationMethod);
	}
	
	@Override
	public List<TreeChromosome> initialize() {
		List<TreeChromosome> population = new ArrayList<>();
		
		int depth = 2;
		
		for (int i = 0; i < populationAmount / 2; i++) {
			population.add(new FullInitializer(mold, depth, mutationMethod).initialize());
			population.add(new GrowInitializer(mold, depth, mutationMethod).initialize());
			depth++;
			if (depth == maxDepth)
				depth = 2;
		}
		
		if ( populationAmount % 2 == 1)
			population.add(new FullInitializer(mold, depth, mutationMethod).initialize());
		
		return population;
	}
}
