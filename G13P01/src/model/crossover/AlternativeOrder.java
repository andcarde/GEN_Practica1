package model.crossover;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import model.MoldI;
import model.chromosome.ChromosomeI;
import model.chromosome.GenI;
import model.chromosome.TravellerChromosome;
import model.mutation.CityMutationI;
import model.random.RandomGenerator;
import model.util.MapIterator;

public class AlternativeOrder extends Crossover {
	
	protected AlternativeOrder(MoldI mold, Double crossProbability) {
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
	
	private Map<Double, Double> createForwardMap(List<Double> genome) {
		Map<Double, Double> map = new HashMap<>();
		Double key, value;
		value = genome.get(genome.size() - 1);
		for (Double d : genome) {
			key = value;
			value = d;
			map.put(key, value);
		}
		return map;
	}
	
	private Map<Double, Double> createBackwardMap(List<Double> genome) {
		Map<Double, Double> map = new HashMap<>();
		Double key, value;
		value = genome.get(0);
		for (int i = genome.size() - 1; i >= 0; i--) {
			key = value;
			value = genome.get(i);
			map.put(key, value);
		}
		return map;
	}
	
	private List<Double> generateSon(boolean forward, List<Double> parent,
			Map<Double, Double> map1, Map<Double, Double> map2) {
		List<Double> son = new ArrayList<>();
		Double value;
		if (forward)
			value = parent.get(0);
		else
			value = parent.get(parent.size() - 1);
		Set<Double> set = new TreeSet<>();
		set.add(value);
		son.add(value);
		MapIterator map1Iterator = new MapIterator(map1, value, set);
		MapIterator map2Iterator = new MapIterator(map2, value, set);
		boolean turn = true;
		for (int i = 0; i < mold.getSize() - 1; i++) {
			if (turn)
				son.add(map1Iterator.next());
			else
				son.add(map2Iterator.next());
			turn = !turn;
		}
		if (!forward)
			Collections.reverse(son);
		return son;
	}
	
	private List<List<Double>> specificCross(List<Double> parent1Genome, List<Double> parent2Genome) {
		Map<Double, Double> parent1Map, parent2Map;
		boolean forward = RandomGenerator.createAleatoryBoolean();
		if (forward) {
			parent1Map = createForwardMap(parent1Genome);
			parent2Map = createForwardMap(parent2Genome);
		} else {
			parent1Map = createBackwardMap(parent1Genome);
			parent2Map = createBackwardMap(parent2Genome);
		}
		List<Double> son1Genome = generateSon(forward, parent1Genome, parent2Map, parent1Map);
		List<Double> son2Genome = generateSon(forward, parent2Genome, parent1Map, parent2Map);
		List<List<Double>> sonsGenome = new ArrayList<>();
		sonsGenome.add(son1Genome);
		sonsGenome.add(son2Genome);
		return sonsGenome;
	}
}
