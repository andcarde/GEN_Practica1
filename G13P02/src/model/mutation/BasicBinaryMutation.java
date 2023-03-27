package model.mutation;

import model.chromosome.BinaryGenI;
import model.random.RandomGenerator;

public class BasicBinaryMutation implements BinaryMutationI {

	private Double mutationProbability;
	
	public BasicBinaryMutation(Double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}
	
	public void act(BinaryGenI gen) {
		for (int i = 0; i < gen.getSize(); i++)
			if (RandomGenerator.createAleatoryBoolean(mutationProbability))
				gen.invertElement(i);
	}
}
