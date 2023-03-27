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
		HashMap<Integer, Integer> p1 = new HashMap<>(), p2 = new HashMap<>(),
				s1 = new HashMap<>(), s2 = new HashMap<>(),
				p1_not_added = new HashMap<>(), p2_not_added = new HashMap<>();
		List<Integer> s1_not_added = new ArrayList<>(), s2_not_added = new ArrayList<>(); 
		int lower_bound = RandomGenerator.createAleatoryInt(mold.getSize() - 2) + 1;
		int upper_bound = RandomGenerator.createAleatoryInt(mold.getSize() - 1 - lower_bound) + lower_bound;
		
		//Introducimos los genes del intervalo en el mapa
		for (int i = 0; i < mold.getSize(); i++) {
			
			p1.put(i, Converter.DoubleToInt((Double) parent1.getGen(i).getGenome()));
			p2.put(i, Converter.DoubleToInt((Double) parent2.getGen(i).getGenome()));
			if (i >= lower_bound && i <= upper_bound) {
				s1.put(i,  Converter.DoubleToInt((Double) parent2.getGen(i).getGenome()));
				s2.put(i,  Converter.DoubleToInt((Double) parent1.getGen(i).getGenome()));
				
			}
		}
		
		ChromosomeI son1 = parent2.copy();
		ChromosomeI son2 = parent1.copy();
		
		//introducimos los genes validos con su respectivo padre
		for (int i = 0; i < mold.getSize(); i++) {
			if (i >= lower_bound && i <= upper_bound) continue;
			int value = Converter.DoubleToInt((Double) parent1.getGen(i).getGenome());
			if (!s1.containsValue(value)) {
				s1.put(i, value);
				son1.setGen(i, parent1.getGen(i));
				
			}
			else {
				s1_not_added.add(i);
			}
			value = Converter.DoubleToInt((Double) parent2.getGen(i).getGenome());
			if (!s2.containsValue(value)) {
				s2.put(i, value);
				son2.setGen(i, parent2.getGen(i));
				
			}
			else {
				s2_not_added.add(i);
			}
		}
		
		//Buscamos los genes restantes
		for (int i = 0; i < mold.getSize(); i++) {
			int value = Converter.DoubleToInt((Double) parent1.getGen(i).getGenome());
			if (!s1.containsValue(value)) {
				p1_not_added.put(i, value);
			}
			value = Converter.DoubleToInt((Double) parent2.getGen(i).getGenome());
			if (!s2.containsValue(value)) {
				p2_not_added.put(i, value);
			}
		}
		
		//Metemos los genes restantes
		int count = 0;
		for (Entry<Integer, Integer> set : p1_not_added.entrySet()) {
			s1.put(s1_not_added.get(count), set.getValue());
			count++;
		}
		count = 0;
		for (Entry<Integer, Integer> set : p2_not_added.entrySet()) {
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
