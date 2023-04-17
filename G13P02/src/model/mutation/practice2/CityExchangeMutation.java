package model.mutation.practice2;

import java.util.List;

import model.chromosome.ChromosomeI;
import model.gen.practice1.GenI;
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
			//Obtenemos los dos indices
			int first_to_move = RandomGenerator.createAleatoryInt(genes.size());
			int second_to_move = RandomGenerator.createAleatoryInt(genes.size());
			while (second_to_move == first_to_move) {
				second_to_move = RandomGenerator.createAleatoryInt(genes.size());
			}
			
			//Se intercambian de posicion
			GenI gen1 = genes.get(first_to_move).copy();
			gen1.setName(genes.get(second_to_move).getName());
			GenI gen2 = genes.get(second_to_move).copy();
			gen2.setName(genes.get(first_to_move).getName());
			genes.set(first_to_move, gen2);
			genes.set(second_to_move, gen1);
		}
		return genes;
	}

}
