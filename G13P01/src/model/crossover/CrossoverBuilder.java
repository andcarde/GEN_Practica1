package model.crossover;

import model.MoldI;
import model.chromosome.GenType;

public class CrossoverBuilder {

	public static CrossoverI build(CrossoverMethod selectionMethod, Double crossoverProbability,
			MoldI mold, GenType genType) {
		switch (genType) {
		case BINARY:
			switch (selectionMethod) {
			case ONE_POINT:
				return new BinaryOnePointCrossover(mold, crossoverProbability);
			case UNIFORM:
				return new UniformCrossover(mold, crossoverProbability);
			default:
				return null;
			}
		case REAL:
			switch (selectionMethod) {
			case ONE_POINT:
				return new RealOnePointCrossover(mold, crossoverProbability);
			case UNIFORM:
				return new UniformCrossover(mold, crossoverProbability);
			default:
				return null;
			}
		default:
			return null;
		}
	}
}
