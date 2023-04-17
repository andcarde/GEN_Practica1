package model.mutation.practice3;

import model.chromosome.practice3.TreeChromosome;
import model.mutation.MutationI;
import model.tree.ArithmeticNode;

public interface TreeMutationI extends MutationI {
	
	ArithmeticNode act(TreeChromosome treeChromosome);
}
