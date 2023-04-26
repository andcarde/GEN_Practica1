package model.mutation.practice3;

import model.chromosome.practice3.TreeChromosome;
import model.gen.practice3.ArithmeticEnum;
import model.gen.practice3.ArithmeticNode;
import model.random.RandomGenerator;

public class FunctionalTreeMutation implements TreeMutationI {
	
	private double probability;
	
	public FunctionalTreeMutation(double probability) {
		this.probability = probability;
	}

	@Override
	public ArithmeticNode act(TreeChromosome treeChromosome) {
		ArithmeticNode node = treeChromosome.getRaiz().copy();
		if (RandomGenerator.createAleatoryBoolean(probability)) {
			int index = 0;
			do {
				index = RandomGenerator.createAleatoryInt(node.getNumSons());
				
			} while (node.getNode(index).isLeaf());
			node.getNode(index).setKnot(ArithmeticEnum.values()[RandomGenerator.createAleatoryInt(ArithmeticEnum.values().length)]);
		}
		return node;
	}

}
