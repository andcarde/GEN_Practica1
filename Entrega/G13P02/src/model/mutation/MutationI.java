package model.mutation;

import model.chromosome.Chromosome;

public interface MutationI {
	
	<T extends Chromosome> T act(T chromosome);
}	
