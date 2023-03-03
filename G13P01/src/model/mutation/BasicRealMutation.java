package model.mutation;

import model.chromosome.RealGenI;

public class BasicRealMutation implements RealMutationI {

	private Double mutationProbability;
	
	public BasicRealMutation(Double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}
	
	@Override
	public void act(RealGenI gen) {
		gen
	}
}
