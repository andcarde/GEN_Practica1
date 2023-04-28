package model.crossover;

import model.MoldI;
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
					return null;
			}
		default:
			return null;
		}
	}
}
