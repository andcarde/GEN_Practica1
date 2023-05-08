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
		CodonChromosome codonParent1 = (CodonChromosome) parent1;
		CodonChromosome codonParent2 = (CodonChromosome) parent2;
		
		List<ChromosomeI> sons = new ArrayList<>();
		CodonChromosome son1 = codonParent1.copy();
		CodonChromosome son2 = codonParent2.copy();
		
		for (int i = 0; i < son1.getCodonsNumber(); i++) {
			if (RandomGenerator.createAleatoryBoolean()) {
				son1.setCodon(i, codonParent2.getCodon(i));
				son2.setCodon(i, codonParent1.getCodon(i));
			}
		}
		
		sons.add(son1);
		sons.add(son2);
		return sons;
	}
}
