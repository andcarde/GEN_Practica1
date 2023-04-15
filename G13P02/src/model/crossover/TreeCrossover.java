package model.crossover;

import java.util.ArrayList;
import java.util.List;

import model.chromosome.TreeChromosome;
import model.random.RandomGenerator;
import model.tree.ArithmeticNode;

public class TreeCrossover {
	
	private double probability;
	
	public TreeCrossover(double crossProbability) {
		probability = crossProbability;
	}
	public List<TreeChromosome> cross(TreeChromosome parent1, TreeChromosome parent2) {
		List<TreeChromosome> list = new ArrayList<>();
		if (RandomGenerator.createAleatoryBoolean(probability)) {
			int index1 = RandomGenerator.createAleatoryInt(parent1.getRaiz().getNumSons());
			int index2 = RandomGenerator.createAleatoryInt(parent2.getRaiz().getNumSons());
	
			ArithmeticNode aux = parent1.getRaiz().getNode(index1).copy();
			
			parent1.getRaiz().setNode(index1, parent2.getRaiz().getNode(index2).copy());
			parent2.getRaiz().setNode(index2, aux);
		}
		list.add(parent1);
		list.add(parent2);
		return list;
	}
}
