package model.crossover;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import model.MoldI;
import model.chromosome.ChromosomeI;
import model.chromosome.GenI;
import model.chromosome.TravellerChromosome;
import model.mutation.CityMutationI;
import model.random.RandomGenerator;

public class PriorityOrderCrossover extends Crossover {

	protected PriorityOrderCrossover(MoldI mold, Double crossProbability) {
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
		List<List<Double>> sonsGenome = specificCross(parent1Genome, parent2Genome);
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
	
	private List<List<Double>> specificCross(List<Double> parent1Genome, List<Double> parent2Genome) {
		int priorityPosition1 = RandomGenerator.createAleatoryInt(mold.getSize());
		int priorityPosition2 = RandomGenerator.createAleatoryInt(mold.getSize() - 1);
		if (priorityPosition2 >= priorityPosition1)
			priorityPosition2++;
		Double[] son1Array = new Double[mold.getSize()], son2Array = new Double[mold.getSize()];
		List<Double> son1Genome = Arrays.asList(son1Array), son2Genome = Arrays.asList(son2Array);
		Set<Double> son1Set = new TreeSet<>(), son2Set = new TreeSet<>();
		Double d;
		d = parent2Genome.get(priorityPosition1);
		son1Genome.set(priorityPosition1, d);
		son1Set.add(d);
		d = parent2Genome.get(priorityPosition2);
		son1Genome.set(priorityPosition2, d);
		son1Set.add(d);
		d = parent1Genome.get(priorityPosition1);
		son2Genome.set(priorityPosition1, d);
		son2Set.add(d);
		d = parent1Genome.get(priorityPosition2);
		son2Genome.set(priorityPosition2, d);
		son2Set.add(d);
		Iterator parent1Iterator = new Iterator(parent1Genome, mold.getSize() - 1);
		Iterator parent2Iterator = new Iterator(parent2Genome, mold.getSize() - 1);
		for (int i = 0; i < mold.getSize(); i++) {
			if (i != priorityPosition1 && i != priorityPosition2) {
				d = parent1Iterator.next();
				while (son1Set.contains(d))
					d = parent1Iterator.next();
				son1Genome.set(i, d);
				d = parent2Iterator.next();
				while (son2Set.contains(d))
					d = parent2Iterator.next();
				son2Genome.set(i, d);
			}
		}
		List<List<Double>> sonsGenome = new ArrayList<>();
		sonsGenome.add(son1Genome);
		sonsGenome.add(son2Genome);
		return sonsGenome;
	}
}
