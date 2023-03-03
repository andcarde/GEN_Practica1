package model.crossover;

import java.util.ArrayList;
import java.util.List;

import model.MoldI;
import model.chromosome.Chromosome;
import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;

public class RealBLXCrossover extends Crossover {

	private static final Double alpha = 0.6;
	
	protected RealBLXCrossover(MoldI mold, Double crossProbability) {
		super(mold, crossProbability);
	}

	@Override
	public List<ChromosomeI> cross(ChromosomeI parent1, ChromosomeI parent2) {
		List<ChromosomeI> sons = new ArrayList<>();
		Chromosome son1 = new Chromosome(this.mold);
		Chromosome son2 = new Chromosome(this.mold);
		
		Double genomeParent1, genomeParent2, genomeSon1, genomeSon2;
		for (int i = 0; i < mold.getNumGenes(); i++) {
			genomeParent1 = (Double) parent1.getGen(i).getGenoma();
			genomeParent2 = (Double) parent2.getGen(i).getGenoma();
			if (genomeParent1 == genomeParent2) {
				genomeSon1 = genomeParent1;
				genomeSon2 = genomeParent1;
			} else  {
				Double cmin, cmax;
				if (genomeParent1 > genomeParent2) {
					cmax = genomeParent1;
					cmin = genomeParent2;
				} else {
					cmax = genomeParent2;
					cmin = genomeParent1;
				}
				Double width = cmax - cmin;
				Double belowLimit = cmin - width * alpha;
				Double upperLimit = cmax + width * alpha;
				genomeSon1 = RandomGenerator.createAleatoryDoublePlus(belowLimit, upperLimit);
				genomeSon2 = RandomGenerator.createAleatoryDoublePlus(belowLimit, upperLimit);
			}
			son1.setGen(i, son1.getGen(i).copy().assimilate(genomeSon1));
			son2.setGen(i, son1.getGen(i).copy().assimilate(genomeSon2));
		}
		
		sons.add(son1);
		sons.add(son2);
		return sons;
	}
}
