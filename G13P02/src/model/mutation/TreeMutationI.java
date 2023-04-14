package model.mutation;

import model.chromosome.TreeChromosome;

public interface TreeMutationI extends MutationI {
	
	TreeChromosome act(TreeChromosome treeChromosome);
}
