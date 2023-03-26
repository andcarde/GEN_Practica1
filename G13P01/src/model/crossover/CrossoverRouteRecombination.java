package model.crossover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.MoldI;
import model.chromosome.ChromosomeI;
import model.random.RandomGenerator;
import model.util.ConnectionTable;
import model.util.Converter;

public class CrossoverRouteRecombination extends Crossover {
	
	public CrossoverRouteRecombination(MoldI mold, Double crossProbability) {
		super(mold, crossProbability);
	}

	@Override
	public List<ChromosomeI> cross(ChromosomeI parent1, ChromosomeI parent2) {
		ConnectionTable connections = new ConnectionTable(parent1, parent2);
		List<ChromosomeI> sons = new ArrayList<>();
		int cur_city = Converter.DoubleToInt((Double) parent1.getGen(0).getGenome());
		Set<Integer> cities_left = new HashSet<>();
		for (int i = 1; i < parent1.getSize(); i++) {
			cities_left.add(Converter.DoubleToInt((Double) parent1.getGen(i).getGenome()));
		}
		HashMap<Integer, Object> s1 = new HashMap<>();
		for (int i = 0; i < parent1.getSize(); i++) {
			s1.put(i, cur_city);
			cities_left.remove(cur_city);
			cur_city = connections.getLeastConnectedCity(cur_city, s1);
			if (cur_city == -1 && i < parent1.getSize() -1) {
				cur_city = (int) cities_left.toArray()[0];
			}
		}
		ChromosomeI son1 = parent1.copy();
		for (int i = 0; i < s1.size(); i++) {
			son1.getGen(i).assimilate(s1.get(i));
		}
		
		cities_left.clear();
		cur_city = Converter.DoubleToInt((Double) parent2.getGen(0).getGenome());
		for (int i = 1; i < parent2.getSize(); i++) {
			cities_left.add(Converter.DoubleToInt((Double) parent2.getGen(i).getGenome()));
		}
		HashMap<Integer, Object> s2 = new HashMap<>();
		for (int i = 0; i < parent1.getSize(); i++) {
			s2.put(i, cur_city);
			cur_city = connections.getLeastConnectedCity(cur_city, s2);
			if (cur_city == -1 && i < parent1.getSize() -1) {
				cur_city = (int) cities_left.toArray()[0];
			}
		}
		ChromosomeI son2 = parent1.copy();
		for (int i = 0; i < s1.size(); i++) {
			son2.getGen(i).assimilate(s1.get(i));
		}
		
		if (RandomGenerator.createAleatoryBoolean(0.5)) {
			sons.add(son1);			
			sons.add(son2);
		}
		else {
			sons.add(son2);
			sons.add(son1);			
		}
		return sons;
	}

}
