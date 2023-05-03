package model.crossover.practice1;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.chromosome.ChromosomeI;
import model.chromosome.practice3.CodonChromosome;
import model.crossover.Crossover;
import model.random.RandomGenerator;

public class UniformCrossover extends Crossover {

	public UniformCrossover(MoldI mold, Double crossProbability) {
		super(mold, crossProbability);
	}

	@Override
	public List<ChromosomeI> cross(ChromosomeI parent1, ChromosomeI parent2) {
		List<ChromosomeI> sons = new ArrayList<>();
		CodonChromosome son1 = new CodonChromosome(this.mold);
		CodonChromosome son2 = new CodonChromosome(this.mold);
		
		Object genome1, genome2;
		for (int i = 0; i < parent1.getGenes().size(); i++) {
			genome1 = parent1.getGenes().get(i);
			genome2 = parent2.getGenes().get(i);
			if (RandomGenerator.createAleatoryBoolean(0.5)) {
				son1.setGen(i, son1.getGenes().get(i).copy().assimilate(genome1));
				son2.setGen(i, son1.getGenes().get(i).copy().assimilate(genome2));
			}
			else {
				son1.setGen(i, son1.getGenes().get(i).copy().assimilate(genome2));
				son2.setGen(i, son1.getGenes().get(i).copy().assimilate(genome1));
			}
		}
		
		sons.add(son1);
		sons.add(son2);
		return sons;
	}
}
