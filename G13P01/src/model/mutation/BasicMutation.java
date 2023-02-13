package model.mutation;

import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;

public class BasicMutation {

	private Double mutationProbability;
	
	public BasicMutation(Double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}
	
	ChromosomeI act(ChromosomeI chromosome) {
		for (int i = 0; i < chromosome.getSize(); i++)
			if (RandomGenerator.createAleatoryBoolean(mutationProbability))
				chromosome.invertElement(i);
		return chromosome;
	}
}
