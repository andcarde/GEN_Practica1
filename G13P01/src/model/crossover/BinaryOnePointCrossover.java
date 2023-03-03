package model.crossover;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.chromosome.Chromosome;
import model.chromosome.ChromosomeI;
import model.chromosome.GenI;
import model.random.RandomGenerator;

public class BinaryOnePointCrossover extends Crossover {

	public BinaryOnePointCrossover(MoldI mold, Double crossProbability) {
		super(mold, crossProbability);
	}
	
	@Override
	public List<ChromosomeI> cross(ChromosomeI parent1, ChromosomeI parent2) {
		List<ChromosomeI> sons = new ArrayList<>();
		Chromosome son1 = new Chromosome(this.mold);
		Chromosome son2 = new Chromosome(this.mold);
		
		List<Object> genome1 = new ArrayList<>();
		List<Object> genome2 = new ArrayList<>();
		
		Integer cutPoint = RandomGenerator.createAleatoryInt(mold.getSize() - 1) + 1;
		
		Integer accumulated = 0;
		
		for (int i = 0; i < mold.getGenes().size(); i++) {
			if (accumulated + mold.getGenes().get(i).)
		}
		
		//List<Boolean> bits = genome.subList(accumulated, accumulated + gen.getSize());
		for (int i = 0; i < cutPoint; i++) {
			Boolean bit = parent1.getElement(i);
			genome1.add(bit);
			Integer accumulated = 0;
			for (GenI gen : this.genes) {
				if (i < gen.getSize() + accumulated)
					return gen.getBit(i - accumulated);
				accumulated += gen.getSize();
			}
			return null;
			genome2.add((boolean) parent2.getElement(i));
		}
		for (int i = cutPoint; i < mold.getSize(); i++) {
			genome1.add((boolean) parent2.getElement(i));
			genome2.add((boolean) parent1.getElement(i));
		}
		
		son1.assimilate(genome1);
		son2.assimilate(genome2);
		
		sons.add(son1);
		sons.add(son2);
		return sons;
	}
}
