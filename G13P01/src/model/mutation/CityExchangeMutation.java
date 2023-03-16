package model.mutation;

import java.util.List;

import model.chromosome.ChromosomeI;
import model.chromosome.GenI;
import model.random.RandomGenerator;

public class CityExchangeMutation implements CityMutationI {

	private Double mutationProbability;

	public CityExchangeMutation(Double mutation) {
		mutationProbability = mutation;
	}

	@Override
	public List<GenI>  act(ChromosomeI chromosome) { 
		List<GenI> genes = chromosome.getGenes();
		if (RandomGenerator.createAleatoryBoolean(mutationProbability)) {
			int first_to_move = RandomGenerator.createAleatoryInt(genes.size());
			int second_to_move = RandomGenerator.createAleatoryInt(genes.size());
			while (second_to_move == first_to_move) {
				second_to_move = RandomGenerator.createAleatoryInt(genes.size());
			}
			GenI aux = genes.get(first_to_move).copy();
			genes.set(first_to_move, genes.get(second_to_move));
			genes.set(second_to_move, aux);
		}
		return genes;
	}

}
