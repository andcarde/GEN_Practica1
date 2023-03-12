package model.mutation;

import java.util.List;

import model.chromosome.GenI;
import model.random.RandomGenerator;

public class CityInverseMutation implements CityMutationI {
	
	private Double mutationProbability;

	public CityInverseMutation(Double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

	@Override
	public List<GenI> act(List<GenI> genes) {
		if (RandomGenerator.createAleatoryBoolean(mutationProbability)) {
			int lower_bound = RandomGenerator.createAleatoryInt(genes.size());
			int upper_bound = RandomGenerator.createAleatoryInt(genes.size());
			while (lower_bound == upper_bound) {
				upper_bound = RandomGenerator.createAleatoryInt(genes.size());
			}
			
			if (lower_bound > upper_bound) {
				int tmp = lower_bound;
				lower_bound = upper_bound;
				upper_bound = tmp;
			}
			int i = lower_bound; int j = upper_bound;
			while (i != j) {
				GenI gen1 = genes.get(i).copy();
				GenI gen2 = genes.get(j).copy();
				genes.set(i, gen2);
				genes.set(j, gen1);
				i++; j--;
			}
		}
		return genes;
	
	}

}
