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
			GenI cur_gen = genes.get(target).copy();
			genes.set(target, gen_to_move); //Movemos el gen a la posicion indicada
										   //despues de guardar el de dicha posicion 
			int cur_index = 0;
			if (pos > target) cur_index = target + 1;
			else cur_index = target - 1;
			while (cur_index != pos) { // Desplazamos el resto hasta 
									  //la posicion origen de gen_to_move
				GenI aux = genes.get(cur_index);
				genes.set(cur_index, cur_gen);
				cur_gen = aux.copy();
				if (pos > target) cur_index++;
				else cur_index--;
			}

		}
		return genes;
	}

}
