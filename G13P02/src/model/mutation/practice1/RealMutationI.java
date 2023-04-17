package model.mutation.practice1;

import model.gen.practice1.RealGenI;
import model.mutation.MutationI;

public interface RealMutationI extends MutationI {
	
	void act(RealGenI gen);
}
