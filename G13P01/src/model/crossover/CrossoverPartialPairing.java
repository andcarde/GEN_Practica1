package model.crossover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import model.MoldI;
import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;
import model.util.Converter;

public class CrossoverPartialPairing extends Crossover {

	protected CrossoverPartialPairing(MoldI mold, Double crossProbability) {
		super(mold, crossProbability);
	}

	@Override
	public List<ChromosomeI> cross(ChromosomeI parent1, ChromosomeI parent2) {
		HashMap<Integer, Integer> p1 = new HashMap<>(), p2 = new HashMap<>(), s1 = new HashMap<>(), s2 = new HashMap<>(), p1_not_added = new HashMap<>(), p2_not_added = new HashMap<>();;
		List<Integer> s1_not_added = new ArrayList<>(), s2_not_added = new ArrayList<>(); 
		int lower_bound = RandomGenerator.createAleatoryInt(parent1.getSize());
		int upper_bound = RandomGenerator.createAleatoryInt(parent1.getSize());
		while (lower_bound == upper_bound) {
			upper_bound = RandomGenerator.createAleatoryInt(parent1.getSize());
		}
		if (lower_bound > upper_bound) {
			int tmp = upper_bound;
			upper_bound = lower_bound;
			lower_bound = tmp;
		}
		
		//Introducimos los genes del intervalo en el mapa
		for (int i = 0; i < parent1.getSize(); i++) {
			
			p1.put(i, Converter.DoubleToInt((Double) parent1.getGen(i).getGenoma()));
			p2.put(i, Converter.DoubleToInt((Double) parent2.getGen(i).getGenoma()));
			if (i >= lower_bound && i <= upper_bound) {
				s1.put(i,  Converter.DoubleToInt((Double) parent2.getGen(i).getGenoma()));
				s2.put(i,  Converter.DoubleToInt((Double) parent1.getGen(i).getGenoma()));
				
			}
		}
		
		ChromosomeI son1 = parent2.copy();
		ChromosomeI son2 = parent1.copy();
		
		//introducimos los genes validos con su respectivo padre
		for (int i = 0; i < parent1.getSize(); i++) {
			if (i >= lower_bound && i <= upper_bound) continue;
			int value = Converter.DoubleToInt((Double) parent1.getGen(i).getGenoma());
			if (!s1.containsValue(value)) {
				s1.put(i, value);
				son1.setGen(i, parent1.getGen(i));
				
			}
			else {
				s1_not_added.add(i);
			}
			value = Converter.DoubleToInt((Double) parent2.getGen(i).getGenoma());
			if (!s2.containsValue(value)) {
				s2.put(i, value);
				son2.setGen(i, parent2.getGen(i));
				
			}
			else {
				s2_not_added.add(i);
			}
		}
		
		//Buscamos los genes restantes
		for (int i = 0; i < parent1.getSize(); i++) {
			int value = Converter.DoubleToInt((Double) parent1.getGen(i).getGenoma());
			if (!s1.containsValue(value)) {
				p1_not_added.put(i, value);
			}
			value = Converter.DoubleToInt((Double) parent2.getGen(i).getGenoma());
			if (!s2.containsValue(value)) {
				p2_not_added.put(i, value);
			}
		}
		
		//Metemos los genes restantes
		int count = 0;
		for (Entry<Integer, Integer> set : p1_not_added.entrySet()) {
			if (s1.containsValue(set.getValue())) {
				System.out.println(set.getValue());
			}
			s1.put(s1_not_added.get(count), set.getValue());
			count++;
		}
		count = 0;
		for (Entry<Integer, Integer> set : p2_not_added.entrySet()) {
			if (s2.containsValue(set.getValue())) {
				System.out.println(set.getValue());
			}
			s2.put(s2_not_added.get(count), set.getValue());
			count++;
		}
		
		//Copiamos el hashmap a los cromosomas
		for (int i = 0; i < s1.size(); i++) {
			son1.getGen(i).assimilate(s1.get(i));
		}
		for (int i = 0; i < s2.size(); i++) {
			son2.getGen(i).assimilate(s2.get(i));
		}

		List<ChromosomeI> sons = new ArrayList<>();
		sons.add(son1); sons.add(son2);

	
		return sons;
	}

}
