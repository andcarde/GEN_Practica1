package model.crossover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		HashMap<Integer, Integer> p1 = new HashMap<>(), p2 = new HashMap<>(), s1 = new HashMap<>(), s2 = new HashMap<>();
		List<Integer> s1_missing_indexes= new ArrayList<>(), s2_missing_indexes = new ArrayList<>(), p1_missing_indexes = new ArrayList<>(), p2_missing_indexes= new ArrayList<>(),
		s1_not_added = new ArrayList<>(), s2_not_added = new ArrayList<>(), p1_not_added = new ArrayList<>(), p2_not_added = new ArrayList<>();
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
			
			p1.put(i, Converter.DoubleToInt((Double) parent1.getGen(i).getGenoma()));
			p2.put(i, Converter.DoubleToInt((Double) parent2.getGen(i).getGenoma()));
			if (i >= lower_bound && i <= upper_bound) {
				s1_missing_indexes.remove((Object) i);
				s2_missing_indexes.remove((Object) i);
				p1_missing_indexes.remove((Object) i);
				p2_missing_indexes.remove((Object) i);
				continue;
			}
			s1.put(i,  Converter.DoubleToInt((Double) parent2.getGen(i).getGenoma()));
			s2.put(i,  Converter.DoubleToInt((Double) parent1.getGen(i).getGenoma()));
		}
		
		ChromosomeI son1 = parent2.copy();
		ChromosomeI son2 = parent1.copy();
		
		for (int i = 0; i < parent1.getSize(); i++) {
			if (i >= lower_bound && i <= upper_bound) continue;
			if (!s1.containsValue(Converter.DoubleToInt((Double) parent1.getGen(i).getGenoma()))) {
				s1.put(i,  Converter.DoubleToInt((Double) parent1.getGen(i).getGenoma()));
				son1.setGen(i, parent1.getGen(i));
				s1_missing_indexes.remove((Object) i);
				p1_missing_indexes.remove((Object) i);
			}
			else {
				s1_not_added.add(i);
				p1_not_added.add(i);
			}
			if (!s2.containsValue(Converter.DoubleToInt((Double) parent2.getGen(i).getGenoma()))) {
				s2.put(i,  Converter.DoubleToInt((Double) parent2.getGen(i).getGenoma()));
				son2.setGen(i, parent2.getGen(i));
				s2_missing_indexes.remove((Object) i);
				p2_missing_indexes.remove((Object) i);
			}
			else {
				s2_not_added.add(i);
				p2_not_added.add(i);
			}
		}
		Set<Object> aux1 = new HashSet<>(), aux2 = new HashSet<>();
		
		
		while (!s1_not_added.isEmpty()) {
			int son_ind = s1_not_added.get(0), par_ind = p1_not_added.get(0);
			son1.setGen(son_ind, parent1.getGen(par_ind));
			s1_not_added.remove((Object) son_ind);
			p1_not_added.remove((Object) par_ind);
		}
		while (!s2_not_added.isEmpty()) {
			int son_ind = s2_not_added.get(0), par_ind = p2_not_added.get(0);
			son2.setGen(son_ind, parent2.getGen(par_ind));
			s2_not_added.remove((Object) son_ind);
			p2_not_added.remove((Object) par_ind);
		}
		
		List<ChromosomeI> sons = new ArrayList<>();
		sons.add(son1); sons.add(son2);
		aux1 = new HashSet<>(); aux2 = new HashSet<>();
		for (int i = 0; i < son1.getGenes().size(); i++) {
			if (aux1.contains(son1.getGen(i).getGenoma())) {
				System.out.println(son1.getGen(i).getGenoma());
			}
			else aux1.add(son1.getGen(i).getGenoma());
		}
		for (int i = 0; i < son2.getGenes().size(); i++) {
			if (aux2.contains(son2.getGen(i).getGenoma())) {
				System.out.println(son2.getGen(i).getGenoma());
			}
			else aux2.add(son2.getGen(i).getGenoma());
		}
		return sons;
	}

}
