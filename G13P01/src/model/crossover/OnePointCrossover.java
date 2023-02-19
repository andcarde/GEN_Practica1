package model.crossover;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.chromosome.Chromosome;
import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;

public class OnePointCrossover extends Crossover {	
	
	public OnePointCrossover(MoldI mold, Double crossProbability) {
		super(mold, crossProbability);
	}
	
	@Override
	public List<ChromosomeI> cross(ChromosomeI parent1, ChromosomeI parent2) {
		List<ChromosomeI> sons = new ArrayList<>();
		Chromosome son1 = new Chromosome(this.mold);
		Chromosome son2 = new Chromosome(this.mold);
		
		List<Boolean> genome1 = new ArrayList<>();
		List<Boolean> genome2 = new ArrayList<>();
		
		Integer cutPoint = RandomGenerator.createAleatoryInt(mold.getSize() - 1) + 1;

		for (int i = 0; i < cutPoint; i++) {
			genome1.add(parent1.getElement(i));
			genome2.add(parent2.getElement(i));
		}
		for (int i = cutPoint; i < mold.getSize(); i++) {
			genome1.add(parent2.getElement(i));
			genome2.add(parent1.getElement(i));
		}
		
		son1.assimilate(genome1);
		son2.assimilate(genome2);
		
		sons.add(son1);
		sons.add(son2);
		return sons;
	}
}
