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
		
		// The sons are generated through the cross of their parents
		List<List<Double>> sonsGenome = specificCross(parent1Genome, parent2Genome);
		
		// The lists of values have to change his type from Double to Object for compatibility reasons.
		List<List<Object>> sonsObjectGenome = new ArrayList<>();
		for (List<Double> sonGenome : sonsGenome) {
			List<Object> objectGenome = new ArrayList<>();
			for (Double d : sonGenome)
				objectGenome.add(d);
			sonsObjectGenome.add(objectGenome);
		}
		
		// The lists of values are transform into TravellerChromosome types.
		List<ChromosomeI> sons = new ArrayList<>();
		CityMutationI mutation = ((TravellerChromosome) parent1).getMutation();
		for (List<Object> sonGenome : sonsObjectGenome) {
			ChromosomeI son = new TravellerChromosome(mold, mutation);
			son.assimilate(sonGenome);
			sons.add(son);
		}
		return sons;
	}
	
	/***
	 * The forward map links the genome values towards his order from back to front.
	 * @param genome
	 * @return
	 */
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
	
	/***
	 * The forward map links the genome values towards his order from front to back.
	 * @param genome
	 * @return
	 */
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
		
		// First value initialization.
		Double value;
		if (forward)
			value = parent.get(0);
		else
			value = parent.get(parent.size() - 1);
		Set<Double> set = new TreeSet<>();
		
		// The first value is added to the son.
		set.add(value);
		son.add(value);
		
		/* The map iterators are generated with the same "value" and "set" for a
		 * map according with the indicated parent
		 */
		MapIterator map1Iterator = new MapIterator(map1, value, set);
		MapIterator map2Iterator = new MapIterator(map2, value, set);
		
		/* Se intercala el orden de ciudades del padre 1 con el orden del padre 2,
		 * insertando la siguiente ciudad a la añadida por el otro progenitor. Si la siguiente
		 * ciudad ya ha sido añadida por el otro padre se pasa a la siguiente dentro del orden
		 * del padre al que le toca añadir ciudad.
		 */
		boolean turn = true;
		for (int i = 0; i < mold.getSize() - 1; i++) {
			if (turn)
				son.add(map1Iterator.next());
			else
				son.add(map2Iterator.next());
			turn = !turn;
		}
		
		// If the sense is from front to back, the son list is inverted.
		if (!forward)
			Collections.reverse(son);
		return son;
	}
	
	private List<List<Double>> specificCross(List<Double> parent1Genome, List<Double> parent2Genome) {
		Map<Double, Double> parent1Map, parent2Map;
		
		// Randomly it will go forward or backward to favor generic diversity.
		boolean forward = RandomGenerator.createAleatoryBoolean();
		
		// Los mapas son creados a partir del genoma de los padres, dependiendo del valor
		// de forward irá hacia adelante o hacia atrás.
		if (forward) {
			parent1Map = createForwardMap(parent1Genome);
			parent2Map = createForwardMap(parent2Genome);
		} else {
			parent1Map = createBackwardMap(parent1Genome);
			parent2Map = createBackwardMap(parent2Genome);
		}
		
		// Sons are being generated.
		List<Double> son1Genome = generateSon(forward, parent1Genome, parent2Map, parent1Map);
		List<Double> son2Genome = generateSon(forward, parent2Genome, parent1Map, parent2Map);
		
		// The sons are returned.
		List<List<Double>> sonsGenome = new ArrayList<>();
		sonsGenome.add(son1Genome);
		sonsGenome.add(son2Genome);
		return sonsGenome;
	}
}
