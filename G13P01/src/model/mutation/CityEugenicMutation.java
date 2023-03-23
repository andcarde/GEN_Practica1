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
		List<Pair<String, Double>> valores = new ArrayList<>();
		for (int i = 0; i < genes.size(); i++) {
			int izq = i - 1; int der = i + 1;
			if (i == 0) izq = genes.size() - 1;
			if (der == genes.size() - 1) izq = 0;
			Double value = (FunctionTSP.getDist(i, izq) + FunctionTSP.getDist(i, der))/2 + 0.0; 
			valores.add(new Pair<String, Double>(genes.get(i).getName(), value));
		}
		
		//TODO
		return genes;
	}
}
