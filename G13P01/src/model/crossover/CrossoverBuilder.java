package model.crossover;

import model.MoldI;

public class CrossoverBuilder {

	public static CrossoverI build(CrossoverMethod selectionMethod, Double crossoverProbability, MoldI mold) {
		switch (selectionMethod) {
		case ONE_POINT:
			return new OnePointCrossover(mold, crossoverProbability);
		case UNIFORM:
			return new UniformCrossover(mold, crossoverProbability);
		default:
			return null;
		}
	}
}
