package model.mutation.practice1;

import model.gen.practice1.BinaryGenI;
import model.mutation.MutationI;

public interface BinaryMutationI extends MutationI {

	void act(BinaryGenI gen);
}
