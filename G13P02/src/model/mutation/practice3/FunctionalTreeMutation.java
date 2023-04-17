package model.mutation.practice3;

import model.chromosome.practice3.TreeChromosome;
import model.random.RandomGenerator;
import model.tree.ArithmeticEnum;
import model.tree.ArithmeticNode;
import model.tree.TerminalEnum;

public class FunctionalTreeMutation implements TreeMutationI {
	
	private double probability;
	
	public FunctionalTreeMutation(double probability) {
		this.probability = probability;
	}

	@Override
	public ArithmeticNode act(TreeChromosome treeChromosome) {
		ArithmeticNode node = treeChromosome.getRaiz().copy();
		if (RandomGenerator.createAleatoryBoolean(probability)) {
			int index = RandomGenerator.createAleatoryInt(node.getNumSons());
			if (node.getNode(index).isLeaf()) {
				node.getNode(index).setFruit(TerminalEnum.values()[RandomGenerator.createAleatoryInt(TerminalEnum.values().length)]);
			}
			else node.getNode(index).setKnot(ArithmeticEnum.values()[RandomGenerator.createAleatoryInt(ArithmeticEnum.values().length)]);
		}
		return node;
	}

}
