package model.mutation.practice2;

import java.util.List;

import model.chromosome.ChromosomeI;
import model.gen.practice1.GenI;
import model.mutation.MutationI;

public interface CityMutationI extends MutationI {
	
	/***
	 * Mutate the given chromosome.
	 * @param chromosome
	 * @return the list of genes of the resulting chromosome (same if
	 * the mutation not take place according to the mutation probability).
	 */
	List<GenI> act(ChromosomeI chromosome);
}
