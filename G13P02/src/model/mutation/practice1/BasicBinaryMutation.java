package model.mutation.practice1;

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
		CodonChromosome cc = (CodonChromosome) chromosome;
		for (int i = 0; i < cc.getCodonsNumber(); i++)
				if (RandomGenerator.createAleatoryBoolean(mutationProbability))
					cc.mutateCodon(i);
		return (T) cc;
	}
}
