package model.mutation;

import java.util.List;

import model.chromosome.ChromosomeI;
import model.chromosome.GenI;
import model.random.RandomGenerator;

public class BasicMutation implements MutationI {

	private Double mutationProbability;
	
	public BasicMutation(Double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}
	
	@Override
	public void act(ChromosomeI chromosome) {
		List<GenI> genes = chromosome.getGenes();
		for (GenI gen : genes)
			for (int i = 0; i < gen.getSize(); i++)
				if (RandomGenerator.createAleatoryBoolean(mutationProbability))
					gen.invertElement(i);
	}
}
