package model.initialization.practice3;

import model.MoldI;
import model.mutation.practice3.TreeMutationI;

public abstract class BinaryTreePopulationInitializer implements TreePopulationInitializer {

	protected final MoldI mold;
	protected final TreeMutationI mutationMethod;
	protected final int populationAmount;
	
	// The maximum depth should be selected in the GUI, with values between 2 and 5
	protected final int maxDepth;
	
	public BinaryTreePopulationInitializer(MoldI mold, int maxDepth, int populationAmount,
			TreeMutationI mutationMethod) {
		this.mold = mold;
		this.maxDepth =  maxDepth;
		this.populationAmount = populationAmount;
		this.mutationMethod = mutationMethod;
	}
}
