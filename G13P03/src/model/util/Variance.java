package model.util;

import java.util.List;

import model.chromosome.ChromosomeI;

public class Variance {
	
	public static double calculate(List<ChromosomeI> population) {
		double value = 0, averageSize = 0;
		
		for (ChromosomeI tree : population)
			averageSize += tree.getSize();
		averageSize /= population.size();
		
		for (ChromosomeI tree : population)
			value += Math.pow(tree.getSize() - averageSize, 2);
		
		return value / population.size();
	}

}
