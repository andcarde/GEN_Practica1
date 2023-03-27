package model.selection;

import java.util.List;

import model.chromosome.ChromosomeI;

public class Positivizer {

	static void positivizeFitness(List<ChromosomeI> population) {
		double toSum = 0.0;
		for (ChromosomeI chromosome : population) {
			if (chromosome.getValue() < toSum)
				toSum = chromosome.getValue();
		}
		toSum *= -1;
		for (ChromosomeI chromosome : population)
			chromosome.displace(toSum);
	}
}
