package model.mutation;

import java.util.List;

import model.chromosome.ChromosomeI;
import model.chromosome.GenI;
import model.random.RandomGenerator;

public class CityInsertionMutation implements CityMutationI {

	private Double mutationProbability;
	
	public CityInsertionMutation(Double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

	@Override
	public List<GenI> act(ChromosomeI chromosome) {
		List<GenI> genes = chromosome.getGenes();
		if (RandomGenerator.createAleatoryBoolean(mutationProbability)) {
			int pos = RandomGenerator.createAleatoryInt(genes.size());
			int target = RandomGenerator.createAleatoryInt(genes.size());
			while (pos == target) {
				target = RandomGenerator.createAleatoryInt(genes.size());
			}
			
			GenI gen_to_move = genes.get(pos).copy();
			gen_to_move.setName(genes.get(target).getName()); //Movemos el gen
			genes.add(target, gen_to_move);
			genes.remove(target);
			
			for (int i = 0; i < genes.size(); i++) {
				genes.get(i).setName("x".concat(String.valueOf(i))); //Actualizamos los nombres
			}
			
			
		}
		return genes;
	}

}
