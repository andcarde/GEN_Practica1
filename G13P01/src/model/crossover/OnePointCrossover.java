package model.crossover;

import java.util.ArrayList;
import java.util.List;

import model.chromosome.ChromosomeFunction1;
import model.chromosome.ChromosomeI;
import model.chromosome.InterpreterI;
import model.random.RandomGenerator;

public class OnePointCrossover extends Crossover {	
	
	public OnePointCrossover(InterpreterI interpreter, Double crossProbability) {
		super(interpreter, crossProbability);
	}
	
	@Override
	public List<ChromosomeI> cross(ChromosomeI parent1, ChromosomeI parent2) {
		List<ChromosomeI> sons = new ArrayList<>();
		Integer size = parent1.getSize();
		Integer cutPoint = RandomGenerator.createAleatoryInt(size - 1) + 1;
		List<Boolean> chromosome1 = new ArrayList<>();
		List<Boolean> chromosome2 = new ArrayList<>();
		for (int i = 0; i < cutPoint; i++) {
			chromosome1.add(parent1.getElement(i));
			chromosome2.add(parent2.getElement(i));
		}
		for (int i = cutPoint; i < size; i++) {
			chromosome1.add(parent2.getElement(i));
			chromosome2.add(parent1.getElement(i));
		}
		sons.add(new ChromosomeFunction1(this.interpreter, chromosome1));
		sons.add(new ChromosomeFunction1(this.interpreter, chromosome2));
		return sons;
	}
}
