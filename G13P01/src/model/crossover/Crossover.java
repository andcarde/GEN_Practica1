package model.crossover;

import java.util.ArrayList;
import java.util.List;

import model.Chromosome;
import model.chromosome.ChromosomeI;
import model.chromosome.InterpreterI;
import model.random.RandomGenerator;

public abstract class Crossover implements CrossoverI {

	protected InterpreterI interpreter;
	private Double crossProbability;
	
	protected Crossover(InterpreterI interpreter, Double crossProbability) {
		this.interpreter = interpreter;
		this.crossProbability = crossProbability;
	}
	
	public List<ChromosomeI> act(List<ChromosomeI> population) {
		List<ChromosomeI> newborns = new ArrayList<>(); 
		List<ChromosomeI> toCross = new ArrayList<>(); 
		for (ChromosomeI ch : population) {
			if (RandomGenerator.createAleatoryBoolean(this.crossProbability))
				toCross.add(ch);
			else
				newborns.add(ch);
		}
		if (toCross.size() % 2 == 1) {
			newborns.add(toCross.get(toCross.size() - 1));
			toCross.remove(toCross.size() - 1);
		}
		while (!toCross.isEmpty()) {
			ChromosomeI parent1 = toCross.get(0);
			ChromosomeI parent2 = toCross.get(RandomGenerator.createAleatoryInt(toCross.size() - 1) + 1);
			newborns.addAll(this.cross(parent1, parent2));
			toCross.remove(parent1);
			toCross.remove(parent2);
		}
		return newborns;
	}
	
	public abstract List<Chromosome> cross(ChromosomeI parent1, ChromosomeI parent2);
}
