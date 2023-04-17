package model.mutation.practice3;

import model.chromosome.practice3.TreeChromosome;
import model.gen.practice3.ArithmeticNode;
import model.mutation.MutationI;

public interface TreeMutationI extends MutationI {
	
	ArithmeticNode act(TreeChromosome treeChromosome);
}
