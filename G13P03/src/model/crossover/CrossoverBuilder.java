package model.crossover;

import model.MoldI;
import model.crossover.practice1.BinaryOnePointCrossover;
import model.crossover.practice1.UniformCrossover;
import model.gen.practice3.GenType;

public class CrossoverBuilder {

	public static CrossoverI build(CrossoverMethod selectionMethod, Double crossoverProbability,
			MoldI mold, GenType genType) {
		switch (genType) {
			case TREE:
				switch (selectionMethod) {
					case CROSSOVER_TREE:
						return new TreeCrossover(mold, crossoverProbability);
					default:
						break;
				}
				break;
			case BINARY:
				switch (selectionMethod) {
				case ONE_POINT:
					return new BinaryOnePointCrossover(mold, crossoverProbability);
				case UNIFORM:
					return new UniformCrossover(mold, crossoverProbability);
				default:
					break;
				}
				break;
			}
		return null;
	}
}
