package model.mutation;

import java.util.ArrayList;
import java.util.List;

import model.chromosome.ChromosomeI;

public class PopulationMutation {

	public static List<ChromosomeI> act(List<ChromosomeI> population) {
		List<ChromosomeI> newPopulation = new ArrayList<>();
		for (ChromosomeI chromosome : population)
			newPopulation.add(chromosome.createMutatedCopy());
		return newPopulation;
	}
}
