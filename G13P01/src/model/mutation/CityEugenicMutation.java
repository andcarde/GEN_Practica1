package model.mutation;

import java.util.ArrayList;
import java.util.List;

import model.chromosome.ChromosomeI;
import model.chromosome.GenI;
import model.fitness.FunctionTSP;
import model.random.RandomGenerator;
import model.util.Pair;

public class CityEugenicMutation implements CityMutationI {
	
	private double mutationProbability;
	
	public CityEugenicMutation(Double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

	@Override
	public List<GenI> act(ChromosomeI chromosome) {
		List<GenI> genes = chromosome.getGenes();
		if (!RandomGenerator.createAleatoryBoolean(mutationProbability))
			return genes;
		
		//Sacamos el valor de cada ciudad, el cual se obtiene al hallar 
		//la distancia de sus adyacentes
		List<Pair<String, Double>> valores = new ArrayList<>();
		for (int i = 0; i < genes.size(); i++) { 
			int izq = i - 1; int der = i + 1;
			if (i == 0) izq = genes.size() - 1;
			if (der == genes.size() - 1) izq = 0;
			Double value = (FunctionTSP.getDist(i, izq) + FunctionTSP.getDist(i, der))/2 + 0.0; 
			valores.add(new Pair<String, Double>(genes.get(i).getName(), value));
		}
		
		//Hallamos los dos peores indices
		int worst_index = 0;
		int less_worse_index = 1;
		if (valores.get(worst_index).getR() > valores.get(less_worse_index).getR()) {
			int tmp = worst_index;
			worst_index = less_worse_index;
			less_worse_index = tmp;
		} 
		for (int i = 2; i < valores.size(); i++) {
			if (valores.get(i).getR() < valores.get(less_worse_index).getR()) {
				if (valores.get(i).getR() < valores.get(worst_index).getR()) {
					less_worse_index = worst_index;
					worst_index = i;
				}
				else less_worse_index = i;
			}
		}
		
		
		//Los intercambiamos de posicion
		GenI worst = genes.get(worst_index).copy();
		GenI less_worse = genes.get(less_worse_index).copy();
		String worst_name = worst.getName();
		worst.setName(less_worse.getName());
		less_worse.setName(worst_name);
		genes.set(less_worse_index, worst);
		genes.set(worst_index, less_worse);
		return genes;
	}
}
