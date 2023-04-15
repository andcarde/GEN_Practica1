package model.mutation;

import model.chromosome.TreeChromosome;
import model.tree.ArithmeticNode;

public interface TreeMutationI extends MutationI {
	
	ArithmeticNode act(TreeChromosome treeChromosome);
}
