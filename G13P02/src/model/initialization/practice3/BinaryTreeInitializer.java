package model.initialization.practice3;

import model.MoldI;
import model.chromosome.practice3.TreeChromosome;
import model.gen.practice3.ArithmeticNode;

public abstract class BinaryTreeInitializer implements TreeInitializer {

	protected final MoldI mold;
	protected final int maxDepth;
	protected int depth;
	
	public BinaryTreeInitializer(MoldI mold, int maxDepth) {
		this.mold = mold;
		this.maxDepth =  maxDepth;
		this.depth = 0;
	}
	
	@Override
	public TreeChromosome initialize() {
		TreeChromosome chromosome = new TreeChromosome(mold, mold.getMutation());
		chromosome.setRaiz(initNode());
		return chromosome;
	}
	
	protected abstract ArithmeticNode initNode();
}
