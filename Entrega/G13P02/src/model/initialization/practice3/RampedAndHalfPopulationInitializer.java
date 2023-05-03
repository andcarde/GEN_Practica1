package model.initialization.practice3;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.chromosome.practice3.TreeChromosome;

public class RampedAndHalfPopulationInitializer extends BinaryTreePopulationInitializer {
	
	public RampedAndHalfPopulationInitializer(MoldI mold, int maxDepth, int populationAmount) {
		super(mold, maxDepth, populationAmount);
	}
	
	@Override
	public List<TreeChromosome> initialize() {
		List<TreeChromosome> population = new ArrayList<>();
		
		int depth = 2;
		
		for (int i = 0; i < populationAmount / 2; i++) {
			population.add(new FullInitializer(mold, depth).initialize());
			population.add(new GrowInitializer(mold, depth).initialize());
			depth++;
			if (depth > maxDepth)
				depth = 2;
		}
		
		if (populationAmount % 2 == 1)
			population.add(new FullInitializer(mold, depth).initialize());
		
		return population;
	}
}
