package model.crossover;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.chromosome.ChromosomeI;
import model.chromosome.practice3.TreeChromosome;
import model.gen.practice3.ArithmeticNode;
import model.random.RandomGenerator;
import model.util.Cast;

public class TreeCrossover extends Crossover {
	
	private double probability;
	
	public TreeCrossover(MoldI mold, double crossProbability) {
		super(mold, crossProbability);
	}
	
	public List<ChromosomeI> cross(ChromosomeI chrParent1, ChromosomeI chrParent2) {
		TreeChromosome parent1 = (TreeChromosome) chrParent1;
		TreeChromosome parent2 = (TreeChromosome) chrParent2;
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
		return Cast.castTreeToChromosome(list);
	}
}
