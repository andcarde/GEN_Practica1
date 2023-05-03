package model.initialization.practice3;

import model.MoldI;

public class InitializerBuilder {

	public static TreePopulationInitializer build(TreeInitializerEnum type, MoldI mold, int maxDepth,
			int populationAmount) {
		switch(type) {
		case FULL:
			return new FullPopulationInitializer(mold, maxDepth, populationAmount);
		case GROW:
			return new GrowPopulationInitializer(mold, maxDepth, populationAmount);
		case RAMPED_AND_HALP:
			return new RampedAndHalfPopulationInitializer(mold, maxDepth, populationAmount);
		default:
			return null;
		}
	}
}
