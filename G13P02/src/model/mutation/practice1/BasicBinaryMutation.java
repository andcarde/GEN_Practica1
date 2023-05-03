package model.mutation.practice1;

import java.util.List;

import model.chromosome.Chromosome;
import model.chromosome.practice3.CodonChromosome;
import model.mutation.MutationI;
import model.random.RandomGenerator;

public class BasicBinaryMutation implements MutationI {

	private Double mutationProbability;
	
	public BasicBinaryMutation(Double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Chromosome> T act(T chromosome) {
		CodonChromosome cc = ((CodonChromosome) chromosome).copy();
		for (List<Boolean> bits : cc.getBits())
			for (Boolean bit : bits)
				if (RandomGenerator.createAleatoryBoolean(mutationProbability))
					bit = !bit;
		return (T) cc;
	}
}
