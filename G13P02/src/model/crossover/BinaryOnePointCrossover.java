package model.crossover;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.chromosome.BinaryGenI;
import model.chromosome.BoundedChromosome;
import model.chromosome.Chromosome;
import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;

public class BinaryOnePointCrossover extends Crossover {

	public BinaryOnePointCrossover(MoldI mold, Double crossProbability) {
		super(mold, crossProbability);
	}
	
	@Override
	public List<ChromosomeI> cross(ChromosomeI parent1, ChromosomeI parent2) {
		List<ChromosomeI> sons = new ArrayList<>();
		Chromosome son1 = new BoundedChromosome(this.mold);
		Chromosome son2 = new BoundedChromosome(this.mold);
		
		List<Object> genomes1 = new ArrayList<>();
		List<Object> genomes2 = new ArrayList<>();
		
		Integer cutPoint = RandomGenerator.createAleatoryInt(mold.getSize() - 1) + 1;
		List<Boolean> genome1, genome2;
		BinaryGenI binaryGen1, binaryGen2;
		
		Integer accumulated = 0;
		for (int i = 0; i < mold.getGenes().size(); i++) {
			genome1 = new ArrayList<>();
			genome2 = new ArrayList<>();
			binaryGen1 = (BinaryGenI) parent1.getGen(i);
			binaryGen2 = (BinaryGenI) parent1.getGen(i);
			int genSize = binaryGen1.getSize();
			for (int j = 0; j < genSize; j++) {
				if (accumulated < cutPoint) {
					genome1.add((boolean) binaryGen1.getBit(j));
					genome2.add((boolean) binaryGen2.getBit(j));
				} else {
					genome1.add((boolean) binaryGen2.getBit(j));
					genome2.add((boolean) binaryGen1.getBit(j));
				}
				accumulated++;
			}
			genomes1.add(genome1);
			genomes2.add(genome2);
		}
		
		son1.assimilate(genomes1);
		son2.assimilate(genomes2);
		
		sons.add(son1);
		sons.add(son2);
		return sons;
	}
	
	/*
	private Boolean getElement(ChromosomeI chromosome, Integer i) {
		Integer accumulated = 0;
		for (GenI gen : chromosome.getGenes()) {
			BinaryGenI binaryGen = (BinaryGenI) gen;
			if (i < binaryGen.getSize() + accumulated)
				return binaryGen.getBit(i - accumulated);
			accumulated += binaryGen.getSize();
		}
		return null;
	}
	*/
}
