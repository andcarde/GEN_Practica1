package model.mutation;

import model.chromosome.BinaryGenI;

public interface BinaryMutationI extends MutationI {

	void act(BinaryGenI gen);
}
