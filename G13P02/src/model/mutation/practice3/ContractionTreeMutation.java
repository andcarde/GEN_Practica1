package model.mutation.practice3;

import model.chromosome.practice3.TreeChromosome;
import model.gen.practice3.ArithmeticNode;
import model.gen.practice3.TerminalEnum;
import model.random.RandomGenerator;

public class ContractionTreeMutation implements TreeMutationI {

	private double probability;
	
	public ContractionTreeMutation(double probability) {
		this.probability = probability;
	}

	@Override
	public ArithmeticNode act(TreeChromosome treeChromosome) {
		ArithmeticNode node = treeChromosome.getRaiz().copy();
		if (RandomGenerator.createAleatoryBoolean(probability)) {
			int index = RandomGenerator.createAleatoryInt(node.getNumSons());
			while (node.getNode(index).isLeaf()) {
				index = RandomGenerator.createAleatoryInt(node.getNumSons());
			}
			node.getNode(index).setKnot(null);
			node.getNode(index).setFruit(TerminalEnum.values()[RandomGenerator.createAleatoryInt(TerminalEnum.values().length)]);
		}
		return node;
	}

}
