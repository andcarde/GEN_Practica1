package model.initialization.practice3;

import model.MoldI;
import model.mutation.practice3.TreeMutationI;

public class InitializerBuilder {

	public static TreePopulationInitializer build(TreeInitializerEnum type, MoldI mold, int maxDepth, int populationAmount, TreeMutationI mutationMethod) {
		switch(type) {
		case FULL:
			return new FullPopulationInitializer(mold, maxDepth, populationAmount, mutationMethod);
		case GROW:
			return new GrowPopulationInitializer(mold, maxDepth, populationAmount, mutationMethod);
		case RAMPED_AND_HALP:
			return new RampedAndHalfPopulationInitializer(mold, maxDepth, populationAmount, mutationMethod);
		default:
			return null;
		
		}
	}
}
