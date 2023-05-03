package model.crossover.practice1;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.chromosome.ChromosomeI;
import model.chromosome.practice3.CodonChromosome;
import model.crossover.Crossover;
import model.gen.practice3.BinaryGen;
import model.random.RandomGenerator;

public class BinaryOnePointCrossover extends Crossover {

	public BinaryOnePointCrossover(MoldI mold, Double crossProbability) {
		super(mold, crossProbability);
	}
	
	@Override
	public List<ChromosomeI> cross(ChromosomeI parent1, ChromosomeI parent2) {
		List<ChromosomeI> sons = new ArrayList<>();
		CodonChromosome son1 = new CodonChromosome(this.mold);
		CodonChromosome son2 = new CodonChromosome(this.mold);
		
		List<Object> genomes1 = new ArrayList<>();
		List<Object> genomes2 = new ArrayList<>();
		
		Integer cutPoint = RandomGenerator.createAleatoryInt(parent1.getSize() - 1) + 1;
		List<Boolean> genome1, genome2;
		BinaryGen binaryGen1, binaryGen2;
		
		Integer accumulated = 0;
		for (int i = 0; i < parent1.getGenes().size(); i++) {
			genome1 = new ArrayList<>();
			genome2 = new ArrayList<>();
			binaryGen1 = (BinaryGen) parent1.getGenes().get(i);
			binaryGen2 = (BinaryGen) parent1.getGenes().get(i);
			int genSize = binaryGen1.getBits().size();
			for (int j = 0; j < genSize; j++) {
				if (accumulated < cutPoint) {
					genome1.add((boolean) binaryGen1.getBits().get(j));
					genome2.add((boolean) binaryGen2.getBits().get(j));
				} else {
					genome1.add((boolean) binaryGen2.getBits().get(j));
					genome2.add((boolean) binaryGen1.getBits().get(j));
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
