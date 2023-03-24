package model.crossover;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.chromosome.BoundedChromosome;
import model.chromosome.BoundedGenI;
import model.chromosome.Chromosome;
import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;

public class RealOnePointCrossover extends Crossover {

	public RealOnePointCrossover(MoldI mold, Double crossProbability) {
		super(mold, crossProbability);
	}
	
	@Override
	public List<ChromosomeI> cross(ChromosomeI parent1, ChromosomeI parent2) {
		List<ChromosomeI> sons = new ArrayList<>();
		Chromosome son1 = new BoundedChromosome(this.mold);
		Chromosome son2 = new BoundedChromosome(this.mold);
		
		Integer cutPoint = RandomGenerator.createAleatoryInt(mold.getGenes().size() - 1) + 1;
		
		Object genome1, genome2;
		for (int i = 0; i < cutPoint; i++) {
			genome1 = parent1.getGen(i).getGenome();
			genome2 = parent2.getGen(i).getGenome();
			son1.setGen(i, (BoundedGenI) son1.getGen(i).copy().assimilate(genome1));
			son2.setGen(i, (BoundedGenI) son1.getGen(i).copy().assimilate(genome2));
		}
		for (int i = cutPoint; i < mold.getGenes().size(); i++) {
			genome1 = parent1.getGen(i).getGenome();
			genome2 = parent2.getGen(i).getGenome();
			son1.setGen(i, (BoundedGenI) son1.getGen(i).copy().assimilate(genome2));
			son2.setGen(i, (BoundedGenI) son1.getGen(i).copy().assimilate(genome1));
		}
		
		sons.add(son1);
		sons.add(son2);
		return sons;
	}
}
