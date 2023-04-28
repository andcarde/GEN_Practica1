package model.util;

import java.util.List;

import model.chromosome.ChromosomeI;
import model.chromosome.practice3.TreeChromosome;

public class Covariance {
	
	public static double calculate(List<TreeChromosome> population) {
		double value = 0, averageSize = 0, averagefitness = 0;
		for (ChromosomeI tree : population) {
			averagefitness += tree.getBasicValue();
			averageSize += tree.getSize();
		}
		averagefitness /= population.size();
		averageSize /= population.size();
		
		for (ChromosomeI tree : population) {
			value += (tree.getSize()-averageSize)*(tree.getBasicValue()-averagefitness);
		}	
		return value/population.size();
	}
}
