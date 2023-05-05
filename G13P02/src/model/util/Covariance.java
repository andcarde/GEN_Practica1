package model.util;

import java.util.List;

import model.chromosome.ChromosomeI;

public class Covariance {
	
	public static double calculate(List<ChromosomeI> population) {
		double value = 0, averageSize = 0, averagefitness = 0;
		
		for (ChromosomeI tree : population) {
			averagefitness += tree.getBasicValue();
			averageSize += tree.getSize();
		}
		
		averagefitness /= population.size();
		averageSize /= population.size();
		
		for (ChromosomeI tree : population) {
			double v1 = tree.getSize() - averageSize;
			double v2 = tree.getBasicValue() - averagefitness;
			value += v1 * v2;
		}	
		return value / population.size();
	}
}
