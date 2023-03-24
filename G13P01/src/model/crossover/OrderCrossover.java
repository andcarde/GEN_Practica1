package model.crossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import model.MoldI;
import model.chromosome.ChromosomeI;
import model.chromosome.GenI;
import model.chromosome.TravellerChromosome;
import model.mutation.CityMutationI;
import model.random.RandomGenerator;

public class OrderCrossover extends Crossover {

	protected OrderCrossover(MoldI mold, Double crossProbability) {
		super(mold, crossProbability);
	}

	@Override
	public List<ChromosomeI> cross(ChromosomeI parent1, ChromosomeI parent2) {
		List<GenI> genes = parent1.getGenes();
		List<Double> parent1Genome = new ArrayList<>();
		for (GenI gen : genes)
			parent1Genome.add((Double) gen.getGenome());
		genes = parent2.getGenes();
		List<Double> parent2Genome = new ArrayList<>();
		for (GenI gen : genes)
			parent2Genome.add((Double) gen.getGenome());
		int lowerBound = RandomGenerator.createAleatoryInt(mold.getSize() - 2) + 1;
		int upperBound = RandomGenerator.createAleatoryInt(mold.getSize() - 1 - lowerBound) + lowerBound;
		List<List<Double>> sonsGenome = specificCross(parent1Genome, parent2Genome, lowerBound, upperBound);
		List<List<Object>> sonsObjectGenome = new ArrayList<>();
		for (List<Double> sonGenome : sonsGenome) {
			List<Object> objectGenome = new ArrayList<>();
			for (Double d : sonGenome)
				objectGenome.add(d);
			sonsObjectGenome.add(objectGenome);
		}
		List<ChromosomeI> sons = new ArrayList<>();
		CityMutationI mutation = ((TravellerChromosome) parent1).getMutation();
		for (List<Object> sonGenome : sonsObjectGenome) {
			ChromosomeI son = new TravellerChromosome(mold, mutation);
			son.assimilate(sonGenome);
			sons.add(son);
		}
		return sons;
	}
	
	private class Iterator {
		
		private List<Double> genoma;
		private int position;
		
		private Iterator(List<Double> genoma, int initialPosition) {
			this.genoma = genoma;
			this.position = initialPosition;
		}
		
		private Double next() {
			position++;
			if (position == genoma.size())
				position = 0;
			return genoma.get(position);
		}
	}
	
	private List<List<Double>> specificCross(List<Double> parent1Genome, List<Double> parent2Genome,
			int lowerBound, int upperBound) {
		Set<Double> son1Set = new TreeSet<>(), son2Set = new TreeSet<>();
		List<Double> son1Genome = new ArrayList<>(mold.getSize()), son2Genome = new ArrayList<>(mold.getSize());
		Double d;
		for (int i = lowerBound; i <= upperBound; i++) {
			d = parent1Genome.get(i);
			son2Set.add(d);
			son2Genome.set(i, d);
			d = parent2Genome.get(i);
			son1Set.add(d);
			son1Genome.set(i, d);
		}
		Iterator parent1Iterator = new Iterator(parent1Genome, upperBound);
		Iterator parent2Iterator = new Iterator(parent2Genome, upperBound);
		for (int i = upperBound + 1; i < mold.getSize(); i++) {
			d = parent1Iterator.next();
			while (son1Set.contains(d))
				d = parent1Iterator.next();
			son1Genome.set(i, d);
			d = parent2Iterator.next();
			while (son2Set.contains(d))
				d = parent2Iterator.next();
			son2Genome.set(i, d);
		}
		for (int i = 0; i < lowerBound; i++) {
			d = parent1Iterator.next();
			while (son1Set.contains(d))
				d = parent1Iterator.next();
			son1Genome.set(i, d);
			d = parent2Iterator.next();
			while (son2Set.contains(d))
				d = parent2Iterator.next();
			son2Genome.set(i, d);
		}
		List<List<Double>> sonsGenome = new ArrayList<>();
		sonsGenome.add(son1Genome);
		sonsGenome.add(son2Genome);
		return sonsGenome;
	}
}
