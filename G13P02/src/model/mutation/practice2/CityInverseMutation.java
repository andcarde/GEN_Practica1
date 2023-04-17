package model.mutation.practice2;

import java.util.List;

import model.chromosome.ChromosomeI;
import model.gen.practice1.GenI;
import model.random.RandomGenerator;

public class CityInverseMutation implements CityMutationI {
	
	private Double mutationProbability;

	public CityInverseMutation(Double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

	@Override
	public List<GenI> act(ChromosomeI chromosome) {
		List<GenI> genes = chromosome.getGenes();
		if (RandomGenerator.createAleatoryBoolean(mutationProbability)) {
			int lower_bound = RandomGenerator.createAleatoryInt(genes.size());
			int upper_bound = RandomGenerator.createAleatoryInt(genes.size());
			while (lower_bound == upper_bound) {
				upper_bound = RandomGenerator.createAleatoryInt(genes.size());
			}
			//Hallamos los dos puntos del tramo
			if (lower_bound > upper_bound) {
				int tmp = lower_bound;
				lower_bound = upper_bound;
				upper_bound = tmp;
			}
			
			//Los intercambiamos uno a uno con respecto al que esta 
			//en la posición inversa del tramo
			int i = lower_bound; int j = upper_bound;
			while (i < j) {
				GenI gen1 = genes.get(i).copy();
				GenI gen2 = genes.get(j).copy();
				gen1.setName(genes.get(j).getName());
				gen2.setName(genes.get(i).getName());
				genes.set(i, gen2);
				genes.set(j, gen1);
				i++; j--;
			}
		}
		return genes;
	
	}

}
