package model.mutation;

import model.chromosome.ChromosomeI;

public interface MutationI {

	ChromosomeI act(ChromosomeI chromosome);
}
