package model.mutation;

import model.chromosome.TreeChromosome;
import model.random.RandomGenerator;
import model.tree.ArithmeticEnum;
import model.tree.ArithmeticNode;
import model.tree.TerminalEnum;

public class BasicTreeMutation implements TreeMutationI {
	
	private double probability;
	
	public BasicTreeMutation(double probability) {
		this.probability = probability;
	}

	@Override
	public ArithmeticNode act(TreeChromosome treeChromosome) {
		ArithmeticNode node = treeChromosome.getRaiz().copy();
		if (RandomGenerator.createAleatoryBoolean(probability)) {
			int index = RandomGenerator.createAleatoryInt(treeChromosome.getRaiz().getNumSons());
			if (treeChromosome.getRaiz().getNode(index).isLeaf()) {
				treeChromosome.getRaiz().getNode(index).setFruit(TerminalEnum.values()[RandomGenerator.createAleatoryInt(TerminalEnum.values().length)]);
			}
			else treeChromosome.getRaiz().getNode(index).setKnot(ArithmeticEnum.values()[RandomGenerator.createAleatoryInt(ArithmeticEnum.values().length)]);
		}
		return node;
	}

}
