package model.initialization.practice3;

import model.Executor;
import model.MoldI;
import model.chromosome.practice3.TreeChromosome;
import model.gen.practice3.ArithmeticNode;
import model.mutation.practice3.TreeMutationI;

public abstract class BinaryTreeInitializer implements TreeInitializer {

	protected final MoldI mold;
	protected final TreeMutationI mutationMethod;
	protected final int maxDepth;
	protected int depth;
	protected Executor exe;
	
	public BinaryTreeInitializer(MoldI mold, int maxDepth, TreeMutationI mutationMethod) {
		this.mold = mold;
		this.maxDepth =  maxDepth;
		this.depth = 0;
		this.mutationMethod = mutationMethod;
	}
	
	@Override
	public TreeChromosome initialize() {
		TreeChromosome chromosome = new TreeChromosome(mold, mutationMethod);
		chromosome.setRaiz(initNode());
		return chromosome;
	}

	protected abstract ArithmeticNode initNode();
}
