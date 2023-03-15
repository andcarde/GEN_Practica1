package model.crossover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.MoldI;
import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;

public class CrossoverPartialPairing extends Crossover {

	protected CrossoverPartialPairing(MoldI mold, Double crossProbability) {
		super(mold, crossProbability);
	}

	@Override
	public List<ChromosomeI> cross(ChromosomeI parent1, ChromosomeI parent2) {
		HashMap<Integer, Integer> p1 = new HashMap<>(), p2 = new HashMap<>(), s1 = new HashMap<>(), s2 = new HashMap<>();
		List<Integer> s1_missing_indexes= new ArrayList<>(), s2_missing_indexes = new ArrayList<>(), p1_missing_indexes = new ArrayList<>(), p2_missing_indexes= new ArrayList<>();
		for (int i = 0; i < parent1.getSize(); i++) {
			s1_missing_indexes.add(i);
			s2_missing_indexes.add(i);
			p1_missing_indexes.add(i);
			p2_missing_indexes.add(i);
		}
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
		for (int i = 0; i < parent1.getSize(); i++) {
			p1.put(i, (Integer) parent1.getGen(i).getGenoma());
			p2.put(i, (Integer) parent2.getGen(i).getGenoma());
			if (i >= lower_bound && i <= upper_bound) continue;
			s1_missing_indexes.remove(i);
			s2_missing_indexes.remove(i);
			p1_missing_indexes.remove(i);
			p2_missing_indexes.remove(i);
			s1.put(i, (Integer) parent2.getGen(i).getGenoma());
			s2.put(i, (Integer) parent1.getGen(i).getGenoma());
		}
		
		ChromosomeI son1 = parent2.copy();
		ChromosomeI son2 = parent1.copy();
		
		for (int i = 0; i < parent1.getSize(); i++) {
			if (i >= lower_bound && i <= upper_bound) continue;
			if (!s1.containsValue((Integer) parent1.getGen(i).getGenoma())) {
				s1.put(i, (Integer) parent1.getGen(i).getGenoma());
				son1.setGen(i, parent1.getGen(i));
				s1_missing_indexes.remove(i);
				p1_missing_indexes.remove(i);
			}
			if (!s2.containsValue((Integer) parent2.getGen(i).getGenoma())) {
				s2.put(i, (Integer) parent2.getGen(i).getGenoma());
				son2.setGen(i, parent2.getGen(i));
				s2_missing_indexes.remove(i);
				p2_missing_indexes.remove(i);
			}
			
		}
		
		while (!s1_missing_indexes.isEmpty()) {
			int ind = s1_missing_indexes.get(0);
			son1.setGen(ind, parent1.getGen(p1_missing_indexes.get(0)));
			s1_missing_indexes.remove(ind);
			p1_missing_indexes.remove(0);
		}
		while (!s2_missing_indexes.isEmpty()) {
			int ind = s2_missing_indexes.get(0);
			son2.setGen(ind, parent2.getGen(p2_missing_indexes.get(0)));
			s2_missing_indexes.remove(ind);
			p2_missing_indexes.remove(0);
		}
		
		List<ChromosomeI> sons = new ArrayList<>();
		sons.add(son1); sons.add(son2);
		
		return sons;
	}

}
