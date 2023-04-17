package model.mutation.practice3;

import model.chromosome.practice3.TreeChromosome;
import model.random.RandomGenerator;
import model.tree.ArithmeticNode;

public class PermutationTreeMutation implements TreeMutationI {
	
	private double probability;
	
	public PermutationTreeMutation(double probability) {
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
			ArithmeticNode aux = node.getNode(index).getLeftBranch().copy();
			node.getNode(index).setLeftBranch(node.getNode(index).getLeftBranch());
			node.getNode(index).setRightBranch(aux);
		}
		return node;
	}

}
