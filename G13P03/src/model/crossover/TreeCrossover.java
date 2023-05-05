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
	
	
	public TreeCrossover(MoldI mold, double crossProbability) {
		super(mold, crossProbability);
	}
	
	public List<ChromosomeI> cross(ChromosomeI chrParent1, ChromosomeI chrParent2) {
		TreeChromosome parent1 = (TreeChromosome) chrParent1;
		TreeChromosome parent2 = (TreeChromosome) chrParent2;
		List<TreeChromosome> list = new ArrayList<>();
		int index1, index2;
		try {
			index1 = RandomGenerator.createAleatoryInt(parent1.getRaiz().getNumSons());
		} catch(Exception e) {index1 = 0;}
		try {
			index2 = RandomGenerator.createAleatoryInt(parent2.getRaiz().getNumSons());
		} catch(Exception e) {index2 = 0;}
		ArithmeticNode aux = null, aux2 = null;
		try {
			aux = parent1.getRaiz().getNode(index1).copy();
			aux2 = parent2.getRaiz().getNode(index2).copy();
		} catch(Exception e) {
			aux = parent1.getRaiz().getNode(index1);
			aux2 = parent2.getRaiz().getNode(index2);
		}
		parent1.getRaiz().setNode(index1, aux2);
		parent2.getRaiz().setNode(index2, aux);
		
		list.add(parent1);
		list.add(parent2);
		return Cast.castTreeToChromosome(list);
	}
}
