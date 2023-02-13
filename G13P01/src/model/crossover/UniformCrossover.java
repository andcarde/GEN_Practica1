package model.crossover;

import java.util.ArrayList;
import java.util.List;

import model.Chromosome;
import model.chromosome.ChromosomeI;
import model.chromosome.InterpreterI;
import model.random.RandomGenerator;

public class UniformCrossover extends Crossover {

	public UniformCrossover(InterpreterI interpreter) {
		super(interpreter);
	}
	
	@Override
	public List<Chromosome> cross(ChromosomeI parent1, ChromosomeI parent2) {
		List<Chromosome> sons = new ArrayList<>();
		Integer size = parent1.getSize();
		List<Boolean> chromosome1 = new ArrayList<>();
		List<Boolean> chromosome2 = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			if (RandomGenerator.createAleatoryBoolean(0.5))
				chromosome1.add(parent1.getElement(i));
			else
				chromosome2.add(parent2.getElement(i));
		}
		sons.add(new Chromosome(this.interpreter, chromosome1));
		sons.add(new Chromosome(this.interpreter, chromosome2));
		return sons;
	}
}
