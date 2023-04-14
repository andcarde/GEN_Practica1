package model.initialization;

import model.chromosome.TreeChromosome;
import model.mutation.TreeMutationI;
import model.random.RandomGenerator;
import model.tree.ArithmeticEnum;
import model.tree.ArithmeticNode;
import model.tree.TerminalEnum;

public class FullInit implements TreeInitializer {

	// The maximum depth should be selected in the GUI, with values between 2 and 5
	
	private final int maxDepth;
	private int depth;
	private TreeMutationI mutationMethod;
	
	public FullInit(int maxDepth, TreeMutationI mutationMethod) {
		this.maxDepth =  maxDepth;
		this.depth = 0;
		this.mutationMethod = mutationMethod;
	}
	
	@Override
	public TreeChromosome initialize() {
		TreeChromosome chromosome = new TreeChromosome(mutationMethod);
		chromosome.setRaiz(initNode());
		return chromosome;
	}
	
	private ArithmeticNode initNode() {
		ArithmeticNode node = new ArithmeticNode();
		if (depth < maxDepth) {
			depth += 1;
			node.setLeftBranch(initNode());
			node.setRightBranch(initNode());
			depth -= 1;
			ArithmeticEnum[] values = ArithmeticEnum.values();
			int index = RandomGenerator.createAleatoryInt(values.length);
			node.setKnot(values[index]);
			node.setNumSons(node.getLeftBranch().getNumSons() + node.getRightBranch().getNumSons() + 2);
		} else {
			TerminalEnum[] values = TerminalEnum.values();
			int index = RandomGenerator.createAleatoryInt(values.length);
			node.setFruit(values[index]);
		}
		return node;
	}
}
