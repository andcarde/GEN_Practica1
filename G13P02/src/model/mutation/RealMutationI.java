package model.mutation;

import model.chromosome.RealGenI;

public interface RealMutationI extends MutationI {
	
	void act(RealGenI gen);
}
