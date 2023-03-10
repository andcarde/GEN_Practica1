package model.crossover;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.chromosome.BoundedChromosome;
import model.chromosome.BoundedGenI;
import model.chromosome.Chromosome;
import model.chromosome.ChromosomeI;

public class RealArithmeticCrossover extends Crossover {

	private static final Double alpha = 0.6;
	
	protected RealArithmeticCrossover(MoldI mold, Double crossProbability) {
		super(mold, crossProbability);
	}

	@Override
	public List<ChromosomeI> cross(ChromosomeI parent1, ChromosomeI parent2) {
		List<ChromosomeI> sons = new ArrayList<>();
		Chromosome son1 = new BoundedChromosome(this.mold);
		Chromosome son2 = new BoundedChromosome(this.mold);
		
		Double genome1, genome2;
		for (int i = 0; i < mold.getNumGenes(); i++) {
			genome1 = (Double) parent1.getGen(i).getGenoma() * alpha;
			genome1 += (Double) parent2.getGen(i).getGenoma() * (1 - alpha);
			genome2 = (Double) parent2.getGen(i).getGenoma() * alpha;
			genome2 += (Double) parent1.getGen(i).getGenoma() * (1 - alpha);
			son1.setGen(i, (BoundedGenI) son1.getGen(i).copy().assimilate(genome1));
			son2.setGen(i, (BoundedGenI) son1.getGen(i).copy().assimilate(genome2));
		}
		
		sons.add(son1);
		sons.add(son2);
		return sons;
	}
}
